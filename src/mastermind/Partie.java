/*
Classe crée par Ninon Prevot et Cassandra SORTAIS
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
import java.util.List;
import java.util.Scanner;

public class Partie {

    private PlateauDeJeu plateau; // Gère la logique du jeu
    private ArrayList<Character> couleursDisponibles; // Couleurs disponibles pour les pions
    private Pion[][] matricePions; // Matrice pour stocker les pions des tentatives (12 lignes x 4 colonnes)

    // Constructeur
    public Partie(int tailleCombinaison, int nbToursMax, List<Character> couleursDisponibles) {
        Combinaison combinaisonSecrete = Combinaison.genererAleatoire(tailleCombinaison, (ArrayList<Character>) couleursDisponibles);
        this.plateau = new PlateauDeJeu(combinaisonSecrete, nbToursMax);
        this.couleursDisponibles = new ArrayList<>(couleursDisponibles);
        this.matricePions = new Pion[12][4]; // 12 tentatives, 4 pions par tentative
    }

    // Méthode pour afficher les règles du jeu
    public void afficherRegles() {
        System.out.println("Bienvenue dans le jeu Mastermind !");
        System.out.println("Règles du jeu :");
        System.out.println("- Vous devez deviner la combinaison secrète.");
        System.out.println("- La combinaison est composée de pions de couleurs.");
        System.out.println("- Vous aurez un nombre limité de tentatives.");
        System.out.println("- Après chaque tentative, vous serez informé du nombre de pions bien placés (noirs) et mal placés (blancs).");
        System.out.println("- Pour finir, vous devez trouver la combinaison secrète avant d'atteindre le nombre maximum de tentatives.");
    }

    // Méthode pour lancer la partie
    public void lancerPartie() {
        Scanner scanner = new Scanner(System.in);
        int tour = 0;

        while (!plateau.estVictoire() && !plateau.estDefaite() && tour < 12) {
            plateau.afficherPlateau();
            afficherMatrice(); // Affiche la matrice des tentatives

            System.out.println("\nProposez une combinaison :");
            String combinaisonEntree = scanner.nextLine().toUpperCase();

            if (combinaisonEntree.length() != 4) {
                System.out.println("Erreur : La combinaison doit avoir exactement 4 pions.");
                continue;
            }

            Pion[] pionsTentative = new Pion[4];
            for (int i = 0; i < 4; i++) {
                char couleur = combinaisonEntree.charAt(i);
                if (!couleursDisponibles.contains(couleur)) {
                    System.out.println("Erreur : Couleur invalide, utilisez une des couleurs disponibles : " + couleursDisponibles);
                    continue;
                }
                pionsTentative[i] = new Pion(couleur);
            }

            ajouterTentative(tour, pionsTentative); // Ajoute la tentative à la matrice
            Combinaison tentative = new Combinaison(pionsTentative);
            plateau.proposerCombinaison(tentative);

            if (plateau.estVictoire()) {
                System.out.println("Bravo ! Vous avez trouvé la combinaison secrète !");
                break;
            } else if (plateau.estDefaite() || tour == 11) {
                System.out.println("Dommage, vous avez perdu ! Le nombre maximum de tentatives est atteint.");
                break;
            }

            tour++; // Passe au tour suivant
        }
        scanner.close();
    }

    // Méthode pour terminer la partie
    public void terminerPartie() {
        if (plateau.estVictoire()) {
            System.out.println("Félicitations ! Vous avez gagné !");
        } else {
            System.out.println("Vous avez perdu ! La combinaison secrète était : " + plateau.getCombinaisonSecrete());
        }
    }

    // Méthode pour ajouter une tentative dans la matrice
    public void ajouterTentative(int tour, Pion[] pions) {
        if (tour < 0 || tour >= 12) {
            System.out.println("Erreur : Le numéro de tour est invalide.");
            return;
        }
        if (pions.length != 4) {
            System.out.println("Erreur : Une tentative doit contenir exactement 4 pions.");
            return;
        }
        matricePions[tour] = pions; // Ajout des pions dans la ligne correspondante
    }

    // Méthode pour afficher la matrice
    public void afficherMatrice() {
        System.out.println("Plateau des tentatives :");
        for (int i = 0; i < matricePions.length; i++) {
            System.out.print("Tour " + (i + 1) + ": ");
            for (int j = 0; j < matricePions[i].length; j++) {
                if (matricePions[i][j] != null) {
                    System.out.print(matricePions[i][j].getCouleur() + " "); // Affiche la couleur du pion
                } else {
                    System.out.print("_ "); // Place vide
                }
            }
            System.out.println();
        }
    }
}
