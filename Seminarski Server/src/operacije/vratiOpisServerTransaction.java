/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Oglas;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistance.DbBroker;

public class vratiOpisServerTransaction extends SistemskeOperacije{

    public vratiOpisServerTransaction(DbBroker db) {
        super(db);
    }

    @Override
    protected Object executeTransaction(Object data) throws SQLException {
        return db.getOpisServer((Oglas)data);
    }

    
}
