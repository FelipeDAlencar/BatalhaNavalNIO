package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.*;

public class Botao extends javax.swing.JButton {  //Herda todas caracteristicas de JButton  

	private static final long serialVersionUID = 1L;
	// Declara COmponentes
    private JLabel label ;  
    private ArrayList<ImageIcon> imagem;
    private ArrayList<ImageIcon> aux;
    private ArrayList<ImageIcon> imagemVertical1;
    private ArrayList<ImageIcon> imagemVertical2;
    private ArrayList<ImageIcon> imagemVertical3;
    private ArrayList<ImageIcon> imagemVertical4;
    private ArrayList<ImageIcon> imagemVertical5;
    private ArrayList<ImageIcon> imagemHorizontal1;
    private ArrayList<ImageIcon> imagemHorizontal2;
    private ArrayList<ImageIcon> imagemHorizontal3;
    private ArrayList<ImageIcon> imagemHorizontal4;
    private ArrayList<ImageIcon> imagemHorizontal5;
    // Declara Variaveis auxiliares
    private int i=0;
    // Construtor de Botao com nome
    public Botao(String text){  
        this(); 
        label.setText(text);  
    }  
    // Construtor de Botao sem nome  
    public Botao(){  
        label = new JLabel();  
        this.add(label);
        imagem = new ArrayList<ImageIcon>();
        imagem.add(new ImageIcon("img/tabuleiro/mar1.jpg"));
        imagem.add(new ImageIcon("img/tabuleiro/mar2.jpg"));
        imagem.add(new ImageIcon("img/tabuleiro/mar3.jpg"));
        aux = imagem;
        criaImagens();
    }
    // Set nome (String) no botao
    public void setText(String nome){  
        label.setText(nome);  
    }  
    // REESCREVE METODO DA CLASSE JBUTTON 
    // Aplica a imagem (image[0]) como fundo do botao (Obs: A imagem determina o tamanho do botao)
    public void paintComponent(Graphics g) {    
 
       ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);    
           
       final Image backgroundImage = imagem.get(i).getImage();    
       // Pega tamanho da imagem  (Obs: A imagem determina o tamanho do botao)
       double scaleX = getWidth() / (double) backgroundImage.getWidth(null);    
       double scaleY = getHeight() / (double) backgroundImage.getHeight(null);
       AffineTransform xform = AffineTransform.getScaleInstance(scaleX, scaleY);
       // Desenha Imagem
       ((Graphics2D) g).drawImage(backgroundImage, xform, this);          
   }   
   public void criaImagens(){
	   // Criando ArrayList de Imagens
	   this.imagemVertical1 = new ArrayList<ImageIcon>();
	   this.imagemVertical2 = new ArrayList<ImageIcon>();
	   this.imagemVertical3 = new ArrayList<ImageIcon>();
	   this.imagemVertical4 = new ArrayList<ImageIcon>();
	   this.imagemVertical5 = new ArrayList<ImageIcon>();
	   
	   this.imagemHorizontal1 = new ArrayList<ImageIcon>();
	   this.imagemHorizontal2 = new ArrayList<ImageIcon>();
	   this.imagemHorizontal3 = new ArrayList<ImageIcon>();
	   this.imagemHorizontal4 = new ArrayList<ImageIcon>();
	   this.imagemHorizontal5 = new ArrayList<ImageIcon>();
	    
	   	// Adiciona Imagem do Barco 1 ao Array
	    this.imagemVertical1.add(new ImageIcon("img/navios/vertical/1a.jpg"));	    
	    this.imagemVertical1.add(new ImageIcon("img/navios/vertical/1b.jpg"));
	    // Adiciona Imagem do Barco 2 ao Array
	    this.imagemVertical2.add(new ImageIcon("img/navios/vertical/2a.jpg"));
	    this.imagemVertical2.add(new ImageIcon("img/navios/vertical/2b.jpg"));
	    // Adiciona Imagem do Barco 3 ao Array
	    this.imagemVertical3.add(new ImageIcon("img/navios/vertical/3a.jpg"));
	    this.imagemVertical3.add(new ImageIcon("img/navios/vertical/3b.jpg"));
	    this.imagemVertical3.add(new ImageIcon("img/navios/vertical/3c.jpg"));
	    // Adiciona Imagem do Barco 4 ao Array
	    this.imagemVertical4.add(new ImageIcon("img/navios/vertical/4a.jpg"));
	    this.imagemVertical4.add(new ImageIcon("img/navios/vertical/4b.jpg"));
	    this.imagemVertical4.add(new ImageIcon("img/navios/vertical/4c.jpg"));
	    this.imagemVertical4.add(new ImageIcon("img/navios/vertical/4d.jpg"));
	    // Adiciona Imagem do Barco 5 ao Array
	    this.imagemVertical5.add(new ImageIcon("img/navios/vertical/5a.jpg"));
	    this.imagemVertical5.add(new ImageIcon("img/navios/vertical/5b.jpg"));
	    this.imagemVertical5.add(new ImageIcon("img/navios/vertical/5c.jpg"));
	    this.imagemVertical5.add(new ImageIcon("img/navios/vertical/5d.jpg"));
	    
	    // Adiciona Imagem do Barco 1 ao Array
	    this.imagemHorizontal1.add(new ImageIcon("img/navios/horizontal/1b.jpg"));	    
	    this.imagemHorizontal1.add(new ImageIcon("img/navios/horizontal/1a.jpg"));
	    // Adiciona Imagem do Barco 2 ao Array
	    this.imagemHorizontal2.add(new ImageIcon("img/navios/horizontal/2b.jpg"));
	    this.imagemHorizontal2.add(new ImageIcon("img/navios/horizontal/2a.jpg"));
	    // Adiciona Imagem do Barco 3 ao Array
	    this.imagemHorizontal3.add(new ImageIcon("img/navios/horizontal/3c.jpg"));
	    this.imagemHorizontal3.add(new ImageIcon("img/navios/horizontal/3b.jpg"));
	    this.imagemHorizontal3.add(new ImageIcon("img/navios/horizontal/3a.jpg"));
	    // Adiciona Imagem do Barco 4 ao Array
	    this.imagemHorizontal4.add(new ImageIcon("img/navios/horizontal/4d.jpg"));
	    this.imagemHorizontal4.add(new ImageIcon("img/navios/horizontal/4c.jpg"));
	    this.imagemHorizontal4.add(new ImageIcon("img/navios/horizontal/4b.jpg"));
	    this.imagemHorizontal4.add(new ImageIcon("img/navios/horizontal/4a.jpg"));
	    // Adiciona Imagem do Barco 5 ao Array
	    this.imagemHorizontal5.add(new ImageIcon("img/navios/horizontal/5d.jpg"));
	    this.imagemHorizontal5.add(new ImageIcon("img/navios/horizontal/5c.jpg"));
	    this.imagemHorizontal5.add(new ImageIcon("img/navios/horizontal/5b.jpg"));
	    this.imagemHorizontal5.add(new ImageIcon("img/navios/horizontal/5a.jpg"));
   }
   // Muda a imagem (image[int i]) do fundo do botao 
   public void setFundo(int tipo){
	   		this.i = tipo;
   }	   
   // Reset no fundo (Volta ao padrao do tabuleiro)
   public void setFundo(){
		  this.imagem = aux;
		  this.i =0;
   }
   // Muda imagem do fundo, conforme posicao do navio
   public void setFundo(boolean posicao, int tipo){
	   // Conforme Tipo e Posicao a variavel da imagem (fundo do botao) muda
	   if (tipo == 0){
		   if (!posicao)
			   this.imagem = this.imagemVertical1;
		   else
			   this.imagem = this.imagemHorizontal1;
	   }
	   if (tipo == 1){
		   if (!posicao)
			   this.imagem = this.imagemVertical2;
		   else
			   this.imagem = this.imagemHorizontal2;
	   }
	   if (tipo == 2){
		   if (!posicao)
			   this.imagem = this.imagemVertical3;
		   else
			   this.imagem = this.imagemHorizontal3;
	   }
	   if (tipo == 3){
		   if (!posicao)
			   this.imagem = this.imagemVertical4;
		   else
			   this.imagem = this.imagemHorizontal4;
	   }
	   if (tipo == 4){
		   if (!posicao)
			   this.imagem = this.imagemVertical5;
		   else
			   this.imagem = this.imagemHorizontal5;
	   }
   }
}