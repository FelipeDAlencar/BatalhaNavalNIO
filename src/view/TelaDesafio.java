package view;

import java.util.List;

import javax.swing.JLabel;

public class TelaDesafio extends TelaGenerica{
	
	private static final long serialVersionUID = 1L;
	private List<JLabel> clientes;
	
	public TelaDesafio(int largura, int altura) {
		super(largura, altura);
		
	}

	public List<JLabel> getClientes() {
		return clientes;
	}

	public void setClientes(List<JLabel> clientes) {
		this.clientes = clientes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
