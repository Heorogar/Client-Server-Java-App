/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Korisnik;
import domen.Opcode;
import java.sql.SQLException;
import persistance.DbBroker;
import transfer.ServerResponse;

public class loginTransaction extends SistemskeOperacije{

    public loginTransaction(DbBroker db) {
        super(db);
    }

    @Override
    protected Object executeTransaction(Object data) throws SQLException {
        ServerResponse response=new ServerResponse(null, Opcode.ErrorCodes.NO_ERROR);
        if(!db.postojiKorisnik((Korisnik)data))
            response.setError(Opcode.ErrorCodes.LOGIN_ERROR);
        return response;
    }
    
}
