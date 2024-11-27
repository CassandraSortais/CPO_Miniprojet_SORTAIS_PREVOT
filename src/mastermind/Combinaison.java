/*
Classe crée par SORTAIS Cassandra
27/11/2024
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mastermind;

/**
 *
 * @author cassandrasortais
 */
import java.util.ArrayList;
import java.util.Random;

public class Combinaison {

    private Pion[] elements; //  représentation de la combinaison sous forme d'un tableau de pions
    private int taille;

    public Combinaison(Pion[] elements) { // Ce constructeur permet de créer une nouvelle combinaison avec le tableau        de pions comme argument
        this.elements = elements;
        this.taille = elements.length;
    }

    // Le but est de créer une combinaison aléatoire de taille donnée en utilisant une liste de couleurs disponibles
    public static Combinaison genererAleatoire(int taille, ArrayList<Character> couleursDisponibles) {
        Pion[] pions = new Pion[taille];
        Random random = new Random();
        for (int i = 0; i < taille; i++) {
            char couleurAleatoire = couleursDisponibles.get(random.nextInt(couleursDisponibles.size()));
            pions[i] = new Pion(couleurAleatoire);
        }
        return new Combinaison(pions);
    }

//Cette méthode est utile pour créer automatiquement une combinaison secrète
    public int[] comparer(Combinaison autre) { // Comparaison de cette combinaison avec une autre pour calculer les pions bien placés et mal placés
        int noirs = 0; // Bien placés
        int blancs = 0; // Mal placés

        boolean[] verifieeCetteCombinaison = new boolean[this.taille];
        boolean[] verifieeAutreCombinaison = new boolean[autre.taille];

        // Calcul des pions noirs, qui correspondent au pions qui sont bien placés
        for (int i = 0; i < this.taille; i++) {
            if (this.elements[i].getCouleur() == autre.elements[i].getCouleur()) {
                noirs++;
                verifieeCetteCombinaison[i] = true;
                verifieeAutreCombinaison[i] = true;
            }
        }

        // Calcul des pions blancs, qui correspondent aux pions qui sont mal placés
        for (int i = 0; i < this.taille; i++) {
            if (!verifieeCetteCombinaison[i]) { // Si ce pion n'est pas déjà bien placé
                for (int j = 0; j < autre.taille; j++) {
                    if (!verifieeAutreCombinaison[j]
                            && this.elements[i].getCouleur() == autre.elements[j].getCouleur()) {
                        blancs++;
                        verifieeAutreCombinaison[j] = true;
                        break;
                    }
                }
            }
        }

        return new int[]{noirs, blancs};
    }
    //La méthode retourne un tableau contenant donc deux valeurs : noirs et blancs

    @Override
    public String toString() { // création d'une chaine de caractères représentant la combinaisons
        StringBuilder sb = new StringBuilder();
        for (Pion pion : elements) {
            sb.append(pion.toString()); // avec la méthode toString(), on parcourt chaque pions dans "elements"
        }
        return sb.toString();
    }
    // Cela permet d'afficher facilement une combinaison sous forme lisible, par exemple : "Rouge Bleu Vert Jaune")
    // Getter pour les éléments

    public Pion[] getElements() {
        return elements;
    }

    int getTaille() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
