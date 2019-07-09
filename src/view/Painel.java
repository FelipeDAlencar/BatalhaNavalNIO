package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Painel extends TelaGenerica {

	private static final long serialVersionUID = 1L;
	private JTextField tfdServer, tfdPort, tfdLogin;
	private JLabel fundo;
	private JButton btnStart, btnClose;

	public Painel(int largura, int altura) {
		super(largura, altura);
		
		this.tfdServer = new JTextField("localhost");
		this.tfdServer.setBounds(320, 120, 300, 30);
		this.add(this.tfdServer);
		
		this.tfdPort = new JTextField("22222");
		this.tfdPort.setBounds(320, 180, 300, 30);
		this.add(this.tfdPort);
		
		this.tfdLogin = new JTextField();
		this.tfdLogin.setBounds(320, 240, 300, 30);
		this.add(this.tfdLogin);
		
		this.btnStart = new JButton();
		this.btnStart.setBounds(180, 340, 200, 60);
		this.btnStart.setContentAreaFilled(false);
		this.btnStart.setBorder(null);
		this.add(this.btnStart);

		this.btnClose = new JButton();
		this.btnClose.setBounds(460, 340, 200, 60);
		this.btnClose.setContentAreaFilled(false);
		this.btnClose.setBorder(null);
		this.add(this.btnClose);

		this.fundo = new JLabel(new ImageIcon(getClass()
				.getResource("/res/batalhanaval.jpg")));
		this.add(this.fundo);

		this.setVisible(true);
	}
	

	public JTextField getTfdServer() {
		return tfdServer;
	}

	public void setTfdServer(JTextField tfdServer) {
		this.tfdServer = tfdServer;
	}

	public JTextField getTfdPort() {
		return tfdPort;
	}

	public void setTfdPort(JTextField tfdPort) {
		this.tfdPort = tfdPort;
	}

	public JTextField getTfdLogin() {
		return tfdLogin;
	}

	public void setTfdLogin(JTextField tfdLogin) {
		this.tfdLogin = tfdLogin;
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(JButton btnStart) {
		this.btnStart = btnStart;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(JButton btnClose) {
		this.btnClose = btnClose;
	}



}
