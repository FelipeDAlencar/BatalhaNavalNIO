package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import model.Jogador;
import model.Posicao;

public class TelaNavios {
	
	// Declara Tela Principal - panelConsole (JPanel)
	private JPanel panelConsole1;
	private JPanel panelConsole2;
	private JPanel cards;
	// Declara Sub-telas (JPanel)

	private JPanel[] caixaVida;
//	private JLabel[] img;
	private JLabel[] nome;
	private JLabel[] vida;
	private JPanel telaBuffer;
	private JLabel buffer;
	// Declara Campo de texto com Barra de rolagem
//	private JTextArea textArea;
	private JScrollPane scrollPane;
	// Declara a classe (Importada do pacote Arquivos)

    // Declara Componente - Botoes Volta e Proximo
//	private JButton ButtonVoltar;
//	private JButton ButtonProximo;
	private JButton ButtonRandom;

	// Declarando e adicionando as Imagens ao vetor de Icon (Diretorio img) 
	private Icon imagem[] = new Icon[5];
	
	// Adicionando Icon (Imagens) ao JLabel[]
	private JLabel label = new JLabel();
	// Nossa classe (Classe importada de Jogo.Jogador)
	private Jogador jogador;
	// Declara variavel auxiliar (Flags)
	private boolean escolha = false;
	private boolean volta = false;
	private boolean posicao = false;
	private boolean random;
	// Construtor da Tela
	public TelaNavios(Jogador jogador) {
		// Cria Card de Telas
		cards = new JPanel(new CardLayout());
		// Cria Telas
		telaConsole1(jogador);
		telaConsole2(jogador);
		// Adiciona Telas ao Card de Telas
		cards.add(getPanelConsole1(), "1");
		cards.add(getPanelConsole2(), "2");
	}
	// TelaConsole1 (Console de Escolhas)
	public void telaConsole1(Jogador jogador) {
		this.jogador = jogador;
		// Cria Tela Principal (JPanel)
		setPanelConsole1(new JPanel());
		// Adiciona imagem do Navio/Peca a tela
		getPanelConsole1().add(getLabel());

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        ButtonHandler handler = new ButtonHandler();
//		setButtonVoltar( (new JButton("Voltar Escolha")) );
//		setButtonProximo( (new JButton("Confirmar Escolha")) );
		setButtonRandom( (new JButton("Escolha de Navios Aleatorios")) );
		// Adicionando os botes a tela principal
//		getPanelConsole1().add(getButtonVoltar());
//		getPanelConsole1().add(getButtonProximo());
		getPanelConsole1().add(getButtonRandom());
		// Adiciona os botoes e radio button ao Manipulador de eventos
//		getButtonVoltar().addActionListener(handler); 
//		getButtonProximo().addActionListener(handler);
		getButtonRandom().addActionListener(handler);

	}
	// Tela Console 2 (Console do Jogo/Navios)
	public void telaConsole2(Jogador player) {
		this.jogador = player;
		// Cria Tela Principal (JPanel)
		setPanelConsole2(new JPanel(new GridLayout(6,1)));
		// Cria Sub-Telas	
//		this.telaNavio = new JPanel[5];
//		this.telaImg = new JPanel[5];
		this.caixaVida = new JPanel[5];
		// Cria Label para Imagem e Nome dos Navios
//		this.img = new JLabel[5];
		this.nome = new JLabel[5];
		for (int i = 0; i < player.getNavios().size(); i++){
    			// Cria Label passando a String com diretorio da imagem dos navios
    			String dirNavio = "img/navios/navio" + Integer.toString((i+1)) + ".jpg";
	//			this.img[i] = new JLabel(new ImageIcon(dirNavio));
				// Cria Label com Nome do Navio, muda a cor, fonte etc
				this.nome[i] = new JLabel();
				this.nome[i].setFont(new Font("Verdana", Font.BOLD, 14));
				this.nome[i].setForeground(Color.RED);
				// Adicionando Labels a Sub-tela
//				this.telaImg[i] = new JPanel(new GridLayout(2,1));
//				this.telaImg[i].add(this.img[i]);
//				this.telaImg[i].add(this.nome[i]);
				// Criando e adicionando Telas a Sub-Tela
				this.caixaVida[i] = new JPanel (new FlowLayout());
			this.vida = new JLabel[player.getNavios().get(i).getPosicao().length];
				for (int contador =0; contador<vida.length; contador++){
					this.vida[contador] = new JLabel(new ImageIcon("img/vida/vida1.jpg"));
					this.caixaVida[i].add(this.vida[contador]);
				}
		
				// Criando e adicionando Telas a Sub-Tela
//				this.telaNavio[i] = new JPanel(new FlowLayout());
//				this.telaNavio[i].add(this.telaImg[i]);
//				this.telaNavio[i].add(this.caixaVida[i]);
//				
//				getPanelConsole2().add(this.telaNavio[i]);
				
    	}
    	this.telaBuffer = new JPanel(new FlowLayout());
		this.buffer = new JLabel();
		this.buffer.setIcon(new ImageIcon("img/buffer/buffer2.gif"));
		this.buffer.setVisible(true);
		
		this.telaBuffer.add(this.buffer);
    	getPanelConsole2().add(this.telaBuffer);
	}
	// Manipulador de Acoes - Botoes  
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int auxSorteado = 0;
				// Se marcar RadioButton
				if (e.getActionCommand().equals("horizontal")){
					setPosicao(true);
				}
				if (e.getActionCommand().equals("vertical")){
					setPosicao(false);
				}
//				// Se "apertar" botao Voltar 
//				if (e.getSource() == getButtonVoltar()) {
//						if (jogador.getNumEscolhas() > 1 ){
//							jogador.setNumEscolhas(jogador.getNumEscolhas()-1);
//							setVolta(true);
//							getLabel().setIcon(getImagem()[jogador.getNumEscolhas()-1]);
////							getTextArea().setText("");
////							getTextArea().setText(getConsulta().ConsultaNavio(jogador.getNumEscolhas()-1, getTextArea().getText(), jogador.getPais()));	
//						}
//				}
//				// Se "apertar" botao Escolher
//				if (e.getSource() == getButtonProximo()) {
//					// Muda Imagem do navio/peca
//					setEscolha(true);
//				}
				// Se "apertar" botao Random
				if (e.getSource() == getButtonRandom()) {
					int aux; 		// Valor auxiliar para estabelecer Posicoes
					if (posicao) 	// TRUE: Vertical // FALSE: Horizontal
						 aux = 1;
					else
						 aux = 10;
					Random ran = new Random();
					
					for (int i=0; i<jogador.getNavios().size(); i++){
						
						int sorteado = ran.nextInt(79)+i;
						for (int contadorHeroi=0; contadorHeroi<5; contadorHeroi++){
							for (int contadorPosicao=0; contadorPosicao<jogador.getNavios().get(contadorHeroi).getPosicao().length; contadorPosicao++){
								if (jogador.getNavios().get(contadorHeroi).getPosicao()[contadorPosicao]!=null){
									if (sorteado==jogador.getNavios().get(contadorHeroi).getPosicao()[contadorPosicao].getXY()){
										sorteado = ran.nextInt(79);
										contadorHeroi=0;
										contadorPosicao=0;
									}
								}
							}	
						}
						// Cria posicao do Heroi levando em conta numero de Rodadas e Tamanho/Posicoes do Navio
						if (i == 0) {
							auxSorteado = verificaPosicao(1, sorteado, aux, posicao);
							jogador.getNavios().get(0).getPosicao()[0] = new Posicao(auxSorteado);
							jogador.getNavios().get(0).getPosicao()[1] = new Posicao(auxSorteado+aux);
							jogador.getNavios().get(0).setVivo(true);
						}
						if (i == 1) {
							auxSorteado = verificaPosicao(2, sorteado, aux, posicao);
							jogador.getNavios().get(1).getPosicao()[0] = new Posicao(auxSorteado);
							jogador.getNavios().get(1).getPosicao()[1] = new Posicao(auxSorteado+aux);
							jogador.getNavios().get(1).setVivo(true);
						}
						if (i == 2) {
							auxSorteado = verificaPosicao(3, sorteado, aux, posicao);
							jogador.getNavios().get(2).getPosicao()[0] = new Posicao(auxSorteado-aux);
							jogador.getNavios().get(2).getPosicao()[1] = new Posicao(auxSorteado);
							jogador.getNavios().get(2).getPosicao()[2] = new Posicao(auxSorteado+aux);
							jogador.getNavios().get(2).setVivo(true);
						}
						if (i == 3) {
							auxSorteado = verificaPosicao(4, sorteado, aux, posicao);
							jogador.getNavios().get(3).getPosicao()[0] = new Posicao(auxSorteado-aux);
							jogador.getNavios().get(3).getPosicao()[1] = new Posicao(auxSorteado);
							jogador.getNavios().get(3).getPosicao()[2] = new Posicao(auxSorteado+aux);
							jogador.getNavios().get(3).getPosicao()[3] = new Posicao(auxSorteado+aux*2);
							jogador.getNavios().get(3).setVivo(true);
						}
						if (i == 4) {
							auxSorteado = verificaPosicao(5, sorteado, aux, posicao);
							jogador.getNavios().get(4).getPosicao()[0] = new Posicao(auxSorteado-aux);
							jogador.getNavios().get(4).getPosicao()[1] = new Posicao(auxSorteado);
							jogador.getNavios().get(4).getPosicao()[2] = new Posicao(auxSorteado+aux);
							jogador.getNavios().get(4).getPosicao()[3] = new Posicao(auxSorteado+aux*2);
							jogador.getNavios().get(4).setVivo(true);
						}
						setRandom(true);
					}
				}
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto do Console");
				exception.printStackTrace();
			}
		}
	}
	
//	public void atualizaHeroisJogador(String pais){
//		for (int i=0; i<imagem.length; i++)
//			this.nome[i].setText(jogador.getHerois().get(i).getNome());
//
//	} 
	public int verificaPosicao(int navio, int contador, int aux, boolean posicao){
		
		Posicao auxiliar = new Posicao(contador);
		int auxContador = contador;
		
		if (navio == 1 || navio == 2){
				if (auxiliar.getX() == 9 && posicao){
						auxContador= contador-aux;
				}
				if (auxiliar.getX() == 9 && auxiliar.getY() == 9){
						auxContador= contador-aux;
				}
				if (auxiliar.getY() == 9 && !posicao){
						auxContador= contador-aux;
				}
		}
		if (navio == 3){
				if (auxiliar.getY() == 0 && !posicao){
					auxContador = contador+aux;
				}
				if (auxiliar.getX() == 0 && posicao){
					auxContador = contador+aux;
				}
				if (auxiliar.getX() == 9 && posicao){
						auxContador = contador-aux;
				}
				if (auxiliar.getX() == 9 && auxiliar.getY() == 9){
						auxContador= contador-aux;
				}
				if (auxiliar.getY() == 9 && !posicao){
						auxContador= contador-aux;
				}
		}
		if (navio == 4 || navio == 5){
				if (auxiliar.getY() == 0 && !posicao){
					auxContador = contador+aux;
				}
				if (auxiliar.getX() == 0 && posicao){
					auxContador = contador+aux;
				}
				if (auxiliar.getX() == 9 && posicao){
						auxContador= contador-aux*2;
				}
				if (auxiliar.getX() == 9 && auxiliar.getY() == 9){
						auxContador= contador-aux*2;
				}
				if (auxiliar.getY() == 9 && !posicao){
						auxContador= contador-aux*2;
				}
		}
		return auxContador;
	} 
	public JPanel getPanelConsole1() {
		return panelConsole1;
	}
	public void setPanelConsole1(JPanel panelConsole1) {
		this.panelConsole1 = panelConsole1;
	}
	public JPanel getPanelConsole2() {
		return panelConsole2;
	}
	public void setPanelConsole2(JPanel panelConsole2) {
		this.panelConsole2 = panelConsole2;
	}
//	public JPanel getPanelDescricao() {
//		return panelDescricao;
//	}
//	public void setPanelDescricao(JPanel panelDescricao) {
//		this.panelDescricao = panelDescricao;
//	}
//	public JTextArea getTextArea() {
//		return textArea;
//	}
//	public void setTextArea(JTextArea textArea) {
//		this.textArea = textArea;
//	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
//	public JButton getButtonVoltar() {
//		return ButtonVoltar;
//	}
//	public void setButtonVoltar(JButton buttonVoltar) {
//		ButtonVoltar = buttonVoltar;
//	}
//	public JButton getButtonProximo() {
//		return ButtonProximo;
//	}
//	public void setButtonProximo(JButton buttonProximo) {
//		ButtonProximo = buttonProximo;
//	}
	public Icon[] getImagem() {
		return imagem;
	}
	public void setImagem(Icon[] imagem) {
		this.imagem = imagem;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public boolean isEscolha() {
		return escolha;
	}
	public void setEscolha(boolean escolha) {
		this.escolha = escolha;
	}
	public boolean isVolta() {
		return volta;
	}
	public void setVolta(boolean volta) {
		this.volta = volta;
	}
	public boolean isPosicao() {
		return posicao;
	}
	public void setPosicao(boolean posicao) {
		this.posicao = posicao;
	}

//	public JPanel[] getTelaNavio() {
//		return telaNavio;
//	}
//
//	public void setTelaNavio(JPanel[] telaNavio) {
//		this.telaNavio = telaNavio;
//	}
//
//	public JPanel[] getTelaImg() {
//		return telaImg;
//	}
//
//	public void setTelaImg(JPanel[] telaImg) {
//		this.telaImg = telaImg;
//	}

	public JPanel[] getCaixaVida() {
		return caixaVida;
	}

	public void setCaixaVida(JPanel[] caixaVida) {
		this.caixaVida = caixaVida;
	}

//	public JLabel[] getImg() {
//		return img;
//	}
//
//	public void setImg(JLabel[] img) {
//		this.img = img;
//	}

	public JLabel[] getNome() {
		return nome;
	}

	public void setNome(JLabel[] nome) {
		this.nome = nome;
	}

	public JLabel[] getVida() {
		return vida;
	}

	public void setVida(JLabel[] vida) {
		this.vida = vida;
	}

	public JPanel getCards() {
		return cards;
	}

	public void setCards(JPanel cards) {
		this.cards = cards;
	}
	public JButton getButtonRandom() {
		return ButtonRandom;
	}
	public void setButtonRandom(JButton buttonRandom) {
		ButtonRandom = buttonRandom;
	}
	public boolean isRandom() {
		return random;
	}
	public void setRandom(boolean random) {
		this.random = random;
	}

	public JPanel getTelaBuffer() {
		return telaBuffer;
	}
	public void setTelaBuffer(JPanel telaBuffer) {
		this.telaBuffer = telaBuffer;
	}
	public JLabel getBuffer() {
		return buffer;
	}
	public void setBuffer(JLabel buffer) {
		this.buffer = buffer;
	}
	
}
