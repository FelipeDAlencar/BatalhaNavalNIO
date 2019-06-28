package view;

import java.awt.CardLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import socket.Cliente;
import model.Jogador;
import model.Posicao;

public class ThreadMonitoraTelas extends Thread {
	// Declaracao das Classes construidas para o jogo 	
	private Jogador jogador;
	private Cliente cliente;
	private JPanel cards;
	private TelaJogador telaJogador;
	private TelaTabuleiro telaTabuleiro;
	private TelaNavios telaNavios;
	private TelaChat telaChat;
	private TelaConexao telaConexao;
	private TelaInicio telaInicio;

	// Construtor, recebe todas classes construidas para jogo e realiza a iteracao entre elas
	public ThreadMonitoraTelas(Jogador jogador, Cliente cliente,  JPanel cards,
			TelaJogador telaPlayer, TelaTabuleiro telaTabuleiro, 
			TelaNavios telaNavios, TelaChat telaChat, TelaConexao telaConexao, 
			TelaInicio telaInicio) {
		this.jogador = jogador;
		this.cliente = cliente;
		this.cards = cards;
		this.telaTabuleiro = telaTabuleiro;
		this.telaJogador =  telaPlayer;
		this.telaNavios = telaNavios;
		this.telaChat = telaChat;
		this.telaConexao = telaConexao;

		this.telaInicio = telaInicio;
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

	// Metodo que executa start() a Thread (Processo Filho)
	public void run() {
		try {

			int contador = 0;
			boolean statusLogin = false;
			boolean proximaTela = false;
			String aux = ""; // String auxiliar do Chat
			// Enquanto Numero de Jogadas for menor que 100 (Numero de posicao de um tabuleiro)
			while (true) {
				System.out.flush();
				if (telaNavios.isEscolha()) { 	// Se tela Console marcar escolha TRUE
					// Se Heroi estiver vivo
					if (jogador.getNavios().get(jogador.getNumEscolhas()-1).isVivo()){
						
						for (int i=0; i<jogador.getNavios().get(jogador.getNumEscolhas()-1).getPosicao().length; i++){
							// Pegando todas posicoes de cada Heroi
							int posicao= jogador.getNavios().get(jogador.getNumEscolhas()-1).getPosicao()[i].getXY();
							if (!telaNavios.isVolta()){
								// Marcando posicoes dos Herois
								telaTabuleiro.getButtonsTab1()[posicao].setEnabled(false);
								telaTabuleiro.getButtonsTab1()[posicao].setFundo(telaTabuleiro.isPosicao(), jogador.getNumEscolhas()-1);
								telaTabuleiro.getButtonsTab1()[posicao].setFundo(i);
								// Adicionando Imagem do Navio a Tela Player
								String dirNavio = "img/navios/navio" + Integer.toString((jogador.getNumEscolhas())) + ".jpg";
								telaJogador.getNavios()[jogador.getNumEscolhas()-1].setIcon(new ImageIcon(dirNavio));

							}
							System.out.println("Thread Monitora Telas - Numero de Escolhas: " + jogador.getNumEscolhas() +" posicao: "+posicao);
						}
						if (jogador.getNumEscolhas() < 5){	// Atualiza Descricao do Navio na Tela Console	
							telaNavios.getLabel().setIcon(telaNavios.getImagem()[jogador.getNumEscolhas()]);
	
						}
						// Incrementa uma rododa.
						jogador.setNumEscolhas(jogador.getNumEscolhas() + 1);
					}
					else { // Apresenta mensagem orientando usuario.
						JOptionPane.showMessageDialog(null,"Ops, primeiro escolha "
								+ "algum lugar no tabuleiro...");
					}
					telaNavios.setEscolha(false); // Marca escolha da Tela Console como FALSE		
				}
				// Atualiza a posicao da Tela Tabuleiro conforme Tela Console 
				telaTabuleiro.setPosicao(telaNavios.isPosicao());
				// Se tela Console marcar volta TRUE
				if (telaNavios.isVolta()){
					// Percorrendo List de Herois
					for (int i=0; i<jogador.getNavios().get(jogador.getNumEscolhas()-1).getPosicao().length; i++){
						// Pegando todas posicoes de cada Heroi para resetar Fundo (remover img navio)
						int posicao= jogador.getNavios().get(jogador.getNumEscolhas()-1).getPosicao()[i].getXY();
						telaTabuleiro.getButtonsTab1()[posicao].setEnabled(true); 
						telaTabuleiro.getButtonsTab1()[posicao].setFundo(); // (Mar azul) 
					}
					jogador.getNavios().get(jogador.getNumEscolhas()-1).setVivo(false); // Mata Navio
					telaNavios.setVolta(false); // Marca volta da Tela Console como FALSE
					telaJogador.getNavios()[jogador.getNumEscolhas()-1].setIcon(new ImageIcon()); // Remove Navio da Tela Player
					System.out.println("Thread Monitora Telas - Voltando escolha...");
				}
				if (jogador.getNumEscolhas() == 6) { // Se roda igual a 6 comeca o jogo
					telaTabuleiro.getCards().setVisible(true); // Mostra o Tabuleiro do Player 2
					CardLayout cl = (CardLayout)(telaNavios.getCards().getLayout());
					cl.show(telaNavios.getCards(), "2"); // Muda tela de exibicao da Tela Console
					// Percorre todos Herois e envia todas posicoes para o servidor
					for (int contadorHeroi = 0; contadorHeroi < jogador.getNavios().size(); contadorHeroi++){
						for (int contadorPosicao = 0; contadorPosicao < jogador.getNavios().get(contadorHeroi).getPosicao().length; contadorPosicao++){
				
							cliente.getMensagemDeSaida().append("#"+jogador.getNavios().get(contadorHeroi).getPosicao()[contadorPosicao].getXY()+"+");
							
						}
					}
					if(cliente.getMensagemDeSaida().length()>0) {
						String msg= cliente.getMensagemDeSaida().toString();
						System.out.println("Mensagem dentro de tread monitora de telas: "+msg);
						cliente.enviarMensagemParaServidor(msg);
						cliente.getMensagemDeSaida().setLength(0);
					}
					jogador.setNumEscolhas(jogador.getNumEscolhas()+1);
				}


				// Atualiza tela do chat (se conexao)
				if (cliente.isStatus()){
					String comandoEntrada = cliente.getComandoEntrada();
	//				System.out.println("Comandos de entradas 111: "+ cliente.getComandoEntrada());
					// Verifica se mensagem nao é igual a ultima...
					if (!comandoEntrada.equals(aux)){
						char[] protocolo;
						protocolo = comandoEntrada.toCharArray();
						// Sofrendo ataque do inimigo
						if (protocolo[0] == '@') {
							if (jogador.verificaPosicao(leIntProtocolo(protocolo))){
								int posicao = jogador.verificaHeroi(leIntProtocolo(protocolo));
								telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setEnabled(true); 
								telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setFundo();
								telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setFundo(1);
								telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setEnabled(false); 
								// Diminui 1 da vida do Jogador
								jogador.setVida(jogador.getVida()-1);
								// Diminui 1 da vida do Heroi (Navio)
								jogador.getNavios().get(posicao).setVida(jogador.getNavios().get(posicao).getVida()-1);
								// Retira uma ponto de cada barra de vida
								telaJogador.getCaixaVida().getComponent(jogador.getVida()).setVisible(false);
								telaNavios.getCaixaVida()[posicao].getComponent(jogador.getNavios().get(posicao).getVida()).setVisible(false);

								CardLayout cl = (CardLayout)(telaTabuleiro.getCards().getLayout());
								cl.show(telaTabuleiro.getCards(), "2");
								telaNavios.getBuffer().setVisible(true);
								jogador.setVez(false);
							}
							else {
								telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setEnabled(false);
								telaTabuleiro.getButtonsTab1()[leIntProtocolo(protocolo)].setFundo(2);

								CardLayout cl = (CardLayout)(telaTabuleiro.getCards().getLayout());
								cl.show(telaTabuleiro.getCards(), "2");
								telaNavios.getBuffer().setVisible(false);
								jogador.setVez(true);
							}

						}
						else if (protocolo[0] == '$'){
							if (leStringProtocolo(protocolo).equals("ini")) {
								jogador.setVez(true);
								JOptionPane.showMessageDialog(null,"VOCE COMECA...");
								telaNavios.getBuffer().setVisible(false);
							}
							else if (leStringProtocolo(protocolo).equals("over")) {
								JOptionPane.showMessageDialog(null,"GAME OVER...");
								CardLayout cl = (CardLayout)(cards.getLayout());
								cl.show(cards, "2");

							}
							else if (leStringProtocolo(protocolo).equals("win")) {
								JOptionPane.showMessageDialog(null,"PARABENS VOCE VENCEU...");
								CardLayout cl = (CardLayout)(cards.getLayout());
								cl.show(cards, "2");
							}
						}
						// Resposta do Ataque (Acertou)
						else if (protocolo[0] == '&') {
							System.out.println("Tela monitora de telas parte do ativamento do primet=iro jogador");
							telaTabuleiro.getButtonsTab2()[leIntProtocolo(protocolo)].setEnabled(false);
							telaTabuleiro.getButtonsTab2()[leIntProtocolo(protocolo)].setFundo(1);
							jogador.setVez(true);
							telaNavios.getBuffer().setVisible(false);
						}
						// Resposta do Ataque (Errou)
						else if (protocolo[0] == '*') {
							telaTabuleiro.getButtonsTab2()[leIntProtocolo(protocolo)].setEnabled(false);
							telaTabuleiro.getButtonsTab2()[leIntProtocolo(protocolo)].setFundo(2);
							jogador.setVez(false);
							telaNavios.getBuffer().setVisible(true);
						}
						else {// Atualiza Chat
							telaChat.setTextAreaConversas(comandoEntrada + "\n"+ telaChat.getTextAreaConversas().getText());
						}
						aux = comandoEntrada;
					}	

					if (!statusLogin){
			
						
						cliente.enviarMensagemParaServidor("$"+telaConexao.getTextFieldLogin().getText()+"+");
			
						
						System.out.println("Numero de conexoes: "+jogador.getNumeroConexoes());

				//		System.out.println("Comandos de entradas 4: "+ cliente.getComandoEntrada());
						telaJogador.getNome().setText(jogador.getNome());

						Thread.sleep(500);
						if (cliente.getComandoEntrada().equals("$OK1")){
				
							System.out.println("Comandos de entradas 3: "+ cliente.getComandoEntrada());

							jogador.setNome(telaConexao.getTextFieldLogin().getText());
							telaJogador.getNome().setText(jogador.getNome());
						}

						Thread.sleep(500);
						if (cliente.getComandoEntrada().equals("$OK2")){
							
							System.out.println("Comandos de entradas 3: "+ cliente.getComandoEntrada());

							telaInicio.getBuffer().setVisible(true);
							statusLogin = true;
							proximaTela = true;;
				
							cliente.enviarMensagemParaServidor("$OK"+"+");
					
							
						}
					}
					if (proximaTela) {	
						Thread.sleep(2000);

						System.out.println("Comandos de entradas 2: "+ cliente.getComandoEntrada());

						cliente.enviarMensagemParaServidor("$OK"+"+");
						JOptionPane.showMessageDialog(null,"Bem vindo a Batalha Naval");
						CardLayout cl = (CardLayout)(cards.getLayout());
						cl.show(cards, "2");

						proximaTela = false;

					}
					
					if (telaNavios.isRandom()){ // Atualiza informacoes do Jogador
						for (int i=0; i<5; i++){
							for (int j=0; j<jogador.getNavios().get(i).getPosicao().length; j++){
								// Pegando todas posicoes de cada Heroi
								int posicao= jogador.getNavios().get(i).getPosicao()[j].getXY();

								System.out.println("Posicao que da bug: "+ posicao);
								// Marcando posicoes dos Navios
								telaTabuleiro.getButtonsTab1()[posicao].setEnabled(false);
								telaTabuleiro.getButtonsTab1()[posicao].setFundo(telaTabuleiro.isPosicao(), i);
								telaTabuleiro.getButtonsTab1()[posicao].setFundo(j);
								System.out.println("Thread Monitora Telas - Numero de Escolhas: " + jogador.getNumEscolhas() +" posicao: "+posicao);
							}
							jogador.setNumEscolhas(jogador.getNumEscolhas()+1);
						}
						telaNavios.setRandom(false);
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}