/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import bd.consultas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jerson
 */
public class Autentificar {
    private static ResultSet res;
    public static boolean validar(String username,String password){
        boolean x=false;
       res= consultas.Select("SELECT ID_USER, USER_USERNAME, USER_PASS, USER_NOMBRE, USER_APELLIDO, USER_FONO, USER_ROL FROM user WHERE USER_USERNAME='"+username+"' LIMIT 1;");
        try {
            if(res.first()){
                    System.out.println("encontro ");
                    
                    if(res.getString("USER_PASS").equals(password)){
                        x=true;
                    }
            }
            else{
                x=false;
            }} catch (SQLException ex) {
            Logger.getLogger(Autentificar.class.getName()).log(Level.SEVERE, null, ex);
        }
       return x;
    }
    public static ResultSet getSQLuser(){
        return res;
    }
    
}
