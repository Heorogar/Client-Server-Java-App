/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;
import domen.Korisnik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import logika.Kontroler;
import operacije.loginTransaction;
import operacije.prijaviNaOglasTransaction;
import operacije.vratiOglaseKlijentTransaction;
import operacije.vratiOglaseServerTransaction;
import operacije.vratiOpisKlijentTransaction;
import operacije.vratiPrijaveTransaction;
import persistance.DbBroker;
import transfer.KlijentRequest;
import transfer.ServerResponse;

public class UsluziKlijenta extends Thread{
    private Socket soket;
    private Korisnik klijent;
    private DbBroker db;
    
    UsluziKlijenta(Socket soket, DbBroker db) {
        this.soket=soket;
        this.db=db;
    }
    @Override
    public void run() {
        while(true){
            try {
                KlijentRequest request=primiZahtev();
                ServerResponse response=odgovoriNaZahtev(request);
                posaljiOdgovor(response);
            } catch (IOException ex) {
                try {
                    soket.close();
                } catch (IOException ex1) {
                    Logger.getLogger(UsluziKlijenta.class.getName()).log(Level.SEVERE, null, ex1);
                }
                return;
            }
        }
    }

    private KlijentRequest primiZahtev() throws IOException {
        KlijentRequest request=null;
        try{
            ObjectInputStream input=new ObjectInputStream(soket.getInputStream());
            request=(KlijentRequest) input.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsluziKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return request;
    }

    private void posaljiOdgovor(ServerResponse response) throws IOException {
        if(response==null)
            throw new IOException();
        ObjectOutputStream output=new ObjectOutputStream(soket.getOutputStream());
        output.writeObject(response);
    }

    private ServerResponse odgovoriNaZahtev(KlijentRequest request) {
        ServerResponse response=null;
        switch(request.getOperacija()){
            case LOGIN: response=(ServerResponse) new loginTransaction(db).transaction(request.getData());
                break;
            case PRIJAVI: response=(ServerResponse) new prijaviNaOglasTransaction(db).transaction(request.getData());
                break;
            case VRATI_OGLASE: response=(ServerResponse) new vratiOglaseKlijentTransaction(db).transaction(request.getData());
                break;
            case VRATI_OPIS: response=(ServerResponse) new vratiOpisKlijentTransaction(db).transaction(request.getData());
                break;
            case VRATI_PRIJAVE: response=(ServerResponse) new vratiPrijaveTransaction(db).transaction(request.getData());
                break;
        }
        return response;
    }
    
}