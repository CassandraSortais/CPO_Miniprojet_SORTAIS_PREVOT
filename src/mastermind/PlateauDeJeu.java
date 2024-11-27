/*
Classe crée par PREVOT Ninon 
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

public class PlateauDeJeu {

    // Les attributs ci-dessous permettent de garder une trace du déroulement du jeu 
    private Combinaison combinaisonSecrete;
    private ArrayList<Combinaison> tentatives;
    private ArrayList<String> reponses;
    private int nbToursMax;

    public PlateauDeJeu(Combinaison combinaisonSecrete, int nbToursMax) { // Le constructeur prépare le plateau de jeu     en configurant les limites et la combinaison secrète
        this.combinaisonSecrete = combinaisonSecrete;
        this.nbToursMax = nbToursMax;
        this.tentatives = new ArrayList<>();
        this.reponses = new ArrayList<>();
    }

    // Cette méthode met à jour l'état du plateau après chaque tentative et génère les indices pour guider le joueur
    public void proposerCombinaison(Combinaison tentative) {
        if (tentatives.size() >= nbToursMax) {
            System.out.println("Le nombre maximum de tours est atteint !");
            return; // Si le joueur à déjà atteint le nombre maximum de tentatives, un message est affiché et la méthode                                               s'arrête avec return
        }

        tentatives.add(tentative);
        int[] resultats = combinaisonSecrete.comparer(tentative);
        String reponse = resultats[0] + " noirs, " + resultats[1] + " blancs"; // Une chaîne de texte est construite avec les résultats, par exemple : "2 noirs, 1 blanc" et ajoutée à la liste réponses
        reponses.add(reponse);
    }

    public void afficherPlateau() { // La méthode fournit une vue complète de l'état actuel du jeu, ce qui va aider le joeur à analyser ses progrès
        System.out.println("=== Plateau de jeu ===");
        for (int i = 0; i < tentatives.size(); i++) {
            System.out.println("Tentative " + (i + 1) + " : " + tentatives.get(i) + " -> " + reponses.get(i));
        }
    }

    public boolean estVictoire() { // Cette méthode détermine si le joueur a gagné en trouvant la bonne combinaison.

        if (tentatives.isEmpty()) {
            return false;
        }
        Combinaison derniereTentative = tentatives.get(tentatives.size() - 1);
        return combinaisonSecrete.comparer(derniereTentative)[0] == combinaisonSecrete.getElements().length;
    }

    // Méthode pour vérifier la défaite
    public boolean estDefaite() { // Cette méthode indique si le joueur a perdu en épuisant toutes ses tentatives.
        return tentatives.size() >= nbToursMax && !estVictoire();
    }

    String getCombinaisonSecrete() { // Cette méthode permet d’afficher la combinaison secrète.
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
