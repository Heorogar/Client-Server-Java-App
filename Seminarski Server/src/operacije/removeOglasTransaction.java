/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Oglas;
import java.sql.SQLException;
import persistance.DbBroker;

public class removeOglasTransaction extends SistemskeOperacije{

    public removeOglasTransaction(DbBroker db) {
        super(db);
    }

    @Override
    protected Object executeTransaction(Object data) throws SQLException {
        db.removeOglas((Oglas) data);
        return "";
    }
    
}
