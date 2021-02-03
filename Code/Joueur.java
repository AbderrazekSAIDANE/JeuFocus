//package jeuFocus;

import java.util.ArrayList;
/**
 * 
 * @author abderrazeksaidane
 *
 */

public class Joueur {


	/**
	* @param jetonsEnJeu est un ArrayList qui contient les pieces qui sont visibles, en jeu et que le joueur peut jouer
	* @param pieceEnStock est un ArrayList qui contient les pieces qui sont en stock, que le joueur peut remetre en jeu
	* @param numeroDuJoueur est un entier indiquant le numéro du joueur pour ensuite lui attribuer les pieces convenable
	* 1 pour des pieces rouges + 
	* 2 pour des pieces vert *  
	* n
	*/

	private ArrayList<Piece> jetonsEnJeu = new ArrayList<>(18);
	private ArrayList<Piece> pieceEnStock = new ArrayList<>(18);
	private int numeroDuJoueur;

	/**
	 * Constructeur par défaut de l'objet Joueur
	 */
	public Joueur() {
		
	}

	/**
	 * Constructeur de l'objet Joueur
	 * @param num est le numéro ou id du joueur 
	 */
	
	public Joueur(int num) {
		this.numeroDuJoueur = num;
	}
	
	/**
	 * Cette méthode nous permet de faire la distribution des pieces sur les deux joueurs
	 * On rempli les ArrayList jetonsEnJeu avec cette méthode
	 */
	
	public void mainDuJoueur() {

		if(numeroDuJoueur ==1) {
			for(int i=0 ; i<18 ;i++)
				this.jetonsEnJeu.add(new Piece("rouge","+")); 
		}
		if(numeroDuJoueur ==2){
			for(int i=0 ; i<18 ;i++)
				this.jetonsEnJeu.add(new Piece("vert","*")); 
		}
	
	}

	/*************Getter***************/
	
	/**
	 * Un getter
	 * @param k indice du ArrayList jetonsEnJeu
	 * @return une piece d'indice k du ArrayList jetonsEnJeu
	 */
	public Piece getJeton(int k) {

		return (this.jetonsEnJeu.get(k));
	}
	
	/**
	 * getJetonsEnJeu
	 * @return ArrayList jetonsEnJeu
	 */
	
	public ArrayList <Piece> getJetonsEnJeu (){

		return (this.jetonsEnJeu);
	}
	

		
	/**
	 * Un getter
	 * @return un entier indiquant le numero, iditifiant du joueur  
	 */
	
	public int getNumeroDuJoueur() {

		return (this.numeroDuJoueur);
	}
		
	/**
	 * Un getter
	 * @return un entier indiant le nombre de piece morte
	 */
	
	public int getNbPieceMorte() {

		return (18-this.getJetonsEnJeu().size());
	}
	
	public Piece getPieceEnStock() {
		Piece p = new Piece();
		p = pieceEnStock.get(0);
		pieceEnStock.remove(0);
		return p;
	}
	
	/**
	 * Un getter
	 * @return  un entier indiquant le nombre de pieces en stock 
	 */
	
	public int getNbPieceEnStock() {

		return (this.pieceEnStock.size());
	}
	
	/*************Setter***************/	
	/**
	 * 
	 * @param p est une piece qu'on ajoute au stock de piece du joueur
	 */
	
	public void setPieceEnStock(Piece p) {
		this.pieceEnStock.add(p);
	}
	
	/**
	 * Cette méthode nous permet afficher le nombre de pieces en stock et mortent du joeur 
	 * @return un String indiquant le nombre de pieces en stock et le nombre de pieces mortent
	 */
	

	
	public void resetPieceEnStock(){
		
		for(int i = 0; i <getNbPieceEnStock() ; i++){
			this.pieceEnStock.remove(0);
		}
		
	}



	public String toString() {

		if(numeroDuJoueur == 1){
			return (Couleur.ROUGE+"Pieces en Stock : " +this.getNbPieceEnStock()+ "\nPieces mortent : "+this.getNbPieceMorte()+"\nPieces en jeu : "+(this.getJetonsEnJeu().size()-this.getNbPieceEnStock())+Couleur.VIDE);
		}
		else {
			return(Couleur.VERT+"Pieces en Stock : " +this.getNbPieceEnStock()+ "\nPieces mortent : "+this.getNbPieceMorte()+"\nPieces en jeu : "+(this.getJetonsEnJeu().size()-this.getNbPieceEnStock())+Couleur.VIDE);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Joueur j = new Joueur();
		Piece p1 = new Piece("Rouge","*");
		Piece p2 = new Piece("Rouge","*");
		Piece p3 = new Piece("Vert","*");
		
		j.setPieceEnStock(p1);
		j.setPieceEnStock(p2);
		j.setPieceEnStock(p3);
		
		
		System.out.println(j.toString());
		System.out.println(j.getPieceEnStock());
	}

}
