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
import javafx.beans.property.SimpleBooleanProperty;
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
    private SimpleIntegerProperty USER_FONO = new SimpleIntegerProperty();
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
     public int getFONO_USER(){
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
  
}
