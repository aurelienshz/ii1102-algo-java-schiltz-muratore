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
	public static int getNeighborsAliveCount(int x, int y, boolean[][] grille){
		int numberAlive = 0;
		
		if (x > 0) if (grille[x-1][y]) numberAlive += 1; //Gauche
		if (x < grille.length) if (grille[x+1][y]) numberAlive += 1; //Droite
		if (y < 0) if (grille[x][y-1]) numberAlive += 1; //Haut
		if (y < grille.length) if (grille[x][y+1]) numberAlive += 1; //Bas
		
		if (x > 0 && y > 0) if (grille[x-1][ y-1]) numberAlive += 1; //Haut Gauche
		if (x < grille.length && y > 0) if (grille[x+1][ y-1]) numberAlive += 1; //Haut Droite
		if (x < grille.length && y < grille.length) if (grille[x+1][ y+1]) numberAlive += 1; // Bas Droite
		if (x >0 && y < grille.length) if (grille[x-1][y+1]) numberAlive += 1; //Bas Gauche
		
		return numberAlive;
	}
	
	
	/**
	 * Execute une itération du jeu de la vie. Modifie la grille.
	 * @param grille
	 */
	public static void step(boolean [][] grille) {
		boolean[][] newGrille = grille;
		int size = grille.length;
		for (int i = 0; i < size; i++ ){
			for (int j = 0; i < size; j++ ){
				if (grille[i][j]){ //Si elle est vivante
					if (getNeighborsAliveCount(i,j,grille) < 2 || getNeighborsAliveCount(i,j,grille) > 3) newGrille[i][j] = false;
				}else{ //Si elle est morte
					if (getNeighborsAliveCount(i,j,grille) == 3) newGrille[i][j] = true;
				}
			}
		}
		grille = newGrille;
	}
	
	
	
	/**
	 * Déroule un jeu de la vie sur la grille donnée en parametre, en fichant les changements dans la console 
	 * @param grille
	 */
	public static void afficheConsole(boolean[][] grille) {
		for(boolean[] ligne : grille) {
			System.out.print("|");
			for(boolean tile: ligne) {
				System.out.print(tile?"O|":" |");
			}
			System.out.println("");
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
