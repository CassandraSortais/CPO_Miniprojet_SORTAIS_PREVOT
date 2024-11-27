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

    private PlateauDeJeu plateau;  //gère la logique du jeu, comme les tentatives, les réponses, et les conditions de victoire ou de défaite.
    private ArrayList<Character> couleursDisponibles;

    // Le constructeur prépare une partie en configurant toutes les règles nécessaires comme la combinaison secrète, le plateau de jeu et les couleurs
    public Partie(int tailleCombinaison, int nbToursMax, List<Character> couleursDisponibles) {
        Combinaison combinaisonSecrete = Combinaison.genererAleatoire(tailleCombinaison, (ArrayList<Character>) couleursDisponibles); // 
        this.plateau = new PlateauDeJeu(combinaisonSecrete, nbToursMax);
        this.couleursDisponibles = new ArrayList<>(couleursDisponibles);
    }

    public void afficherRegles() {     // Méthode pour afficher les règles du jeu et les explications qui aide le joeur à comprendre comment jouer
        System.out.println("Bienvenue dans le jeu Mastermind !");
        System.out.println("Règles du jeu :");
        System.out.println("- Vous devez deviner la combinaison secrète.");
        System.out.println("- La combinaison est composée de pions de couleurs.");
        System.out.println("- Vous aurez un nombre limité de tentatives.");
        System.out.println("- Après chaque tentative, vous serez informé du nombre de pions bien placés (noirs) et mal placés (blancs).");
        System.out.println("- Pour finir, vous devez trouver la combinaison secrète avant d'atteindre le nombre maximum de tentatives.");
    }

    public void lancerPartie() {     //Cette méthode lance une partie et enregistre la progression du jeu 
        Scanner scanner = new Scanner(System.in);
        while (!plateau.estVictoire() && !plateau.estDefaite()) {
            plateau.afficherPlateau();
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

            // Création de la tentative et ajout au plateau
            Combinaison tentative = new Combinaison(pionsTentative);
            plateau.proposerCombinaison(tentative);

            // On vérifie si c'est une victoire ou une défaite
            if (plateau.estVictoire()) {
                System.out.println("Bravo ! Vous avez trouvé la combinaison secrète !");
                break;
            } else if (plateau.estDefaite()) {
                System.out.println("Dommage, vous avez perdu ! Le nombre maximum de tentatives est atteint.");
                break;
            }
        }
        scanner.close();
    }

    // Cette méthode termine la partie et affiche le résultat
    public void terminerPartie() {
        if (plateau.estVictoire()) {
            System.out.println("Félicitations ! Vous avez gagné !");
        } else {
            System.out.println("Vous avez perdu ! La combinaison secrète était : " + plateau.getCombinaisonSecrete());
        }
    }
}
