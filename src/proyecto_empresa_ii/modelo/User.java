/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii.modelo;

/**
 *
 * @author Pascal
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author jerson
 */
public class User {

  
    /*private IntegerProperty ID_USER; 
    private StringProperty USER_USERNAME; 
    private StringProperty USER_PASS; 
    private StringProperty USER_NOMBRE; 
    private StringProperty USER_APELLIDO; 
    private StringProperty USER_FONO; 
    private IntegerProperty USER_ROL;
   */
    
    private SimpleIntegerProperty ID_USER = new SimpleIntegerProperty();
    private SimpleStringProperty USER_USERNAME = new SimpleStringProperty();
    private SimpleStringProperty USER_PASS = new SimpleStringProperty();
    private SimpleStringProperty USER_NOMBRE = new SimpleStringProperty();
    private SimpleStringProperty USER_APELLIDO = new SimpleStringProperty();
    private SimpleStringProperty USER_FONO = new SimpleStringProperty();
    private SimpleIntegerProperty USER_ROL = new SimpleIntegerProperty();
    private SimpleIntegerProperty HABILITADO = new SimpleIntegerProperty();
  
   
    public User(String USER_USERNAME) 
    {
      //this.ID_USER =new SimpleIntegerProperty(ID_USER);
      this.USER_USERNAME=new SimpleStringProperty (USER_USERNAME);
      /*this.USER_PASS= new SimpleStringProperty (USER_PASS);
      this.USER_NOMBRE= new SimpleStringProperty (USER_NOMBRE);
      this.USER_APELLIDO= new SimpleStringProperty (USER_APELLIDO);
      this.USER_FONO=new SimpleStringProperty (USER_FONO);
      this.USER_ROL=new SimpleIntegerProperty(USER_ROL);
     */   
    }
        
    /*public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    public int getID_USER(){
        return ID_USER.get();
    }
    public String getUSER_USERNAME(){
        return USER_USERNAME.get();
    }
    public String getPASS_USER(){
        return USER_PASS.get();
    }
     public String getFONO_USER(){
        return USER_FONO.get();
    }
       public int getROL(){
        return USER_ROL.get();
    }
      public int getHABILITADO(){
       return HABILITADO.get();
    }
     public void setHABILITADOR(int HABILITADO) {
        this.HABILITADO.set(HABILITADO);
    }
     public String getUSER_NOMBRE(){
        return USER_NOMBRE.get();
    }
   public String getUSER_APELLIDO(){
        return USER_APELLIDO.get();
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER.set(ID_USER);
    }

    public void setUSER_USERNAME(String USER_USERNAME) {
        this.USER_USERNAME.set(USER_USERNAME);
    }

    public void setUSER_PASS(String USER_PASS) {
        this.USER_PASS.set(USER_PASS);
    }

    public void setUSER_NOMBRE(String USER_NOMBRE) {
        this.USER_NOMBRE.set(USER_NOMBRE);
    }

    public void setUSER_APELLIDO(String USER_APELLIDO) {
        this.USER_APELLIDO.set(USER_APELLIDO);
    }

    public void setUSER_FONO(String USER_FONO) {
        this.USER_FONO.set(USER_FONO);
    }

    public void setUSER_ROL(int USER_ROL) {
        this.USER_ROL.set(USER_ROL); 
    }
   
    @Override
    public String toString(){
        
    return USER_USERNAME.get();
    }
    
    public static void llenar_informacion(Connection connection, ObservableList<User> lista){
        try {
            String sql="SELECT USER_USERNAME FROM user WHERE HABILITADO=1;";
            ResultSet res = consultas.Select(sql);
            while(res.next()){
            lista.add(
                    new User( 
                             res.getString("USER_USERNAME")
                    )
            );
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
   
    public int Insert_User(String User_username,String User_pass,String User_fono,int User_rol,String User_nombre,String User_apellido){
        int x;
        String sql="INSERT INTO user( USER_USERNAME, USER_PASS, USER_NOMBRE, USER_APELLIDO, USER_FONO, USER_ROL) VALUES ('"+User_username+"','"+User_pass+"','"+User_nombre+"','"+User_apellido+"','"+User_fono+"','"+User_rol+"')";
        x=consultas.Insert(sql);
        return x;
    }
  
}
