/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package FenetrePrincipal;

import IntroFenetre.FenetrePrincipal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author ninon
 */



public class IntroFenetre extends JFrame {

    public IntroFenetre() {
        // Configuration de la fenêtre
        setTitle("Introduction MasterMind");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Titre
        JLabel titreLabel = new JLabel("Bienvenue dans MasterMind!", JLabel.CENTER);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titreLabel, BorderLayout.NORTH);

        // Description
        JTextArea descriptionArea = new JTextArea(
            "Le jeu MasterMind consiste à deviner une combinaison secrète de couleurs.\n"
            + "Vous avez plusieurs tentatives pour trouver la solution.\n"
            + "- Un point blanc indique une couleur correcte à la bonne position.\n"
            + "- Un point noir indique une couleur correcte à la mauvaise position.\n"
        );
        descriptionArea.setEditable(false);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        // Bouton pour commencer le jeu
        JButton startButton = new JButton("Commencer le jeu");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre d'introduction
                new FenetrePrincipal().setVisible(true); // Ouvre la fenêtre principale
            }
        });
        add(startButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Centre la fenêtre
    }

    public static void main(String[] args) {
        // Lancer la fenêtre d'introduction
     
    SwingUtilities.invokeLater(() -> new IntroFenetre().setVisible(true));
}
    }


 
