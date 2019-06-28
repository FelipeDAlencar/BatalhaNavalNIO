package socket;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

import javax.swing.JOptionPane;

import model.Jogador;
import view.ThreadMonitoraTelas;


public class Servidor_NIO {

	private final static int PORT = 22222;
	private final static  String HOST = "localhost";
	private ServerSocketChannel serverSocketChannel = null;
	private  Selector selector= null;
	private  StringBuffer messageResponse = new StringBuffer();
	private Charset charset = Charset.forName("ISO-8859-2");
	private StringBuffer mensagemDoCliente = new StringBuffer();
	boolean mensagem = true;
	
	private Jogador jog1;
	private Jogador jog2;

	private static int numeroConexao;

	private List<SocketChannel> clientes;  
	private InetSocketAddress listenAddress;
		
	public Servidor_NIO() {
		listenAddress = new InetSocketAddress(this.HOST, this.PORT);
		try {
			this.selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);

			// vincular o canal de soquete do servidor à porta
			serverSocketChannel.bind(listenAddress);
			serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		this.jog1 = new Jogador();
		this.jog2 = new Jogador();
		clientes= new ArrayList<>();

	}


	public void inicializar() throws IOException{

		System.out.println("Server started on port >> " + PORT);

		int i=0;

		while (true) {


			int readyCount = selector.select();
			if (readyCount == 0) {
				continue;
			}

			if (numeroConexao<=2){

				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator iterator = keys.iterator();

				while (iterator.hasNext()) {
					SelectionKey key = (SelectionKey) iterator.next();
					iterator.remove();

					if (!key.isValid()) {
						continue;
					}

					if (key.isAcceptable()) { // Accept client connections
						i++;
						SocketChannel channel = serverSocketChannel.accept();
						channel.configureBlocking(false);
						Socket socket = channel.socket();
						SocketAddress remoteAddr = socket.getRemoteSocketAddress();
						System.out.println("Connected to: " + remoteAddr);

						channel.register(this.selector, SelectionKey.OP_READ);
						clientes.add(channel);

						System.out.println("Cliente numero: |" +i+ "| Aceito");
						
						
						this.numeroConexao= clientes.size();
						
					} else if (key.isReadable()) {
						SocketChannel channel = (SocketChannel) key.channel();

						lerMensagem(channel);
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
		messageResponse.setLength(0);
		messageResponse.append(addMsg);
		messageResponse.append('\n');
		ByteBuffer buf = charset.encode(CharBuffer.wrap(messageResponse));
		sc.write(buf);
	}


	private void lerMensagem(SocketChannel sc) {
		if (!sc.isOpen()) return;

		mensagemDoCliente.setLength(0);
		ByteBuffer byteBuffer = ByteBuffer.allocate(256);
		byteBuffer.clear();
		try {
			while (true) {
				int n = -1;
				try {
					n= sc.read(byteBuffer);
				} catch (Exception e) {

					JOptionPane.showMessageDialog(null,"Comunicação inerrompida brutalmente!");
					System.exit(0);
				}

				if (n > 0) {
					byteBuffer.flip();
					CharBuffer cbuf = charset.decode(byteBuffer);
					while (cbuf.hasRemaining()) {
						char c = cbuf.get();
						if (c == '\r' || c == '\n')
							break;
						mensagemDoCliente.append(c);
					}
				}
				if(n < 1) {
					return;
				}
				String strin = mensagemDoCliente.toString();
				System.out.println("Mensagem recebida no servidor é : "+strin);
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

			System.out.println("chegou nas responsabilidades");

			System.out.println("Tamanho da lista: "+clientes.size());

			char p[]= mensagem.toCharArray();

			System.out.println("Mensagem de serviço requisitado: "+mensagem);

			if (p[0] == '$') {
				System.out.println("Entrou nesse simbolo: '$'");

				if (this.clientes.size() == 1){
					writeResp(channel, "$OK2");

				}	
				//		if (this.numeroConexao == 2){
				if (this.clientes.size() > 1){
					writeResp(channel, "$OK2");
				}
			}
			//Marcando as escolhas seu Mapa .
			else if (p[0] == '#') {

				System.out.println("Entrou nesse simbolo: '#'");

				if (this.clientes.size() == 1){
					System.out.println("<- Comunicacao - Protocolo (Jogo/Escolhas) recebendo informacao do Jogador 1: " );
					jog1.addNavio(leIntProtocolo(p));
				}	
				if (this.clientes.size() == 2){
					System.out.println("<- Comunicacao - Protocolo (Jogo/Escolhas) recebendo informacao do Jogador 2: ");
					jog2.addNavio(leIntProtocolo(p));
				}
				if (jog1.isVez() && jog2.isVez()){
					writeResp(clientes.get(0), "$ini");
					
					this.numeroConexao =1;
					//System.out.println("COMECOU");
				}
			}
			// Ataca adversario (Mapa adversario).
			else if (p[0] == '@') {
				System.out.println("Entrou nesse simbolo: '@'");
				//		
				System.out.println("Valor da conexao: ----> "+this.numeroConexao);
				if (this.numeroConexao == 1){
					// Marca local da peï¿½a/navio no tabuleiro/mapa do player 1
					// protocolo[1]; ï¿½ o valor da posicao
					if (jog2.verificaPosicao(leIntProtocolo(p))){

						writeResp(clientes.get(0),"&"+leIntProtocolo(p));
						String sv= String.valueOf(p);
						writeResp(clientes.get(1), sv);
						jog2.setVida(jog2.getVida()-1);

						Thread.sleep(500);

					}
					else{
						writeResp(clientes.get(0),"*"+leIntProtocolo(p));
						String sv= String.valueOf(p);
						writeResp(clientes.get(1), sv);
						Thread.sleep(500);
						this.numeroConexao= 2;
					}
				}	 

				if (this.numeroConexao == 2 ){
					System.out.println("Jogador 1 "+jog1.isVez());
					System.out.println("Jogador 2 "+jog2.isVez());

					// Marca local da peï¿½a/navio no tabuleiro/mapa do player 2
					// protocolo[1]; ï¿½ o valor da posicao
					if (jog1.verificaPosicao(leIntProtocolo(p))){
						writeResp(clientes.get(1),"&"+leIntProtocolo(p));
						String sv= String.valueOf(p);
						writeResp(clientes.get(0), sv);

						Thread.sleep(500);
						jog1.setVida(jog1.getVida()-1);
						this.numeroConexao=2;
					}
					else {
						writeResp(clientes.get(1),"*"+leIntProtocolo(p));
						String sv= String.valueOf(p);
						writeResp(clientes.get(0), sv);
						this.numeroConexao= 1;
						// Escreve no Chat
						Thread.sleep(1500);
					}
				}
			}      if (jog1.getVida() == 0){
				Thread.sleep(500);
				writeResp(clientes.get(1),"$win");

				writeResp(clientes.get(0),"$over");

				System.out.println("<- Comunicacao - Protocolo (Jogo/Ataque) Jogador 2 Vence...");
			}
			if (jog2.getVida() == 0) {
				Thread.sleep(500);
				writeResp(clientes.get(0),"$win");

				writeResp(clientes.get(1),"$over");
				System.out.println("<- Comunicacao - Protocolo (Jogo/Ataque) Jogador 1 Vence...");
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

	public int leIntProtocolo(char[] protocolo){
		String palavra;
		if  (protocolo.length==3)
			palavra = String.valueOf(protocolo[1]) + String.valueOf(protocolo[2]);
		else 
			palavra = String.valueOf(protocolo[1]);

		return Integer.parseInt(palavra);
	}

	public String leStringProtocolo(char[] protocolo){
		char[] palavra = new char[protocolo.length-1];
		for (int i=0; i < palavra.length; i++){
			palavra[i] = protocolo[i+1];
		}
		return  String.valueOf(palavra);
	}
	

	
	public static void main(String[] args)
			throws Exception {
		Servidor_NIO servidor= new Servidor_NIO();

		servidor.inicializar();

	}



}
