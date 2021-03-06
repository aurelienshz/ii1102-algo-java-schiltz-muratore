package jeudelavie;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdDraw;


public class Jeu {
	
	public static Scanner scan = new Scanner(System.in);
	
	public static final int FPS = 10;
	
	/**
	 * Execute une itération du jeu de la vie.
	 * Modifie la grille (passage de la grille par référence)
	 * @param grille
	 */
	public static boolean[][] step(boolean [][] grille) {
		
		boolean[][] newGrille = new boolean[grille.length][grille[0].length];
		
		int width = grille.length;
		int height = grille[0].length;
		int aliveNeighbors = 0;
		
		for (int i = 0; i < width; i++ ){
			for (int j = 0; j < height; j++ ){
						
				// Le comptage des voisins vivants
	
				aliveNeighbors = 0;
				
				for(int k = - 1; k <= 1; k++) {
					for (int l = -1; l <= 1 ; l++) {
						if(!(k == 0 && l ==0)) {
							// Vérification de l'existence du voisin :
							if(i + k >= 0 && i + k < width && j + l >= 0 && j+l < height) {
								if(grille[i+k][j+l]) {
									aliveNeighbors ++;
								}
							}
						}
					}
				}
				
				
				// Réaffectation des valeurs dans la nouvelle grille : 
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
	
	
	public static boolean[][] initGrilleConsole() {
		
		int choice = -1;
		boolean[][] grille = new boolean[0][0];
		
		System.out.println("### Menu : ###");
		System.out.println("1. Charger une grille à partir d'un fichier");
		System.out.println("2. Créer une nouvelle grille");
		System.out.println("3. Générer une grille aléatoirement");

		choice = scan.nextInt();
		
		while(choice < 1 && choice > 3) {
			System.out.println("Choix invalide !");
			choice = scan.nextInt();
		}
		
		if(choice == 1) {
			grille =  lireGrille("save.txt");
		}
		else {
			System.out.println("Dimensions de la grille :");
			System.out.println("Largeur : ");
			int width = scan.nextInt();
			System.out.println("Hauteur : ");
			int height = scan.nextInt();
			
			if(choice == 2) {
				grille = new boolean[height][width];
				
				for (int i = 0; i < height; i ++) {
					for (int j = 0; j < width; j ++) {
						grille[i][j] = true;
					}
				}
				
				for (int i = 0; i < height; i ++) {
					for (int j = 0; j < width; j ++) {
						System.out.println("Cellule : ligne "+ (j+1) +", colonne "+ (i+1) +" vivante ? [O/n]");
						String c = scan.next();
						if(c.toLowerCase().equals("o") || c.equals("")) {
							grille[i][j] = true;
						}
						else {
							grille[i][j] = false;
						}
					}
				}	
			}
			else {
				grille = generateRandomGrid(width, height);
			}
		}
		
		/*
		 * Vérification de la validité de la grille et nouvelle tentative si l'user n'est pas satisfait :
		 */
		
		String ch = "a";
		System.out.println();
		afficheConsole(grille);
		System.out.println("Cette grille vous convient-elle ? [o/n]");

		ch = scan.next();
		
		if(ch.toLowerCase().equals("o") || ch.equals("")) {
			return grille;
		}
		else {
			grille = initGrilleConsole();
			return grille;
		}
		
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
	public static void afficheAskSize2D(int x, int y){
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
		int tailleMax = 25;
		int x = tailleMin;
		int y = tailleMin;
		StdDraw.setCanvasSize(200, 200);
		StdDraw.setPenRadius(0.003);
		afficheAskSize2D(x, y);
		
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
				if (x < tailleMin) { x = tailleMin; };
				if (y < tailleMin) { y = tailleMin; };
				if (x > tailleMax) { x = tailleMax; };
				if (y > tailleMax) { y = tailleMax; };
				StdDraw.clear();
				afficheAskSize2D(x, y);
			}
		}
		int[] a = {x,y};
		return a;
		
	}

	/**
	 * Affiche un écran avec un titre sur une première ligne et deux boutons répartis sur la dernière ligne un sur la moitié gauche, l'autre sur la droite
	 * @param titre
	 * @param bouton1 à gauche
	 * @param bouton2 à droite
	 */
	private static void afficheEcranDeuxBoutons(String titre, String bouton1, String bouton2){
		Font font = new Font("Arial", Font.BOLD, 20);
		StdDraw.setCanvasSize(200, 100);
		StdDraw.setPenRadius(0.003);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(0, 0.5, 1, 0.5);
		StdDraw.line(0.5, 0, 0.5, 0.5);
		StdDraw.setFont(font);
		StdDraw.text(0.5, 0.5 + 0.25 ,titre);
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(0.25, 0.25 ,bouton1);
		StdDraw.text(0.75, 0.25 ,bouton2);
		StdDraw.setPenColor(StdDraw.BLACK);
	}
	
	/**
	 * Affiche une fenêtre qui demande le mode de saisie du tableau
	 * @return 1 si "Importer" 2 si "Créer"
	 */
	public static int askInitMode(){
		int output = 0;
		
		afficheEcranDeuxBoutons("Mode de saisie :", "Importer", "Créer");
		
		while (output == 0){
			while (!StdDraw.mousePressed()){
				StdDraw.show(80);
			}
			
			if (StdDraw.mousePressed()){
				
				int n = (int) (StdDraw.mouseX()*2);
				int m = (int) (StdDraw.mouseY()*2);
				
				if (m < 1){
					if (n < 1) {
						output = 1;
					}else{
						output = 2;
					}
				}
			}
		}
		StdDraw.clear();
		StdDraw.show(150);
		return output;
	}
	
	/**
	 * Affiche une fenêtre qui demande le mode de saisie du tableau
	 * @return 1 si "Aléatoire" 2 si "Saisir"
	 */
	public static int askFillingMode(){
		int output = 0;
		
		afficheEcranDeuxBoutons("Mode de saisie :", "Aléatoire", "Saisir");
		
		while (output == 0){
			while (!StdDraw.mousePressed()){
				StdDraw.show(80);
			}
			
			if (StdDraw.mousePressed()){
				
				int n = (int) (StdDraw.mouseX()*2);
				int m = (int) (StdDraw.mouseY()*2);
				
				if (m < 1){
					if (n < 1) {
						output = 1;
					} else {
						output = 2;
					}
				}
			}
		}
		StdDraw.clear();
		StdDraw.show(100);
		return output;
	}
	
	/**
	 * Permet d'initialiser une grille via une interface StdDraw en cliquant sur les cases pour changer leur etat
	 * @return grille
	 */
	public static boolean[][] initGrille2D() {
		
		int[] size;
		boolean[][] grille = null;
		int initMode = askInitMode();
		
		if(initMode == 1) {
			grille = lireGrille("save.txt");
		}
		else if (initMode == 2){
			size = askSize2D();
			int fillingMode = askFillingMode();
			if(fillingMode == 2) {
				// True : Saisie de grille à la main :
				grille = saisieGrille2D(size[1],size[0]);
			}
			else if(fillingMode == 1) {
				// False = Grille aléatoire
				grille = generateRandomGrid(size[1],size[0]);
			}
		}
		else {
			grille = initGrille2D();
		}
		return grille;		
	}
	
	/**
	 * Génération aléatoire d'une grille 
	 * @param sizeX largeur de la grille
	 * @param sizeY hauteur de la grille
	 * @return la grille générée
	 */
	public static boolean[][] generateRandomGrid(int sizeX, int sizeY) {
		Random rand = new Random();
		boolean[][] grille = new boolean[sizeX][sizeY];
		
		for(int i = 0; i<grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				
				grille[i][j] = rand.nextInt(2)==1?true:false;
			}
		}
		
		return grille;
	}
	
	/**
	 * Saisie d'une grille à l'aide d'une interface graphique 
	 * @param sizeX largeur de la grille
	 * @param sizeY hauteur de la grille
	 * @return la grille saisie
	 */
	public static boolean[][] saisieGrille2D(int sizeX, int sizeY) {
		
		boolean[][] grille = new boolean[sizeX][sizeY];
		
		StdDraw.setCanvasSize(40*grille.length, grille[0].length*40);
		
		for(int i = 0; i < grille.length; i++) {
			for(int j = 0; j < grille[i].length; j++) {
				grille[i][j] = false;
			}
		}
		StdDraw.show(80);
		afficheGrille2D(grille);
		while (!StdDraw.isKeyPressed(10)){
			if (StdDraw.mousePressed()){
				int n = (int) (StdDraw.mouseX()*grille.length);
				int m = (int) (StdDraw.mouseY()*grille[0].length);
				
				if (grille[n][m]) {
					grille[n][m] = false;
				}else{
					grille[n][m] = true;
				}
			}
			StdDraw.show(80);
			StdDraw.clear();
			affiche2D(grille);
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
	 * @param grille la grille du jeu
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
	 * @param fichier le nom du fichier
	 * @return la grille importée
	 */
	public static boolean[][] lireGrille(String fichier) {
		    //Lecture du fichier
			String line = null;
		    ArrayList<boolean[]> list = new ArrayList<boolean[]>();
		    try {
		        BufferedReader reader = new BufferedReader(new FileReader(fichier));
		        while((line = reader.readLine()) != null){
		            line = line.replace(";", "");
		            boolean[] lineB = new boolean[line.length()];
		            for(int i = 0; i < line.length(); i++) {
		            	
		            	boolean val = false;
		            	
		            	if (line.charAt(i) == '1'){
		            		val = true;
		            	}
		            	
		            	lineB[i] = val;
		            	
		            }

		            list.add(lineB);
		        }
		        reader.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    //Conversion ArrayList -> Array 2D
		    boolean[][] convList = new boolean[list.size()][list.get(0).length];

		    for(int n = 0; n < list.size(); n++)
		    {
		         convList[n] = list.get(n); 
		    }
		    
		    return convList;
	}
	
	/**
	 * Enregistre une grille dans un fichier dont le nom est donné en paramètre
	 * @param grille la grille du jeu
	 * @param fichier le nom du fichier
	 */
	public static void ecrireGrille(boolean[][] grille, String fichier) {
		
		StringBuffer ligne = new StringBuffer();
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(fichier,"UTF-8");
		
			for (boolean[] line : grille) {
				for(boolean tile : line) {
					String val = tile?"1":"0";
					ligne.append(val + ";");
				}
				writer.println(ligne);
				ligne.delete(0, ligne.length());
			}
			writer.close();
			System.out.println("Grille Sauvegardée");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Affiche les touches nécessaire pour jouer au jeu.
	 */
	public static void afficheInstructions() {
		
		StdDraw.setCanvasSize(500, 300);
		StdDraw.setPenRadius(0.003);
		Font font = new Font("Arial", Font.PLAIN, 15);
		Font fontBold = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(fontBold);
		StdDraw.text(0.5, 0.90, "Instructions du jeu de la vie :");
		StdDraw.setFont(font);
		StdDraw.text(0.5, 0.75, "SHIFT : avancer à la fenêtre suivante");
		StdDraw.text(0.5, 0.61, "ENTREE : idem, mais uniquement");
		StdDraw.text(0.5, 0.56, "pour la saisie au clic du tableau");
		StdDraw.text(0.5, 0.45, "r : pour recommencer");
		StdDraw.text(0.5, 0.34, "s : pour sauvegarder le tableau actuel");
		StdDraw.text(0.5, 0.23, "q : Quitter");
		StdDraw.setFont(fontBold);
		StdDraw.text(0.5, 0.10, "Bon jeu !");
		
		while (!StdDraw.isKeyPressed(16)){
			StdDraw.show(80);
		}
	}
	
	
	public static final int endGoodbye = 1;
	public static final int endImmobile = 2;
	public static final int endStable = 3;
	
	/**
	 * Message de fin de jeu
	 * @param immobile Détermine si la grille est stable ou immobile
	 */
	public static void messageFin(int endType) {
		StdDraw.setCanvasSize(500, 300);
		StdDraw.setPenRadius(0.003);
		Font font = new Font("Arial", Font.PLAIN, 15);
		Font fontBold = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(fontBold);
		if(endType == endImmobile) {
			StdDraw.text(0.5, 0.90, "Grille immobile");
			StdDraw.text(0.5, 0.15, "Au revoir !");
		} else if(endType == endStable) {
			StdDraw.text(0.5, 0.90, "État stable sur deux générations");
			StdDraw.text(0.5, 0.15, "Au revoir !");
		}
		else {
			StdDraw.text(0.5, 0.90, "Au revoir !");
		}
		
		StdDraw.setFont(font);
		StdDraw.setFont(fontBold);
		
		StdDraw.show();
	}
	
	/* Time to  


                        (
                          )     (
                   ___...(-------)-....___
               .-""       )    (          ""-.
         .-'``'|-._             )         _.-|
        /  .--.|   `""---...........---""`   |
       /  /    |                             |
       |  |    |                             |
        \  \   |                             |
         `\ `\ |                             |
           `\ `|                             |
           _/ /\                             /
          (__/  \                           /
       _..---""` \                         /`""---.._
    .-'           \                       /          '-.
   :               `-.__             __.-'              :
   :                  ) ""---...---"" (                 :
    '._               `"--...___...--"`              _.'
      \""--..__                              __..--""/
       '._     """----.....______.....----"""     _.'
          `""--..,,_____            _____,,..--""`
                        `"""----"""`



	 */
}
