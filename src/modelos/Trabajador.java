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
public class Trabajador {
    private SimpleIntegerProperty ID_TRABAJADOR = new SimpleIntegerProperty();
    private SimpleStringProperty PASS_TRABAJADOR = new SimpleStringProperty();
    private SimpleStringProperty NOMBRE_TRABAJADOR = new SimpleStringProperty();
    private SimpleStringProperty APELLIDO_TRABAJADOR = new SimpleStringProperty();
    private SimpleIntegerProperty FONO_TRABAJADOR = new SimpleIntegerProperty();
    private SimpleBooleanProperty INVITADO = new SimpleBooleanProperty();
    
    
    public int getID_TRABAJADOR(){
        return ID_TRABAJADOR.get();
    }
    public String getPASS_TRABAJADOR(){
        return PASS_TRABAJADOR.get();
    }
    public String getNOMBRE_TRABAJADOR(){
        return NOMBRE_TRABAJADOR.get();
    }
     public String getAPELLIDO_TRABAJADOR(){
        return APELLIDO_TRABAJADOR.get();
    }
       public int getFONO_TRABAJADOR(){
        return FONO_TRABAJADOR.get();
    }
         public Boolean getINVITADO(){
        return INVITADO.get();
    }
}
