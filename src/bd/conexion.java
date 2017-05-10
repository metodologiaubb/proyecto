/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jerson
 */
public class conexion {
    private static Connection con;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user ="root";
    private static final String password = "1705";
    private static final String bd = "jdbc:mysql://localhost:3306/proyecto";

    public conexion() {
        con=null;
        try {
        Class.forName(driver);
        con=DriverManager.getConnection(bd, user, password);
        if(con!=null){
            System.out.println("conexion OK...");
        }
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("Error al intentar conectar al servidor"+ e);
        }
        
    }
    public Connection getconexion (){
        return con;
    }
    public void desconectar(){
    con=null;
    if(con == null){
        System.out.println("Conexion terminada");
    }
    }
    
}
