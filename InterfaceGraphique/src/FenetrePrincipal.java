
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author cassandrasortais
 */
public class FenetrePrincipal extends javax.swing.JFrame {

    // Création du tableau de boutons
    private JButton[] boutons = new JButton[48];
    private int[] clicsBoutons = new int[48];
    private char[] combinaisonSecrete = {'R', 'V', 'J', 'B'}; // Exemple de combinaison secrète
    private int ligneActuelle = 0; // Suivi de la ligne actuelle (0 à 11)



    /**
     * Creates new form FenetrePrincipal
     */
    public FenetrePrincipal() {
        initComponents(); // Initialise les composants créés par le GUI Builder

        getContentPane().setBackground(new java.awt.Color(204, 204, 255));

        
        // Boucle pour récupérer chaque bouton depuis le GUI Builder (btn1, btn2, ..., btn48)
        for (int i = 0; i < 48; i++) {
            // Création du nom de chaque bouton
            String nomBouton = "btn" + (i + 1); // "btn1", "btn2", ..., "btn48"

            try {
                // Utilisation de la réflexion pour obtenir chaque bouton en fonction de son nom
                boutons[i] = (JButton) this.getClass().getDeclaredField(nomBouton).get(this);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace(); // Affiche l'erreur si le bouton n'est pas trouvé
            }
        }

        // Ajouter des actions aux boutons
        for (int i = 0; i < 48; i++) {
            int index = i; // Capturer l'index dans la boucle
            boutons[i].addActionListener(e -> {
                // Incrémenter le compteur de clics pour le bouton
                clicsBoutons[index]++;

                // Modifier la couleur en fonction du nombre de clics
                switch (clicsBoutons[index] % 4) {
                    case 1: // Premier clic
                        boutons[index].setBackground(Color.RED);
                        break;
                    case 2: // Deuxième clic
                        boutons[index].setBackground(Color.GREEN);
                        break;
                    case 3: // Troisième clic
                        boutons[index].setBackground(Color.YELLOW);
                        break;
                    case 0: // Quatrième clic
                        boutons[index].setBackground(Color.BLUE);
                        break;
                }
            });
        }

        // Initialiser et configurer le bouton "Valider"
        btnValider = new JButton("Valider");
        btnValider.setBounds(10, 10, 100, 30); // Positionner le bouton (à ajuster selon votre interface)
        this.add(btnValider); // Ajouter le bouton à la fenêtre principale

        // Ajouter un ActionListener au bouton "Valider"
        btnValider.addActionListener(e -> validerCombinaison());
    }

  
    private void validerCombinaison() {
   
    // Vérifier que le nombre d'essais est inférieur à 12 avant de valider
    if (ligneActuelle >= 12) {
        JOptionPane.showMessageDialog(this, "Toutes les tentatives ont été utilisées.", "Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
        return; // Sortir sans faire d'autres actions si on a dépassé les 12 essais
    }

    int noirs = 0; // Compteur des bien placés
    int blancs = 0; // Compteur des mal placés
    boolean[] verifieSecrete = new boolean[4]; // Pour éviter de compter 2 fois
    boolean[] verifieTentative = new boolean[4];

    // Récupérer la combinaison du joueur (ligne actuelle)
    char[] tentative = new char[4];
    for (int i = 0; i < 4; i++) {
        // Calculer l'indice pour le bouton en fonction de la ligne actuelle (12 lignes, 4 colonnes)
        int index = ligneActuelle * 4 + i;
        if (index < boutons.length) {
            Color couleur = boutons[index].getBackground();
            if (couleur.equals(Color.RED)) {
                tentative[i] = 'R';
            } else if (couleur.equals(Color.GREEN)) {
                tentative[i] = 'V';
            } else if (couleur.equals(Color.YELLOW)) {
                tentative[i] = 'J';
            } else if (couleur.equals(Color.BLUE)) {
                tentative[i] = 'B';
            }
        }
    }

    // Étape 1 : Compter les noirs (bien placés)
    for (int i = 0; i < 4; i++) {
        if (tentative[i] == combinaisonSecrete[i]) {
            noirs++;
            verifieSecrete[i] = true;
            verifieTentative[i] = true;
        }
    }

    // Étape 2 : Compter les blancs (mal placés)
    for (int i = 0; i < 4; i++) {
        if (!verifieTentative[i]) { // Si ce pion n'est pas déjà bien placé
            for (int j = 0; j < 4; j++) {
                if (!verifieSecrete[j] && tentative[i] == combinaisonSecrete[j]) {
                    blancs++;
                    verifieSecrete[j] = true;
                    break;
                }
            }
        }
    }

    // Afficher le résultat pour cette ligne
    JOptionPane.showMessageDialog(this, 
        "Résultat : " + noirs + " noir(s) et " + blancs + " blanc(s)", 
        "Validation", JOptionPane.INFORMATION_MESSAGE);

    // Vérifier si la combinaison est correcte
    if (noirs == 4) {
        JOptionPane.showMessageDialog(this, "Vous avez gagné !", "Félicitations", JOptionPane.INFORMATION_MESSAGE);
        btnValider.setEnabled(false); // Désactiver le bouton Valider
    } else if (ligneActuelle == 11) {
        // Vérifier si on a atteint la dernière ligne
        JOptionPane.showMessageDialog(this, "Vous avez perdu", "Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
        btnValider.setEnabled(false); // Désactiver le bouton Valider
    } else {
        // Passer à la ligne suivante uniquement si le jeu n'est pas terminé
        ligneActuelle++;
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

        jPanel2 = new javax.swing.JPanel();
        btnValider = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        btn19 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn26 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn28 = new javax.swing.JButton();
        btn29 = new javax.swing.JButton();
        btn30 = new javax.swing.JButton();
        btn31 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btn33 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btn35 = new javax.swing.JButton();
        btn36 = new javax.swing.JButton();
        btn37 = new javax.swing.JButton();
        btn38 = new javax.swing.JButton();
        btn39 = new javax.swing.JButton();
        btn40 = new javax.swing.JButton();
        btn41 = new javax.swing.JButton();
        btn42 = new javax.swing.JButton();
        btn43 = new javax.swing.JButton();
        btn44 = new javax.swing.JButton();
        btn45 = new javax.swing.JButton();
        btn46 = new javax.swing.JButton();
        btn47 = new javax.swing.JButton();
        btn48 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new java.awt.FlowLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setForeground(new java.awt.Color(255, 153, 153));

        btnValider.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnValider.setText("Valider");
        btnValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValiderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnValider)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnValider)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(204, 204, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Bienvenue dans MasterMind !\nChoisis 4 couleurs, clique sur Valider.\nRéessaie avec les indices jusqu'à \ntrouver la combinaison secrète \nBonne chance !");
        jTextArea1.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        getContentPane().add(jLabel1);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanel1.setLayout(new java.awt.GridLayout(12, 4, 5, 5));

        btn1.setName("btn1"); // NOI18N
        jPanel1.add(btn1);
        btn1.getAccessibleContext().setAccessibleDescription("");

        jPanel1.add(btn2);

        btn3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(btn3);

        btn4.setBackground(new java.awt.Color(153, 204, 255));
        btn4.setText(" ");
        jPanel1.add(btn4);

        btn5.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn5);

        btn6.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn6);

        btn8.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn8);

        btn7.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn7);

        btn9.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn9);

        btn10.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn10);

        btn11.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn11);

        btn12.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn12);

        btn13.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn13);

        btn14.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn14);

        btn15.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn15);

        btn16.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn16);

        btn17.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn17);

        btn18.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn18);

        btn19.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn19);

        btn20.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn20);

        btn21.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn21);

        btn22.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn22);

        btn23.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn23);

        btn24.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn24);

        btn25.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn25);

        btn26.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn26);

        btn27.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn27);

        btn28.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn28);

        btn29.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn29);

        btn30.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn30);

        btn31.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn31);

        btn32.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn32);

        btn33.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn33);

        btn34.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn34);

        btn35.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn35);

        btn36.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn36);

        btn37.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn37);

        btn38.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn38);

        btn39.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn39);

        btn40.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn40);

        btn41.setBackground(new java.awt.Color(153, 204, 255));
        btn41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn41ActionPerformed(evt);
            }
        });
        jPanel1.add(btn41);

        btn42.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn42);

        btn43.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn43);

        btn44.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn44);

        btn45.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn45);

        btn46.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn46);

        btn47.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn47);

        btn48.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(btn48);

        getContentPane().add(jPanel1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValiderActionPerformed

// TODO add your handling code here:
        validerCombinaison();
    }//GEN-LAST:event_btnValiderActionPerformed

    private void btn41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn41ActionPerformed

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
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn17;
    private javax.swing.JButton btn18;
    private javax.swing.JButton btn19;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn23;
    private javax.swing.JButton btn24;
    private javax.swing.JButton btn25;
    private javax.swing.JButton btn26;
    private javax.swing.JButton btn27;
    private javax.swing.JButton btn28;
    private javax.swing.JButton btn29;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn30;
    private javax.swing.JButton btn31;
    private javax.swing.JButton btn32;
    private javax.swing.JButton btn33;
    private javax.swing.JButton btn34;
    private javax.swing.JButton btn35;
    private javax.swing.JButton btn36;
    private javax.swing.JButton btn37;
    private javax.swing.JButton btn38;
    private javax.swing.JButton btn39;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn40;
    private javax.swing.JButton btn41;
    private javax.swing.JButton btn42;
    private javax.swing.JButton btn43;
    private javax.swing.JButton btn44;
    private javax.swing.JButton btn45;
    private javax.swing.JButton btn46;
    private javax.swing.JButton btn47;
    private javax.swing.JButton btn48;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnValider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
