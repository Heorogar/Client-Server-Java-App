/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Oglas;
import domen.Oglas.Senioritet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModelGlavneForme extends AbstractTableModel{
    private ArrayList<Oglas> oglasi;
    private ArrayList<Oglas> sviOglasi;

    public ModelGlavneForme(ArrayList<Oglas> oglasi) {
        this.oglasi = oglasi;
        sviOglasi=(ArrayList<Oglas>) oglasi.clone();
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
        return 3;
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
        }
        return res;
    }
    public void update(Senioritet s) {
        if(s.equals(Senioritet.Svi)){
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
}
