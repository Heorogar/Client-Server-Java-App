/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Oglas.Senioritet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logika.Kontroler;
import modeli.ModelGlavneForme;

public class GlavnaForma extends javax.swing.JFrame {
    private ModelGlavneForme model;
    public GlavnaForma(ModelGlavneForme model) {
        initComponents();
        this.model=model;
        tabelaOglasi.setModel(model);
        setujComboSenioritet();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaOglasi = new javax.swing.JTable();
        btnPrijava = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnPrethodnePrijave = new javax.swing.JButton();
        labelaTitula = new javax.swing.JLabel();
        comboSenioritet = new javax.swing.JComboBox();
        labelaSenioritet = new javax.swing.JLabel();
        labelaPozicija = new javax.swing.JLabel();
        txtPretragaPozicije = new javax.swing.JTextField();
        btnPretragaPozicije = new javax.swing.JButton();
        btnOpis = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(700, 200));

        tabelaOglasi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelaOglasi);

        btnPrijava.setText("Prijavi se");
        btnPrijava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrijavaActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnPrethodnePrijave.setText("Tvoje prijave");
        btnPrethodnePrijave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrethodnePrijaveActionPerformed(evt);
            }
        });

        labelaTitula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelaTitula.setText("Oglasi");

        comboSenioritet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelaSenioritet.setText("Senioritet:");

        labelaPozicija.setText("Pozicija:");

        btnPretragaPozicije.setText("Pretrazi poziciju");
        btnPretragaPozicije.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretragaPozicijeActionPerformed(evt);
            }
        });

        btnOpis.setText("Pogledaj Opis");
        btnOpis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOpis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboSenioritet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtPretragaPozicije, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPrijava, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrethodnePrijave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPretragaPozicije, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelaSenioritet)
                            .addComponent(labelaPozicija))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(labelaTitula)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addComponent(labelaTitula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelaSenioritet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboSenioritet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelaPozicija)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPretragaPozicije, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPretragaPozicije)
                        .addGap(66, 66, 66)
                        .addComponent(btnOpis)
                        .addGap(48, 48, 48)
                        .addComponent(btnPrijava)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrethodnePrijave)
                        .addGap(46, 46, 46)
                        .addComponent(btnRefresh)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPretragaPozicijeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretragaPozicijeActionPerformed
        model.update(txtPretragaPozicije.getText());
        model.fireTableDataChanged();
    }//GEN-LAST:event_btnPretragaPozicijeActionPerformed

    private void btnOpisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpisActionPerformed
        int red=tabelaOglasi.getSelectedRow();
        if(red==-1)
            JOptionPane.showMessageDialog(this, "Molimo Vas kliknite na oglas prvo");
        else{
            Opis o=new Opis(this, true, Kontroler.getInstance().getOpisOglas(model.getOglasi().get(red)));
            o.setVisible(true);
        }
    }//GEN-LAST:event_btnOpisActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        String error=Kontroler.getInstance().refreshujOglase();
        if(error.equals(""))
            model.fireTableDataChanged();
        else
            printError(error);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnPrethodnePrijaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrethodnePrijaveActionPerformed
        printError(Kontroler.getInstance().kreirajPrethodnePrijave());
    }//GEN-LAST:event_btnPrethodnePrijaveActionPerformed

    private void btnPrijavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrijavaActionPerformed
        int red=tabelaOglasi.getSelectedRow();
        if(red==-1)
            JOptionPane.showMessageDialog(this, "Molimo Vas kliknite na oglas prvo");
        else{
            PrijavaNaOglas prijava=new PrijavaNaOglas(this, true, model.getOglasi().get(red));
            prijava.setVisible(true);
        }
    }//GEN-LAST:event_btnPrijavaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpis;
    private javax.swing.JButton btnPrethodnePrijave;
    private javax.swing.JButton btnPretragaPozicije;
    private javax.swing.JButton btnPrijava;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox comboSenioritet;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelaPozicija;
    private javax.swing.JLabel labelaSenioritet;
    private javax.swing.JLabel labelaTitula;
    private javax.swing.JTable tabelaOglasi;
    private javax.swing.JTextField txtPretragaPozicije;
    // End of variables declaration//GEN-END:variables

    private void setujComboSenioritet() {
        comboSenioritet.removeAllItems();
        for(Senioritet s:Senioritet.values())
            comboSenioritet.addItem(s);
        comboSenioritet.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.update((Senioritet) comboSenioritet.getSelectedItem());
                model.fireTableDataChanged();
            }
        });
    }
    private void printError(String error){
        if(!error.equals(""))
            JOptionPane.showMessageDialog(this, error);
    }
}
