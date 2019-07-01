package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.scene.layout.Border;
import model.Jogador;
import socket.Cliente;

public class TelaInicio extends JPanel{
	//Declarando tela com formulario 
 	private TelaConexao telaConexao;

 	//Declarando Panels para imagem de loading
	private JPanel panelLoading;
	private Container containerInformations;
	private JLabel imgLoading;

	//Declarando Objetos necessarios para o jogador
	private Cliente cliente;
	private Jogador jogador;
	
	public TelaInicio(Cliente cliente){
		this.cliente = cliente;
		
		//Instanciando Painel da Imagem
		this.panelLoading = new JPanel(new FlowLayout());
		
		//Instanciando Container da Imagem, Para consegir centralizar
		this.containerInformations = new Container();
		this.containerInformations.setLayout(new BoxLayout(this.containerInformations, BoxLayout.PAGE_AXIS));
		
		//Instanciando imagem
		this.imgLoading = new JLabel();
		this.imgLoading.setIcon(new ImageIcon("img/loading1.gif"));
		this.imgLoading.setVisible(false);
		
		//Iniciando a tela de coneção
		this.telaConexao = new TelaConexao(this, cliente);
		
		//adicionando imagem ao painel
		this.panelLoading.add(this.imgLoading);
		
		//adicionando paineis ao conteiner
		this.containerInformations.add(this.telaConexao, Component.CENTER_ALIGNMENT);
		this.containerInformations.add(this.panelLoading, Component.CENTER_ALIGNMENT);

		//adicionando container a tela
	    this.add(this.containerInformations, BorderLayout.SOUTH);
	    
	}
	
	// Getter and Setters
	public TelaConexao getTelaConexao() {
		return telaConexao;
	}
	public void setTelaConexao(TelaConexao telaConexao) {
		this.telaConexao = telaConexao;
	}
	public JLabel getBuffer() {
		return imgLoading;
	}
	public void setBuffer(JLabel buffer) {
		this.imgLoading = buffer;
	}

}
