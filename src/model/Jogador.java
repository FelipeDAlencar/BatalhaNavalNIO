package model;

import java.util.ArrayList;


public class Jogador {

	private int id = 0;
	private String nome;
	private int numEscolhas = 1;
	private ArrayList<Navio> navios = new ArrayList<Navio>();
	private int vida = 15;
	private int numeroNavios = 5;
	private int numeroConexoes= 0;

	// Auxiliares
	private int contadorDeNavio = 0;
	private int contadorPosicao = 0;
	private boolean vez = false;
	
	public Jogador(){
		this.criaNavios();
	}
	
	public Jogador(String nome, String senha) {
		this.nome = nome;
		this.criaNavios();
	}
	
	public Jogador(String nome){
		this.nome = nome;
		this.criaNavios();
	}

	public void criaNavios(){
		
		Posicao[] posicoesNavio1 = new Posicao[2];
		Navio Navio1 = new Navio("Barco", posicoesNavio1, 2);
		
		Posicao[] posicoesNavio2 = new Posicao[2];
		Navio Navio2 = new Navio("Costeiro", posicoesNavio2, 2);
		
		Posicao[] posicoesNavio3 = new Posicao[3];
		Navio Navio3 = new Navio("Destroyer", posicoesNavio3, 3);
		
		Posicao[] posicoesNavio4 = new Posicao[4];
		Navio Navio4 = new Navio("Cargueiro", posicoesNavio4, 4);
		
		Posicao[] posicoesNavio5 = new Posicao[4];
		Navio Navio5 = new Navio("Submarino", posicoesNavio5, 4);
		
		this.navios.add(Navio1);
		this.navios.add(Navio2);
		this.navios.add(Navio3);
		this.navios.add(Navio4);
		this.navios.add(Navio5);
	}
	
	public void addNavio(int posicao) {
		if (getContadorHeroi() <= 4){
			if (getContadorPosicao()==getNavios().get(getContadorHeroi()).getPosicao().length){
				setContadorHeroi(getContadorHeroi()+1);
				setContadorPosicao(0);
			}
			this.getNavios().get(getContadorHeroi()).getPosicao()[getContadorPosicao()] = new Posicao(posicao);
			setContadorPosicao(getContadorPosicao()+1);
			if  (getContadorHeroi() == 4 && getContadorPosicao() == 3){
				setVez(true);
			}
		}
		else {
				setContadorHeroi(0);
		}
	}	
	
	public boolean verificaPosicao(int posicao) {
		 for (int contadorHeroi = 0; contadorHeroi < 5; contadorHeroi++){
	        	for (int contadorPosicao = 0; contadorPosicao < this.getNavios().get(contadorHeroi).getPosicao().length; contadorPosicao++){
	        		if (this.getNavios().get(contadorHeroi).getPosicao()[contadorPosicao].getXY() == posicao){
						return true;
	        		}
	        	}
		 }
		return false;
	}
	
	public int verificaHeroi(int posicao) {
		int aux = 0; 
		for (int contadorHeroi = 0; contadorHeroi < 5; contadorHeroi++){
	        	for (int contadorPosicao = 0; contadorPosicao < this.getNavios().get(contadorHeroi).getPosicao().length; contadorPosicao++){
	        		if (this.getNavios().get(contadorHeroi).getPosicao()[contadorPosicao].getXY() == posicao){
	        			aux = contadorHeroi;
	        		}
	        	}
		 }
		return aux;
	}
	
	public String getNome() {
		return nome;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isVez() {
		return vez;
	}
	public void setVez(boolean vez) {
		this.vez = vez;
	}
	public int getNumEscolhas() {
		return numEscolhas;
	}
	public void setNumEscolhas(int numRodadas) {
		this.numEscolhas = numRodadas;
	}
	
	public ArrayList<Navio> getNavios() {
		return navios;
	}

	public void setNavios(ArrayList<Navio> navios) {
		this.navios = navios;
	}

	public int getContadorDeNavio() {
		return contadorDeNavio;
	}

	public void setContadorDeNavio(int contadorDeNavio) {
		this.contadorDeNavio = contadorDeNavio;
	}

	public int getVida() {
		return this.vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getNumeroNavios() {
		return numeroNavios;
	}
	public void setNumeroNavios(int numeroNavios) {
		this.numeroNavios = numeroNavios;
	}

	public int getContadorHeroi() {
		return contadorDeNavio;
	}

	public void setContadorHeroi(int contadorHeroi) {
		this.contadorDeNavio = contadorHeroi;
	}

	public int getContadorPosicao() {
		return contadorPosicao;
	}

	public void setContadorPosicao(int contadorPosicao) {
		this.contadorPosicao = contadorPosicao;
	}

	public int getNumeroConexoes() {
		return numeroConexoes;
	}

	public void setNumeroConexoes(int numeroConexoes) {
		this.numeroConexoes = numeroConexoes;
	}
	
	
}
