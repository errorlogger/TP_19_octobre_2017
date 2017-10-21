/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boundaries;


import fr.jm.todolist.dao.TdListDAO;
import fr.jm.todolist.dto.TdEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jean marc
 */
public class MainJframe extends javax.swing.JFrame {

    private TdEvent event ;
    private TdListDAO dao;
    private Connection cn;
    private DefaultTableModel model;
    private DefaultComboBoxModel modelCombo;
    private Map<String,Integer> mapIdCategorie;
    private Map<Integer,Integer> mapRowId;
    
    public MainJframe() throws SQLException {
        initComponents();
        
        setVisible(true);
        
        //cn = DatabaseConnection.getInstance();
        cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/todoliste","root","");
        
        //instanciation d'un objet (DTO) TdEvent, variable d'instance
        event = new TdEvent();
        
        //instanciation d'un objet (DAO) TdListDAO , variable d'instance
        dao = new TdListDAO(cn);
        
        //instanciation d'un objet mapRowId qui permet de garder un lien entre le numéro de la ligne
        //et l'id correspondant à cette même ligne
        //lorsqu'une ligne est sélectionnée un label affiche sont id en bas de la fenetre
        mapRowId = new HashMap<>();
        
        //instanciation d'un objet mapIdCategorie qui permet de garder un lien entre les ids des categorie et les valeur pour
        //identification de la foreign key id_categorie de la table des taches
        //les categories dont les clés et les ids sont les valeurs <nom_categorie,id>
        Map<String,Integer> mapIdCategorie = new HashMap<>();
        
        //tant qu'aucune ligne n'est sélectionnée les boutons sont désactivés
        jModify.setEnabled(false);
        jAdd.setEnabled(false);
        jDelete.setEnabled(false);
        
        initTable();
    }

    
    
    public void initTable() throws SQLException{
        /* initialisation du tableau et de la combobox via des models*/
        model = (DefaultTableModel) jTable.getModel();
        modelCombo = (DefaultComboBoxModel) jCategories.getModel();
        
        //a chaque appel de la fonction le nombre de row du tableau et les elements de la combobox sont mis à zéro
        model.setRowCount(0);
        modelCombo.removeAllElements();
        
        //la fonction getAllTdEventAsDao() renvoie un objet DAO dont on extrait le rst, variable d'instance de cet objet
        ResultSet rst =dao.getAllTdEventAsDao().getRst();
        
        // positionnement du curseur
        rst.beforeFirst();
        
        // instanciation d'un tableau d'objet pour remplir notre tableau
        Object row[] = new Object[5];
        
        //numéro de la ligne
        int rowNumber = 0;
        
        //reset du map de correspondance row/id car à chaque appel de initTable cet correspondances changent
        mapRowId.clear();
        
        while(rst.next()){
            
            mapRowId.put(rowNumber,rst.getInt(1));//remplissage de mapRowId
            
            //remplissage de row
            row[0]=rst.getString(2);
            row[1]=rst.getString(3);
            row[2]=rst.getBoolean(4);
            row[3]=rst.getBoolean(5);
            
            //ajout des données au table via son model
            model.addRow(row);
            
            //incrémentation de row car row sert de clé dans mapRowId
            rowNumber++;
        }
        //la fonction getFkMap de la class TdListDAO renvoie un map <categorie,id>
        mapIdCategorie = dao.getFkMap();
        
        //extraction de toute les clés pour les afficher dans la combo box 
        //les valeurs sont mises dans un sorted set
        Set<String> lCat =  mapIdCategorie.keySet();
        
        
        //instanciation d'un treeSet et convertion du set en TreeSet pour classer les catégorie en ordre alphabétique via une boucle for
        SortedSet<String> listCategorie = new TreeSet<>();
        for (String entry : lCat) {
            listCategorie.add(entry);
            
        }
        //ajout des éléments classés par ordre alphabetique dans la combo box via son model
        for (String o : listCategorie) {
            
            modelCombo.addElement(o);
            
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jText = new javax.swing.JTextArea();
        jCategories = new javax.swing.JComboBox();
        jAdd = new javax.swing.JButton();
        jModify = new javax.swing.JButton();
        jDelete = new javax.swing.JButton();
        jReset = new javax.swing.JButton();
        jUrgent = new javax.swing.JCheckBox();
        jFait = new javax.swing.JCheckBox();
        jMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Taches", "Catégorie", "Urgent", "Fait"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable.setColumnSelectionAllowed(true);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);
        if (jTable.getColumnModel().getColumnCount() > 0) {
            jTable.getColumnModel().getColumn(0).setPreferredWidth(350);
            jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable.getColumnModel().getColumn(2).setMaxWidth(70);
            jTable.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        jText.setColumns(20);
        jText.setRows(5);
        jText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jText);

        jCategories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCategoriesMouseClicked(evt);
            }
        });
        jCategories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCategoriesActionPerformed(evt);
            }
        });

        jAdd.setText("Add");
        jAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddActionPerformed(evt);
            }
        });

        jModify.setText("Modify");
        jModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jModifyActionPerformed(evt);
            }
        });

        jDelete.setText("Delete");
        jDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteActionPerformed(evt);
            }
        });

        jReset.setText("Reset");
        jReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetActionPerformed(evt);
            }
        });

        jUrgent.setText("Urgent");
        jUrgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUrgentActionPerformed(evt);
            }
        });

        jFait.setText("Terminé");
        jFait.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFaitActionPerformed(evt);
            }
        });

        jMessage.setText("Id = ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jUrgent, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jFait))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jModify, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jReset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jCategories)
                            .addComponent(jUrgent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jModify, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFait))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jReset, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCategoriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCategoriesMouseClicked
        jModify.setEnabled(true);
        jAdd.setEnabled(true);
    }//GEN-LAST:event_jCategoriesMouseClicked

    private void jAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddActionPerformed
         //affectation des variables de l'objet event, variable d'instance de notre classe jframe
        setEvent(event);
        try {
            dao.addTdEvent(event);
            initTable();
            resetAll();
        } catch (SQLException ex) {
            Logger.getLogger(MainJframe.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jAddActionPerformed

    private void jModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jModifyActionPerformed
         //affectation des variables de l'objet event, variable d'instance de notre classe jframe
        setEvent(event);
        try {
               dao.modifyTdEvent(event);
               initTable();
               resetAll();
                     
        }catch (SQLException ex) {
               Logger.getLogger(MainJframe.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }//GEN-LAST:event_jModifyActionPerformed

    private void jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteActionPerformed
         //affectation des variables de l'objet event, variable d'instance de notre classe jframe
        setEvent(event);
        
        try {
            dao.deleteTdEvent(event);
            System.out.println(event.getTdId());
            
             initTable();
             resetAll();
        } catch (SQLException ex) {
            Logger.getLogger(MainJframe.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jDeleteActionPerformed

    private void jResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetActionPerformed
        resetAll();
        jModify.setEnabled(false);
        jAdd.setEnabled(false);
        jDelete.setEnabled(false);
        
    }//GEN-LAST:event_jResetActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        //lors d'un click sur une ligne du tableau on recupère ses données via le model et on renseigne tous les champs
        //de notre frame avec ces dites données
        //au passage on recupère le numéro de la ligne sélectionnée pour retrouver son id correspondante grace à notre map
        //mapRowId
        int row = jTable.getSelectedRow();
        
        jText.setText((String) model.getValueAt(row, 0));
        jCategories.setSelectedItem((String) model.getValueAt(row, 1));
        jUrgent.setSelected((Boolean) model.getValueAt(row, 2));
        jFait.setSelected((Boolean) model.getValueAt(row, 3));
        jMessage.setText("ID = " + mapRowId.get(jTable.getSelectedRow()).toString());
        
        jAdd.setEnabled(false);
        jDelete.setEnabled(true);
        jModify.setEnabled(false);
        
        //on affecte les variables de notre objet event avec ces données
        setEvent(event);
        
      
        
    }//GEN-LAST:event_jTableMouseClicked

    private void jTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextKeyTyped
        if (jTable.getSelectedRow() != -1){
            jModify.setEnabled(true);
            jAdd.setEnabled(true);
        }else
        jAdd.setEnabled(true);
    }//GEN-LAST:event_jTextKeyTyped

    private void jCategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCategoriesActionPerformed
         if (!jText.getText().isEmpty() || !(jText.getText().equals(""))) {
           jModify.setEnabled(true);
           jAdd.setEnabled(true);
         }
    }//GEN-LAST:event_jCategoriesActionPerformed

    private void jFaitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFaitActionPerformed
         if (!jText.getText().isEmpty() || !(jText.getText().equals(""))) {
           jModify.setEnabled(true);
           jAdd.setEnabled(true);
       }
    }//GEN-LAST:event_jFaitActionPerformed

    private void jUrgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUrgentActionPerformed
         if (!jText.getText().isEmpty() || !(jText.getText().equals(""))) {
           jModify.setEnabled(true);
           jAdd.setEnabled(true);
       }System.out.println("yes");
    }//GEN-LAST:event_jUrgentActionPerformed

    public void resetAll(){
        jMessage.setText("ID = ");
        jText.setText("");
        jCategories.setSelectedIndex(0);
        jUrgent.setSelected(false);
        jFait.setSelected(false);
        
        jModify.setEnabled(false);
        jAdd.setEnabled(false);
        jDelete.setEnabled(false);
    }
    
    private void setEvent(TdEvent ev){
        
        //affectation des variables de l'objet event, variable d'instance de notre classe jframe
        event.setTdTache(jText.getText());
        event.setTdCategorie(mapIdCategorie.get((String)jCategories.getSelectedItem()));
        event.setTdUrgent(jUrgent.isSelected());
        event.setTdFait(jFait.isSelected()); 
        
        //si les données proviennent d'une ligne existante dans notre tableau, on peut alors renseigner l'id
        //getSelectedRow nous renvoie -1 si aucune ligne n'est sélectionnée
        if (jTable.getSelectedRow() != -1){
            event.setTdId(mapRowId.get(jTable.getSelectedRow()));
            
        }
    }
    /**
     * @param args the command line arguments
     */
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAdd;
    private javax.swing.JComboBox jCategories;
    private javax.swing.JButton jDelete;
    private javax.swing.JCheckBox jFait;
    private javax.swing.JLabel jMessage;
    private javax.swing.JButton jModify;
    private javax.swing.JButton jReset;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JTextArea jText;
    private javax.swing.JCheckBox jUrgent;
    // End of variables declaration//GEN-END:variables
}
