package view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TelaConsole {
	// Declara Telas
	private JPanel panelConsole;
	private Container telaConsole;
	private JPanel cards;
	// Declara Componentes
	//private JButton player;
	private JButton multiplayer;

	private TelaPrincipal telaPrincipal;
	// Construtor, recebe tela Principal e 
	public TelaConsole(JPanel cards, TelaPrincipal telaPrincipal){
		this.telaPrincipal = telaPrincipal;
		// Recebe Cards
		this.cards = cards;
		// Instancia Tela Principal
		this.panelConsole = new JPanel();
		this.panelConsole.setLayout(new GridLayout(3,1));
		// Instancia Container
		this.telaConsole = new Container();
		this.telaConsole.setLayout(new BoxLayout(telaConsole, BoxLayout.PAGE_AXIS));
		// Instancia Componentes - Botes (Player One, Multiplayer e Ranking)
//		this.player = new JButton("Desafiar Computador");
//		this.player.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.multiplayer = new JButton("Desafiar Jogador");
		this.multiplayer.setAlignmentX(Component.CENTER_ALIGNMENT);
	
		// Cria e instancia Manipulador de Acoes
		ButtonHandler handler = new ButtonHandler();
	//	this.player.addActionListener(handler);
		this.multiplayer.addActionListener(handler);

		// Adiciona Componentes a Tela
//		this.telaConsole.add(this.player);
		this.telaConsole.add(this.multiplayer);

		// Adiciona Sub-tela a Tela
		this.panelConsole.add(telaConsole);
	}
	// Manipulador de Acoes Botoes 
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
//				if (event.getSource() == player){
//					CardLayout cl = (CardLayout)(cards.getLayout());
//			        cl.show(cards, "3");
//				}
				if (event.getSource() == multiplayer){
					CardLayout cl = (CardLayout)(cards.getLayout());
			        cl.show(cards, "3");
			        telaPrincipal.getTelaJogador().setStatus(true);
				}
		}
	}
	// Getters and Setters
	public Container getTelaConsole() {
		return telaConsole;
	}
	public void setTelaConsole(Container telaInicio) {
		this.telaConsole = telaInicio;
	}
	public JPanel getPanelConsole() {
		return panelConsole;
	}
	public void setPanelConsole(JPanel panelConsole) {
		this.panelConsole = panelConsole;
	}
}
