package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaPrincipal extends TelaGenerica{

	private static final long serialVersionUID = 1L;

	private JLabel namePlayer1, namePlayer2, lifePlayer1, lifePlayer2;
	private JPanel boxLife1, boxLife2;
	private TelaTabuleiro tab;
	
	public TelaPrincipal(int altura, int largura) {
		super(altura, largura);
		this.setLayout(null);
		
		this.namePlayer1 = new JLabel("Seu Nome: Name Player");
		this.namePlayer1.setBounds(20,10,300,60);
		this.add(this.namePlayer1);
		
		this.namePlayer2 = new JLabel("Nome do Inimigo: Name Player");
		this.namePlayer2.setBounds(700,10,300,60);
		this.add(this.namePlayer2);
		
		
		this.lifePlayer1 = new JLabel("Vida P1 |");
		this.lifePlayer1.setBounds(380,25,300,60);
		this.add(this.lifePlayer1);
		
		this.lifePlayer2 = new JLabel("| Vida P2");
		this.lifePlayer2.setBounds(440,25,300,60);
		this.add(this.lifePlayer2);
		
		this.boxLife1 = new JPanel();
		this.boxLife1.setBackground(new Color(10,255,15));
		this.boxLife1.setBounds(205,45,160,20);
		this.add(this.boxLife1);
		
		this.boxLife2 = new JPanel();
		this.boxLife2.setBackground(new Color(255,10,15));
		this.boxLife2.setBounds(510,45,160,20);
		this.add(this.boxLife2);
		
		this.tab = new TelaTabuleiro();
		this.tab.setBounds(50,100,900,470);
		this.add(this.tab);

	}

	public JLabel getNamePlayer1() {
		return namePlayer1;
	}

	public void setNamePlayer1(JLabel namePlayer1) {
		this.namePlayer1 = namePlayer1;
	}

	public JLabel getNamePlayer2() {
		return namePlayer2;
	}

	public void setNamePlayer2(JLabel namePlayer2) {
		this.namePlayer2 = namePlayer2;
	}


	public JLabel getLifePlayer1() {
		return lifePlayer1;
	}

	public void setLifePlayer1(JLabel lifePlayer1) {
		this.lifePlayer1 = lifePlayer1;
	}

	public JLabel getLifePlayer2() {
		return lifePlayer2;
	}

	public void setLifePlayer2(JLabel lifePlayer2) {
		this.lifePlayer2 = lifePlayer2;
	}

	public JPanel getBoxLife1() {
		return boxLife1;
	}

	public void setBoxLife1(JPanel boxLife1) {
		this.boxLife1 = boxLife1;
	}

	public JPanel getBoxLife2() {
		return boxLife2;
	}

	public void setBoxLife2(JPanel boxLife2) {
		this.boxLife2 = boxLife2;
	}

	public TelaTabuleiro getTab() {
		return tab;
	}

	public void setTab(TelaTabuleiro tab) {
		this.tab = tab;
	}


	
	
}
