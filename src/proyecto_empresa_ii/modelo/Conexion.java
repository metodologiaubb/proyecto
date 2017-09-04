package proyecto_empresa_ii.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Balannor
 */
public class Conexion { 
    public Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/proyecto";
    private final String user = "root";
    private final String password = "8661";
    
    public Connection getConnection(){
        return connection;
    }
    public void setConnection(Connection connection){
         this.connection= connection;
    }
    public void cerrarConexion(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void establecerConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connection = DriverManager.getConnection(url,user,password);
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}

