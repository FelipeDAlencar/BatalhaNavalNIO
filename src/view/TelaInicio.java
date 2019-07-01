package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Jogador;
import socket.Cliente;

public class TelaInicio {
	// Declara Classe Grafica
 	private TelaConexao telaConexao;

	private JPanel telaInicio;
	private JPanel telaBuffer;
	private Container telaInformacoes;
	private JLabel buffer;

	// Declara Classe de Comunicacao de Dados
	private Cliente cliente;
	private Jogador jogador;
	// Construtor, recebe a tela Principal, Cliente (Pacote de Comunicacao e o Card de delas)
	public TelaInicio(Cliente cliente){

		this.cliente = cliente;
		this.telaInicio = new JPanel();
		this.buffer = new JLabel();
		this.buffer.setIcon(new ImageIcon("img/buffer/buffer1.gif"));
		this.buffer.setVisible(false);
		
		this.telaBuffer = new JPanel(new FlowLayout());
		
		this.telaConexao = new TelaConexao(telaInicio, cliente);
		
		this.telaInformacoes = new Container();
		this.telaInformacoes.setLayout(new BoxLayout(telaInformacoes, BoxLayout.PAGE_AXIS));
	    
		this.telaInicio.add(this.telaConexao.getPanelConexao());
		
		this.telaBuffer.add(this.buffer);
		this.telaInformacoes.add(telaBuffer, Component.CENTER_ALIGNMENT);

	    this.telaInicio.add(telaInformacoes);
	    
	}
	// Getter and Setters
	public JPanel getTelaInicio() {
		return telaInicio;
	}
	public void setTelaInicio(JPanel telaInicio) {
		this.telaInicio = telaInicio;
	}
	// Getter and Setters
	public TelaConexao getTelaConexao() {
		return telaConexao;
	}
	public void setTelaConexao(TelaConexao telaConexao) {
		this.telaConexao = telaConexao;
	}
	public JLabel getBuffer() {
		return buffer;
	}
	public void setBuffer(JLabel buffer) {
		this.buffer = buffer;
	}

}
