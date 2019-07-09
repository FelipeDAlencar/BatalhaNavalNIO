package model;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

import javax.swing.JOptionPane;

import model.Jogador;


public class Servidor {

	private static int QUAN_CONNECTIONS;
	private final static int PORT = 22222;
	private final static  String HOST = "localhost";

	private ServerSocketChannel serverSocketChannel = null;
	private Selector selector= null;
	private StringBuffer messageResponse = new StringBuffer();
	private Charset charset = Charset.forName("ISO-8859-2");
	private StringBuffer messageClient = new StringBuffer();

	private Jogador player1, player2;

	private List<SocketChannel> clients;  

	private InetSocketAddress listenAddress;

	public Servidor(){
		try {

			this.listenAddress = new InetSocketAddress(Servidor.HOST, Servidor.PORT);
			this.selector = Selector.open();
			this.serverSocketChannel = ServerSocketChannel.open();
			this.serverSocketChannel.configureBlocking(false);

			this.serverSocketChannel.bind(listenAddress);
			this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

			this.player1 = new Jogador();
			this.player2 = new Jogador();

			this.clients = new ArrayList<>();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void inicializar() throws IOException{

		System.out.println("O servidor foi iniciado na porta >> " + Servidor.PORT);

		int i=0;

		while (true) {

			int readyCount = selector.select();
			if (readyCount == 0) {
				continue;
			}

			if (QUAN_CONNECTIONS<=2){

				Set<SelectionKey> keys = this.selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();

				while (iterator.hasNext()) {
					SelectionKey key = (SelectionKey) iterator.next();
					iterator.remove();

					if (!key.isValid()) {
						continue;
					}

					if (key.isAcceptable()) {
						i++;
						SocketChannel channel = serverSocketChannel.accept();
						channel.configureBlocking(false);

						channel.register(this.selector, SelectionKey.OP_READ);
						clients.add(channel);

						Servidor.QUAN_CONNECTIONS= clients.size();

					} else if (key.isReadable()) {
						SocketChannel channel = (SocketChannel) key.channel();

						this.lerMensagem(channel);
					} else if (key.isWritable()) {

					}
				}
			}

		}
	}


	private void writeResp(SocketChannel sc, String addMsg) throws IOException {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.messageResponse.setLength(0);
		this.messageResponse.append(addMsg);
		this.messageResponse.append('\n');
		ByteBuffer buf = charset.encode(CharBuffer.wrap(this.messageResponse));
		sc.write(buf);
	}


	private void lerMensagem(SocketChannel sc) {
		if (!sc.isOpen()) return;

		this.messageClient.setLength(0);
		ByteBuffer byteBuffer = ByteBuffer.allocate(256);
		byteBuffer.clear();
		try {
			while (true) {
				int n = -1;
				try {
					n= sc.read(byteBuffer);
				} catch (Exception e) {

					JOptionPane.showMessageDialog(null,"Comunicacao interrompida");
					System.exit(0);
				}

				if (n > 0) {
					byteBuffer.flip();
					CharBuffer cbuf = this.charset.decode(byteBuffer);
					while (cbuf.hasRemaining()) {
						char c = cbuf.get();
						if (c == '\r' || c == '\n')
							break;
						this.messageClient.append(c);
					}
				}
				if(n < 1) {
					return;
				}
				String strin = this.messageClient.toString();
				servicoRequesitado(sc, strin);

			}


		}
		catch (Exception exc) {         
			exc.printStackTrace();
			try {
				sc.close();
				sc.socket().close();
			}
			catch (Exception e) {
			}
		}
	}
	public void servicoRequesitado(SocketChannel channel, String mensagem) {

		try {

			System.out.println("Clients: "+clients.size());

			String m = mensagem.substring(0,1);

			System.out.println("Service Message: "+mensagem);

			if (m.equals("$")) {
				if(this.clients.size()==1) {
					player1.setNome(mensagem.substring(1));
				}
				if(this.clients.size()==2) {
					player2.setNome(mensagem.substring(1));
				}

				writeResp(channel, "$OK2");

			}

			//Avisando que está pronto
			else if(m.equals("&")) {
				if (player1.getNome().equals(mensagem.substring(1))) {
					player1.setPronto(true);
				}else if(player2.getNome().equals(mensagem.substring(1))){
					player2.setPronto(true);
				}

				if(player1.isPronto()&&player2.isPronto()) {
					writeResp(clients.get(0), "&start");
					writeResp(clients.get(0), "&first");
					writeResp(clients.get(1), "&start");
					writeResp(clients.get(1), "&second");
				
				}

				//recebe a mensagem separada por espaços
			}else if(m.equals("@")) {
				String split[] = mensagem.substring(1).split(" ");
				
				//se o p1 enviou, repassar para p2
				if (split[0].equals(player1.getNome())) {
					writeResp(clients.get(1), "@"+split[1]);
					
					//se p2 enviou repassar para p1
				}else if(split[0].equals(player2.getNome())) {
					writeResp(clients.get(0), "@"+split[1]);
					
					//se foi uma resposta correta  ou errada avisar ao oponente
				}else {
					if (split[1].equals(player1.getNome())) {
						writeResp(clients.get(1), mensagem);
					}else if (split[1].equals(player2.getNome())) {
						writeResp(clients.get(0), mensagem);
					}
				}
			}
		}
		catch (Exception exc) {         
			exc.printStackTrace();
			try {
				channel.close();
				channel.socket().close();
			}
			catch (Exception e) {
			}
		}


	}


	public static void main(String[] args)
			throws Exception {
		Servidor servidor= new Servidor();

		servidor.inicializar();

	}

	public void clear() {
		this.messageClient.setLength(0);
	}

}
