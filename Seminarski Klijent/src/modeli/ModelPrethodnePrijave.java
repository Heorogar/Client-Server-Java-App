/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Prijava;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

public class ModelPrethodnePrijave extends AbstractTableModel{
    private ArrayList<Prijava> prijave;
    private ArrayList<Prijava> svePrijave;

    public ModelPrethodnePrijave(ArrayList<Prijava> prijave) {
        this.prijave = prijave;
        svePrijave=(ArrayList<Prijava>) prijave.clone();
    }
    public ArrayList<Prijava> getPrijave() {
        return prijave;
    }

    public void setPrijave(ArrayList<Prijava> prijave) {
        svePrijave=(ArrayList<Prijava>) prijave.clone();
        this.prijave = prijave;
    }
    @Override
    public int getRowCount() {
        return prijave.size();
    }
    @Override
    public int getColumnCount() {
        return 6;
    }
    @Override
    public String getColumnName(int columnIndex) {
        String res="";
        switch(columnIndex){
            case 0: res="Pozicija";
                break;
            case 1: res="Firma";
                break;
            case 2: res="Senioritet";
                break;
            case 3: res="Datum isteka";
                break;
            case 4: res="CV link";
                break;
            case 5: res="Mail";
                break;
        }
        return res;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prijava p=prijave.get(rowIndex);
        Object res=null;
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        switch(columnIndex){
            case 0: res=p.getOglas().getPozicija(); 
                break;
            case 1: res=p.getOglas().getFirma().getIme(); 
                break;
            case 2: res=p.getOglas().getSenioritet(); 
                break;
            case 3: res=sdf.format(p.getOglas().getDatumIsteka()); 
                break;
            case 4: res=p.getCvLink(); 
                break; 
            case 5: res=p.getMail();
                break;
        }
        return res;
    }
    public void update(Object selectedItem) {
        if(selectedItem.equals("Svi oglasi")){
            if(svePrijave.size()!=prijave.size())
                prijave=(ArrayList<Prijava>) svePrijave.clone();
            return;
        }
        ArrayList<Prijava> temp=new ArrayList<>();
        temp.ensureCapacity(svePrijave.size());
        if(selectedItem.equals("Aktuelni oglasi")){
            for(int i=0;i<svePrijave.size();i++)
                if(svePrijave.get(i).getOglas().getDatumIsteka().after(new Date()))
                    temp.add(svePrijave.get(i));
        }
        else
            for(int i=0;i<svePrijave.size();i++)
                if(svePrijave.get(i).getOglas().getDatumIsteka().before(new Date()))
                    temp.add(svePrijave.get(i));
        prijave=temp;
    }
}
