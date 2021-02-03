//package jeuFocus;
import java.util.*;

/**
 * 
 * @author abderrazeksaidane
 *
 */

public class PlateauDeJeu {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_WHITE = "\u001B[37m";
	/**
	* @param plateau est un tableau à deux dimensions qui contient toutes les cases du jeu
	*/
	private List< List<Case> > plateau = new ArrayList< List<Case> >();
	

	/**
	 * Constructeur par défaut de l'objet PlateauDeJeu
	 */
	
	public PlateauDeJeu () {
		
	}
	
	/**
	 * Constructeur de l'objet PlateauDeJeu
	 * @param plateauCompletPieces plateauCompletPieces est une variables qui contient toutes les cases du plateau 
	 */
	
	public PlateauDeJeu(List< List<Case> > plateauCompletPieces){
		this.plateau = plateauCompletPieces;
	}
	
	/**
	 * addCaseToPlateu Cette méthode nous permet d'ajouter des cases au plateau 
	 * @param row1 est une List qui contien les cases d'une seul ligne, rangée
	 */
	public void addCaseToPlateau(List<Case> row1) {
			
		this.plateau.add(row1);
	}
	
	
	/**
	 * Cette méthode nous permet d'ajouter toutes les cases avec les pieces des deux joueurs au plateau de jeu 
	 * @param j1 est l'un des joueurs de la partie
	 * @param j2 est l'un des joueurs de la partie
	 */
	
	public void creationCases(Joueur j1, Joueur j2) {

		int x = 1;
		int k = 0;
		int m =0;
		
		while(x<=6) {
			List<Case> row1 = new ArrayList<Case>(6);
			List<Case> row2 = new ArrayList<Case>(6);
			
			for(int y = 1; y<=6 ; y++) {
				Case c = new Case(x,y);
				if( (x!=3) && (x!=4) ) {
					
					if( (y % 2) == 0 ) { //choix du type de piece qu'on mets dans la case
						c.addPiece(j2.getJeton(k));
						row1.add(c);
						k++;
					}
					
					if( (y % 2) != 0) { //choix du type de piece qu'on mets dans la case
						c.addPiece(j1.getJeton(m));
						row1.add(c);
						m++;
					}
				}
				
				if( (x==3) || (x==4) ) {
					
					if( (y % 2) == 0 ) { //choix du type de piece qu'on mets dans la case
						c.addPiece(j1.getJeton(m));
						row2.add(c);
						k++;
					}
					
					if((y % 2) != 0) { //choix du type de piece qu'on mets dans la case
						c.addPiece(j2.getJeton(k));
						row2.add(c);
						m++;
					}
				}
			}
			if( x==3 ||  x==4) { //Formation d'un tableau à deux dimensions
				this.addCaseToPlateau(row2);
			}
			else { 
				this.addCaseToPlateau(row1);
			}		
			x++;
		}
}
	
	/*********** rechercherCase **************/
	
	/**
	 * Cette méthode nous permet de retrouver une case du plateau 
	 * @param x coordonnée de la case que nous cherchons 
	 * @param y coordonnée de la case que nous cherchons 
	 * @return la case si elle est trouvée
	 */
	
	public Case rechercherCase(int x, int y) {
	
		return (this.plateau.get(x).get(y)); 
	}
	
	/************** dessinCase ******************/
	
	/**
	 * Cette méthode d'importer le contenu d'une case est l'afficher sur a fenetre de commande
	 * @param x coordonnée de la case que nous cherchons 
	 * @param y coordonnée de la case que nous cherchons 
	 * @return les pieces de la case
	 */ 
	public String dessinCase(int x, int y) {

		String piecesCase = "";
		piecesCase += this.rechercherCase(x,y).toString();
		return piecesCase;
	}
	
	/********* dessinPlateau *************/
	
	/**
	 * Cette méthode nous permet de dessiner le plateau contenant les cases et leur pièces
	 */
	
	public void dessinPlateau() {

		int nombrePieceCase =0;
		int base = 20;
		String piecesCase = "";
		for(int i =0 ; i<6 ; i++) {
			System.out.print("+--------------------");
		}
		
		System.out.println("+");
		
		for(int j = 0; j<6 ; j++) {
			
			for(int k = 0 ; k<6 ; k++) {
				System.out.print("|"+k+""+j);
				
				piecesCase = this.dessinCase(k,j);
				System.out.print(piecesCase);
				nombrePieceCase = this.rechercherCase(k,j).getTailleCase();

				for(int r=3*nombrePieceCase+2 ; r<base ; r++) {
					System.out.print(" ");
				}
				
				base = 20;
			}
			
		System.out.println("|");
		
		for(int i =0 ; i<6 ; i++) {
			System.out.print("+--------------------");
		}
		
		System.out.println("+");
		
		}
	}
	
	/**
	 * Cette méthode nous permet de dessiner le plateau contenant les cases et leur pièces avec une fleche -> qui veux dire que le joueur maitrise cette case et qu'il peut faire son jeu
	 * @param joueur est le joueur a qui est le tour
	 * @param x coordonnée de la case que nous cherchons 
	 * @param y coordonnée de la case que nous cherchons 
	 */
	public void dessinPlateauReponse(Joueur joueur, int x, int y) {

		int nombrePieceCase =0;
		int base = 20;
		String piecesCase = "";

		for(int i =0 ; i<6 ; i++) {
			System.out.print("+--------------------");
		}
		
		System.out.println("+");
		
		for(int j = 0; j<6 ; j++) {
			for(int i = 0 ; i<6 ; i++) {
				System.out.print("|");

				piecesCase = this.dessinCase(i,j);
				System.out.print(piecesCase);
				nombrePieceCase = this.rechercherCase(i,j).getTailleCase();

				if(i == x && j ==y ) {
					System.out.print(Couleur.JAUNE + "->"+Couleur.VIDE);
					base = base -2;
				}
					
				for(int r=3*nombrePieceCase ; r<base ; r++) {
					System.out.print(" ");
				}
				base = 20;
			}
			
		System.out.println("|");
		
		for(int i =0 ; i<6 ; i++) {
			System.out.print("+--------------------");
		}
		
		System.out.println("+");
		
		}
	}
	
	/**
	 * Cette méthode nous permet de dessiner le plateau contenant les cases et leur pièces avec une fleche -> ou <-
	 * qui montre au joueur dans quelle direction il peut faire bouger les pieces qu'il veut
	 * @param focus est la partie de focus en cours
	 * @param p est le plateau qui contient les pieces 
	 * @param joueur est le joueur a qui est le tour
	 * @param x coordonée de la case qu'on cherche 
	 * @param y coordonée de la case qu'on cherche 
	 * @param nombreDeplacer est un entier qui contient le nombre de pieces que nous souhaitons deplacer 
	 */
	
	public void dessinPlateauChoix(Jeu focus, PlateauDeJeu p, Joueur joueur, int x, int y, int nombreDeplacer) {

		int nombrePieceCase =0;
		int base = 20;
		String piecesCase = "";

		for(int i =0 ; i<6 ; i++) {
			System.out.print("+--------------------");
		}
		
		System.out.println("+");
		
		for(int j = 0; j<6 ; j++) {
			for(int i = 0 ; i<6 ; i++) {
				System.out.print("|");
				piecesCase = this.dessinCase(i,j);
				System.out.print(piecesCase);
				nombrePieceCase = this.rechercherCase(i,j).getTailleCase();

				if(i == x && j ==y ) {
					System.out.print(ANSI_YELLOW + "->"+ANSI_RESET);
					base = base -2;
				}
				
				if(x+nombreDeplacer == i && y ==j && x+nombreDeplacer < 6 && focus.verificationCase(x,  y, joueur ,  nombreDeplacer) == 1) {
					System.out.print("<-");
					base= base -2;
				}
				if(x-nombreDeplacer == i && y ==j && x-nombreDeplacer>=0 && focus.verificationCase(x,  y, joueur ,  nombreDeplacer)==1) {
					System.out.print("<-");
					base= base -2;
				}
			
				if(y+nombreDeplacer == j && x ==i && y+nombreDeplacer < 6 && focus.verificationCase(x,  y, joueur ,  nombreDeplacer)==1) {
					System.out.print("<-");
					base= base -2;
				}
				if(y-nombreDeplacer == j && x ==i && y-nombreDeplacer>=0 && focus.verificationCase(x,  y, joueur ,  nombreDeplacer)==1) {
					System.out.print("<-");
					base= base -2;
				}
				
					
				for(int r=3*nombrePieceCase ; r<base ; r++) {
					System.out.print(" ");
				}
				base = 20;
			}
			
		System.out.println("|");
		
		for(int i =0 ; i<6 ; i++) {
			System.out.print("+--------------------");
		}
		
		System.out.println("+");
		
		}
	}
	
	/****************Getter********************/
	
	/**
	 * Un getter 
	 */
	public void getPlateau() {
		
		for(int i=0 ; i<6 ; i++) {
			for(int j=0 ; j<6 ; j++) {
				this.plateau.get(i).get(j).toString();
			}
		}
	}
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		}
	}

