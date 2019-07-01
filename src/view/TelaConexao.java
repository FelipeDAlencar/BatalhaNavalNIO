package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.*;
import socket.Cliente;

public class TelaConexao extends JPanel{
	// Declara Telas
	private JPanel cont;

	// Declaração dos componentes para conexão com servidor
	private JLabel labelServer;
	private JLabel labelPort;
	private JLabel labelNamePlayer;
	private JButton btnConnect;
	private JButton btnClose;
	private JTextField tfdHost;
	private JTextField tfdPort;
	private JTextField tfdNamePlayer;

	// Declaração da Classe de Comunicacao de Dados
	private Cliente cliente;

	//Declara Controlador dos botões
	ButtonConexaoHandler handlerCon;

	// Variaveis auxiliares
	private String host;
	private int porta;
	private PrintStream saida;
	private String comandoSaida = "";
	private boolean status = false;

	// Construtor da Tela
	public TelaConexao(JPanel cont, Cliente cliente) {
		// Recebe dados por parametro
		this.cont = cont;
		this.cliente = cliente;

		this.setLayout(new GridLayout(10,1));

		// instanciando componentes do formulário
		this.labelServer = new JLabel("Servidor:");
		this.tfdHost = new JTextField("localhost");
		this.labelPort = new JLabel("Porta:");
		this.tfdPort = new JTextField("22222");
		this.labelNamePlayer = new JLabel("Nome do Jogador:                 ");
		this.tfdNamePlayer = new JTextField();


		//Instanciando botoes
		this.btnConnect = new JButton("Iniciar");
		this.btnClose = new JButton("Cancelar");

		// Adiciona Componentes de formulário
		this.add(this.labelServer);
		this.add(this.tfdHost);
		this.add(this.labelPort);
		this.add(this.tfdPort);
		this.add(this.labelNamePlayer);
		this.add(this.tfdNamePlayer);
		this.add(new JLabel());
		this.add(this.btnConnect);




		//Adicionando ações aos botões
		this.handlerCon = new ButtonConexaoHandler(this);
		this.btnConnect.addActionListener(this.handlerCon);
		this.btnClose.addActionListener(this.handlerCon);


	}
	
	//Controlador dos botões
	private class ButtonConexaoHandler implements ActionListener {
		JPanel panelConexao;
		public ButtonConexaoHandler(JPanel panelConexao) {
			this.panelConexao = panelConexao;
		}

		public void actionPerformed(ActionEvent event) {
			//Se for clicado em iniciar
			if (event.getSource() == btnConnect) {
				//Obtem dados para conexão
				host = getTextFieldHost().getText();
				porta = Integer.parseInt(getTextFieldPorta().getText());

				cliente.setPorta(porta);
				cliente.setHost(host);

				// Inicia a conexão do jogador
				try {
					cliente.setStatus(cliente.conecta());
				} catch (Exception e) {
					System.out.println("Erro ao iniciar comunicacao");
					e.printStackTrace();

				} 

				//Invertendo Botões
				panelConexao.remove(btnConnect);
				panelConexao.add(btnClose);
			} 


			//Se for clicado em cancelar
			else{
				// Cliente é desconectado do servidor 
				//cliente.desconecta();

				//inverte botões
				panelConexao.remove(btnClose);
				panelConexao.add(btnConnect);
				setStatus(false); 
			}
		}
	}


	public JLabel getlabelPort() {
		return labelPort;
	}
	public void setlabelPort(JLabel labelPort) {
		this. labelPort = labelPort;
	}
	public JLabel getServerLabel() {
		return labelServer;
	}
	public void setServerLabel(JLabel server) {
		this.labelServer = server;
	}
	public JButton getConectaServer() {
		return btnConnect;
	}
	public void setConectaServer(JButton conectaServer) {
		this.btnConnect = conectaServer;
	}
	public JButton getCloseServer() {
		return btnClose;
	}
	public void setCloseServer(JButton closeServer) {
		this.btnClose = closeServer;
	}
	public JTextField getTextFieldHost() {
		return tfdHost;
	}
	public void setTextFieldHost(JTextField textFieldHost) {
		this.tfdHost = textFieldHost;
	}
	public JTextField getTextFieldPorta() {
		return tfdPort;
	}
	public void setTextFieldPorta(JTextField textFieldPorta) {
		this.tfdPort = textFieldPorta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cli) {
		this.cliente = cli;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	public PrintStream getSaida() {
		return saida;
	}
	public void setSaida(PrintStream saida) {
		this.saida = saida;
	}
	public String getComandoSaida() {
		return comandoSaida;
	}
	public void setComandoSaida(String comandoSaida) {
		this.comandoSaida = comandoSaida;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public JLabel getLoginLabel() {
		return labelNamePlayer;
	}
	public void setLoginLabel(JLabel loginLabel) {
		this.labelNamePlayer = loginLabel;
	}

	public JTextField getTextFieldLogin() {
		return tfdNamePlayer;
	}
	public void setTextFieldLogin(JTextField textFieldLogin) {
		this.tfdNamePlayer = textFieldLogin;
	}
	public JPanel getCont() {
		return cont;
	}
	public void setCont(JPanel cont) {
		this.cont = cont;
	}
}
