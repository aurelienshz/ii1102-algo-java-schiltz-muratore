package jeudelavie;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdDraw;

public class Startup {
	
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String [] args) throws IOException {
		if(args.length > 0 && args[0].equals("console")) {
		    handleConsole();
		}
		else {
			handle2D();
		}
	 
	}
	
	
	private static void handle2D() {
		
		Jeu.afficheInstructions();
		boolean[][] grille = Jeu.initGrille2D();
		int end = Jeu.endGoodbye;
		
		/*Détection d'un état stable sur les deux dernières générations.
		 * On pourrait stocker l'historique complet et faire un test sur tout l'historique à chaque génération pour détecter
		 * les états stables sur plus de 2 générations. 
		 */
		boolean[][][] previousGrids = new boolean[2][grille.length][grille[0].length];
		
		StdDraw.setCanvasSize(40*grille.length, grille[0].length*40);
		Jeu.affiche2D(grille);
		
		
		while(true) {
			
			
			
			if (StdDraw.isKeyPressed(16)){ //Appui sur shift
				previousGrids[0] = previousGrids[1];
				previousGrids[1] = grille;
				grille = Jeu.step(grille);
				StdDraw.clear();
				Jeu.affiche2D(grille);
			}
			
			if (StdDraw.isKeyPressed(83)){ //Appui sur s
				Jeu.ecrireGrille(grille, "save.txt");
			}
			
			if(StdDraw.isKeyPressed(81)) {
				StdDraw.clear();
				break;
			}
			
			if (StdDraw.isKeyPressed(82)){ // Appui sur r
				System.out.println("Jeu réinitialisé");
				StdDraw.clear();
				StdDraw.show(500/Jeu.FPS);
				grille = Jeu.initGrille2D();
				StdDraw.setCanvasSize(40*grille.length, grille[0].length*40);
				Jeu.affiche2D(grille);
			}
			
			if(Arrays.deepEquals(grille, previousGrids[0])) {
				end = Jeu.endStable;
				break;
			}	
			
			if(Arrays.deepEquals(grille, previousGrids[1])) {
				end = Jeu.endImmobile;
				break;
			}
			
			StdDraw.show(1000/Jeu.FPS);
			
			
		}
		Jeu.messageFin(end);
		
	}


	public static void handleConsole() {
		System.out.println("### JEU DE LA VIE ###");
		boolean[][] grille = Jeu.initGrilleConsole();
		
		/*Détection d'un état stable sur les deux dernières générations.
		 * On pourrait stocker l'historique complet et faire un test sur tout l'historique à chaque génération pour détecter
		 * les états stables sur plus de 2 générations. 
		 */
		boolean[][][] previousGrids = new boolean[2][grille.length][grille[0].length];
		
		
		System.out.println("Appuyer sur Entrée pour afficher l'étape suivante");
		System.out.println("Saisir Q+Entrée pour quitter");
		System.out.println("Saisir S+Entrée pour sauvegarder la grille et quitter");
		
		String ch ="a";
				
		while(!ch.equals("q") && !ch.equals("s")) { // On quitte en appuyant sur "q" puis entrer

			System.out.println("Nouvelle génération : ");
			Jeu.afficheConsole(grille);
			previousGrids[0] = previousGrids[1];
			previousGrids[1] = grille;
			
			grille = Jeu.step(grille);
			
			ch = scan.nextLine();
			
			if(Arrays.deepEquals(grille, previousGrids[0])) {
				System.out.println("On a atteint un état stable sur 2 générations.");
				ch = ch.equals("s")?"s":"q";
			}	
			
			if(Arrays.deepEquals(grille, previousGrids[1])) {
				System.out.println("Grille immobile !");
				ch = ch.equals("s")?"s":"q";
			}
			
			if(ch.equals("s")) {
				Jeu.ecrireGrille(grille, "save.txt");
			}
		}
	}
	
}
