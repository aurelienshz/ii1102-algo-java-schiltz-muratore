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
		
		boolean [][] grille = null; //Jeu.initGrille2D(30, 30); ou Jeu.ligreGrille(fichier)
		while(i++ < 1000) {
			grille = Jeu.step(grille);
			
			StdDraw.clear();
			Jeu.affiche2D(grille);
			StdDraw.show(1000/Jeu.FPS);
		}
	}


	public static void handleConsole() {
		boolean [][] grille = {
			{false, false, false},
			{true,  true,  true },
			{false, false, false}		
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
