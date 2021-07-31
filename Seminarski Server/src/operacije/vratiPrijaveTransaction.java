/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Korisnik;
import java.sql.SQLException;
import persistance.DbBroker;
import transfer.ServerResponse;

public class vratiPrijaveTransaction extends SistemskeOperacije{

    public vratiPrijaveTransaction(DbBroker db) {
        super(db);
    }

    @Override
    protected Object executeTransaction(Object data) throws SQLException {
        return new ServerResponse(db.vratiPrijave((Korisnik)data));
    }
    
}
