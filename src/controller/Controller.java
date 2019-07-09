package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import model.Cliente;
import view.Botao;
import view.Painel;
import view.TelaEscolha;
import view.TelaGenerica;
import view.TelaLoading;
import view.TelaPrincipal;

public class Controller implements ActionListener, MouseListener{
	//Declaração de telas
	private Painel painel;
	private TelaPrincipal telaPrincipal;
	private TelaLoading loading;
	private TelaEscolha telaEscolha;

	private Botao buttonsTab1[]; // = new JButton[100]; // Botoes 0 - 99
	private Botao buttonsTab2[];

	private Cliente cliente;
	private int auxPosition;
	private int qHidro = 5;
	private int qSubmarino = 4;
	private int qCruzador = 3;
	private int qEncouracado = 2;
	private int qPorta = 1;

	//variáveis auxiliares do formulário
	private String host, login;
	private int port;




	public Controller(Painel painel,
			TelaPrincipal telaPrincipal, TelaLoading loading, TelaEscolha telaEscolha) {
		//instanciado telas
		this.painel = painel;
		this.telaPrincipal = telaPrincipal;
		this.loading = loading;
		this.telaEscolha = telaEscolha;

		//Adicionando eventos
		this.painel.getBtnStart().addActionListener(this);
		this.painel.getBtnClose().addActionListener(this);

		this.buttonsTab1 = this.telaPrincipal.getTab().getButtonsTab1();
		this.buttonsTab2 = this.telaPrincipal.getTab().getButtonsTab2();
		//Adiciona eventos as casas do tabuleiro
		for (int i = 0; i < this.telaPrincipal.getTab().getButtonsTab1().length; i++) {
			this.buttonsTab1[i].addActionListener(this);
			this.buttonsTab2[i].addActionListener(this);
		}

		this.telaPrincipal.getTab().getPronto().addActionListener(this);

		//adicionando eventos na tela de escolha
		this.telaEscolha.getHidroaviao().addMouseListener(this);
		this.telaEscolha.getCruzador().addMouseListener(this);
		this.telaEscolha.getPortaAvioes().addMouseListener(this);
		this.telaEscolha.getSubmarino().addMouseListener(this);
		this.telaEscolha.getEncouracado().addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Se clicar em iniciar conecta ao servidor
		if(e.getSource()==painel.getBtnStart()) {

			try {
				//abrindo tela de carregamento
				loading.setVisible(true);

				//obtendo dados do formulário
				host = this.painel.getTfdServer().getText();
				port = Integer.parseInt(this.painel.getTfdPort().getText());
				login = this.painel.getTfdLogin().getText();

				//instanciando cliente com os dados do formulário
				cliente = new Cliente();
				cliente.setPorta(port);
				cliente.setHost(host);

				//conectando cliente ao servidor
				cliente.setStatus(cliente.conecta());

				//Iniciando a thread
				new threadReceve(cliente).start();

				//MSG
				//enviando primeira mensagem para informar nome do jogador
				cliente.enviarMensagemParaServidor("$"+this.login+"+");


			} catch (Exception ex) {
				System.out.println("Erro ao iniciar comunicacao");
				ex.printStackTrace();

			} 

			//Se clicar em cancelar fecha a tela
		}else if(e.getSource()==this.painel.getBtnClose()) {
			System.exit(0);

			//Se clicar em Estou pronto
		}else if(e.getSource()==this.telaPrincipal.getTab().getPronto()) {
			try {
				//Verifica se terminou de posicionar os navios
				if(qCruzador==0&&qEncouracado==0&&qHidro==0&&qPorta==0&&qSubmarino==0) {
				//avisa para o servidor que está pronto
				cliente.enviarMensagemParaServidor("&"+this.login+"+");
				telaPrincipal.getTab().getPronto().setEnabled(false);
				this.loading.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Certifique-se que posicionou todos os návios");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


		//Tela principal
		try {

			for (int contador = 0; contador < this.buttonsTab1.length; contador++) { 
				// Se "apertar" no Tabuleiro (Player 1)
				// PRIMEIRA ETAPA - Escolhendo a Posicao para cada Peca/Navio
				if (e.getSource() == this.buttonsTab1[contador]) {
					if (!this.buttonsTab1[contador].isStatus()) {
						this.auxPosition = contador;
						this.telaEscolha.setVisible(true);			
					}

				} 
				else if (e.getSource() == this.buttonsTab2[contador]) { // Se "apertar" no Tabuleiro Adversario
					if (this.telaPrincipal.getTab().getJogador().isVez()) {
						// envia para o servidor 
						cliente.enviarMensagemParaServidor("@"+login+" "+contador+"+");
						telaPrincipal.getTab().getLoading().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null,"Aguarde, ainda não é sua vez...");
					}

				}


			}
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto do Tabuleiro");
			exception.printStackTrace();
		}

	}



	@Override
	public void mouseClicked(MouseEvent e) {
		//Tela de escolha
		if (e.getSource()==this.telaEscolha.getPortaAvioes()) {
			if (this.qPorta!=0 && !colisao(1, auxPosition)) {
				for (int i = auxPosition; i < auxPosition+3; i++) {
					this.buttonsTab1[i].setBackground(new Color(0,255,0));
					this.buttonsTab1[i].setStatus(true);
					this.buttonsTab1[i].setColor(1);
				}

				this.qPorta--;
				this.telaEscolha.getLabelPortaAvioes().setText("Porta Aviões: "+qPorta);
				this.telaEscolha.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "Certifique-se que ainda tem návio disponível ou que não está colidindo com algo");
			}

		}else if (e.getSource() == this.telaEscolha.getEncouracado()){
			if (this.qEncouracado!=0  && !colisao(2, auxPosition)) {
				this.buttonsTab1[auxPosition].setBackground(new Color(0,0,0));
				this.buttonsTab1[auxPosition].setStatus(true);
				this.buttonsTab1[auxPosition].setColor(2);

				this.buttonsTab1[auxPosition+15].setBackground(new Color(0,0,0));
				this.buttonsTab1[auxPosition+15].setStatus(true);
				this.buttonsTab1[auxPosition+15].setColor(2);

				this.qEncouracado--;
				this.telaEscolha.getLabelEncouracado().setText("Ecouraçado: "+qEncouracado);
				this.telaEscolha.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "Certifique-se que ainda tem návio disponível ou que não está colidindo com algo");
			}

		}else if (e.getSource()==this.telaEscolha.getCruzador()){
			if (this.qCruzador!=0 && !colisao(3, auxPosition)) {
				for (int i = auxPosition; i < auxPosition+2; i++) {
					this.buttonsTab1[i].setBackground(new Color(255,255,0));
					this.buttonsTab1[i].setStatus(true);
					this.buttonsTab1[i].setColor(3);
				}

				this.qCruzador--;
				this.telaEscolha.getLabelCruzador().setText("Cruzador: "+qCruzador);
				this.telaEscolha.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "Certifique-se que ainda tem návio disponível ou que não está colidindo com algo");
			}

		}else if (e.getSource() == this.telaEscolha.getHidroaviao()){
			if (this.qHidro!=0 && !colisao(5, auxPosition)) {
				this.buttonsTab1[auxPosition].setBackground(new Color(255,0,0));
				this.buttonsTab1[auxPosition].setStatus(true);
				this.buttonsTab1[auxPosition].setColor(5);
				this.buttonsTab1[auxPosition+14].setBackground(new Color(255,0,0));
				this.buttonsTab1[auxPosition+14].setStatus(true);
				this.buttonsTab1[auxPosition+14].setColor(5);
				this.buttonsTab1[auxPosition+16].setBackground(new Color(255,0,0));
				this.buttonsTab1[auxPosition+16].setStatus(true);
				this.buttonsTab1[auxPosition+16].setColor(5);

				this.qHidro--;
				this.telaEscolha.getLabelHidroaviao().setText("Hidroavião: "+qHidro);
				this.telaEscolha.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "Certifique-se que ainda tem návio disponível ou que não está colidindo com algo");
			}

		}else if (e.getSource()==this.telaEscolha.getSubmarino()){
			if (this.qSubmarino!=0) {
				this.buttonsTab1[auxPosition].setBackground(new Color(255,165,0));
				this.buttonsTab1[auxPosition].setStatus(true);
				this.buttonsTab1[auxPosition].setColor(4);
				this.qSubmarino--;
				this.telaEscolha.getLabelSubmarino().setText("Submarino: "+qSubmarino);
				this.telaEscolha.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "Certifique-se que ainda tem návio disponível ou que não está colidindo com algo");
			}

		}

	}

	private boolean colisao(int quant, int p) {
		switch (quant) {
		case 1:
			//Se nao couber na tela
			if(p==13||p==14||p==28||p==29||p==43||p==44||p==58||p==59||p==73||p==74||
			p==88||p==89||p==103||p==104||p==118||p==119||p==133||p==134||p==148||p==149||
			p==163||p==164||p==178||p==179||p==193||p==194||p==208||p==209||p==223||p==224)
				return true;

			//Se for colidir com outro navio
			for (int i = p; i < p+3; i++) {
				if (this.buttonsTab1[i].isStatus())
					return true;
			}
			break;

		case 2:
			//Se nao couber na tela
			if (p==210||
			p==211||p==212||p==213||p==214||p==215||p==216||p==217||p==218||p==219||
			p==220||p==222||p==223||p==224) 
				return true;

			//Se for colidir com outro navio
			if(this.buttonsTab1[p+15].isStatus())
				return true;
			break;
		case 3:
			//se nao couber na tela
			if(p==14||p==29||p==44||p==59||p==74||p==89||p==104||p==119||p==134||p==149||
			p==164d||p==179||p==194||p==209||p==224)
				return true;

			//se for colidir com outro navio
			for (int i = p; i < p+2; i++) {
				if(this.buttonsTab1[i].isStatus())
					return true;
			}
			break;

		case 5:
			//se nao couber na tela
			if(p==14||p==29||p==44||p==59||p==74||p==89||p==104||p==119||p==134||p==149||
			p==164||p==179||p==194||p==209||p==0||p==15||p==30||p==45||p==60||p==75||
			p==90||p==105||p==120||p==135||p==150||p==165||p==180||p==195||p==210||
			p==211||p==212||p==213||p==214||p==215||p==216||p==217||p==218||p==219||
			p==220||p==222||p==223||p==224)
				return true;

			//se for colidir com outro navio
			if(this.buttonsTab1[p+14].isStatus()||this.buttonsTab1[p+16].isStatus())
				return true;

			break;
		}
		return false;
	}

	public void alterarTela(TelaGenerica close, TelaGenerica open) {
		close.setVisible(false);
		open.setVisible(true);
	}	

	public class threadReceve extends Thread{
		Cliente client;
		public threadReceve(Cliente client) {
			this.client = client;
		}
		public void run(){

			while (true) {
				try {
					if (cliente.isStatus()) {
						String m = "";
						if (cliente.getComandoEntrada().length()>0) {
							m = cliente.getComandoEntrada().substring(0,1);
						}

						//Se a entrada for $OK1 
						if (cliente.getComandoEntrada().equals("$OK1")){
							System.out.println("Comandos de entradas 3: "+ cliente.getComandoEntrada());

						}
						Thread.sleep(1000);
						//Se a entrada for $OK2 iniciar a nova tela
						if (cliente.getComandoEntrada().equals("$OK2")){

							System.out.println("Comandos de entradas 4: "+ cliente.getComandoEntrada());
							telaPrincipal.getNamePlayer1().setText("Seu Nome: "+login);
							//fechando tela de carregamento
							Thread.sleep(3000);
							loading.setVisible(false);

							//fechando o painel e abrindo a tela principal
							alterarTela(painel, telaPrincipal);

							cliente.setComandoEntrada("");

							//o jogo pode começar
						}else if(cliente.getComandoEntrada().equals("&start")) {
							Thread.sleep(200);
							loading.setVisible(false);
							telaPrincipal.getTab().getTabuleiroInimigo().setVisible(true);
							//Sou o primeiro a começar
						}else if(cliente.getComandoEntrada().equals("&first")) {
							telaPrincipal.getTab().getJogador().setVez(true);
							JOptionPane.showMessageDialog(null, "Você começa");

							cliente.setComandoEntrada("");
							//recebi uma mensagem do oponente
						}else if(cliente.getComandoEntrada().equals("&second")){
							telaPrincipal.getTab().getLoading().setVisible(true);
							JOptionPane.showMessageDialog(null, "Aguarde a jogada do oponente");
							cliente.setComandoEntrada("");
							
							//recebi mensagem do oponente
						}else if(m.equals("@")) {

							String[] split = cliente.getComandoEntrada().substring(1).split(" ");
							//recebi um ataque
							if (split.length==1) {
								int position = Integer.parseInt(split[0]);
								if (buttonsTab1[position].isStatus()) {
									buttonsTab1[position].setBackground(new Color(255,255,255));
									cliente.enviarMensagemParaServidor("@OK "+login+" "+position+" "+buttonsTab1[position].getColor()+"+");
									telaPrincipal.getTab().getJogador().setVez(false);

									//Diminui um pixel na minha barra de life
									telaPrincipal.getBoxLife1().setBounds(
											telaPrincipal.getBoxLife1().getX()+5,
											telaPrincipal.getBoxLife1().getY(),
											telaPrincipal.getBoxLife1().getWidth()-5,
											telaPrincipal.getBoxLife1().getHeight());
									
									//verifica se Perdeu
									if(telaPrincipal.getBoxLife1().getWidth()==0) {
										JOptionPane.showMessageDialog(null, "Você Perdeu!!!");
									}

								}else {
									buttonsTab1[position].setBackground(new Color(120,120,120));
									cliente.enviarMensagemParaServidor("@NOT "+login+" "+position+"+");
									telaPrincipal.getTab().getJogador().setVez(true);
									telaPrincipal.getTab().getLoading().setVisible(false);

								}
								cliente.setComandoEntrada("");
								//recebi uma resposta
							}else {
								int position = Integer.parseInt(split[2]);
								if(split[0].equals("OK")) {
									verificaCor(buttonsTab2[position], Integer.parseInt(split[3]));
									telaPrincipal.getTab().getJogador().setVez(true);
									telaPrincipal.getTab().getLoading().setVisible(false);

									//Diminui um pixel na barra de life do inimigo
									telaPrincipal.getBoxLife2().setBounds(
											telaPrincipal.getBoxLife2().getX(),
											telaPrincipal.getBoxLife2().getY(),
											telaPrincipal.getBoxLife2().getWidth()-5,
											telaPrincipal.getBoxLife2().getHeight());
									
									//verifica se venceu
									if(telaPrincipal.getBoxLife2().getWidth()==0) {
										JOptionPane.showMessageDialog(null, "Você Venceu!!!");
									}

								}else {
									buttonsTab2[position].setBackground(new Color(120,120,120));
									telaPrincipal.getTab().getJogador().setVez(false);
								}
								cliente.setComandoEntrada("");
							}


						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		private void verificaCor(Botao botao, int cor) {
			switch (cor) {
			case 1:
				botao.setBackground(new Color(0,255,0));
				break;

			case 2:
				botao.setBackground(new Color(0,0,0));
				break;

			case 3:
				botao.setBackground(new Color(255,255,0));
				break;

			case 4:
				botao.setBackground(new Color(255,165,0));
				break;

			case 5:
				botao.setBackground(new Color(255,0,0));
				break;

			default:
				break;
			}

		}
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
