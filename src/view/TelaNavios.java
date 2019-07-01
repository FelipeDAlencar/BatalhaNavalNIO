package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import model.Jogador;
import model.Posicao;

public class TelaNavios {

	//Declara Tela Principal
	private JPanel panelConsole1;
	private JPanel panelConsole2;
	private JPanel cards;

	//Declarando componentes dos tabuleiros
	private JPanel[] boxLife;
	private JLabel[] nome;
	private JLabel[] vida;
	private JPanel panelBuffer;
	private JLabel buffer;
	private JScrollPane scrollPane;
	private JButton btnRandom;
	private Icon[] image;
	private JLabel label;
	//Declarando Jogador
	private Jogador player;

	// Declarando variáveis auxiliares
	private boolean choice; 
	private boolean back;
	private boolean position;
	private boolean random;


	// Construtor da Tela
	public TelaNavios(Jogador player) {
		this.player = player;

		// Cria Card de Telas
		this.cards = new JPanel(new CardLayout());

		// Cria Telas
		this.createConsole1();
		this.createConsole2();

		// Adiciona Telas ao Card de Telas
		this.cards.add(this.panelConsole1, "1");
		this.cards.add(this.panelConsole2, "2");

		this.image = new Icon[5];
		this.label = new JLabel();
	}

	// TelaConsole1
	public void createConsole1() {

		// Cria Tela Principal (JPanel)
		this.panelConsole1 = (new JPanel());

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;

		//Adicionanco Ação ao botão de adicionar Návios
		ButtonHandler handler = new ButtonHandler();
		this.btnRandom =  new JButton("Posicionar Navios Aleatóriamente");
		this.panelConsole1.add(this.btnRandom);
		this.btnRandom.addActionListener(handler);

	}

	// Tela Console 2
	public void createConsole2() {
		// Cria Tela Principal (JPanel)
		this.panelConsole2 = new JPanel(new GridLayout(6,1));

		this.boxLife = new JPanel[5];

		this.nome = new JLabel[5];
		for (int i = 0; i < player.getNavios().size(); i++){

			// Cria Label passando a String com diretorio da imagem dos navios
			

			// Cria Label com Nome do Navio, muda a cor, fonte etc
			this.nome[i] = new JLabel();
			this.nome[i].setFont(new Font("Verdana", Font.BOLD, 14));
			this.nome[i].setForeground(Color.RED);

		}

		this.panelBuffer = new JPanel(new FlowLayout());
		this.buffer = new JLabel();
		this.buffer.setIcon(new ImageIcon("img/buffer/buffer2.gif"));
		this.buffer.setVisible(true);

		this.panelBuffer.add(this.buffer);
		getPanelConsole2().add(this.panelBuffer);
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

				// Se "apertar" botao Random
				if (e.getSource() == getbtnRandom()) {
					int aux; 		// Valor auxiliar para estabelecer Posicoes
					if (position) 	// TRUE: Vertical // FALSE: Horizontal
						aux = 1;
					else
						aux = 10;
					Random ran = new Random();

					for (int i=0; i<player.getNavios().size(); i++){

						int sorteado = ran.nextInt(79)+i;
						for (int j=0; j<5; j++){
							for (int k=0; k<player.getNavios().get(j).getPosicao().length; k++){
								if (player.getNavios().get(j).getPosicao()[k]!=null){
									if (sorteado==player.getNavios().get(j).getPosicao()[k].getXY()){
										sorteado = ran.nextInt(79);
										j=0;
										k=0;
									}
								}
							}	
						}

						// Cria position do Heroi levando em conta numero de Rodadas e Tamanho/Posicoes do Navio
						if (i == 0) {
							auxSorteado = verificaPosicao(1, sorteado, aux, position);
							player.getNavios().get(0).getPosicao()[0] = new Posicao(auxSorteado);
							player.getNavios().get(0).getPosicao()[1] = new Posicao(auxSorteado+aux);
							player.getNavios().get(0).setVivo(true);
						}
						if (i == 1) {
							auxSorteado = verificaPosicao(2, sorteado, aux, position);
							player.getNavios().get(1).getPosicao()[0] = new Posicao(auxSorteado);
							player.getNavios().get(1).getPosicao()[1] = new Posicao(auxSorteado+aux);
							player.getNavios().get(1).setVivo(true);
						}
						if (i == 2) {
							auxSorteado = verificaPosicao(3, sorteado, aux, position);
							player.getNavios().get(2).getPosicao()[0] = new Posicao(auxSorteado-aux);
							player.getNavios().get(2).getPosicao()[1] = new Posicao(auxSorteado);
							player.getNavios().get(2).getPosicao()[2] = new Posicao(auxSorteado+aux);
							player.getNavios().get(2).setVivo(true);
						}
						if (i == 3) {
							auxSorteado = verificaPosicao(4, sorteado, aux, position);
							player.getNavios().get(3).getPosicao()[0] = new Posicao(auxSorteado-aux);
							player.getNavios().get(3).getPosicao()[1] = new Posicao(auxSorteado);
							player.getNavios().get(3).getPosicao()[2] = new Posicao(auxSorteado+aux);
							player.getNavios().get(3).getPosicao()[3] = new Posicao(auxSorteado+aux*2);
							player.getNavios().get(3).setVivo(true);
						}
						if (i == 4) {
							auxSorteado = verificaPosicao(5, sorteado, aux, position);
							player.getNavios().get(4).getPosicao()[0] = new Posicao(auxSorteado-aux);
							player.getNavios().get(4).getPosicao()[1] = new Posicao(auxSorteado);
							player.getNavios().get(4).getPosicao()[2] = new Posicao(auxSorteado+aux);
							player.getNavios().get(4).getPosicao()[3] = new Posicao(auxSorteado+aux*2);
							player.getNavios().get(4).setVivo(true);
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

	public int verificaPosicao(int navio, int contador, int aux, boolean position){

		Posicao auxiliar = new Posicao(contador);
		int auxContador = contador;

		if (navio == 1 || navio == 2){
			if (auxiliar.getX() == 9 && position){
				auxContador= contador-aux;
			}
			if (auxiliar.getX() == 9 && auxiliar.getY() == 9){
				auxContador= contador-aux;
			}
			if (auxiliar.getY() == 9 && !position){
				auxContador= contador-aux;
			}
		}
		if (navio == 3){
			if (auxiliar.getY() == 0 && !position){
				auxContador = contador+aux;
			}
			if (auxiliar.getX() == 0 && position){
				auxContador = contador+aux;
			}
			if (auxiliar.getX() == 9 && position){
				auxContador = contador-aux;
			}
			if (auxiliar.getX() == 9 && auxiliar.getY() == 9){
				auxContador= contador-aux;
			}
			if (auxiliar.getY() == 9 && !position){
				auxContador= contador-aux;
			}
		}
		if (navio == 4 || navio == 5){
			if (auxiliar.getY() == 0 && !position){
				auxContador = contador+aux;
			}
			if (auxiliar.getX() == 0 && position){
				auxContador = contador+aux;
			}
			if (auxiliar.getX() == 9 && position){
				auxContador= contador-aux*2;
			}
			if (auxiliar.getX() == 9 && auxiliar.getY() == 9){
				auxContador= contador-aux*2;
			}
			if (auxiliar.getY() == 9 && !position){
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

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public Icon[] getImagem() {
		return image;
	}
	public void setImagem(Icon[] imagem) {
		this.image = imagem;
	}

	public Jogador getJogador() {
		return player;
	}
	public void setJogador(Jogador player) {
		this.player = player;
	}
	public boolean isEscolha() {
		return choice;
	}
	public void setEscolha(boolean choice) {
		this.choice = choice;
	}
	public boolean isVolta() {
		return back;
	}
	public void setVolta(boolean back) {
		this.back = back;
	}
	public boolean isPosicao() {
		return position;
	}
	public void setPosicao(boolean position) {
		this.position = position;
	}

	public JPanel[] getCaixaVida() {
		return boxLife;
	}

	public void setCaixaVida(JPanel[] boxLife) {
		this.boxLife = boxLife;
	}


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
	public JButton getbtnRandom() {
		return btnRandom;
	}
	public void setbtnRandom(JButton buttonRandom) {
		btnRandom = buttonRandom;
	}
	public boolean isRandom() {
		return random;
	}
	public void setRandom(boolean random) {
		this.random = random;
	}

	public JPanel getTelaBuffer() {
		return panelBuffer;
	}
	public void setTelaBuffer(JPanel telaBuffer) {
		this.panelBuffer = telaBuffer;
	}
	public JLabel getBuffer() {
		return buffer;
	}
	public void setBuffer(JLabel buffer) {
		this.buffer = buffer;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

}
