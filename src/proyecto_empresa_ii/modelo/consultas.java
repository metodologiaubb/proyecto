/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jerson
 */
public class consultas {
    private static Connection reg;
    public consultas(Connection con) {
        
        reg= con;
    }
    
    public static  ResultSet Select(String sql) {
        
    Statement st;
    ResultSet rs=null;
        try {
            st = reg.createStatement();
            rs= st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(consultas.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    
        return rs;
    }
    //retorna 0 si es exitosa la insercion y -1 si no lo es  
    public static int Insert(String sql){
        int x;
        try {
            PreparedStatement pps =reg.prepareStatement(sql);
            pps.executeUpdate();
            x=0;
        } catch (SQLException ex) {
            Logger.getLogger(consultas.class.getName()).log(Level.SEVERE, null, ex);
            x=-1;
        }
        return x;
    }
}
