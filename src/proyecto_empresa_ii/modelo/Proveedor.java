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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import proyecto_empresa_ii.ComboBoxAutoComplete;

/**
 *
 * @author balannor
 */


public class Proveedor{
	private IntegerProperty id_proveedor;
	private StringProperty nombre_proveedor;
	private IntegerProperty dcto_proveedor;
	private IntegerProperty c_pro_dcto;

	public Proveedor(int id_proveedor, String nombre_proveedor, 
                int dcto_proveedor, int c_pro_dcto) { 

            this.id_proveedor = new SimpleIntegerProperty(id_proveedor);
            this.nombre_proveedor = new SimpleStringProperty(nombre_proveedor);
            this.dcto_proveedor = new SimpleIntegerProperty(dcto_proveedor);
            this.c_pro_dcto = new SimpleIntegerProperty(c_pro_dcto);
	}

	//Metodos atributo: id_proveedor
	public int getId_proveedor() {
            return id_proveedor.get();
	}
	public void setId_proveedor(int id_proveedor) {
            this.id_proveedor = new SimpleIntegerProperty(id_proveedor);
	}
	public IntegerProperty Id_proveedorProperty() {
            return id_proveedor;
	}
	//Metodos atributo: nombre_proveedor
	public String getNombre_proveedor() {
            return nombre_proveedor.get();
	}
	public void setNombre_proveedor(String nombre_proveedor) {
            this.nombre_proveedor = new SimpleStringProperty(nombre_proveedor);
	}
	public StringProperty Nombre_proveedorProperty() {
            return nombre_proveedor;
	}
	//Metodos atributo: dcto_proveedor
	public int getDcto_proveedor() {
            return dcto_proveedor.get();
	}
	public void setDcto_proveedor(int dcto_proveedor) {
            this.dcto_proveedor = new SimpleIntegerProperty(dcto_proveedor);
	}
	public IntegerProperty Dcto_proveedorProperty() {
            return dcto_proveedor;
	}
	//Metodos atributo: c_pro_dcto
	public int getC_pro_dcto() {
            return c_pro_dcto.get();
	}
	public void setC_pro_dcto(int c_pro_dcto) {
            this.c_pro_dcto = new SimpleIntegerProperty(c_pro_dcto);
	}
	public IntegerProperty C_pro_dctoProperty() {
            return c_pro_dcto;
	}
        public static void llenarInformacion(Connection connection , ObservableList<Proveedor>lista){
            Statement statement;
            ResultSet resultado ;
            try {
                statement = connection.createStatement();
                resultado = statement.executeQuery("SELECT ID_PROVEEDOR, NOMBRE_PROVEEDOR, DCTO_PROVEEDOR, C_PRO_DCTO FROM proveedor;");
                while (resultado.next()) {
                    lista.add(
                            new Proveedor(
                                    resultado.getInt("ID_PROVEEDOR"),
                                    resultado.getString("NOMBRE_PROVEEDOR"),
                                    resultado.getInt("DCTO_PROVEEDOR"),
                                    resultado.getInt("C_PRO_DCTO")
                            )
                    );
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        }
        public static void autocompletar(ComboBox<Proveedor> cmb, ObservableList<Proveedor>list){
            cmb.setTooltip(new Tooltip());
            cmb.getItems().addAll(list);
            new ComboBoxAutoComplete<Proveedor>(cmb);
        }
        
        @Override
        public String toString(){
            return nombre_proveedor.get()+"("+id_proveedor.get()+")";
        }
}