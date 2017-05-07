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
public class Admin {
    private SimpleStringProperty ID_ADMIN = new SimpleStringProperty();
    private SimpleStringProperty  PASS_ADMIN = new SimpleStringProperty();
    private SimpleStringProperty NOMBRE_ADMIN = new SimpleStringProperty();
    private SimpleStringProperty APELLIDO_ADMIN = new SimpleStringProperty();
    
    public String getID_ADMIN(){
        return ID_ADMIN.get();
    }
    public String getPASS_ADMIN(){
        return PASS_ADMIN.get();
    }
    public String getNOMBRE_ADMIN(){
        return NOMBRE_ADMIN.get();
    }
     public String getAPELLIDO_ADMIN(){
        return APELLIDO_ADMIN.get();
    }
   
}
