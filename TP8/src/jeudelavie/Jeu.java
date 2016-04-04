package jeudelavie;

import edu.princeton.cs.introcs.StdDraw;


public class Jeu {
	
	public static final int FPS = 5;
	
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
	 * Permet d'initialiser une grille via une interface StdDraw en cliquant sur les cases pour changer leur etat
	 * @param height
	 * @param width
	 * @return
	 */
	public static boolean [][] initGrille2D(int height, int width) {
		boolean[][] grille = new boolean[height][width];
		
		for(int i = 0; i < grille.length; i++) {
			for(int j = 0; j < grille[i].length; j++) {
				grille[i][j] = true;
			}
		}
		
		return grille;		
	}
	/**
	 * Déroule un jeu de la vie sur grille affichée à l'aide de StdDraw
	 * @param grille 
	 */
	public static void affiche2D(boolean [][] grille) {
		
	
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
