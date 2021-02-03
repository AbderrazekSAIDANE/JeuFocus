//package jeuFocus;
import java.util.*;

/**
 * 
 * @author abderrazeksaidane
 *
 */

public class Case {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	/**
	* @param jetons est un arrayList qui va contenir toute les pièces dans une case
	* @param x & 
	* @param y coordonées de la case
	*/
	
	private ArrayList<Piece> jetons = new ArrayList<>();
	private int x;
	private int y;
	
	/**
	 * Constructeur par défaut de l'objet Case
	 */
	public Case() {

	}

	/**
	 * Constructeur de l'objet Case
	 * @param x coordonée de la case
	 * @param y coordonée de la case
	 */
	
	public Case(int x, int y) {

		this.x = x;
		this.y = y;

	}
	
	/**
	 * Cette méthode nous permet d'ajouter une piece dans une case au début du jeu
	 * @param p est la piece qu'on va ajouter à la case
	 */
	
	public void addPiece(Piece p) {

		this.jetons.add(p);
	}
	
	/**
	 * Cette méthode est une amélioration de addPiece, afin d'ajouter toute une pile et d'en supprimée une autre
	 * Car dans le jeu on bouge de(s) piece(s) se qui fai que des cases gagnent de(s) pièce(s) 
	 * @param c est la case à laquelle on veut ajouter une série de pieces
	 * @param nombreDePiece nombreDePiece est le nombre de pieces qu'on veut ajouter à la case
	 */
	
	public void addPile(Case c, int nombreDePiece) {

		int pointeurPieceCaseDepart = c.jetons.size()-nombreDePiece;
		int tailleCaseArrivee = this.jetons.size();
		
		for (int i=0 ; i<nombreDePiece ; i++) {
			
			this.jetons.add( (tailleCaseArrivee) , (c.jetons.get(pointeurPieceCaseDepart)) );
			tailleCaseArrivee = this.jetons.size();
			pointeurPieceCaseDepart ++;
		}	

		for( int i =0 ; i<nombreDePiece ; i++) {

			pointeurPieceCaseDepart = c.jetons.size();
			c.jetons.remove(pointeurPieceCaseDepart-1);
		}

	}
	
	/******************** Getter ***********************/
	
	/**
	 * Un getter 
	 * @return un entier indiquant la taille du arrayList de la case
	 */
	
	public int getTailleCase() {

		return (this.jetons.size());
	}
	
	/***** getJetonsCase() *********/
	/**
	 * Un getter 
	 * @return un ArrayList des pieces dans la case
	 */
	
	public ArrayList<Piece> getJetonsCase(){

		return (this.jetons);
	}
	

	/**
	 * Un getter
	 * @return une piece située au sommet de la pile de la case
	 */
	
	public Piece getPieceHaut() {

		int sizeRow = 0;

		sizeRow = this.jetons.size();
		
		return (this.jetons.get(sizeRow-1));
	}
	
	/***** getCoordonnee() *********/
	/**
	 * Un getter 
	 * @return un String indiquant les coordonées de la case 
	 */
	
	public String getCoordonnee() {

		return ("Les coordonnées de la case sont : x= "+this.x+", y= "+this.y);
	}
	

	/************************ toString ************************/
	/**
	 * Cette méthode nous permet afficher toutes les pieces d'une case 
	 * @return un String indiquant les pieces de la case
	 */
	public String toString(){

		String piecesCase = "";
		
		for( int i=0 ; i<this.jetons.size() ; i++) {			
			piecesCase += this.jetons.get(i).toString(); 
		}

		return piecesCase;
	}

		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Piece p = new Piece("rouge","+");
		Piece p2 = new Piece("vert","*");
		Case c = new Case(0,0);
		c.addPiece(p);
		c.addPiece(p2);
		System.out.println(c.toString());
		
	}

}
