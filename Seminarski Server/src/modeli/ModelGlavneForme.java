/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Firma;
import domen.Oglas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ModelGlavneForme extends AbstractTableModel{
    private ArrayList<Oglas> oglasi;
    private ArrayList<Oglas> sviOglasi;
    private HashSet<Oglas> promenjeniOglasi;
    
    public ModelGlavneForme(ArrayList<Oglas> oglasi){
        this.oglasi=oglasi;
        sviOglasi=(ArrayList<Oglas>) oglasi.clone();
        promenjeniOglasi=new HashSet<>();
    }

    public HashSet<Oglas> getPromenjeniOglasi() {
        return promenjeniOglasi;
    }
    public ArrayList<Oglas> getOglasi() {
        return oglasi;
    }
    public void setOglasi(ArrayList<Oglas> oglasi) {
        this.oglasi = oglasi;
        sviOglasi=(ArrayList<Oglas>) oglasi.clone();
    }
    @Override
    public int getRowCount() {
        return oglasi.size();
    }
    @Override
    public int getColumnCount() {
        return 4;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Oglas oglas=oglasi.get(rowIndex);
        Object res=null;
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        switch(columnIndex){
            case 0: res=oglas.getPozicija(); 
                break;
            case 1: res=oglas.getSenioritet(); 
                break;
            case 2: res=sdf.format(oglas.getDatumIsteka()); 
                break;
            case 3: res=oglas.getFirma().getIme(); 
                break;
        }
        return res;
    }
    @Override
    public String getColumnName(int column) {
        String res="";
        switch(column){
            case 0: res="Pozicija"; 
                break;
            case 1: res="Senioritet";
                break;
            case 2: res="Datum isteka";
                break;
            case 3: res="Firma";
                break;
        }
        return res;
    }
    public void update(Oglas.Senioritet s) {
        if(s.equals(Oglas.Senioritet.Svi)){
            if(sviOglasi.size()!=oglasi.size())
                oglasi=(ArrayList<Oglas>) sviOglasi.clone();
            return;
        }
        ArrayList<Oglas> temp=new ArrayList<>();
        temp.ensureCapacity(sviOglasi.size());
        for(int i=0;i<sviOglasi.size();i++)
            if(sviOglasi.get(i).getSenioritet().equals(s))
                temp.add(sviOglasi.get(i));
        oglasi=temp;
    }
    public void update(String pozicija) {
        ArrayList<Oglas> temp=new ArrayList<>();
        temp.ensureCapacity(oglasi.size());
        String pattern=".*"+pozicija+".*";
        for(int i=0;i<oglasi.size();i++)
            if(oglasi.get(i).getPozicija().matches(pattern))
                temp.add(oglasi.get(i));
        oglasi=temp;
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        Oglas oglas=oglasi.get(rowIndex);
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        switch(columnIndex){
            case 0: oglas.setPozicija((String) o); 
                break;
            case 1: oglas.setSenioritet((Oglas.Senioritet) o);
                break;
            case 2: try {oglas.setDatumIsteka(sdf.parse((String) o));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.mm.yyyy");
            }
                break;
            case 3: oglas.setFirma((Firma) o);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
        promenjeniOglasi.add(oglas);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void removeOglas(int red) {
        Oglas oglas=oglasi.get(red);
        if(sviOglasi.size()==oglasi.size())
            sviOglasi.remove(red);
        else
            for(Oglas o:sviOglasi)
                if(o.equals(oglas)){
                    sviOglasi.remove(o);
                    return;
                }
        oglasi.remove(red);
    }

    public HashSet<Firma> vratiFirme() {
        HashSet<Firma> firme=new HashSet<>();
        for(int i=0;i<sviOglasi.size();i++)
            if(!firme.contains(sviOglasi.get(i).getFirma()))
                firme.add(sviOglasi.get(i).getFirma());
        return firme;    
    }

    public void update(Firma firma) {
        if(firma.getId()==-1){
            if(sviOglasi.size()!=oglasi.size())
                oglasi=(ArrayList<Oglas>) sviOglasi.clone();
            return;
        }
        ArrayList<Oglas> temp=new ArrayList<>();
        for(int i=0;i<sviOglasi.size();i++)
            if(sviOglasi.get(i).getFirma().equals(firma))
                temp.add(sviOglasi.get(i));
        oglasi=temp;
    }
    
    
}
