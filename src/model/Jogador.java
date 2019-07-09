package model;

public class Jogador {

	private int id = 0;
	private String nome;
	private int numEscolhas = 1;
	private int vida = 15;
	private int numeroNavios = 5;
	private int numeroConexoes= 0;
	private boolean pronto;
	private boolean vez = false;
	
	
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

	public int getNumeroConexoes() {
		return numeroConexoes;
	}

	public void setNumeroConexoes(int numeroConexoes) {
		this.numeroConexoes = numeroConexoes;
	}
	public boolean isPronto() {
		return pronto;
	}
	public void setPronto(boolean pronto) {
		this.pronto = pronto;
	}
	
	
}
