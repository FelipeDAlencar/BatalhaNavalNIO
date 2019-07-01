package view;

import java.awt.*;
import javax.swing.*;

import model.Jogador;
import socket.Cliente;

public class TelaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	//Declarando telas jogo
	private JPanel telaPrincipal;
	private TelaJogador telaJogador;
	private TelaTabuleiro telaTabuleiro;
	private TelaNavios telaNavios;
	private TelaChat telaChat;
	
	//Declarando jogador
	private Cliente cliente;
	private Jogador jogador;

	public TelaPrincipal(Cliente cliente, Jogador jogador) {
		
		try {			
			this.telaPrincipal = new JPanel();
			this.telaPrincipal.setLayout(new BorderLayout());
			
			this.cliente = cliente;
			
			//Instanciando telas
			this.telaJogador = new TelaJogador(jogador);
			this.telaTabuleiro = new TelaTabuleiro(this.cliente, jogador);
			this.telaNavios = new TelaNavios(jogador);
			this.telaChat = new TelaChat();
			
			// Adicionando telas a tela principal
			this.telaPrincipal.add(telaJogador, BorderLayout.PAGE_START);
			this.telaPrincipal.add(telaTabuleiro.getTabuleiro1(), BorderLayout.LINE_START);
			this.telaPrincipal.add(telaTabuleiro.getTabuleiro2(), BorderLayout.LINE_END);
			this.telaPrincipal.add(telaNavios.getCards(), BorderLayout.CENTER);
			this.telaPrincipal.add(telaChat, BorderLayout.SOUTH);
			
		    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
		} catch (Exception exception) {
			System.out.println(exception);
			exception.printStackTrace();
		}
	}
	
	// Getter and Setters
	public JPanel getTelaPrincipal() {
		return telaPrincipal;
	}
	public void setTelaPrincipal(JPanel telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
	}
	public TelaJogador getTelaJogador() {
		return telaJogador;
	}
	public void setTelaJogador(TelaJogador telaPlayer) {
		this.telaJogador = telaPlayer;
	}
	public TelaTabuleiro getTelaTabuleiro() {
		return telaTabuleiro;
	}
	public void setTelaTabuleiro(TelaTabuleiro telaTabuleiro) {
		this.telaTabuleiro = telaTabuleiro;
	}
	public TelaNavios getTelaNavios() {
		return telaNavios;
	}
	public void setTelaNavios(TelaNavios telaNavios) {
		this.telaNavios = telaNavios;
	}
	public TelaChat getTelaChat() {
		return telaChat;
	}
	public void setTelaChat(TelaChat telaChat) {
		this.telaChat = telaChat;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador player) {
		this.jogador = player;
	}
	
}