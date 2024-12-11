/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author cassandrasortais
 */
mport javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetrePrincipal extends javax.swing.JFrame {

    private JButton[][] boutonsGrille; // Boutons pour la grille des tentatives
    private JButton boutonProposer; // Bouton pour proposer une tentative
    private JTextArea zoneResultats; // Zone pour afficher les résultats
    private JTextField[] champsTentative; // Champs pour entrer une tentative
    private Partie partie; // Partie en cours

    public FenetrePrincipal() {
        initComponents();
        // Initialiser une partie avec des paramètres par défaut
        partie = new Partie(4, 12, java.util.Arrays.asList('R', 'B', 'G', 'Y', 'P', 'O'));
        setupUI();
    }

    private void setupUI() {
        // Définir un layout principal
        this.setLayout(new BorderLayout());

        // Panneau central pour la grille des tentatives
        JPanel panneauGrille = new JPanel();
        panneauGrille.setLayout(new GridLayout(12, 4, 5, 5)); // 12 lignes x 4 colonnes, espacement 5px
        boutonsGrille = new JButton[12][4];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 4; j++) {
                boutonsGrille[i][j] = new JButton("_"); // Placeholder
                boutonsGrille[i][j].setEnabled(false); // Les boutons ne sont pas cliquables
                panneauGrille.add(boutonsGrille[i][j]);
            }
        }
        this.add(panneauGrille, BorderLayout.CENTER);

        // Panneau pour la saisie et les actions
        JPanel panneauActions = new JPanel();
        panneauActions.setLayout(new FlowLayout());

        // Champs de saisie pour une tentative
        champsTentative = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            champsTentative[i] = new JTextField(2); // 2 colonnes pour chaque champ
            panneauActions.add(champsTentative[i]);
        }

        // Bouton pour proposer une tentative
        boutonProposer = new JButton("Proposer");
        boutonProposer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proposerTentative();
            }
        });
        panneauActions.add(boutonProposer);

        // Ajouter le panneau des actions au bas
        this.add(panneauActions, BorderLayout.SOUTH);

        // Zone pour afficher les résultats
        zoneResultats = new JTextArea(10, 30);
        zoneResultats.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(zoneResultats);
        this.add(scrollPane, BorderLayout.EAST);

        // Configuration de la fenêtre principale
        this.setTitle("Jeu Mastermind");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // Gérer la logique lorsqu'une tentative est proposée
    private void proposerTentative() {
        String tentative = "";
        for (JTextField champ : champsTentative) {
            tentative += champ.getText().toUpperCase().trim();
        }

        if (tentative.length() != 4) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une combinaison de 4 couleurs.");
            return;
        }

        // Convertir la tentative en tableau de Pions
        Pion[] pions = new Pion[4];
        for (int i = 0; i < 4; i++) {
            char couleur = tentative.charAt(i);
            pions[i] = new Pion(couleur);
        }

        // Proposer la tentative à la partie
        Combinaison combinaison = new Combinaison(pions);
        partie.plateau.proposerCombinaison(combinaison);

        // Mettre à jour l'affichage de la grille
        int tour = partie.plateau.tentatives.size() - 1;
        for (int i = 0; i < 4; i++) {
            boutonsGrille[tour][i].setText(String.valueOf(pions[i].getCouleur()));
        }

        // Mettre à jour les résultats
        zoneResultats.append("Tentative " + (tour + 1) + ": " + tentative + " -> " +
                partie.plateau.reponses.get(tour) + "\n");

        // Vérifier les conditions de victoire ou de défaite
        if (partie.plateau.estVictoire()) {
            JOptionPane.showMessageDialog(this, "Bravo ! Vous avez trouvé la combinaison secrète !");
            boutonProposer.setEnabled(false);
        } else if (partie.plateau.estDefaite()) {
            JOptionPane.showMessageDialog(this, "Dommage ! Vous avez perdu. La combinaison secrète était : " +
                    new String(partie.plateau.combinaisonSecrete.getElements()));
            boutonProposer.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new FenetrePrincipal();
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        Proposer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 102, 204));

        jTextField1.setText("jTextField1");

        Proposer.setText("jButton1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Proposer)
                        .addGap(108, 108, 108))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(Proposer))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(197, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetrePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Proposer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
