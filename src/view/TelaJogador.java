package view;

import java.awt.*;
import javax.swing.*;

import model.Jogador;

public class TelaJogador extends JPanel{
	// Declara Sub-Telas
	private JPanel panelImg;
	private JPanel panelMenu;
	private JPanel panelLife;
	private JPanel panelMyLife;
	private JPanel panelLifeOpponent;
	private JPanel panelExtras;
	// Declara Sub-sub-Telas
	private JPanel boxLife;
	private JPanel boxLifeOpponent;
	private JPanel boxShip;
	// Declara Componentes
	private JLabel labelLife;
	private JLabel labelLifeOpponet;
	// Declara Componentes
	private JLabel labelWaint;
	private JLabel life[];
	private JLabel lifeOpponent[];
	private JLabel ship[];
	private Jogador player;
	boolean status;
	private JLabel labelPlayerName;
	// Construtor, recebe Jogador	
	public TelaJogador(Jogador player) {
		this.player = player;
		// Instancia Tela
		this.setLayout(new BorderLayout());
		// Instancia Sub-telas
		this.panelImg = new JPanel();
		this.panelMenu = new JPanel(new GridLayout(2,4));
		this.panelImg.setLayout(new BoxLayout(panelImg,BoxLayout.Y_AXIS));
		this.panelLife = new JPanel(new GridLayout(2,1));
		this.panelMyLife = new JPanel(new FlowLayout());
		this.panelLifeOpponent = new JPanel(new FlowLayout());
		this.panelExtras = new JPanel(new BorderLayout());
		// Instancia Sub-sub-Telas
		this.boxLife = new JPanel(new GridLayout(1,15));
		this.boxLifeOpponent = new JPanel(new GridLayout(1,15));
		this.boxShip = new JPanel(new GridLayout(1,5));

		this.labelWaint = new JLabel("Agardando a jogada do oponente...");
		this.labelLife = new JLabel("Sua Vida: ");
		this.labelLife.setFont(new Font("Roboto", Font.BOLD, 10));
		this.labelLifeOpponet = new JLabel("Vida do Oponente: ");
		this.labelLifeOpponet.setFont(new Font("Roboto", Font.BOLD, 10));
		
		//adicionando icone vida do jogador
		this.life = new JLabel[15];
		for (int i =0; i<life.length; i++){
			this.life[i] = new JLabel(new ImageIcon("img/vida/life1.jpg"));
			this.boxLife.add(this.life[i]);
		}
		
		//Adicionando icone vida do inimigo
		this.lifeOpponent = new JLabel[15];
		for (int i =0; i<lifeOpponent.length; i++){
			this.lifeOpponent[i] = new JLabel(new ImageIcon("img/vida/life2.jpg"));
			this.boxLifeOpponent.add(this.lifeOpponent[i]);
		}
		
		
		this.ship = new JLabel[5];
		for (int i =0; i<ship.length; i++){
			this.ship[i] = new JLabel();
			this.boxShip.add(this.ship[i]);
		}
		
	    // Adicionando Componentes as Sub-Telas
		this.panelMyLife.add(this.labelLife);
		this.panelMyLife.add(this.boxLife);
		
		this.panelLifeOpponent.add(this.labelLifeOpponet);
		this.panelLifeOpponent.add(this.boxLifeOpponent);
		
		this.panelLife.add(panelMyLife);
		this.panelLife.add(panelLifeOpponent);

		// Adicionando Sub-telas a Telas
		this.panelMenu.add(this.panelLife);
		this.panelMenu.add(this.panelExtras);
		this.labelPlayerName= new JLabel("Jogador: ");
		this.panelImg.add(labelPlayerName);
		this.panelImg.add(this.labelPlayerName);
		this.panelImg.add(this.labelPlayerName);
		
		// Adicionando Telas a Tela Principal
		this.add(panelMenu, BorderLayout.NORTH);
		this.add(panelImg, BorderLayout.WEST);
	}
	public JPanel getPanelImg() {
		return panelImg;
	}
	public void setPanelImg(JPanel panelImg) {
		this.panelImg = panelImg;
	}
	public JPanel getPanelMenu() {
		return panelMenu;
	}
	public void setPanelMenu(JPanel panelMenu) {
		this.panelMenu = panelMenu;
	}
	public JPanel getPanelLife() {
		return panelLife;
	}
	public void setPanelLife(JPanel panelLife) {
		this.panelLife = panelLife;
	}
	public JPanel getPanelExtras() {
		return panelExtras;
	}
	public void setPanelExtras(JPanel panelExtras) {
		this.panelExtras = panelExtras;
	}
	public JPanel getBoxLife() {
		return boxLife;
	}
	public void setBoxLife(JPanel boxLife) {
		this.boxLife = boxLife;
	}
	public JPanel getBoxShip() {
		return boxShip;
	}
	public void setBoxShip(JPanel boxShip) {
		this.boxShip = boxShip;
	}
	public JLabel getLabelLife() {
		return labelLife;
	}
	public void setLabelLife(JLabel labelLife) {
		this.labelLife = labelLife;
	}
	public JLabel[] getLife() {
		return life;
	}
	public void setLife(JLabel[] life) {
		this.life = life;
	}
	public JLabel[] getShip() {
		return ship;
	}
	public void setShip(JLabel[] ship) {
		this.ship = ship;
	}
	public Jogador getPlayer() {
		return player;
	}
	public void setPlayer(Jogador player) {
		this.player = player;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public JLabel getLabelPlayerName() {
		return labelPlayerName;
	}
	public void setLabelPlayerName(JLabel labelPlayerName) {
		this.labelPlayerName = labelPlayerName;
	}

	
	
}
