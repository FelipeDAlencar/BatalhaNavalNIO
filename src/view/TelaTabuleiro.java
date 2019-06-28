package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import socket.Cliente;
import model.Jogador;
import model.Posicao;

public class TelaTabuleiro implements ActionListener {
	// Declara Telas (JPanel`s)
	private JPanel tabuleiro1;
	private JPanel tabuleiro2;

	private Botao buttonsTab1[] = new Botao[100]; // = new JButton[100]; // Botoes 0 - 99
	private Botao buttonsTab2[] = new Botao[100]; // = new JButton[100]; // Botoes 0 - 99
	private boolean posicao = false;

	private Jogador jogador;
	private Cliente cliente;
	private boolean escolha = false;
	private JPanel cards;
	private JPanel tabuleiroInimigo;
	private int bombaTipo = 1;
	// Contrutor da Tela
	public TelaTabuleiro(Cliente cliente, Jogador jogador) { // Nossa classe (Importada de Jogo.Jogador)
		this.jogador = jogador;
		this.cliente = cliente;
		setTabuleiro1(new JPanel(new GridLayout(10, 1, 0, 0)));
		this.cards = new JPanel(new CardLayout());
		// Cria novas Telas (JPanel`s)
		this.tabuleiroInimigo = new JPanel(new GridLayout(10, 1, 0, 0));
		this.tabuleiroInimigo.setVisible(false);
		
		
		this.cards.add(this.tabuleiroInimigo, "1");
//        this.cards.add(this.animacao, "2");
        setTabuleiro2(cards);
		// Adiciona os botoes (JButton) no vetor de Botoes
		for (int contador = 0; contador < 100; contador++) {
			// Criando Tabuleiro1 (Player 1)
			getButtonsTab1()[contador] = new Botao();
			// Criando Tabuleiro2 (Player 2)			
			getButtonsTab2()[contador] = new Botao();
			// Adicionando ao Manipulador de Eventos
			getButtonsTab1()[contador].addActionListener(this);
			getButtonsTab2()[contador].addActionListener(this);
			// Adiciona objetos (Botoes) na Tela (JPanel)
			getTabuleiro1().add(getButtonsTab1()[contador]);
			this.tabuleiroInimigo.add(getButtonsTab2()[contador]);
		}
		// Visivel , Invisivel
		this.cards.setVisible(false);
	}
	// Manipulador de Acoes - Botoes (0-99) de ambos tabuleiros (Player 1 e 2)
	public void actionPerformed(ActionEvent e) {
		try {
			int auxContador = 0;
			for (int contador = 0; contador < 100; contador++) { // Se "apertar" no Tabuleiro (Player 1)
				// PRIMEIRA ETAPA - Escolhendo a Posicao para cada Peca/Navio
				if (e.getSource() == getButtonsTab1()[contador]) {
					
					int aux; 		// Valor auxiliar para estabelecer Posicoes
					if (posicao) 	// TRUE: Vertical // FALSE: Horizontal
						 aux = 1;
					else
						 aux = 10;

						// Cria posicao do Heroi levando em conta numero de Rodadas e Tamanho/Posicoes do Navio
						if (jogador.getNumEscolhas() == 1) {
							auxContador = verificaPosicao(1, contador, aux, posicao);
							jogador.getNavios().get(0).getPosicao()[0] = new Posicao(auxContador);
							jogador.getNavios().get(0).getPosicao()[1] = new Posicao(auxContador);
							jogador.getNavios().get(0).setVivo(true);
						}
						if (jogador.getNumEscolhas() == 2) {
						
							auxContador = verificaPosicao(2, contador, aux, posicao);
							jogador.getNavios().get(1).getPosicao()[0] = new Posicao(auxContador);
							jogador.getNavios().get(1).getPosicao()[1] = new Posicao(auxContador);
							jogador.getNavios().get(1).setVivo(true);
						}
						if (jogador.getNumEscolhas() == 3) {
							auxContador = verificaPosicao(3, contador, aux, posicao);
							jogador.getNavios().get(2).getPosicao()[0] = new Posicao(auxContador-aux);
							jogador.getNavios().get(2).getPosicao()[1] = new Posicao(auxContador);
							jogador.getNavios().get(2).getPosicao()[2] = new Posicao(auxContador);
							jogador.getNavios().get(2).setVivo(true);
						}
						if (jogador.getNumEscolhas() == 4) {
							auxContador = verificaPosicao(4, contador, aux, posicao);
							jogador.getNavios().get(3).getPosicao()[0] = new Posicao(auxContador-aux);
							jogador.getNavios().get(3).getPosicao()[1] = new Posicao(auxContador);
							jogador.getNavios().get(3).getPosicao()[2] = new Posicao(auxContador);
							jogador.getNavios().get(3).getPosicao()[3] = new Posicao(auxContador);
							jogador.getNavios().get(3).setVivo(true);
						}
						if (jogador.getNumEscolhas() == 5) {
							auxContador = verificaPosicao(5, contador, aux, posicao);
							jogador.getNavios().get(4).getPosicao()[0] = new Posicao(auxContador-aux);
							jogador.getNavios().get(4).getPosicao()[1] = new Posicao(auxContador);
							jogador.getNavios().get(4).getPosicao()[2] = new Posicao(auxContador+aux);
							jogador.getNavios().get(4).getPosicao()[3] = new Posicao(auxContador+aux);
							jogador.getNavios().get(4).setVivo(true);
						}
						if (jogador.getNumEscolhas() >= 6 ) {  // Mensagem de orientacao
							JOptionPane.showMessageDialog(null,"O jogo ja comecou, escolha a Posicao "
												+ "que deseja atacar no tabuleiro do Player 2");
						} 
						setEscolha(true); // Marca como escolhido
					
				} 
				if (e.getSource() == getButtonsTab2()[contador]) { // Se "apertar" no Tabuleiro Adversario
						if (jogador.isVez()) {
							// envia para o servidor 
							System.out.println("Jogador Ativado");
							cliente.enviarMensagemParaServidor("@"+contador+"+");
						}else {
							System.out.println("Jogador Não Ativado");
							JOptionPane.showMessageDialog(null,"Aguarde, ainda não é sua vez...");
						}
							
				}
					
			}
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto do Tabuleiro");
			exception.printStackTrace();
		}
	}
	public int verificaPosicao(int navio, int contador, int aux, boolean posicao){
		
		Posicao auxiliar = new Posicao(contador);
		int auxContador = contador;
		//System.out.println("contador: "+ contador);
		//System.out.println("x: "+auxiliar.getX() + " y: "+ auxiliar.getY());
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
		//System.out.println("novo contador: "+ auxContador);
		return auxContador;
	} 

	// Getters and Setters
	public JPanel getTabuleiro1() {
		return tabuleiro1;
	}
	public void setTabuleiro1(JPanel tabuleiro1) {
		this.tabuleiro1 = tabuleiro1;
	}
	public JPanel getTabuleiro2() {
		return tabuleiro2;
	}
	public void setTabuleiro2(JPanel tabuleiro2) {
		this.tabuleiro2 = tabuleiro2;
	}
	public Botao[] getButtonsTab1() {
		return buttonsTab1;
	}
	public void setButtonsTab1(Botao[] buttonsTab1) {
		this.buttonsTab1 = buttonsTab1;
	}
    public Botao[] getButtonsTab2() {
		return buttonsTab2;
	}
	public void setButtonsTab2(Botao[] buttonsTab2) {
		this.buttonsTab2 = buttonsTab2;
	}
	public boolean isPosicao() {
		return posicao;
	}
	public void setPosicao(boolean posicao) {
		this.posicao = posicao;
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public JPanel getCards() {
		return cards;
	}
	public void setCards(JPanel cards) {
		this.cards = cards;
	}
	public JPanel getTabuleiroInimigo() {
		return tabuleiroInimigo;
	}
	public void setTabuleiroInimigo(JPanel tabuleiroInimigo) {
		this.tabuleiroInimigo = tabuleiroInimigo;
	}

	public int getTipo() {
		return bombaTipo;
	}
	public void setTipo(int tipo) {
		this.bombaTipo = tipo;
	}

	public int getBombaTipo() {
		return bombaTipo;
	}
	public void setBombaTipo(int bombaTipo) {
		this.bombaTipo = bombaTipo;
	}
	
}