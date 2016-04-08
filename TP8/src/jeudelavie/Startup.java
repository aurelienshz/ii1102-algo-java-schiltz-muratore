package jeudelavie;

import java.io.IOException;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdDraw;

public class Startup {
	
	
	
	public static void main(String [] args) throws IOException {
		if(args.length > 0 && args[0].equals("console")) {
		    handleConsole();
		}
		else {
			handle2D();
			
			
		}
	 
	}
	
	
	private static void handle2D() {
		
		int i=0;
		Jeu.afficheInstructions();
		boolean[][] grille = Jeu.initGrille2D();
		
		StdDraw.setCanvasSize(40*grille.length, grille[0].length*40);
		Jeu.affiche2D(grille);
		
		while(i++ < 1000) {
			if (StdDraw.isKeyPressed(16)){ //Appui sur shift
				grille = Jeu.step(grille);
				StdDraw.clear();
				Jeu.affiche2D(grille);
				}
			if (StdDraw.isKeyPressed(83)){ //Appui sur s
				Jeu.ecrireGrille(grille, "save.txt");
			}
			if (StdDraw.isKeyPressed(82)){ // Appui sur r
				System.out.println("Jeu réinitialisé");
				StdDraw.clear();
				StdDraw.show(500/Jeu.FPS);
				grille = Jeu.initGrille2D();
				StdDraw.setCanvasSize(40*grille.length, grille[0].length*40);
				Jeu.affiche2D(grille);
			}
				StdDraw.show(1000/Jeu.FPS);
		}
	}


	public static void handleConsole() {
		System.out.println("# JEU DE LA VIE #");
		System.out.println("Appuyer sur Entrée pour afficher l'étape suivante, saisir Q pour quitter");
		boolean [][] grille = {
			{false, false, false, true},
			{true,  true,  true, false},
			{false, false, false, true}		
		};
		String ch ="a";
		Scanner scan = new Scanner(System.in);
				
		while(!ch.equals("q")) { // On quitte en appuyant sur "q" puis entrer
			Jeu.afficheConsole(grille);
			ch = scan.nextLine();
			grille = Jeu.step(grille);
			
		}
		scan.close();
		
	}
	
	
}
