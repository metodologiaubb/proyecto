package proyecto_empresa_ii.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author balannor
 */


public class Cotizacion{
	private IntegerProperty id_cot;
	private StringProperty fecha_cot;
	private IntegerProperty id_creador;
	private IntegerProperty n_productos;
	private StringProperty descripcion;
	private StringProperty token;

	public Cotizacion(int id_cot, String fecha_cot, int id_creador, 
        int n_productos, String descripcion, String token) { 
            
            this.id_cot     = new SimpleIntegerProperty(id_cot);
            this.fecha_cot  = new SimpleStringProperty(fecha_cot);
            this.id_creador = new SimpleIntegerProperty(id_creador);
            this.n_productos= new SimpleIntegerProperty(n_productos);
            this.descripcion= new SimpleStringProperty(descripcion);
            this.token      = new SimpleStringProperty(token);
	}

	//Metodos atributo: id_cot
	public int getId_cot() {
		return id_cot.get();
	}
	public void setId_cot(int id_cot) {
		this.id_cot = new SimpleIntegerProperty(id_cot);
	}
	public IntegerProperty Id_cotProperty() {
		return id_cot;
	}
	//Metodos atributo: fecha_cot
	public String getFecha_cot() {
		return fecha_cot.get();
	}
	public void setFecha_cot(String fecha_cot) {
		this.fecha_cot = new SimpleStringProperty(fecha_cot);
	}
	public StringProperty Fecha_cotProperty() {
		return fecha_cot;
	}
	//Metodos atributo: id_creador
	public int getId_creador() {
		return id_creador.get();
	}
	public void setId_creador(int id_creador) {
		this.id_creador = new SimpleIntegerProperty(id_creador);
	}
	public IntegerProperty Id_creadorProperty() {
		return id_creador;
	}
	//Metodos atributo: n_productos
	public int getN_productos() {
		return n_productos.get();
	}
	public void setN_productos(int n_productos) {
		this.n_productos = new SimpleIntegerProperty(n_productos);
	}
	public IntegerProperty N_productosProperty() {
		return n_productos;
	}
	//Metodos atributo: descripcion
	public String getDescripcion() {
		return descripcion.get();
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = new SimpleStringProperty(descripcion);
	}
	public StringProperty DescripcionProperty() {
		return descripcion;
	}
	//Metodos atributo: token
	public String getToken() {
		return token.get();
	}
	public void setToken(String token) {
		this.token = new SimpleStringProperty(token);
	}
	public StringProperty TokenProperty() {
		return token;
	}
            
        public static  ResultSet Cotizaciones(){
        String sql= "SELECT ID_COT, "
                    + "FECHA_COT, "
                    + "ID_CREADOR, "
                    + "N_PRODUCTOS, "
                    + "DESCRIPCION,  "
                    + "TOKEN "        
                    + "FROM  cotizacion";
        ResultSet resultSet=consultas.Select(sql); 
        return resultSet;
    }
            public static void llenarInformacion(ObservableList<Cotizacion>lista, HashMap<Integer,Integer> listaCotiza){
            try {
                ResultSet resultado = Cotizaciones();
                
                while (resultado.next()) {
                    Cotizacion llena = new Cotizacion(
                                    resultado.getInt("ID_COT"), 
                                    resultado.getString("FECHA_COT"), 
                                    resultado.getInt("ID_CREADOR"), 
                                    resultado.getInt("N_PRODUCTOS"), 
                                    resultado.getString("DESCRIPCION"),
                                    resultado.getString("TOKEN")
                            );
                    lista.add(llena);
                    listaCotiza.put(llena.getId_cot(), lista.indexOf(llena));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        public static Cotizacion llenaOne( ResultSet resultado) throws SQLException{
             Cotizacion llena = new Cotizacion(
                                    resultado.getInt("ID_COT"), 
                                    resultado.getString("FECHA_COT"), 
                                    resultado.getInt("ID_CREADOR"), 
                                    resultado.getInt("N_PRODUCTOS"), 
                                    resultado.getString("DESCRIPCION"),
                                    resultado.getString("TOKEN")
                            );
                    return llena;
        }

        @Override
        public String toString(){
            return descripcion.get()+"("+id_cot.get()+")";
        }
}