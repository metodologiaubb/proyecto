/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.User;

/**
 *
 * @author jerson
 */
public class Sesion {
    public static User CurrentUser;
    public Sesion(ResultSet res) {
        try {
            CurrentUser=new User();
            res.first();
            CurrentUser.setID_USER(res.getInt("ID_USER"));
            CurrentUser.setUSER_USERNAME(res.getString("USER_USERNAME"));
            CurrentUser.setUSER_PASS(res.getString("USER_PASS"));
            CurrentUser.setUSER_NOMBRE(res.getString("USER_NOMBRE"));
            CurrentUser.setUSER_APELLIDO(res.getString("USER_APELLIDO"));
            CurrentUser.setUSER_FONO(res.getString("USER_FONO"));
            CurrentUser.setUSER_ROL(res.getInt("USER_ROL"));
                    } catch (SQLException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
