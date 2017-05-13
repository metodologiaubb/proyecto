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
public class Marca {
     private SimpleIntegerProperty ID_MARCA = new SimpleIntegerProperty();
     private SimpleStringProperty NOMBRE_MARCA = new SimpleStringProperty();
     
     public int getID_MARCA(){
         return ID_MARCA.get();
     }
     public String getNOMBRE_MARCA(){
         return NOMBRE_MARCA.get();
     }

    public void setID_MARCA(int ID_MARCA) {
        this.ID_MARCA.set(ID_MARCA);
    }

    public void setNOMBRE_MARCA(String NOMBRE_MARCA) {
        this.NOMBRE_MARCA.set(NOMBRE_MARCA);
    }
     
     public static String Insert_marca(String Nombre_marca){
         String sql="INSERT INTO marca( NOMBRE_MARCA) VALUES ('"+Nombre_marca+"');";
         return sql;
     }
}
