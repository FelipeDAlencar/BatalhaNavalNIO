package model;

public class Navio {
	private String nome;
	private Posicao[] posicao;
	private int vida;
	private boolean vivo; 

	public Navio(String nome, Posicao[] posicao, int vida) {
		
		 setNome(nome);
		 setPosicao(posicao);
		 setVida(vida);
		 setVivo(false);
	}


	public boolean isVivo() {
		return vivo;
	}
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Posicao[] getPosicao() {
		return posicao;
	}
	public void setPosicao(Posicao[] posicao) {
		this.posicao = posicao;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
}