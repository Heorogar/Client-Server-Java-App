/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import java.sql.SQLException;
import persistance.DbBroker;

public abstract class SistemskeOperacije {
    protected DbBroker db;
    public static enum Operacija{
        LOGIN,
        VRATI_OPIS,
        PRIJAVI,
        VRATI_OGLASE,
        VRATI_PRIJAVE,
        SET_OPIS,
        UKLONI_OGLAS,
        DODAJ_OGLAS,
        UPDATE_OGLAS
    }
    public SistemskeOperacije(DbBroker db) {
        this.db = db;
    }
    public Object transaction(Object data){
        Object response=null;
        try {
            db.otvoriKonekciju();
            response=executeTransaction(data);
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            ex.printStackTrace();
        } finally {
            db.zatvoriKonekciju();
        }
        return response;
    }
    protected abstract Object executeTransaction(Object data) throws SQLException;
}
