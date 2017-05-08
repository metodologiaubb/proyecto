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
}
