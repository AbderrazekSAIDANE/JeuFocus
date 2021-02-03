//package jeuFocus;
enum Couleur {
    ROUGE, VERT, JAUNE, VIDE;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Grâce à cette redéfinition, on bénéficie de la coloration dans
    // le terminal de façon claire et lisble au niveau de l'écriture
    // du code (cf. main() ci-dessous).
    public String toString() {
	String affichage = "";
        switch (this) {
	case ROUGE :
	    affichage = "\u001B[31m";
	    break;
	case VERT :
	    affichage = "\u001B[32m";
	    break;
	case JAUNE :
	    affichage = "\u001B[93m";
	    break;
	case VIDE :
	    affichage = "\u001B[0m";
	    break;
	}
	return affichage;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public static void main(String args[]) {
	System.out.println(Couleur.ROUGE + "Annick " +
			   Couleur.VERT + "Alexandre " +
			   Couleur.JAUNE + "is..." +
			   Couleur.VIDE + "the boss !");
    }
}
