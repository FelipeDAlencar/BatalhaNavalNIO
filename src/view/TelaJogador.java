package view;

import java.awt.*;
import javax.swing.*;

import model.Jogador;

public class TelaJogador {
	// Declara tela Principal
	private JPanel panelPlayer;
	// Declara Sub-Telas
	private JPanel telaImg;
	private JPanel telaMenu;
	private JPanel telaVida;
	private JPanel telaOutros;
	// Declara Sub-sub-Telas
	private JPanel caixaVida;
	private JPanel caixaNavios;
	// Declara Componentes
	private JLabel vidaText;
	private JLabel naviosText;
	// Declara Componentes
	private JLabel nome;
	private JLabel vida[];
	private JLabel navios[];
	private Jogador jogador;
	boolean status;
	private JLabel jogadorNome;
	// Construtor, recebe Jogador	
	public TelaJogador(Jogador jogador) {
		this.jogador = jogador;
		// Instancia Tela
		this.panelPlayer = new JPanel(new BorderLayout());
		// Instancia Sub-telas
		this.telaImg = new JPanel();
		this.telaMenu = new JPanel(new GridLayout(2,4));
		this.telaImg.setLayout(new BoxLayout(telaImg,BoxLayout.Y_AXIS));
		this.telaVida = new JPanel(new FlowLayout());
		this.telaOutros = new JPanel(new BorderLayout());
		// Instancia Sub-sub-Telas
		this.caixaVida = new JPanel(new GridLayout(1,15));
		this.caixaNavios = new JPanel(new GridLayout(1,5));

		this.nome = new JLabel("nome");
		this.nome.setFont(new Font("Verdana", Font.BOLD, 14));
		this.nome.setForeground(Color.RED);
		this.vidaText = new JLabel("Vida");
		this.vidaText.setFont(new Font("Verdana", Font.BOLD, 22));
		this.naviosText = new JLabel("Navios");
		this.naviosText.setFont(new Font("Verdana", Font.BOLD, 22));

		this.vida = new JLabel[15];
		for (int i =0; i<vida.length; i++){
			this.vida[i] = new JLabel(new ImageIcon("img/vida/vida1.jpg"));
			this.caixaVida.add(this.vida[i]);
		}
		this.navios = new JLabel[5];
		for (int i =0; i<navios.length; i++){
			this.navios[i] = new JLabel();
			this.caixaNavios.add(this.navios[i]);
		}
		
	    // Adicionando Componentes as Sub-Telas
		this.telaVida.add(this.vidaText);
		this.telaVida.add(this.caixaVida);

		// Adicionando Sub-telas a Telas
		this.telaMenu.add(this.telaVida);
		this.telaMenu.add(this.telaOutros);
		jogadorNome= new JLabel("Jogador: ");
		this.telaImg.add(jogadorNome);
		this.telaImg.add(this.nome);
		this.telaImg.add(this.nome);
		// Adicionando Telas a Tela Principal
		this.panelPlayer.add(telaMenu, BorderLayout.CENTER);
		this.panelPlayer.add(telaImg, BorderLayout.WEST);
	}
			
	// Getters and Setters
	public JPanel getPanelPlayer() {
		return panelPlayer;
	}
	public void setPanelPlayer(JPanel panelPlayer) {
		this.panelPlayer = panelPlayer;
	}
	public JPanel getTelaMenu() {
		return telaMenu;
	}
	public void setTelaMenu(JPanel telaMenu) {
		this.telaMenu = telaMenu;
	}
	public JPanel getTelaVida() {
		return telaVida;
	}
	public void setTelaVida(JPanel telaVida) {
		this.telaVida = telaVida;
	}
	public JPanel getTelaOutros() {
		return telaOutros;
	}
	public void setTelaOutros(JPanel telaOutros) {
		this.telaOutros = telaOutros;
	}
	public JPanel getCaixaVida() {
		return caixaVida;
	}
	public void setCaixaVida(JPanel caixaVida) {
		this.caixaVida = caixaVida;
	}
	public JPanel getCaixaNavios() {
		return caixaNavios;
	}
	public void setCaixaNavios(JPanel caixaNavios) {
		this.caixaNavios = caixaNavios;
	}
	public JLabel getVidaText() {
		return vidaText;
	}
	public void setVidaText(JLabel vidaText) {
		this.vidaText = vidaText;
	}
	public JLabel getNaviosText() {
		return naviosText;
	}
	public void setNaviosText(JLabel naviosText) {
		this.naviosText = naviosText;
	}

	public JLabel getNome() {
		return nome;
	}
	public void setNome(JLabel nome) {
		this.nome = nome;
	}
	public JLabel[] getVida() {
		return vida;
	}
	public void setVida(JLabel[] vida) {
		this.vida = vida;
	}
	public JLabel[] getNavios() {
		return navios;
	}
	public void setNavios(JLabel[] navios) {
		this.navios = navios;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
}
