/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import static java.util.Collections.unmodifiableMap;
import java.util.HashMap;
import java.util.Map;

public class Opcode {
    public static enum Operacija{
        LOGIN,
        VRATI_OPIS,
        PRIJAVI,
        VRATI_OGLASE,
        VRATI_PRIJAVE
    };
    public static enum ErrorCodes{
        NO_ERROR,
        LOGIN_ERROR,
        OGLAS_EXPIRED_ERROR,
        DUPLA_PRIJAVA_ERROR,
        SERVER_ERROR,
        BAD_REQUEST_ERROR
    };
    public static Map<ErrorCodes, String> errors=initMap();

    private static Map<ErrorCodes, String> initMap() {
        Map<ErrorCodes, String> map = new HashMap<>();
        map.put(ErrorCodes.NO_ERROR, "");
        map.put(ErrorCodes.LOGIN_ERROR, "Pogresan username ili lozinka");
        map.put(ErrorCodes.OGLAS_EXPIRED_ERROR, "Oglas je istekao");
        map.put(ErrorCodes.DUPLA_PRIJAVA_ERROR, "Vec ste se prijavili na ovaj oglas ili je istekao");
        map.put(ErrorCodes.SERVER_ERROR, "Server eror, molimo Vas pokusajte kasnije");
        map.put(ErrorCodes.BAD_REQUEST_ERROR, "Whoops! Doslo je do greske, molimo Vas proverite unos. Ukoliko nije Vasa greska posaljite mail na foo@bar.com i otpusticemo sve koji su zaduzeni za ovu sramotnu, sramotnu gresku!!!");
        return unmodifiableMap(map);    
    }
}
