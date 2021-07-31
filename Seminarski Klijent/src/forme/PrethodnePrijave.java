/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logika.Kontroler;
import modeli.ModelPrethodnePrijave;

public class PrethodnePrijave extends javax.swing.JFrame {
    private ModelPrethodnePrijave model;
    public PrethodnePrijave(ModelPrethodnePrijave model) {
        initComponents();
        this.model=model;
        tabelaPrijave.setModel(model);
        setujComboAktuelnosti();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPrijave = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnOpis = new javax.swing.JButton();
        comboAktuelnost = new javax.swing.JComboBox();
        labelaAktuelnost = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(700, 200));

        tabelaPrijave.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaPrijave);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Prethodne prijave");

        btnOpis.setText("Opis");
        btnOpis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpisActionPerformed(evt);
            }
        });

        comboAktuelnost.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelaAktuelnost.setText("Aktuelnost");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelaAktuelnost)
                            .addComponent(btnOpis, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboAktuelnost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelaAktuelnost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboAktuelnost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(btnOpis))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpisActionPerformed
        int red=tabelaPrijave.getSelectedRow();
        if(red==-1)
            JOptionPane.showMessageDialog(this, "Molimo Vas prvo kliknite na zeljenu prijavu");
        else{
            Opis o=new Opis(this, true, Kontroler.getInstance().getOpisPrijave(model.getPrijave().get(red)));
            o.setVisible(true);
        }
    }//GEN-LAST:event_btnOpisActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpis;
    private javax.swing.JComboBox comboAktuelnost;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelaAktuelnost;
    private javax.swing.JTable tabelaPrijave;
    // End of variables declaration//GEN-END:variables

    private void setujComboAktuelnosti() {
        comboAktuelnost.removeAllItems();
        comboAktuelnost.addItem("Svi oglasi");
        comboAktuelnost.addItem("Aktuelni oglasi");
        comboAktuelnost.addItem("Istekli oglasi");
        comboAktuelnost.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.update(comboAktuelnost.getSelectedItem());
                model.fireTableDataChanged();
            }
        });
    }
}
