/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Opcode;
import domen.Prijava;
import persistance.DbBroker;
import transfer.ServerResponse;
import transfer.TransferServerResponseException;

public class prijaviNaOglasTransaction extends SistemskeOperacije{

    public prijaviNaOglasTransaction(DbBroker db) {
        super(db);
    }

    @Override
    protected Object executeTransaction(Object data) {
        try {
            db.prijaviNaOglas((Prijava)data);
            return new ServerResponse(null);
        } catch (TransferServerResponseException ex) {
            return new ServerResponse(null, Opcode.ErrorCodes.DUPLA_PRIJAVA_ERROR);
        }
    }
    
}
