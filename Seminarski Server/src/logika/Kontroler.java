/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import domen.Firma;
import domen.Oglas;
import forme.GlavnaForma;
import forme.PokretanjeServera;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import modeli.ModelGlavneForme;
import niti.PrihvatiKonekcije;
import operacije.dodajOglasTransaction;
import operacije.removeOglasTransaction;
import operacije.setujOpisTransaction;
import operacije.updateOglasTransaction;
import operacije.vratiOglaseServerTransaction;
import operacije.vratiOpisServerTransaction;
import persistance.DbBroker;
import persistance.PropertyKonstante;
import persistance.PropertyReader;
import transfer.Port;

public class Kontroler {
    private static Kontroler instanca;
    private PrihvatiKonekcije server;
    private GlavnaForma gf;
    private DbBroker db;
    private ModelGlavneForme modelGF;
    
    private Kontroler(){
        
    }
    public static Kontroler getInstance(){
        if(instanca==null)
            instanca=new Kontroler();
        return instanca;
    }
    public static void main(String[] args){
        PropertyReader pr=new PropertyReader(PropertyKonstante.PROPERTY_FILE_PATH);
        PokretanjeServera psf=new PokretanjeServera(pr.vratiProperty(PropertyKonstante.URL), 
                pr.vratiProperty(PropertyKonstante.USER), pr.vratiProperty(PropertyKonstante.PASS));
        psf.setVisible(true);
    }
    public String pokreniServer(String url, String username, String pass) {
        if(DbBroker.testirajParametre(url, username, pass)){
            db=new DbBroker(url, username, pass);
            server=new PrihvatiKonekcije(Port.PORT, url, username, pass);
            server.start();
            return "";
        }
        return "Pogresni parametri, pokusajte ponovo";
    }

    public String setOpis(Oglas oglas, String opis) {
        oglas.setOpis(opis);
        return (String) new setujOpisTransaction(db).transaction(oglas);
    }
    public String getOpis(Oglas o){
        return (String) new vratiOpisServerTransaction(db).transaction(o);
    }

    public void kreirajGlavnuFormu() {
        ArrayList<Oglas> oglasi=(ArrayList<Oglas>) new vratiOglaseServerTransaction(db).transaction(null);
        modelGF=new ModelGlavneForme(oglasi);
        gf=new GlavnaForma(modelGF);
        gf.setVisible(true);    
    }

    public String removeOglas(Oglas o) {
        return (String) new removeOglasTransaction(db).transaction(o);
    }

    public String dodajOglas(String polozaj, Oglas.Senioritet senioritet, String datumIsteka, Firma firma, String opis) {
        if(polozaj.isEmpty() || opis.isEmpty())
            return "Molimo Vas popunite sva polja";
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        Oglas o;
        try {
            o = new Oglas(polozaj, firma, senioritet, sdf.parse(datumIsteka), opis);
        } catch (ParseException ex) {
            return "Datum mora biti u dd.mm.yyyy formatu";
        }
        int pk= (int) new dodajOglasTransaction(db).transaction(o);
        o.setId(pk);
        modelGF.getOglasi().add(o);
        modelGF.fireTableDataChanged();
        return "";
    }

    public String sacuvajOglase(HashSet<Oglas> promenjeniOglasi) {
        for(Oglas o:promenjeniOglasi){
            if(o.getPozicija().equals("") || o.getOpis().equals(""))
                return "Molimo Vas popunite sva prazna polja";
            String error=(String) new updateOglasTransaction(db).transaction(o);
            if (!error.equals(""))
                return error;
        }
        modelGF.getPromenjeniOglasi().clear();
        return "";
    }
}
