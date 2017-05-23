/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos;

import bd.consultas;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Producto {

    private SimpleIntegerProperty ID_PRODUCTO = new SimpleIntegerProperty();
    private SimpleStringProperty NOMBRE_PRODUCTO = new SimpleStringProperty();
    private SimpleStringProperty U_MEDIDA = new SimpleStringProperty();
    private SimpleStringProperty PENTREGA = new SimpleStringProperty();
    private SimpleIntegerProperty ID_PROVEEDOR= new SimpleIntegerProperty();
    private SimpleIntegerProperty ID_MARCA = new SimpleIntegerProperty();  
    private SimpleIntegerProperty VALOR = new SimpleIntegerProperty();

    
           
    public int getID_PRODUCTO(){
        return ID_PRODUCTO.get();
    }
    public int getID_PROVEEDOR(){
        return ID_PROVEEDOR.get();
    }
    public int getID_MARCA(){
        
        return ID_MARCA.get();
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
    public int getVALOR(){
        return VALOR.get();
    }
 

    public void setID_PRODUCTO(int ID_PRODUCTO) {
        this.ID_PRODUCTO.set(ID_PRODUCTO);
    }
        public void setID_PROVEEDOR(int ID_PROVEEDOR) {
        this.ID_PROVEEDOR.set(ID_PROVEEDOR);
    }
            public void setID_MARCA(int ID_MARCA) {
        this.ID_MARCA.set(ID_MARCA);
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

    public void setVALOR(int PRECIO_PRODUCTO) {
        this.VALOR.set(PRECIO_PRODUCTO);
    }
    
    
    
    
    //por modificar el insert
    public int Insert_Producto(String Nombre_producto,String U_medida,String Pentrega,int Valor,int Id_marca){
        int x;
        String sql="INSERT INTO producto( NOMBRE_PRODUCTO, U_MEDIDA, PENTREGA,PRECIO_PRODUCTO, ID_MARCA) VALUES ('"+Nombre_producto+"','"+U_medida+"','"+Pentrega+"','"+Valor+"','"+Id_marca+"');";
        x=consultas.Insert(sql);
        return x;
    }
    
    
    
    
    
    
    
}
