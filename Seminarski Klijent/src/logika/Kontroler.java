/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import domen.Korisnik;
import domen.Oglas;
import domen.Opcode;
import domen.Opcode.Operacija;
import domen.Prijava;
import transfer.TransferServerResponseException;
import forme.GlavnaForma;
import forme.Login;
import forme.PrethodnePrijave;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeli.ModelGlavneForme;
import modeli.ModelPrethodnePrijave;
import transfer.KlijentRequest;
import transfer.Port;
import transfer.ServerResponse;
import transfer.ServerTransfer;

public class Kontroler {
    private static Kontroler instanca;

    private GlavnaForma gf;
    private ServerTransfer komunikacija;
    private ModelGlavneForme modelGF;
    private Korisnik korisnik;
    
    private Kontroler(){
        try {
            komunikacija=new ServerTransfer("localhost", Port.PORT);
        } catch (TransferServerResponseException ex) {
            JOptionPane.showConfirmDialog(null,
            "Neuspesno povezivanje sa serverom, molimo Vas proverite Vasu internet konekciju i zauzetost porta ili pokusajte kasnije. Hvala na strpljenju.",
            "Connection failed", JOptionPane.DEFAULT_OPTION);
            System.exit(0);
        }
    }
    public static Kontroler getInstance(){
        if(instanca==null)
            instanca=new Kontroler();
        return instanca;
    }
    public static void main(String[] args){
        Kontroler.getInstance();
        Login loginf=new Login();
        loginf.setVisible(true);        
    }
    public String ulogujSe(String username, String password) {
        try {
            Korisnik korisnik=new Korisnik(username,password);
            komunikacija.transfer(new KlijentRequest(Operacija.LOGIN, korisnik));
            this.korisnik=korisnik;
        } catch (TransferServerResponseException ex) {
            tryAgain(ex);
            return ex.getMessage();
        }
        return "";
    }
    public String kreirajGlavnuFormu() {
        ServerResponse response;
        try {
            response = komunikacija.transfer(new KlijentRequest(Operacija.VRATI_OGLASE, 0));
        } catch (TransferServerResponseException ex) {
            return ex.getMessage();
        }
        modelGF=new ModelGlavneForme((ArrayList<Oglas>) response.getData());
        gf=new GlavnaForma(modelGF);
        gf.setVisible(true);
        return "";
    }

    public String getOpisOglas(Oglas oglas) {
        try {
            String opis=(String) komunikacija.transfer(new KlijentRequest(Operacija.VRATI_OPIS, oglas)).getData();
//            oglas.setOpis(opis);
            return opis;
        } catch (TransferServerResponseException ex) {
            tryAgain(ex);
            return ex.getMessage();
        }
    }
    
    public String getOpisPrijave(Prijava prijava) {
        try {
            String opis=(String) komunikacija.transfer(new KlijentRequest(Operacija.VRATI_OPIS, prijava.getOglas())).getData();
//            prijava.getOglas().setOpis(opis);
            return opis;
        } catch (TransferServerResponseException ex) {
            tryAgain(ex);
            return ex.getMessage();
        }
    }
    
    public String refreshujOglase() {
        ServerResponse response;
        try {
            response = komunikacija.transfer(new KlijentRequest(Operacija.VRATI_OGLASE, 0));
        } catch (TransferServerResponseException ex) {
            tryAgain(ex);
            return ex.getMessage();
        }
        modelGF.setOglasi((ArrayList<Oglas>) response.getData());
        return "";
    }
    public String kreirajPrethodnePrijave() {
        ServerResponse response;
        try {
            response = komunikacija.transfer(new KlijentRequest(Operacija.VRATI_PRIJAVE, korisnik));
        } catch (TransferServerResponseException ex) {
            tryAgain(ex);
            return ex.getMessage();
        }
        ModelPrethodnePrijave modelPP=new ModelPrethodnePrijave((ArrayList<Prijava>) response.getData());
        PrethodnePrijave ppf=new PrethodnePrijave(modelPP);
        ppf.setVisible(true);
        return "";
    }
    public String posaljiPrijavu(Oglas oglas, String ime, String prezime, String mail, String cv) {
        String pattern=" *";
        if(ime.matches(pattern) || prezime.matches(pattern) || mail.matches(pattern) || cv.matches(pattern))
            return "Molimo Vas popunite sva polja";
        KlijentRequest request=new KlijentRequest(Operacija.PRIJAVI, new Prijava(oglas, cv, ime, prezime, mail, korisnik));
        try {
            komunikacija.transfer(request);
        } catch (TransferServerResponseException ex) {
            tryAgain(ex);
            return ex.getMessage();
        }
        return "";
    }
    private void tryAgain(TransferServerResponseException ex){
        if(ex.getError().equals(Opcode.ErrorCodes.SERVER_ERROR))
            try {
                komunikacija=new ServerTransfer("localhost", Port.PORT);
        } catch (TransferServerResponseException ex1) {
            
        }
    }
}
