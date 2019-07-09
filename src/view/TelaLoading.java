package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TelaLoading extends TelaGenerica{
	

	private static final long serialVersionUID = 1L;
	
	private JLabel loading;
	
	public TelaLoading(int largura, int altura) {
		super(largura, altura);
		
		this.setUndecorated(true);
		
		this.loading = new JLabel(new ImageIcon(getClass()
				.getResource("/res/loading.gif")));
		this.add(this.loading);
		
	}
	

}
