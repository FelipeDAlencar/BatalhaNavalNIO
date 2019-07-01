package app;

import java.awt.CardLayout;
import java.awt.Container;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.Jogador;
import socket.Cliente;
import socket.Servidor_NIO;
import view.TelaConsole;
import view.TelaInicio;
import view.TelaPrincipal;
import view.ThreadMonitoraTelas;


public class Main {

	public static void main(String args[]) throws UnknownHostException,	IOException, InterruptedException {
	

		JFrame frame = new JFrame("Batalha Naval");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
		// Cria e instancia Classe de Comunicacao de Dados 
		Cliente cliente = new Cliente();
		// Cria o Card de Telas
		JPanel cards;
		cards = new JPanel(new CardLayout());		
		// Cria Jogador
		Jogador jogador = new Jogador();				
		// Cria Telas do Jogo
		TelaInicio telaInicio = new TelaInicio(cliente);		
		TelaPrincipal telaPrincipal = new TelaPrincipal(cliente, jogador);	
		TelaConsole telaConsole = new TelaConsole(cards, telaPrincipal);
		// Adiciona Telas do Jogo ao Card De Telas
		cards.add(telaInicio.getTelaInicio(), "1");
		cards.add(telaConsole.getTelaConsole(), "2");
		cards.add(telaPrincipal.getTelaPrincipal(), "3");        


		// Criando e instanciando container e adicionando Card de Telas ao Container   
		Container cont = frame.getContentPane();
		cont.add(cards);
		// Tamanho e Visivel
		frame.setSize(1000, 660);
		frame.setVisible(true);

		// Aponta o processador para thread.
		ExecutorService service = Executors.newFixedThreadPool(1);
		// Inicializa Thread responsavel por verificar Rodadas e controlar as Sub-Telas quando necessario.
		service.submit(new ThreadMonitoraTelas(jogador, cliente, cards, telaPrincipal.getTelaJogador(),
				telaPrincipal.getTelaTabuleiro(), telaPrincipal.getTelaNavios(), telaPrincipal.getTelaChat(),
				telaInicio.getTelaConexao(),  telaInicio));
		
		

	}
}
