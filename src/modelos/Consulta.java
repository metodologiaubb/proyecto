/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Pascal
 */
public class Consulta {  
    private SimpleStringProperty ID_CONSULTA = new SimpleStringProperty();
    private SimpleStringProperty ID_LECTOR = new SimpleStringProperty();
    private SimpleStringProperty FECHA_CONS= new SimpleStringProperty();

    public String getID_CONSULTA(){
        return ID_CONSULTA.get();
    } 
    public String getID_LECTOR(){
        return ID_LECTOR.get();
    }
    public String getFECHA_CONS(){
        return FECHA_CONS.get();
    }

    public void setID_CONSULTA(String ID_CONSULTA) {
        this.ID_CONSULTA.set(ID_CONSULTA);
    }

    public void setID_LECTOR(String ID_LECTOR) {
        this.ID_LECTOR.set(ID_LECTOR);
    }

    public void setFECHA_CONS(String FECHA_CONS) {
        this.FECHA_CONS.set(FECHA_CONS);
    }
    
    
    
}
