package app;

import controller.Controller;
import view.Painel;
import view.TelaEscolha;
import view.TelaLoading;
import view.TelaPrincipal;

public class Main {

	public static void main(String[] args) {
		Painel painel = new Painel(840,500);
		TelaPrincipal telaPrincipal = new TelaPrincipal(1000,600);
		TelaLoading loading = new TelaLoading(150, 150);
		TelaEscolha telaEscolha = new TelaEscolha(450, 300);
		new Controller(painel, telaPrincipal, loading, telaEscolha);

		

	}

}
