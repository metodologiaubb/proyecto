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
    
    public static String Insert_Proveedor(String Nombre_proveedor, int Dcto_proveedor,int C_pro_dcto){
        String sql="INSERT INTO proveedor ( NOMBRE_PROVEEDOR, DCTO_PROVEEDOR, C_PRO_DCTO) VALUES ('"+Nombre_proveedor+"','"+Dcto_proveedor+"','"+C_pro_dcto+"');";
        return sql;
    }
    public static String Insert_Proveedor(String Nombre_proveedor, int Dcto_proveedor){
        String sql="INSERT INTO proveedor ( NOMBRE_PROVEEDOR, DCTO_PROVEEDOR) VALUES ('"+Nombre_proveedor+"','"+Dcto_proveedor+"');";
        return sql;
    }

    public void setID_PROVEEDOR(int ID_PROVEEDOR) {
        this.ID_PROVEEDOR.set(ID_PROVEEDOR);
    }

    public void setNOMBRE_PROVEEDOR(String NOMBRE_PROVEEDOR) {
        this.NOMBRE_PROVEEDOR.set(NOMBRE_PROVEEDOR);
    }

    public void setDCTO_PROVEEDOR(int DCTO_PROVEEDOR) {
        this.DCTO_PROVEEDOR.set(DCTO_PROVEEDOR);
    }

    public void setC_PRO_DCTO(int C_PRO_DCTO) {
        this.C_PRO_DCTO.set(C_PRO_DCTO);
    }
}
