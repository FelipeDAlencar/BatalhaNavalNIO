package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class TelaChat extends JPanel{
 
	// Declara Componentes
	private JTextArea textAreaConversas;
	private JScrollPane scrollPane;
	private JTextField textFieldChat;

	public TelaChat(){
		this.setLayout(new GridBagLayout());
		// Cria nova Tela (JPanel)
		setTextFieldChat(new JTextField(20));
		setTextAreaConversas(new JTextArea(5, 20));
		getTextAreaConversas().setEditable(false);
		scrollPane = new JScrollPane(textAreaConversas);

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(scrollPane, c);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		this.add(textFieldChat, c);
//		getPanelChat().add(buttonChat);
	}
//	// Manipulador de Acoes - Botoes (BottonsChat)
//	private class ButtonHandler implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			try {
//				// Se botao for apertado
//				if (e.getSource() == getButtonChat()) {
//					// Envia para o servidor
//					cliente.getSaida().println(getTextFieldChat().getText());
//					System.out.println("Transporte de mensagens: "+getTextFieldChat().getText());
//				}
//				if (e.getSource() == getTextFieldChat()) {
//					cliente.getSaida().println(getTextFieldChat().getText());
//				}
//			} catch (Exception exception) {
//				JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto");
//				exception.printStackTrace();
//			}
//		}
//	}
	public JTextArea getTextAreaConversas() {
		return textAreaConversas;
	}
	public void setTextAreaConversas(JTextArea textAreaConversas) {
		this.textAreaConversas = textAreaConversas;
	}
	public void setTextAreaConversas(String texto) {
		this.textAreaConversas.setText(texto);
	}	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	public JTextField getTextFieldChat() {
		return textFieldChat;
	}
	public void setTextFieldChat(JTextField textFieldChat) {
		this.textFieldChat = textFieldChat;
	}
//	public JButton getButtonChat() {
//		return buttonChat;
//	}
//	public void setButtonChat(JButton buttonChat) {
//		this.buttonChat = buttonChat;
//	}

}
