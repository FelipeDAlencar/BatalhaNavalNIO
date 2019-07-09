package view;

public class Botao extends javax.swing.JButton {  

	private static final long serialVersionUID = 1L;
	private boolean status = false;
	private int color;
	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
	

   
}