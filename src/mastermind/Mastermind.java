/*
TP MINI PROJET - MASTERMIND
TDC
SORTAIS CASSANDRA
PREVOT NINON 
20 Novembre 2024
 
 */
package mastermind;

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
    }
}

