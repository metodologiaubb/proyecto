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
public class Producto {
    private SimpleIntegerProperty ID_PRODUCTO = new SimpleIntegerProperty();
    private SimpleStringProperty NOMBRE_PRODUCTO = new SimpleStringProperty();
    private SimpleStringProperty U_MEDIDA = new SimpleStringProperty();
    private SimpleStringProperty PENTREGA = new SimpleStringProperty();
    private SimpleIntegerProperty PRECIO_PRODUCTO = new SimpleIntegerProperty();
    private SimpleIntegerProperty ID_MARCA = new SimpleIntegerProperty();
    
    public int getID_PRODUCTO(){
        return ID_PRODUCTO.get();
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
    public int getID_MARCA(){
        return ID_MARCA.get();
    }
    
    
     public static String Insert_Producto(String Nombre_producto,String U_medida,String Pentrega,int Precio_producto,int Id_marca){
        String sql="INSERT INTO producto( NOMBRE_PRODUCTO, U_MEDIDA, PENTREGA,PRECIO_PRODUCTO, ID_MARCA) VALUES ('"+Nombre_producto+"','"+U_medida+"','"+Pentrega+"','"+Precio_producto+"','"+Id_marca+"');";
        return sql;
    }
}
