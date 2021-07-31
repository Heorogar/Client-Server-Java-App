/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Oglas;
import domen.Opcode;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistance.DbBroker;
import transfer.ServerResponse;
import transfer.TransferServerResponseException;

public class vratiOpisKlijentTransaction extends SistemskeOperacije{

    public vratiOpisKlijentTransaction(DbBroker db) {
        super(db);
    }

    @Override
    protected Object executeTransaction(Object data) throws SQLException {
        ServerResponse response=new ServerResponse(null);
        try {
            response.setData(db.getOpisKlijent((Oglas) data));
        } catch (TransferServerResponseException ex) {
            response.setError(Opcode.ErrorCodes.OGLAS_EXPIRED_ERROR);
        }
        return response;
    }
    
}
