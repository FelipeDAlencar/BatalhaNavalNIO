package view;

import java.awt.*;
import javax.swing.*;
import model.Cliente;
import model.Jogador;

public class TelaTabuleiro extends JPanel {

	private static final long serialVersionUID = 1L;
	// Declara Telas (JPanel)
	private JPanel tabuleiro1;
	private JPanel tabuleiro2;
	
	private JButton pronto;
	private JLabel loading;
	

	//declara casas
	private Botao buttonsTab1[] = new Botao[225]; // = new JButton[100]; // Botoes 0 - 99
	private Botao buttonsTab2[] = new Botao[225]; // = new JButton[100]; // Botoes 0 - 99
	private boolean posicao = false;

	private Jogador jogador;
	private Cliente cliente;
	private boolean escolha = false;
	private JPanel cards;
	private JPanel tabuleiroInimigo;
	private int bombaTipo = 1;
	// Contrutor da Tela
	public TelaTabuleiro() { // Nossa classe (Importada de Jogo.Jogador)
		this.setLayout(null);
		
		this.jogador = new Jogador();
		
		this.tabuleiro1 = new JPanel(new GridLayout(15, 1, 0, 0));
		this.tabuleiro1.setBounds(20,60,400,400);
		
		this.cards = new JPanel(new CardLayout());
		
		// Cria novas Telas (JPanel`s)
		this.tabuleiroInimigo = new JPanel(new GridLayout(15, 1, 0, 0));		
		
		this.cards.add(this.tabuleiroInimigo, "1");
        
		//instanciando tabuleiro 2
		this.tabuleiro2=cards;
		this.tabuleiroInimigo.setBounds(480,60,400,400);
		this.tabuleiroInimigo.setVisible(false);
        
        this.pronto = new JButton("Estou pronto");
    	this.pronto.setBounds(110,10,210,30);
    	this.add(pronto);
        
		// Adicionando as casa do tabuleiro
        
		for (int contador = 0; contador < 225; contador++) {
			
			// Criando Tabuleiro1
			this.buttonsTab1[contador] = new Botao();
			this.buttonsTab1[contador].setBackground(new Color(0,0,100));
			
			// Criando Tabuleiro2 (Player 2)			
			this.buttonsTab2[contador] = new Botao();
			this.buttonsTab2[contador].setBackground(new Color(0,0,100));
			
			// Adiciona objetos (Botoes) na Tela (JPanel)
			this.tabuleiro1.add(this.buttonsTab1[contador]);
			this.tabuleiroInimigo.add(this.buttonsTab2[contador]);
		}
		
		// Visivel , Invisivel
		this.cards.setVisible(true);
		
		this.loading = new JLabel(new ImageIcon(getClass()
				.getResource("/res/loading2.gif")));
		this.loading.setBounds(600,15,200,30);
		loading.setVisible(false);
		this.add(this.loading);
		
		add(tabuleiro1);
		add(tabuleiroInimigo);
		setBackground(new Color(100,120,120));
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


	public JButton getPronto() {
		return pronto;
	}


	public void setPronto(JButton pronto) {
		this.pronto = pronto;
	}



	public JLabel getLoading() {
		return loading;
	}



	public void setLoading(JLabel loading) {
		this.loading = loading;
	}
	
	
	
}