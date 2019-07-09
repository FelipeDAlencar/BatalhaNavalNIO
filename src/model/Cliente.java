package model;


import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

import javax.swing.JOptionPane;


public class Cliente {

	private String host;
	private int porta;
	private boolean status;
	private String comandoEntrada;  
	private ByteBuffer buf;
	private SocketChannel socketChanelCliente;
	private Selector selector;

	private StringBuffer mensagemDeSaida;
	private StringBuffer aux;


	public Cliente () throws UnknownHostException, IOException {
		this.buf = ByteBuffer.allocate(256);
		this.comandoEntrada="";
		this.status=false;
		this.mensagemDeSaida = new StringBuffer();
		this.aux = new StringBuffer();
	}



	public boolean conecta() throws Exception {
		//Iniciando conexão com o servidor
		InetSocketAddress addr = new InetSocketAddress(this.host, this.porta);
		
		this.selector = Selector.open();
		this.socketChanelCliente = SocketChannel.open();
		this.socketChanelCliente.configureBlocking(false);
		this.socketChanelCliente.connect(addr);
		this.socketChanelCliente.register(this.selector, SelectionKey.OP_CONNECT |SelectionKey.OP_READ | SelectionKey.OP_WRITE);

		this.status = true;
		
		//abrindo porta para comunicação com o servidor
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						if (selector.select() > 0) {
							Boolean doneStatus = comunicacaoComServidor(selector.selectedKeys());
							if (doneStatus) {
								break;
							}
						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread1.start();

		return status;

	}

	public boolean comunicacaoComServidor(Set readySet) throws IOException {
		SelectionKey key = null;
		Iterator iterator = null;
		iterator = readySet.iterator();

		while (iterator.hasNext()) {
			key = (SelectionKey) iterator.next();
			iterator.remove();

			if (key.isConnectable()) {
				Boolean connected = processConnect(key);
				if (!connected) {
					return true;
				}

			}
			// Testa se o canal desta chave esta pronto para leitura
			if (key.isReadable()) {

				SocketChannel sc = (SocketChannel) key.channel ();
				ByteBuffer bb = ByteBuffer.allocate(15);
				
				
				try {
					sc.read (bb);
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null,"Comunição inerrompida brutalmente!");
					System.exit(0);
				}
				String result = new String (bb.array ()).trim();
				this.comandoEntrada=result;
			}
			
		}
		return false;

	}

	public Boolean processConnect(SelectionKey key) {
		SocketChannel sc = (SocketChannel) key.channel();
		try {
			while (sc.isConnectionPending()) {
				sc.finishConnect();
			}
		} catch (IOException e) {
			key.cancel();
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean enviarMensagemParaServidor(String msg) throws IOException, InterruptedException {
		char strin[];
		aux.setLength(0);
		
		if(msg.length()> 0) {
			strin = msg.toString().toCharArray();

			for(int i=0; i< msg.length(); i++) {

				if(strin[i]== '+'){
					if(aux.length()> 0) {
						String str = aux.toString();
						Thread.sleep(500);
						ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
						socketChanelCliente.write(buffer);
						buffer.clear();
						aux.setLength(0);
						Thread.sleep(300);

					}
				}else {
					if (strin[i] == '\r' || strin[i] == '\n') break;
						aux.append(strin[i]);
				}

			}

		}
		return true;

	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getComandoEntrada() {
		return comandoEntrada;
	}
	public void setComandoEntrada(String comandoEntrada) {
		this.comandoEntrada = comandoEntrada;
	}

	public ByteBuffer getBuf() {
		return buf;
	}
	public void setBuf(ByteBuffer buf) {
		this.buf = buf;
	}

	public SocketChannel getSocketChanelCliente() {
		return socketChanelCliente;
	}
	public void setSocketChanelCliente(SocketChannel socketChanelCliente) {
		this.socketChanelCliente = socketChanelCliente;
	}
	public Selector getSelector() {
		return selector;
	}
	public void setSelector(Selector selector) {
		this.selector = selector;
	}

	public StringBuffer getMensagemDeSaida() {
		return mensagemDeSaida;
	}

	public void setMensagemDeSaida(StringBuffer mensagemDeSaida) {
		this.mensagemDeSaida = mensagemDeSaida;
	}


}