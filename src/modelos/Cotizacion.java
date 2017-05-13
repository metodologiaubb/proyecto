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
public class Cotizacion {
    
    
    private SimpleIntegerProperty ID_COT = new SimpleIntegerProperty();
    private SimpleStringProperty FECHA_COT = new SimpleStringProperty();
    private SimpleIntegerProperty ID_CREADOR = new SimpleIntegerProperty();
    private SimpleStringProperty DESCRIPCION = new SimpleStringProperty();
    private SimpleStringProperty TOKEN = new SimpleStringProperty();
    
    public int getID_COT(){
        return ID_COT.get();
    }
    public String getFECHA_COT(){
        return FECHA_COT.get();
    }
    public int getID_CREADOR(){
        return ID_CREADOR.get();
    }
    public String getDESCRIPCION(){
        return DESCRIPCION.get();
    }
    public String getTOKEN(){
        return TOKEN.get();
    }

    public void setID_COT(int ID_COT) {
        this.ID_COT.set(ID_COT);
    }

    public void setFECHA_COT(String FECHA_COT) {
        this.FECHA_COT.set(FECHA_COT);
    }

    public void setID_CREADOR(int ID_CREADOR) {
        this.ID_CREADOR.set(ID_CREADOR);
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION.set(DESCRIPCION);
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN.set(TOKEN);
    }
    
    
    public static String Insert_cod(int ID_creador,String Descripcion,String token){
        String sql="INSERT INTO cotizacion( ID_CREADOR, DESCRIPCION, TOKEN) VALUES ("+ID_creador+",'"+ Descripcion +"','"+token+"')";
        return sql;
    }

    
}
