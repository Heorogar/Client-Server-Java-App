/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import domen.Firma;
import domen.Korisnik;
import domen.Oglas;
import domen.Oglas.Senioritet;
import domen.Opcode;
import domen.Prijava;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.TransferServerResponseException;

public class DbBroker {
    private Connection conn;
    private String url;
    private String user;
    private String pass;
    private PropertyReader pr;
    
    public DbBroker(String url, String user, String pass) {
        pr=new PropertyReader(PropertyKonstante.PROPERTY_FILE_PATH);
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    public static boolean testirajParametre(String url, String username, String pass){
        try {
            PropertyReader pr=new PropertyReader(PropertyKonstante.PROPERTY_FILE_PATH);
            Class.forName(pr.vratiProperty(PropertyKonstante.DRIVER));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try (Connection connection=DriverManager.getConnection(url,username,pass)){
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    public void otvoriKonekciju(){
        try {
            Class.forName(pr.vratiProperty(PropertyKonstante.DRIVER));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn=DriverManager.getConnection(url,user,pass);
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void zatvoriKonekciju(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void commit(){
        try {
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void rollback(){
        try {
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Oglas> vratiOglase() throws SQLException {
        String upit="SELECT Pozicija,Senioritet,Datum_isteka,Firma,PK FROM oglasi";
        ArrayList<Oglas> oglasi=new ArrayList<>();
        try (PreparedStatement ps=conn.prepareStatement(upit)){
            try(ResultSet rs=ps.executeQuery(upit)){
                while(rs.next()){
                    Senioritet senioritet=Senioritet.Svi;
                    switch(rs.getString("Senioritet")){
                        case "Junior": senioritet=Senioritet.Junior;
                            break;
                        case "Medior": senioritet=Senioritet.Medior;
                            break;
                        case "Senior": senioritet=Senioritet.Senior;
                            break;
                    }
                    oglasi.add(new Oglas(rs.getString("Pozicija"), vratiFirmu(rs.getInt("Firma")),senioritet,rs.getDate("Datum_isteka"),rs.getInt("PK")));
                }
            }
        }
        return oglasi;
    }

    public void setOpis(Oglas oglas, String opis) throws SQLException {
        String upit="UPDATE oglasi SET Opis=? WHERE PK=?";
        try(PreparedStatement ps=conn.prepareStatement(upit)){
            ps.setString(1, opis);
            ps.setInt(2, oglas.getId());
            ps.executeUpdate();
        }
    }

    public String getOpisServer(Oglas o) throws SQLException {
        String upit="SELECT Opis FROM oglasi WHERE PK=?";
        try (PreparedStatement ps=conn.prepareStatement(upit)){
            ps.setInt(1, o.getId());
            try(ResultSet rs=ps.executeQuery()){
                rs.next();
                return rs.getString("Opis");
            }
        }
    }
    public String getOpisKlijent(Oglas o) throws TransferServerResponseException, SQLException {
        String upit="SELECT Opis FROM oglasi WHERE PK=?";
        try (PreparedStatement ps=conn.prepareStatement(upit)){
            ps.setInt(1, o.getId());
            try(ResultSet rs=ps.executeQuery()){
                if(!rs.next())
                    throw new TransferServerResponseException(Opcode.ErrorCodes.OGLAS_EXPIRED_ERROR);
                return rs.getString("Opis");
            }
        } 
    }

    public void removeOglas(Oglas o) throws SQLException {
        String upit="DELETE FROM oglasi WHERE PK=?";
        try(PreparedStatement ps=conn.prepareStatement(upit)){
            ps.setInt(1,o.getId());
            ps.executeUpdate();
        }
    }

    public int dodajOglas(Oglas oglas) throws SQLException {
        String upit="INSERT INTO oglasi (Pozicija,Senioritet,Datum_isteka,Opis,Firma) VALUES (?,?,?,?,?)";
        try(PreparedStatement ps=conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, oglas.getPozicija());
            String s="Svi";
            switch(oglas.getSenioritet()){
                case Senior: s="Senior";
                    break;
                case Medior: s="Medior";
                    break;
                case Junior: s="Junior";
                    break;
            }
            ps.setString(2, s);
            ps.setDate(3, new Date(oglas.getDatumIsteka().getTime()));
            ps.setString(4, oglas.getOpis());
            ps.setInt(5, oglas.getFirma().getId());
            ps.executeUpdate();
            try(ResultSet rs=ps.getGeneratedKeys()){
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public boolean postojiKorisnik(Korisnik korisnik) throws SQLException {
        String upit="SELECT * FROM korisnici WHERE Ime=?";
        try(PreparedStatement ps=conn.prepareStatement(upit)){
            ps.setString(1, korisnik.getIme());
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next() && rs.getString("Ime").equals(korisnik.getIme()) && rs.getString("Sifra").equals(korisnik.getSifra()))
                    return true;
            }
        }
        return false;
    }

    public void prijaviNaOglas(Prijava prijava) throws TransferServerResponseException {
        if(prijava.getOglas().getDatumIsteka().before(new java.util.Date()))
            throw new TransferServerResponseException(Opcode.ErrorCodes.DUPLA_PRIJAVA_ERROR);
        String upit="INSERT INTO prijave VALUES (?,?,?,?,?,?)";
        try(PreparedStatement ps=conn.prepareStatement(upit)){
            ps.setString(1, prijava.getKorisnik().getIme());
            ps.setInt(2, prijava.getOglas().getId());
            ps.setString(3, prijava.getMail());
            ps.setString(4, prijava.getCvLink());
            ps.setString(5, prijava.getIme());
            ps.setString(6, prijava.getPrezime());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new TransferServerResponseException(Opcode.ErrorCodes.DUPLA_PRIJAVA_ERROR);
        }
    }

    public void updateOglas(Oglas oglas) throws SQLException {
        String upit="UPDATE oglasi SET Pozicija=?,Senioritet=?,Datum_isteka=?,Firma=? WHERE PK=?";
        try(PreparedStatement ps=conn.prepareStatement(upit)){
            ps.setString(1, oglas.getPozicija());
            String s="Svi";
            switch(oglas.getSenioritet()){
                case Senior: s="Senior";
                    break;
                case Medior: s="Medior";
                    break;
                case Junior: s="Junior";
                    break;
            }
            ps.setString(2, s);
            ps.setDate(3, new Date(oglas.getDatumIsteka().getTime()));
            ps.setInt(4, oglas.getFirma().getId());
            ps.setInt(5, oglas.getId());
            ps.executeUpdate();
        }
    }

    public Object vratiPrijave(Korisnik korisnik) throws SQLException {
        String upit="SELECT * FROM prijave WHERE Korisnik=?";
        ArrayList<Prijava> prijave=new ArrayList<>();
        try(PreparedStatement ps=conn.prepareStatement(upit)){
            ps.setString(1, korisnik.getIme());
            try(ResultSet rs=ps.executeQuery()){
                while(rs.next()){
                    try {
                        Oglas oglas = vratiOglas(rs.getInt("Oglas"));
                        Prijava p=new Prijava(oglas, rs.getString("CV"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("Mail"), korisnik);
                        prijave.add(p);
                    } catch (TransferServerResponseException ex) {
                    }
                }
            }
        }
        return prijave;
    }
    
    private Oglas vratiOglas(int id) throws TransferServerResponseException, SQLException{
        String upit="SELECT * FROM oglasi WHERE PK=?";
        Oglas o=null;
        try(PreparedStatement ps=conn.prepareStatement(upit)){
            ps.setInt(1, id);
            try(ResultSet rs=ps.executeQuery()){
                if(!rs.next())
                    throw new TransferServerResponseException(Opcode.ErrorCodes.OGLAS_EXPIRED_ERROR);
                Senioritet s=Senioritet.Svi;
                switch(rs.getString("Senioritet")){
                    case "Senior": s=Senioritet.Senior;
                        break;
                    case "Medior": s=Senioritet.Medior;
                        break;
                    case "Junior": s=Senioritet.Junior;
                        break;
            }
                o=new Oglas(rs.getString("Pozicija"), vratiFirmu(rs.getInt("Firma")), s, rs.getDate("Datum_isteka"), id);
            }
        } 
        return o;
    }
    
    private Firma vratiFirmu(int id) throws SQLException{
        String upit="SELECT * FROM firme WHERE PK=?";
        try(PreparedStatement ps=conn.prepareStatement(upit)){
            ps.setInt(1, id);
            try(ResultSet rs=ps.executeQuery()){
                rs.next();
                return new Firma(id, rs.getString("Ime"));
            }
        }
    }
}
