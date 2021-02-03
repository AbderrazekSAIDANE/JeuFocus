//package jeuFocus;

/**
 * 
 * @author abderrazeksaidane
 *
 */

public class Piece {
	
	/**
	* @param couleurs &
	* @param typeDePiece sont les pricipales caractéristiques de chaque pièce
	*/
	
	private String couleurs;
	private String typeDePiece;

	/**
	 * Constructeur par défaut de l'objet Piece
	 */
	
	public Piece() {
		this.couleurs = null;
		this.typeDePiece = null;
	}


	/**
	 * Constructeur de l'objet Piece
	 * @param couleurs défini la couleurs de la piece Rouge/Vert
	 * @param type défini le type de la piece +/* 
	 */
	
	public Piece(String couleurs, String type){

		this.couleurs = couleurs;
		this.typeDePiece = type;
	}
	
	/**
	 * Getter du typeDePiece
	 * @return le type de la pièce * ou +
	 */
	
	public String getTypePiece() {
		return (this.typeDePiece);
	}


	/**
	 * toString() affiche la pièce
	 */
	
	public String toString() {

		String color = "";
		
		if (couleurs.compareToIgnoreCase("Vert") == 0) {
			color = Couleur.VERT+"["+this.typeDePiece+"]"+Couleur.VIDE;
		}
		if (couleurs.compareToIgnoreCase("Rouge") == 0) {
			color = Couleur.ROUGE+"["+this.typeDePiece+"]"+Couleur.VIDE;
		}
		return color;
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Piece p1 = new Piece("Rouge","*");
		//System.out.println(p1.toString());
		System.out.println(p1.toString());
	}

}
