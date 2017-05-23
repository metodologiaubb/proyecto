/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Pascal
 */
import bd.consultas;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jerson
 */
public class User {
    private SimpleIntegerProperty ID_USER = new SimpleIntegerProperty();
    private SimpleStringProperty USER_USERNAME = new SimpleStringProperty();
    private SimpleStringProperty USER_PASS = new SimpleStringProperty();
    private SimpleStringProperty USER_NOMBRE = new SimpleStringProperty();
    private SimpleStringProperty USER_APELLIDO = new SimpleStringProperty();
    private SimpleStringProperty USER_FONO = new SimpleStringProperty();
    private SimpleIntegerProperty USER_ROL = new SimpleIntegerProperty();
    
       
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
   
   
   
   
    public int Insert_User(String User_username,String User_pass,String User_fono,int User_rol,String User_nombre,String User_apellido){
        int x;
        String sql="INSERT INTO user( USER_USERNAME, USER_PASS, USER_NOMBRE, USER_APELLIDO, USER_FONO, USER_ROL) VALUES ('"+User_username+"','"+User_pass+"','"+User_nombre+"','"+User_apellido+"','"+User_fono+"','"+User_rol+"')";
        x=consultas.Insert(sql);
        return x;
    }
  
}
