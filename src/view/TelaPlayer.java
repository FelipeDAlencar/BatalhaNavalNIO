package view;

import java.awt.*;

import javax.swing.*;

import model.Jogador;

public class TelaPlayer extends JPanel{
	// Declara Sub-Telas
	private JPanel telaImg;
	private JPanel telaVida;
	
	// Declara Sub-sub-Telas
	private JPanel caixaVida;
	// Declara Componentes
	private JLabel vidaText;
	private JLabel totalText;
	// Declara Componentes
	private JLabel img;
	private JLabel nome;

	// Construtor, recebe Jogador	
	public TelaPlayer(Jogador player) {
		this.setLayout(new BorderLayout());
	
		// Instancia Sub-telas
		this.telaImg = new JPanel();
		this.telaImg.setLayout(new BoxLayout(telaImg,BoxLayout.Y_AXIS));
		this.telaVida = new JPanel(new FlowLayout());

		// Instancia Sub-sub-Telas
		this.caixaVida = new JPanel(new GridLayout(1,15));
		
		// Instancia Componentes (Labels)
		this.nome = new JLabel("nome");
		this.nome.setFont(new Font("Verdana", Font.BOLD, 14));
		this.nome.setForeground(Color.RED);
		this.vidaText = new JLabel("Vida");
		this.vidaText.setFont(new Font("Verdana", Font.BOLD, 16));

		// Pega informacoes do Jogador
		this.getNome().setText(player.getNome());

		// Adicionando Componentes as Sub-Telas
		this.telaVida.add(this.vidaText);
		this.telaVida.add(this.caixaVida);
		this.telaImg.add(this.img);
		this.telaImg.add(this.nome);
		// Adicionando Telas a Tela Principal
		this.add(telaImg, BorderLayout.WEST);
	}
	// Getters and Setters

	public JPanel getTelaImg() {
		return telaImg;
	}
	public void setTelaImg(JPanel telaImg) {
		this.telaImg = telaImg;
	}

	public JPanel getTelaVida() {
		return telaVida;
	}
	public void setTelaVida(JPanel telaVida) {
		this.telaVida = telaVida;
	}

	public JPanel getCaixaVida() {
		return caixaVida;
	}
	public void setCaixaVida(JPanel caixaVida) {
		this.caixaVida = caixaVida;
	}

	public JLabel getVidaText() {
		return vidaText;
	}
	public void setVidaText(JLabel vidaText) {
		this.vidaText = vidaText;
	}

	public JLabel getTotalText() {
		return totalText;
	}
	public void setTotalText(JLabel totalText) {
		this.totalText = totalText;
	}
	public JLabel getImg() {
		return img;
	}
	public void setImg(JLabel img) {
		this.img = img;
	}
	public JLabel getNome() {
		return nome;
	}
	public void setNome(JLabel nome) {
		this.nome = nome;
	}
}
