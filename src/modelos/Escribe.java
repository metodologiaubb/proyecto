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
public class Escribe {
         private SimpleIntegerProperty ID_ESCRIBE = new SimpleIntegerProperty();
     private SimpleIntegerProperty ID_COTI = new SimpleIntegerProperty();
        private SimpleIntegerProperty ID_ESCRITOR = new SimpleIntegerProperty();
     
     public int getID_ESCRIBE(){
         return ID_ESCRIBE.get();
     }
     public int getID_COTI(){
         return ID_COTI.get();
     }
      public int getID_ESCRITOR(){
         return ID_ESCRITOR.get();
     }

    public void setID_ESCRIBE(int ID_ESCRIBE) {
        this.ID_ESCRIBE.set(ID_ESCRIBE);
    }

    public void setID_COTI(int ID_COTI) {
        this.ID_COTI.set(ID_COTI);
    }

    public void setID_ESCRITOR(int ID_ESCRITOR) {
        this.ID_ESCRITOR.set(ID_ESCRITOR);
    }
      
       
    }
      


