package socket;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;


public class ClienteNIO {

	private String host;
	private int porta;
	private boolean status = false;
	private String mensagemParaServidor = "";  
	private String mensagemVindaDoServidor = "";
	private BufferedReader userIn;

	private boolean escrita= false;

	private  ByteBuffer buf = ByteBuffer.allocate(256);
	private  SocketChannel socketChanelCliente;
	private StringBuffer mensagemDeSaida = new StringBuffer();
	private StringBuffer aux = new StringBuffer();


	public ClienteNIO () throws UnknownHostException, IOException {
	}



	public boolean conecta() throws Exception {
		InetSocketAddress addr = new InetSocketAddress(this.host, this.porta);
		socketChanelCliente = SocketChannel.open();
		socketChanelCliente.configureBlocking(false);
		socketChanelCliente.connect(addr);

		this.status = true;

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					InputStream stream = new ByteArrayInputStream(mensagemParaServidor.getBytes());
					userIn = new BufferedReader( new InputStreamReader(stream));
					FixedSeparatorMessageTokenizer tokenizer = new	 FixedSeparatorMessageTokenizer("\n",Charset.forName("UTF-8"));

					String msg;
					while (status && (msg = userIn.readLine())!= null) {
						msg += "\n"; //make sure to add the end of line
						// write the line to the server
						ByteBuffer outbuf = ByteBuffer.wrap(msg.getBytes("UTF-8"));
						while (outbuf.remaining() > 0) {
							socketChanelCliente.write(outbuf); //Writing from outbuf to sChannel
						}
						// read a line from the server and print it
						while (!tokenizer.hasMessage()) {
							buf.clear(); //The position is set to zero, the limit is set to the capacity

							socketChanelCliente.read(buf);//An attempt is made to read up to r bytes from the channel,
							// (where r is the number of bytes remaining in the buffer)

							buf.flip();//The limit is set to the current position and then the position
							// is set to zero
							tokenizer.addBytes(buf);
							
						}

						mensagemVindaDoServidor = tokenizer.nextMessage();
						System.out.println(mensagemVindaDoServidor);
					//	System.out.println(tokenizer.nextMessage()); // write the line to the screen
						if (msg.equals("bye\n")){
							System.out.println("Client exit...");
							status = false;
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

//	public boolean comunicacaoComServidor(Set readySet) throws IOException {
//		SelectionKey key = null;
//		Iterator iterator = null;
//		iterator = readySet.iterator();
//
//		while (iterator.hasNext()) {
//			key = (SelectionKey) iterator.next();
//			iterator.remove();
//
//			if (key.isConnectable()) {
//				Boolean connected = processConnect(key);
//				if (!connected) {
//					return true;
//				}
//
//			}
//			// Testa se o canal desta chave está pronto para leitura
//			if (key.isReadable()) {
//				System.out.println("Entrou na leitura");
//
//				SocketChannel sc = (SocketChannel) key.channel ();
//				ByteBuffer bb = ByteBuffer.allocate(15);
//
//
//				try {
//					sc.read (bb);
//				} catch (Exception e) {
//
//					JOptionPane.showMessageDialog(null,"Comunicação inerrompida brutalmente!");
//					System.exit(0);
//				}
//				String result = new String (bb.array ()).trim();
//
//				System.out.println ( "Mensagem recebida de  Servidor: " + result + " Comprimento da mensagem = "+ result.length ());
//
//				setComandoEntrada(result);
//			}
//			if(key.isWritable()) {
//
//				escrita=true;
//			}
//		}
//		r'eturn false;
//
//	}

//	public Boolean processConnect(SelectionKey key) {
//		SocketChannel sc = (SocketChannel) key.channel();
//		try {
//			while (sc.isConnectionPending()) {
//				sc.finishConnect();
//			}
//		} catch (IOException e) {
//			key.cancel();
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

	
	
	public void enviarMensagemParaServidor(String msg) throws IOException, InterruptedException {

		if(!escrita) {
			return;
		}
		char strin[];
		aux.setLength(0);
		System.out.println("Tentando enviar mensagem: "+msg);
		if(msg.length()> 0) {
			strin = msg.toString().toCharArray();

			for(int i=0; i< msg.length(); i++) {

				if(strin[i]== '+'){
					if(aux.length()> 0) {
						String str = aux.toString();
						System.out.println("Mensagem enviada: "+str);

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

	}
	
	
	/**
	 * @return the mensagemParaServidor
	 */
	public String getMensagemParaServidor() {
		return mensagemParaServidor;
	}



	/**
	 * @param mensagemParaServidor the mensagemParaServidor to set
	 */
	public void setMensagemParaServidor(String mensagemParaServidor) {
		this.mensagemParaServidor = mensagemParaServidor;
	}



	/**
	 * @return the mensagemVindaDoServidor
	 */
	public String getMensagemVindaDoServidor() {
		return mensagemVindaDoServidor;
	}



	/**
	 * @param mensagemVindaDoServidor the mensagemVindaDoServidor to set
	 */
	public void setMensagemVindaDoServidor(String mensagemVindaDoServidor) {
		this.mensagemVindaDoServidor = mensagemVindaDoServidor;
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
		return mensagemParaServidor;
	}
	public void setComandoEntrada(String comandoEntrada) {
		this.mensagemParaServidor = comandoEntrada;
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

	public StringBuffer getMensagemDeSaida() {
		return mensagemDeSaida;
	}

	public void setMensagemDeSaida(StringBuffer mensagemDeSaida) {
		this.mensagemDeSaida = mensagemDeSaida;
	}


}