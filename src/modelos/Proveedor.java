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
 * @author jerson
 */
public class Proveedor {
    private SimpleIntegerProperty ID_PROVEEDOR = new SimpleIntegerProperty();
    private SimpleStringProperty NOMBRE_PROVEEDOR = new SimpleStringProperty();
    private SimpleIntegerProperty DCTO_PROVEEDOR = new SimpleIntegerProperty();
    private SimpleIntegerProperty C_PRO_DCTO = new SimpleIntegerProperty();
    
    
    public int getID_PROVEEDOR(){
        return ID_PROVEEDOR.get();
    }
    public String getNOMBRE_PROVEEDOR(){
        return NOMBRE_PROVEEDOR.get();
    }
    public int getDCTO_PROVEEDOR(){
        return DCTO_PROVEEDOR.get();
    }
    public int getC_PRO_DCTO(){
        return C_PRO_DCTO.get();
    }
}
