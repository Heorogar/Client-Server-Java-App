/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import javax.swing.JOptionPane;
import logika.Kontroler;

public class PokretanjeServera extends javax.swing.JFrame {

    public PokretanjeServera(String url, String username, String pass) {
        initComponents();
        popuniDefaultPolja(url, username, pass);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelaNaslov = new javax.swing.JLabel();
        labelaUputstvo = new javax.swing.JLabel();
        labelaUrl = new javax.swing.JLabel();
        labelaUsername = new javax.swing.JLabel();
        labelaSifra = new javax.swing.JLabel();
        txtUrl = new javax.swing.JTextField();
        txtSifra = new javax.swing.JTextField();
        btnPokreniServer = new javax.swing.JButton();
        txtUsername = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(700, 250));

        labelaNaslov.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelaNaslov.setText("Pokretanje servera");

        labelaUputstvo.setText("Unesite parametre baze:");

        labelaUrl.setText("Url:");

        labelaUsername.setText("Username:");

        labelaSifra.setText("Sifru:");

        btnPokreniServer.setText("Pokreni server");
        btnPokreniServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokreniServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelaUputstvo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelaUsername)
                                    .addComponent(labelaUrl))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUrl)
                                    .addComponent(txtUsername)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPokreniServer))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelaSifra)
                        .addGap(0, 252, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(txtSifra)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(labelaNaslov)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelaNaslov)
                .addGap(18, 18, 18)
                .addComponent(labelaUputstvo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelaUrl)
                    .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelaUsername)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelaSifra)
                    .addComponent(txtSifra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnPokreniServer)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPokreniServerActionPerformed
        String error=Kontroler.getInstance().pokreniServer(txtUrl.getText(),txtUsername.getText(),txtSifra.getText());
        if(!error.equals(""))
            JOptionPane.showMessageDialog(this, error);
        else{
            Kontroler.getInstance().kreirajGlavnuFormu();
            dispose();
        }
    }//GEN-LAST:event_btnPokreniServerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPokreniServer;
    private javax.swing.JLabel labelaNaslov;
    private javax.swing.JLabel labelaSifra;
    private javax.swing.JLabel labelaUputstvo;
    private javax.swing.JLabel labelaUrl;
    private javax.swing.JLabel labelaUsername;
    private javax.swing.JTextField txtSifra;
    private javax.swing.JTextField txtUrl;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    private void popuniDefaultPolja(String url, String username, String pass) {
        txtUrl.setText(url);
        txtUsername.setText(username);
        txtSifra.setText(pass);
    }
}
