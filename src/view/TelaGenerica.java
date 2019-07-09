package view;
import javax.swing.JFrame;

public abstract class TelaGenerica extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public TelaGenerica(int largura, int altura) {
		setSize(largura, altura);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

}
