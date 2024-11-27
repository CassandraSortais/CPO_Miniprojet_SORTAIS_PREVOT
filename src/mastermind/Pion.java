/*
Classe crée par Ninon PREVOT et SORTAIS Cassandra SORTAIS découverte du projet donc c'est pour cela qu'on l'a fait à 2
20/11/2024
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mastermind;

/**
 *
 * @author cassandrasortais
 */
public class Pion {

    // Attribut représentant la couleur du pion
    private Character couleur;

    // Constructeur pour initialiser un pion avec une couleur donnée
    public Pion(Character couleur) {
        this.couleur = couleur;
    }

    // Accesseur pour obtenir la couleur du pion
    public Character getCouleur() {
        return couleur;
    }

    // Redéfinition de la méthode toString() pour afficher la couleur du pion
    @Override
    public String toString() {
        return couleur.toString();
    }
}
