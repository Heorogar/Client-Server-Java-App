/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Oglas implements Serializable{
    public static enum Senioritet{
        Svi,
        Junior,
        Medior,
        Senior
    };
    private String pozicija;
    private Firma firma;
    private Senioritet senioritet;
    private Date datumIsteka;
    private String opis;
    private int id;
    
    public Oglas(String pozicija, Firma firma, Senioritet senioritet, Date datumIsteka, String opis) {
        this.pozicija = pozicija;
        this.firma=firma;
        this.senioritet = senioritet;
        this.datumIsteka = datumIsteka;
        this.opis = opis;
    }

    public Oglas(String pozicija, Firma firma, Senioritet senioritet, Date datumIsteka, int id) {
        this.pozicija = pozicija;
        this.firma = firma;
        this.senioritet = senioritet;
        this.datumIsteka = datumIsteka;
        this.opis = "";
        this.id = id;
    }
    
    public Oglas(String pozicija, Firma firma, Senioritet senioritet, Date datumIsteka) {
        this.pozicija = pozicija;
        this.firma=firma;
        this.senioritet = senioritet;
        this.datumIsteka = datumIsteka;
        this.opis="";
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }
    
    public String getOpis() {
        return opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }
    public String getPozicija() {
        return pozicija;
    }    
    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }
    public Senioritet getSenioritet() {
        return senioritet;
    }
    public void setSenioritet(Senioritet senioritet) {
        this.senioritet = senioritet;
    }
    public Date getDatumIsteka() {
        return datumIsteka;
    }
    public void setDatumIsteka(Date datumIsteka) {
        this.datumIsteka = datumIsteka;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Oglas other = (Oglas) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
