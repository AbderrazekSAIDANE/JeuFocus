//package jeuFocus;
import java.util.*;

/**
 * 
 * @author abderrazeksaidane
 *
 */

public class Jeu {
	
	private Joueur j1 = new Joueur();
	private Joueur j2 = new Joueur();
	private PlateauDeJeu terrain = new PlateauDeJeu();
	
	/**
	 * Constructeur de la classe Jeu 
	 * @param j1 est le premier joueur de la partie 
	 * @param j2 est le deuxième joueur de la partie
	 * @param focus est le plateau qui contient toutes les pieces
	 */
	public Jeu(Joueur j1, Joueur j2, PlateauDeJeu focus) {
		this.j1 = j1;
		this.j2 = j2;
		this.terrain = focus;
	}
	  
	
	/**
	 * Selon les lois du jeu il faut vérifier que le jouer à qui est le tour est sa pièce en haut de la pile 
	 * @param x est la coordonée de la case
	 * @param y est la coordonnée de la case 
	 * @param j est le joueur à qui est le tour
	 * @return
	 */
	
	public int verificationCaseUnique(int x, int y, Joueur j) {
		int retour = 0;
		Case c = new Case();
		c = this.terrain.rechercherCase(x,y);	
		if( c.getTailleCase() > 0){
			if ( c.getPieceHaut().getTypePiece() == j.getJeton(0).getTypePiece()) {
				retour = 1;
			} 
			
		}
		else {
				retour = 0;
			}
		return retour ;
	}
	
	/**
	 * Selon les lois du jeu il faut vérifier que le jouer à qui est le tour est sa pièce en haut de la pile 
	 * Cette méthode réalise cette verification
	 * @param x st la coordonée de la case
	 * @param y est la coordonnée de la case 
	 * @param j est le joueur à qui est le tour
	 * @param nombreDeplacer est un entier indiquant le nombre de pieces qu'on veut déplacer
	 * @return un entier 1 ou 0 
	 */
	
	public int verificationCase(int x, int y,Joueur j, int nombreDeplacer) {
		
		Case c = new Case();
		c = this.terrain.rechercherCase(x,y);

		if ( c.getPieceHaut().getTypePiece() == j.getJeton(0).getTypePiece() && c.getTailleCase()>=nombreDeplacer ) {
			return 1;
		} 
		else {
			return 0;
		}

	}
	
	/**
	 * Moyen de verifier si le joueur à au moins un controle sur les cases du plateau 
	 * @param j est le joueur à qui est le tour
	 * @return un entier 1 ou 0 
	 */
	public int verificationTousCases(Joueur j){
		int caseControler = 0;
		for(int i =0; i < 6; i++){
			for(int k = 0; k < 6; k++){
				if(terrain.rechercherCase(i,k).getTailleCase() > 0){
					if (verificationCaseUnique(i, k, j) == 1)
						caseControler = 1;
				}
			}
		}
		return caseControler;
	}
	
	/**
	 * Cette méthode nous assure le déssin des flèches entrantes 
	 * @param focus 
	 * @param p est une variable contenant les pieces du plateau
	 * @param joueur est le joueur à qui est le tour
	 * @param x est la coordonnée de la case
	 * @param y est la coordonnée de la case
	 * @param nombreDeplacer est un entier contenant le nombre de pièces à deplacer 
	 */
	public void demandeDeDeplacement(Jeu focus, PlateauDeJeu p, Joueur joueur, int x, int y, int nombreDeplacer) {
		
		if(verificationCase(x, y, joueur, nombreDeplacer) == 1 && p.rechercherCase(x,y).getTailleCase()>=nombreDeplacer) {
			p.dessinPlateauChoix(focus, p, joueur, x, y, nombreDeplacer);
		}
	}
	
	public void bougerPieces(Joueur joueur, int xDepart, int yDepart, int direction, int nombre) {
		int xArrive=0,yArrive=0;
		int nombrePieceCase = 0;
		int reponseSysteme = 0;
		Scanner dir = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);

		while(reponseSysteme == 0) {

			if(direction == 8 && yDepart-nombre >= 0 ) { 
				xArrive = xDepart;
				yArrive = yDepart-nombre;
				reponseSysteme = 1;	
				break;
			}
			
			
			if(direction == 4 && xDepart - nombre >= 0 ) {
				xArrive = xDepart-nombre;
				yArrive = yDepart;
				reponseSysteme = 1;
				break;
			}
	
			if(direction == 2 && yDepart + nombre <= 5) {
				xArrive = xDepart;
				yArrive = yDepart+nombre;
				reponseSysteme = 1;
				break;
			}
			
			if(direction == 6 && xDepart + nombre <= 5) {
				xArrive = xDepart+nombre;
				yArrive = yDepart;
				reponseSysteme = 1;
				break;
			}

			System.out.println("Vous sorter du plateau choisissez une autre direction de déplacement");
			direction = dir.nextInt();
			
		}

		(this.terrain.rechercherCase(xArrive, yArrive)).addPile(this.terrain.rechercherCase(xDepart, yDepart),nombre);
		nombrePieceCase = this.terrain.rechercherCase(xArrive, yArrive).getTailleCase();

		if(nombrePieceCase>5) {
			this.removeOverflow(joueur, this.terrain.rechercherCase(xArrive,yArrive));
		}
		
	}
	
	/**
	 * Cette méthode nous permet d'enlever le sur plus de 5 dans une case 
	 * @param j
	 * @param c
	 */
	public void removeOverflow(Joueur j, Case c) {

		int tailleCase = 0;
		tailleCase = c.getTailleCase();
		while(tailleCase > 5) {
			
			if(c.getJetonsCase().get(0).getTypePiece() == c.getJetonsCase().get(tailleCase-1).getTypePiece()) {
				j.setPieceEnStock(c.getJetonsCase().get(0));
			}
			else {
				//Trouver l'adversaire 
				
				if(this.j1.getJeton(0).getTypePiece() != c.getJetonsCase().get(tailleCase-1).getTypePiece()) {
					j1.getJetonsEnJeu().remove(0);
				}
				
				if (this.j2.getJeton(0).getTypePiece() != c.getJetonsCase().get(tailleCase-1).getTypePiece()) {
					j2.getJetonsEnJeu().remove(0);
				}
			}
			c.getJetonsCase().remove(0);
			tailleCase = c.getTailleCase();
		} 
	}

	/**
	 * Méthode pour trouver qui est gagnat
	 * @param j1
	 * @param j2
	 * @return
	 */
	
	public int gagnant(Joueur j1, Joueur j2) {
		int flagGagnant = 0;
					
			
			if(verificationTousCases(j1) == 0 && j1.getNbPieceEnStock() == 0){
				System.out.println(Couleur.VERT+"Le joueur j2 est le gagnant"+Couleur.VIDE);
				flagGagnant = 1;
			}
			if (verificationTousCases(j2) == 0 && j2.getNbPieceEnStock() == 0){
				System.out.println(Couleur.ROUGE+"Le joueur j1 est le gagnant"+Couleur.VIDE);
				flagGagnant = 1;
			}
			
		return flagGagnant;
	}
	
	//Getter Joueur 1
	public Joueur getJ1() {
		return (this.j1);
	}
	
	//Getter Joueur 2
	public Joueur getJ2() {
		return (this.j2);
	}
	
	//Getter Terrain de jeu
	public PlateauDeJeu getTerrain() {
		return (this.terrain);
	}
	
	//Afficher les attributs de la classe 
	
	public String toString() {
		return ("le premier joueur et "+this.getJ1()+"\nLe deuième est "+this.getJ2()+"\nLe plateau est "+this.getTerrain());
	}
	
	public static void main(String[] args) {
		
		/****** Déclaration des variables *******/
		Joueur j1 = new Joueur(1);//+
		Joueur j2 = new Joueur(2);//*
		Scanner x = new Scanner(System.in);
		Scanner y = new Scanner(System.in);
		Scanner pos = new Scanner(System.in);
		Scanner pas = new Scanner(System.in);
		Scanner dir = new Scanner(System.in);
		int xPos = 0, yPos = 0, direction = 0, pasDep =0, position;
		j1.mainDuJoueur();
		j2.mainDuJoueur();
		PlateauDeJeu tableDeJeu = new PlateauDeJeu();
		tableDeJeu.creationCases(j1,j2);	
		Jeu focus = new Jeu(j1,j2,tableDeJeu);
		Joueur j = null;
		j = j1;
		int retour;
		int controle;
		int confDep = 1; 
		String a = "";
		boolean etat = false;
		String reponseStock = "";
		Case c = new Case();
		int joueStock = 0;
		int joueurDisqualifier = 0;
		/**************************** Début du jeu ***************************/
		
		System.out.println(Couleur.JAUNE+"Bienvenue au Jeu Focus !!!\nLa position de chaque case est mentionnée, veuillez vous en service"+Couleur.VIDE);
		tableDeJeu.dessinPlateau();

		/*************** Boucle tournante jusqu'à avoir un gagnant *******************/
		while(focus.gagnant(j1,j2) == 0) {
					
					System.out.println("Le Tour est au joueur " +j.getNumeroDuJoueur()+" "+j.getJeton(0)+"\n"+j.toString()); //Petite notiication du couleur qui à la main 
					
					/***************** Utiliser le stock *******************/
					
					if(j.getNbPieceEnStock() > 0) {

						System.out.println("Voulez vous utiliser votre stock de pièce ?\nTaper O si vous êtes d'accord, N si vous refusez\nSi vous tapez O une piece de votre stock va être ajouter à la case que vous avez sélectionné");
						reponseStock = pos.nextLine();
						
						if(reponseStock.equals("O")) {
							System.out.println("Donner les coordonées où vous souhaitez ajouter une piece");
							joueStock = 1;

							/********************** Coordonnées à jouer **********************/
							// L'exception donne une meilleur qualité au code, dans le cas ou le joueur entre une coordonée nos existante ou un caractère etranger au jeu 
							// Une autre demande de coordonées sera adressée au joueur	
							do {
							while(etat == false){
												try{
													a = pos.nextLine();
													position = Integer.parseInt(a);
													xPos = position / 10;
													yPos = position%10;
													etat = true;
												}
												catch (Exception e){
													System.out.println("Vous n'avez pas entré un chiffre");	
													etat = false ; 
													System.out.println("redonnez les positions");
												}
							}

							etat = false ; 
								if ( (xPos > 5 || xPos < 0) || (yPos < 0 || yPos > 5 ) ){
									System.out.println("redonnez les positions");
								}

							}while( (xPos > 5 || xPos < 0) || (yPos < 0 || yPos > 5) );
							// Avec les coordonées que le joueur a entré on lance la recherche de la case
							c = tableDeJeu.rechercherCase(xPos,yPos);
							// On pioche une piece du stock du joueur courant 
							Piece p = j.getPieceEnStock();
							// on ajoute la pièce à la case
							c.addPiece(p);
							// Si la case contient plus de 5 pieces on enleve le surplus avec traitement  
							focus.removeOverflow(j,c); 
							// On affiche le nouveau plateau 
							tableDeJeu.dessinPlateau();

						}
						// Si un des joueurs n'a plus de case controlé sur le plateau et qu'il dispose d'un stock qui ne souhaite pas utiliser le joueur est automatiquement perdant 
							if(!reponseStock.equals("O") && focus.verificationTousCases(j) == 0 ) {   
								j.resetPieceEnStock();
								joueurDisqualifier = 1 ;
							}

					}

					// Si le joueur disposant d'un stock de pieces ne souhaite pas s'en servire et qu'il a des pieces au niveau du plateau libre à lui de faire de choisir les pièces qu'il veut bouger 
					if( joueStock == 0 && joueurDisqualifier == 0){
						System.out.println("Donner les coordonées où se situent les pièces que vous souhaitez jouer");
						do{	
							/********************** Coordonnées à jouer **********************/
							// L'exception donne une meilleur qualité au code, dans le cas ou le joueur entre une coordonée nos existante ou un caractère etranger au jeu 
							// Une autre demande de coordonées sera adressée au joueur	
							do {
								
								while(etat == false){
													try{
														a = pos.nextLine();
														position = Integer.parseInt(a);
														xPos = position / 10;
														yPos = position%10;
														etat = true;
													}
													catch (Exception e){
														System.out.println("Vous n'avez pas entré un chiffre");	
														etat = false ; 
														System.out.println("redonnez les positions");
													}
								}

								etat = false ; 
									if ( (xPos > 5 || xPos < 0) || (yPos < 0 || yPos > 5 ) ){
										System.out.println("redonnez les positions");
									}

							}while( (xPos > 5 || xPos < 0) || (yPos < 0 || yPos > 5) );

							/********* Verifier que le joueur contrôle la case si non on demande les nouveaux coordonnées ********/

							controle = focus.verificationCaseUnique(xPos, yPos, j);
							if(controle == 0){
								System.out.println("Vous ne controler pas cette case");
								System.out.println("Redonner les coordonées où se situent les pièces que vous souhaitez jouer");
							}

						}while(controle != 1);

						/********************** Pointer la case qu'on souhaite jouer **********************/

						tableDeJeu.dessinPlateauReponse(j,xPos,yPos); 
			
						/********************** Verifier que le pas de déplacement est possible **********************/
						do { // Ce while est très important pour le on déroulement du Jeu 
							// Si un joueur sélectionne un pas de déplacement important mais que ce pas va l'emmener hors la zone du plateau 
							// Cette boucle lui donne la chance de changer son pas en choissant la dériction de déplacement égale à 0
							
							if(tableDeJeu.rechercherCase(xPos,yPos).getTailleCase() == 1){ // Au premier tour toutes les combinaison de jeu ne peuvent faire qu'un pas de déplacement 
								pasDep = 1;
								System.out.println("");
								System.out.println(Couleur.JAUNE+"la case ne contenait qu'une seule pièce par conséquence le pas de déplacement est automatiquement 1"+Couleur.VIDE);
							}
							else {	
								do {
									System.out.println("Donner le pas de déplacement");
									while(etat == false){
														
														try{
															a = pos.nextLine();
															pasDep = Integer.parseInt(a);
															etat = true;
														}
														catch (Exception e){
															System.out.println("Vous n'avez pas entré un chiffre");	
															etat = false ;
															System.out.println("Donner le pas de déplacement");	
														}
									}

									etat = false ; 
									if(tableDeJeu.rechercherCase(xPos,yPos).getTailleCase()<pasDep) {
										System.out.println("Cette case ne contient pas autant de pièces");
										System.out.print("Re");
									}
					
								}while(tableDeJeu.rechercherCase(xPos,yPos).getTailleCase()<pasDep);
							
							}	
							
							/***********************************************************/

							System.out.println("");
							System.out.println("");
							focus.demandeDeDeplacement(focus, tableDeJeu,j,xPos,yPos,pasDep);
							System.out.println(j.toString());
							
							/********************** Direction de déplacement  **********************/
							// L'exception donne une meilleur qualité au code, dans le cas ou le joueur entre une direction nos existante ou un caractère etranger au jeu 
							// Une autre demande de direction sera adressée au joueur
							
							do {
								System.out.println("Donner la direction de deplacement des pièces ");
								
								if(tableDeJeu.rechercherCase(xPos,yPos).getTailleCase()>1)
									System.out.println(Couleur.JAUNE+"Si vous souhaitez changer le pas de déplacement tapez 0"+Couleur.VIDE);
								
								while(etat == false){
														
													try{
														a = pos.nextLine();
														direction = Integer.parseInt(a);
														etat = true;
													}
													catch (Exception e){
														System.out.println("Vous n'avez pas entré un chiffre");	
														etat = false ;
															System.out.println("Donner la direction de deplacement des pièces ");	
													}
								}

								etat = false ; 
								if(direction == 0)
									confDep = 0;
								else 
									confDep = 1;
								
							}while(direction % 2 !=0 && direction <=8);
						}while(confDep == 0);

						/************************** Dessin du réultat final après déplacement de pièces ******************************/
						focus.bougerPieces(j,xPos,yPos,direction,pasDep);
						tableDeJeu.dessinPlateau();
					}
					System.out.println(j.toString());
					joueStock = 0;
					/************************* Changement du joueur ************************/ 
					if(j == j1)
						j = j2;
						
					else 
						j = j1;
			
		}
	}
}
