package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TelaEscolha extends TelaGenerica{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel submarino, encouracado, portaAvioes, hidroaviao, cruzador;
	private JLabel labelSubmarino, labelEncouracado, labelPortaAvioes; 
	private JLabel labelHidroaviao, labelCruzador;
	
	public TelaEscolha(int largura, int altura) {
		super(largura, altura);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(null);
		
		this.submarino = new JLabel(new ImageIcon(getClass().getResource("/res/submarino.png")));
		this.submarino.setBounds(65,40,40,40);
		this.add(this.submarino);
		
		this.labelSubmarino = new JLabel("Submarino: 4");
		this.labelSubmarino.setBounds(40,80,100,40);
		this.add(this.labelSubmarino);
		
		this.encouracado = new JLabel(new ImageIcon(getClass().getResource("/res/encouracado.png")));
		this.encouracado.setBounds(65,150,40,40);
		this.add(this.encouracado);
		
		this.labelEncouracado = new JLabel("Encouracado: 2");
		this.labelEncouracado.setBounds(30,210,150,40);
		this.add(this.labelEncouracado);
		
		this.portaAvioes = new JLabel(new ImageIcon(getClass().getResource("/res/portaavioes.png")));
		this.portaAvioes.setBounds(200,110,40,40);
		this.add(this.portaAvioes);
		
		this.labelPortaAvioes = new JLabel("Porta Aviões: 1");
		this.labelPortaAvioes.setBounds(170,150,150,40);
		this.add(this.labelPortaAvioes);
		
		this.hidroaviao = new JLabel(new ImageIcon(getClass().getResource("/res/hidroaviao.png")));
		this.hidroaviao.setBounds(330,40,40,40);
		this.add(this.hidroaviao);
		
		this.labelHidroaviao = new JLabel("Hidroavião: 5");
		this.labelHidroaviao.setBounds(300,80,150,40);
		this.add(this.labelHidroaviao);
		
		this.cruzador = new JLabel(new ImageIcon(getClass().getResource("/res/cruzador.png")));
		this.cruzador.setBounds(330,150,40,40);
		this.add(this.cruzador);
		
		this.labelCruzador = new JLabel("Cruzador: 3");
		this.labelCruzador.setBounds(310,210,150,40);
		this.add(this.labelCruzador);
		

	}

	public JLabel getSubmarino() {
		return submarino;
	}

	public void setSubmarino(JLabel submarino) {
		this.submarino = submarino;
	}

	public JLabel getEncouracado() {
		return encouracado;
	}

	public void setEncouracado(JLabel encouracado) {
		this.encouracado = encouracado;
	}

	public JLabel getPortaAvioes() {
		return portaAvioes;
	}

	public void setPortaAvioes(JLabel portaAvioes) {
		this.portaAvioes = portaAvioes;
	}

	public JLabel getHidroaviao() {
		return hidroaviao;
	}

	public void setHidroaviao(JLabel hidroaviao) {
		this.hidroaviao = hidroaviao;
	}

	public JLabel getCruzador() {
		return cruzador;
	}

	public void setCruzador(JLabel cruzador) {
		this.cruzador = cruzador;
	}

	public JLabel getLabelSubmarino() {
		return labelSubmarino;
	}

	public void setLabelSubmarino(JLabel labelSubmarino) {
		this.labelSubmarino = labelSubmarino;
	}

	public JLabel getLabelEncouracado() {
		return labelEncouracado;
	}

	public void setLabelEncouracado(JLabel labelEncouracado) {
		this.labelEncouracado = labelEncouracado;
	}

	public JLabel getLabelPortaAvioes() {
		return labelPortaAvioes;
	}

	public void setLabelPortaAvioes(JLabel labelPortaAvioes) {
		this.labelPortaAvioes = labelPortaAvioes;
	}

	public JLabel getLabelHidroaviao() {
		return labelHidroaviao;
	}

	public void setLabelHidroaviao(JLabel labelHidroaviao) {
		this.labelHidroaviao = labelHidroaviao;
	}

	public JLabel getLabelCruzador() {
		return labelCruzador;
	}

	public void setLabelCruzador(JLabel labelCruzador) {
		this.labelCruzador = labelCruzador;
	}
	
	
	
}
