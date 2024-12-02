/*TP MINI PROJET - MASTERMIND
TDC
SORTAIS CASSANDRA
PREVOT NINON 
20 Novembre 2024
27Nomvembre 2024
 */


import java.util.ArrayList;
import mastermind.Combinaison;
import mastermind.Partie;
import mastermind.Pion;
import mastermind.PlateauDeJeu;

/**
 *
 * @author cassandrasortais
 */
public class Mastermind {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Création de quelques pions de différentes couleurs
        Pion pionRouge = new Pion('R');
        Pion pionBleu = new Pion('B');
        Pion pionVert = new Pion('G');

        // Test des accesseurs
        System.out.println("Couleur du pion rouge : " + pionRouge.getCouleur()); // Affiche 'R'
        System.out.println("Couleur du pion bleu : " + pionBleu.getCouleur());   // Affiche 'B'
        System.out.println("Couleur du pion vert : " + pionVert.getCouleur());   // Affiche 'G'

        // Test de la méthode toString()
        System.out.println("Représentation du pion rouge : " + pionRouge); // Affiche 'R'
        System.out.println("Représentation du pion bleu : " + pionBleu);   // Affiche 'B'
        System.out.println("Représentation du pion vert : " + pionVert);   // Affiche 'G'

        // Couleurs disponibles
        ArrayList<Character> couleursDisponibles = new ArrayList<>();
        couleursDisponibles.add('R');
        couleursDisponibles.add('B');
        couleursDisponibles.add('G');
        couleursDisponibles.add('Y');

        // Génération d'une combinaison aléatoire
        Combinaison combinaison1 = Combinaison.genererAleatoire(4, couleursDisponibles);
        System.out.println("Combinaison 1 : " + combinaison1);

        // Création d'une combinaison statique
        Pion[] pionsStatiques = {new Pion('R'), new Pion('B'), new Pion('G'), new Pion('Y')};
        Combinaison combinaison2 = new Combinaison(pionsStatiques);
        System.out.println("Combinaison 2 : " + combinaison2);

        // Comparaison des combinaisons
        int[] resultats = combinaison1.comparer(combinaison2);
        System.out.println("Résultats comparaison : Bien placés = " + resultats[0] + ", Mal placés = " + resultats[1]);
        // Initialisation d'une combinaison secrète et du plateau
        Combinaison combinaisonSecrete = Combinaison.genererAleatoire(4, couleursDisponibles);
        PlateauDeJeu plateau = new PlateauDeJeu(combinaisonSecrete, 10);

        System.out.println("\nCombinaison secrète générée (non affichée pour le joueur) !");

        // Simulation de tentatives par le joueur
        Combinaison tentative1 = new Combinaison(new Pion[]{new Pion('R'), new Pion('B'), new Pion('G'), new Pion('Y')});
        Combinaison tentative2 = new Combinaison(new Pion[]{new Pion('Y'), new Pion('G'), new Pion('B'), new Pion('R')});
        Combinaison tentative3 = combinaisonSecrete; // Pour simuler une victoire

        // Propositions et affichage du plateau après chaque tentative
        plateau.proposerCombinaison(tentative1);
        plateau.afficherPlateau();

        plateau.proposerCombinaison(tentative2);
        plateau.afficherPlateau();

        plateau.proposerCombinaison(tentative3); // Tentative correcte
        plateau.afficherPlateau();

        // Vérification des conditions de victoire ou défaite
        if (plateau.estVictoire()) {
            System.out.println("\nVictoire ! Vous avez trouvé la combinaison secrète !");
        } else if (plateau.estDefaite()) {
            System.out.println("\nDéfaite ! Vous avez épuisé toutes vos tentatives !");
        } else {
            System.out.println("\nLa partie continue !");
        }
        // Création d'une instance de Partie avec 4 couleurs possibles et 10 tours max
        Partie partie = new Partie(4, 10, couleursDisponibles);

        // Lancer la partie
        partie.lancerPartie();
    }
}
