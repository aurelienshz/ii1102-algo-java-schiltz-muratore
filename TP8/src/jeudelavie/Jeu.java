package jeudelavie;

import java.awt.Font;

import edu.princeton.cs.introcs.StdDraw;


public class Jeu {
	
	public static final int FPS = 10;
	
	/**
	 * Retourne le nombre de voisins vivants d'une case.
	 * @param x
	 * @param y
	 * @param grille
	 * @return
	 */
	public static int countAliveNeighbors(int x, int y, boolean[][] grille){
		int numberAlive = 0;
		
		int maxX = grille.length - 1;
		int maxY = grille[0].length - 1;
		
		// Voisins adjacents :
		if (x > 0) {
			if(grille[x-1][y]) {
				numberAlive += 1;
			}
		}
		if (x < maxX) {
			// Droite :
			if (grille[x+1][y]) {
				numberAlive += 1;
			}
		}
		
		if (y > 0) {
			// Haut :
			if (grille[x][y-1]) {
				numberAlive += 1;
			}
		}
		if (y < maxY) {
			// Bas :
			if (grille[x][y+1]) {
				numberAlive += 1;
			}
		}
		
		
		// Voisins diagonaux :
		if (x > 0 && y > 0) {
			//Haut Gauche
			if (grille[x-1][y-1]) { 
				numberAlive += 1;
			}
		}
		if (x < maxX && y > 0) {
			//Haut Droite
			if (grille[x+1][y-1]) {
				numberAlive += 1;
			}
		}
		if (x < maxX && y < maxY) {
			// Bas Droite
			if (grille[x+1][y+1]) {
				numberAlive += 1;
			}
		}
		if (x > 0 && y < maxY) {
			//Bas Gauche
			if (grille[x-1][y+1]) {
				numberAlive += 1;
			}
		}
		
		
		return numberAlive;
	}
	
	
	/**
	 * Execute une itération du jeu de la vie.
	 * Modifie la grille (passage de la grille par référence)
	 * @param grille
	 */
	public static boolean[][] step(boolean [][] grille) {
		boolean[][] newGrille = new boolean[grille.length][grille[0].length];
		
		int width = grille.length;
		int height = grille[0].length;
		for (int i = 0; i < width; i++ ){
			for (int j = 0; j < height; j++ ){
				int aliveNeighbors = countAliveNeighbors(i,j,grille);
				if (grille[i][j]) {
					//Si elle est vivante : condition d'extinction
					if (aliveNeighbors < 2 || aliveNeighbors > 3) {
						newGrille[i][j] = false;
					}
					else {
						// On réaffecte pour éviter des effets de bord :
						newGrille[i][j] = true;
					}
				}
				else {
					//Si elle est morte : condition de naissance
					if (aliveNeighbors == 3) {
						newGrille[i][j] = true;
					}
					else {
						// On réaffecte pour éviter des effets de bord :
						newGrille[i][j] = false;
					}
					// else : elle reste morte
				}
			}
		}
		return newGrille;
	}
	
	
	
	/**
	 * Déroule un jeu de la vie sur la grille donnée en parametre, en fichant les changements dans la console 
	 * @param grille
	 */
	public static void afficheConsole(boolean[][] grille) {
		for(int i = 0; i < grille[0].length; i++) {
			System.out.print("|");
			for(int j = 0; j < grille.length; j++) {
					System.out.print(grille[j][i]?"O|":" |");
				}
				System.out.println();
		}
	}
	
	/**
	 * Affiche l'écran de saisie de taille du tableau
	 * @param x
	 * @param y
	 */
	public static void afficheaskSize2D(int x, int y){
		Font font = new Font("Arial", Font.BOLD, 20);
		Font font2 = new Font("Arial", Font.BOLD, 30);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(0, 0.75, 1, 0.75);
		StdDraw.line(0, 0.5, 1, 0.5);
		StdDraw.line(0, 0.25, 1, 0.25);
		StdDraw.line(0.5, 0.75, 0.5, 0);
		StdDraw.setFont(font);
		StdDraw.text(0.5, 0.75 + 0.25/2 ,"Taille du tableau :");
		StdDraw.setFont(font2);
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.text(0.25, 0.25 + 0.25/2.3 ,"+");
		StdDraw.text(0.75, 0.25 + 0.25/2.3 ,"+");
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(0.25, 0.25/2.3 ,"-");
		StdDraw.text(0.75, 0.25/2.3 ,"-");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.25, 0.5 + 0.25/2.3 ,"X = " + String.valueOf(x));
		StdDraw.text(0.75, 0.5 + 0.25/2.3 ,"Y = " + String.valueOf(y));
	}
	
	/**
	 * Affiche un écran graphique de saisie de taille de tableau et renvoie la taille validée
	 * Validation avec : SHIFT
	 * @return array avec x:width, y:height
	 */
	public static int[] askSize2D(){
		int tailleMin = 4;
		int x = tailleMin;
		int y = tailleMin;
		StdDraw.setCanvasSize(200, 200);
		StdDraw.setPenRadius(0.003);
		afficheaskSize2D(x, y);
		
		while (!StdDraw.isKeyPressed(16)){
			StdDraw.show(80);
			
			if (StdDraw.mousePressed()){
				
				int n = (int) (StdDraw.mouseX()*2);
				int m = (int) (StdDraw.mouseY()*4);
				
				switch (m) {
					case 0: if (n < 1) {
									x--;
							}else{
								 	y--;
							}
							break;
					case 1 :if (n < 1) {
									x++;
							}else{
								 	y++;
							}
							break;	
				}
				if (x < tailleMin || y < tailleMin) {
					x = tailleMin;
					y = tailleMin;
				}
				StdDraw.clear();
				afficheaskSize2D(x, y);

			}
		}
		int[] a = {x,y};
		return a;
		
	}
	
	/**
	 * Permet d'initialiser une grille via une interface StdDraw en cliquant sur les cases pour changer leur etat
	 * Lancer le jeu : Appuyer sur ENTREE
	 * @param height
	 * @param width
	 * @return grille
	 */
	public static boolean[][] initGrille2D(int height, int width) {
		int[] size = askSize2D();
		boolean[][] grille = new boolean[size[1]][size[0]];
		
		StdDraw.setCanvasSize(40*grille.length, grille[0].length*40);
		
		for(int i = 0; i < grille.length; i++) {
			for(int j = 0; j < grille[i].length; j++) {
				grille[i][j] = false;
			}
		}
		
		afficheGrille2D(grille);
		while (!StdDraw.isKeyPressed(10)){
			
			StdDraw.show(60);
			
			if (StdDraw.mousePressed()){
				
				int n = (int) (StdDraw.mouseX()*grille.length);
				int m = (int) (StdDraw.mouseY()*grille[0].length);
				double tailleEllipse = 0;
				
				if (grille[n][m]){
					StdDraw.setPenColor(StdDraw.WHITE);
					tailleEllipse = 2.22;
					grille[n][m] = false;
				}else{
					StdDraw.setPenColor(StdDraw.RED);
					tailleEllipse = 2.3;
					grille[n][m] = true;
				}
				StdDraw.filledEllipse((double) (n+0.5)/grille.length , (double) (m+0.5)/grille[0].length, (double) 1/(tailleEllipse*grille.length), (double) 1/(tailleEllipse*grille[0].length));
			}
		}
		
		return grille;		
	}
	
	/**
	 * Affiche la grille "graphique" en fonction de la taille de celle ci
	 * @param grille
	 */
	
	public static void afficheGrille2D(boolean [][] grille){
		double penRadius = 0.0025;
		StdDraw.setPenRadius(penRadius);
        StdDraw.setPenColor(StdDraw.BLUE);
        for(double i = 1.0; i<grille.length; i++) {
        	StdDraw.line(i/grille.length, 1, i/grille.length, 0);
        }
        
        for(double j = 0; j<grille[0].length; j++) {
        	StdDraw.line(1, j/grille[0].length, 0, j/grille[0].length);
        }
	}
	
	/**
	 * Affiche l'état en cours du jeu de la vie à l'aide de StdDraw
	 * @param grille 
	 */
	public static void affiche2D(boolean [][] grille) {
		
		afficheGrille2D(grille);
        
        StdDraw.setPenColor(StdDraw.RED);
		for(int i = 0; i<grille.length; i++) {
			for(int j = 0; j<grille[0].length; j++) {
				if(grille[i][j]){
					StdDraw.filledEllipse((double) (i+0.5)/grille.length , (double) (j+0.5)/grille[0].length, (double) 1/(2.25*grille.length), (double) 1/(2.25*grille[0].length));
				}
			}
		}
	}
	
	/**
	 * Lit une grille depuis un fichier dont le chemin est donné en paramètre
	 * @param fichier
	 * @return
	 */
	public static boolean [][] lireGrille(String fichier) {
		return null;
	}
}
