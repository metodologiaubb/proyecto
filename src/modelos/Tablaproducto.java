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
public class Tablaproducto {
    private SimpleIntegerProperty ID_PRODUCTO = new SimpleIntegerProperty();
    private SimpleStringProperty NOMBRE_PRODUCTO = new SimpleStringProperty();
    private SimpleStringProperty U_MEDIDA = new SimpleStringProperty();
    private SimpleStringProperty PENTREGA = new SimpleStringProperty();
    private SimpleIntegerProperty PRECIO_PRODUCTO = new SimpleIntegerProperty();
    private SimpleStringProperty NOMBRE_PROVEEDOR= new SimpleStringProperty();
    private SimpleStringProperty NOMBRE_MARCA = new SimpleStringProperty();
    private SimpleIntegerProperty ID_MARCA = new SimpleIntegerProperty();

        
        
        
    public int getID_PRODUCTO(){
        return ID_PRODUCTO.get();
    }

    public String getNOMBRE_PROVEEDOR() {
        return NOMBRE_PROVEEDOR.get();
    }

    public String getNOMBRE_MARCA() {
        return NOMBRE_MARCA.get();
    }
    
    public String getNOMBRE_PRODUCTO(){
        return NOMBRE_PRODUCTO.get();
    }
    public String getU_MEDIDA(){
        return U_MEDIDA.get();
    }
    public String getPENTREGA(){
        return PENTREGA.get();
    }
    public int getPRECIO_PRODUCTO(){
        return PRECIO_PRODUCTO.get();
    }
 

    public void setID_PRODUCTO(int ID_PRODUCTO) {
        this.ID_PRODUCTO.set(ID_PRODUCTO);
    }
    public void setNOMBRE_MARCA(String NOMBRE_MARCA) {
        this.NOMBRE_MARCA.set(NOMBRE_MARCA);
    }
     public void setNOMBRE_PROVEEDOR(String nprove) {
        this.NOMBRE_PROVEEDOR.set(nprove);
    }

    public void setNOMBRE_PRODUCTO(String NOMBRE_PRODUCTO) {
        this.NOMBRE_PRODUCTO.set(NOMBRE_PRODUCTO);
    }

    public void setU_MEDIDA(String U_MEDIDA) {
        this.U_MEDIDA.set(U_MEDIDA);
    }

    public void setPENTREGA(String PENTREGA) {
        this.PENTREGA.set(PENTREGA);
    }

    public void setPRECIO_PRODUCTO(int PRECIO_PRODUCTO) {
        this.PRECIO_PRODUCTO.set(PRECIO_PRODUCTO);
    }

}
