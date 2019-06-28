package model;

public class Posicao {
	private int xy;
	private int x;
	private int y;
	
	public Posicao(int valor) {
		setXY(valor);
	}
	
	public void moverCima(int movimento) {
		setXY(getXY()+ (movimento*10)); 
	}
	
	public void moverBaixo(int movimento) {
		setXY(getXY()- (movimento*10));
	}
	
	public void moverDireita(int movimento) {
		setXY(getXY() + movimento);
	}
	
	public void moverEsquerda(int movimento) {
		setXY(getXY() - movimento);
	}

	public int getXY() {
		return xy;
	}

	public void setXY(int xy) {
		this.xy = xy;
		String auxiliar = Integer.toString(xy);
		char[] aux = auxiliar.toCharArray();
		if  (aux.length==2){
			setX(Integer.parseInt(String.valueOf(aux[1])));
			setY(Integer.parseInt(String.valueOf(aux[0])));
		}
		else {
			setY(0);
			setX(Integer.parseInt(String.valueOf(aux[0])));
		}	
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}