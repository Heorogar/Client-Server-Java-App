/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Oglas.Senioritet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
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
        napraviComboNaTabeli();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaOglasi = new javax.swing.JTable();
        labelaNaslov = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnOpis = new javax.swing.JButton();
        labelaSenioritet = new javax.swing.JLabel();
        comboSenioritet = new javax.swing.JComboBox();
        labelaPozicija = new javax.swing.JLabel();
        txtPozicija = new javax.swing.JTextField();
        btnPretrazi = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnDodajOglas = new javax.swing.JButton();

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

        labelaNaslov.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelaNaslov.setText("Oglasi");

        btnSave.setText("Sacuvaj izmene");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnOpis.setText("Izmeni opis");
        btnOpis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpisActionPerformed(evt);
            }
        });

        labelaSenioritet.setText("Senioritet:");

        comboSenioritet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelaPozicija.setText("Pozicija:");

        btnPretrazi.setText("Pretrazi poziciju");
        btnPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziActionPerformed(evt);
            }
        });

        btnDelete.setText("Obrisi oglas");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnDodajOglas.setText("Dodaj oglas");
        btnDodajOglas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajOglasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboSenioritet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPretrazi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelaSenioritet)
                            .addComponent(txtPozicija)
                            .addComponent(labelaPozicija)
                            .addComponent(btnOpis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDodajOglas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(labelaNaslov)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelaNaslov)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelaSenioritet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboSenioritet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelaPozicija)
                        .addGap(12, 12, 12)
                        .addComponent(txtPozicija, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPretrazi)
                        .addGap(27, 27, 27)
                        .addComponent(btnOpis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDodajOglas)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(33, 33, 33)
                        .addComponent(btnSave)
                        .addGap(21, 21, 21))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziActionPerformed
        model.update(txtPozicija.getText());
        model.fireTableDataChanged();
    }//GEN-LAST:event_btnPretraziActionPerformed

    private void btnOpisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpisActionPerformed
        int red=tabelaOglasi.getSelectedRow();
        if(red==-1)
            JOptionPane.showMessageDialog(this, "Molimo Vas kliknite na oglas prvo");
        else{
            Opis o=new Opis(this, true, Kontroler.getInstance().getOpis(model.getOglasi().get(red)), model.getOglasi().get(red));
            o.setVisible(true);
        }
    }//GEN-LAST:event_btnOpisActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        printError(Kontroler.getInstance().sacuvajOglase(model.getPromenjeniOglasi()));
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int red=tabelaOglasi.getSelectedRow();
        if(red==-1)
            JOptionPane.showMessageDialog(this, "Molimo Vas kliknite na oglas prvo");
        String error=Kontroler.getInstance().removeOglas(model.getOglasi().get(red));
        if(error.equals("")){
            model.removeOglas(red);
            model.fireTableDataChanged();
        }
        else
            printError(error);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDodajOglasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajOglasActionPerformed
        DodajOglas dodajf=new DodajOglas(this, true);
        dodajf.setVisible(true);
    }//GEN-LAST:event_btnDodajOglasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDodajOglas;
    private javax.swing.JButton btnOpis;
    private javax.swing.JButton btnPretrazi;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox comboSenioritet;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelaNaslov;
    private javax.swing.JLabel labelaPozicija;
    private javax.swing.JLabel labelaSenioritet;
    private javax.swing.JTable tabelaOglasi;
    private javax.swing.JTextField txtPozicija;
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

    private void napraviComboNaTabeli() {
        JComboBox<Senioritet> combo=new JComboBox<>();
        combo.removeAllItems();
        combo.addItem(Senioritet.Senior);
        combo.addItem(Senioritet.Medior);
        combo.addItem(Senioritet.Junior);
        tabelaOglasi.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(combo));    
    }
    
    private void printError(String error){
        if(!error.equals(""))
            JOptionPane.showMessageDialog(this, error);
    }
}
