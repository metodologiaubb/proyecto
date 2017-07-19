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


public class Marca{
	private IntegerProperty id_marca;
	private StringProperty nombre_marca;

	public Marca(int id_marca, String nombre_marca) { 
		this.id_marca = new SimpleIntegerProperty(id_marca);
		this.nombre_marca = new SimpleStringProperty(nombre_marca);
	}

	//Metodos atributo: id_marca
	public int getId_marca() {
		return id_marca.get();
	}
	public void setId_marca(int id_marca) {
		this.id_marca = new SimpleIntegerProperty(id_marca);
	}
	public IntegerProperty Id_marcaProperty() {
		return id_marca;
	}
	//Metodos atributo: nombre_marca
	public String getNombre_marca() {
		return nombre_marca.get();
	}
	public void setNombre_marca(String nombre_marca) {
		this.nombre_marca = new SimpleStringProperty(nombre_marca);
	}
	public StringProperty Nombre_marcaProperty() {
		return nombre_marca;
	}

    /**
     *
     * @param connection
     * @param lista
     */
    public static void llenarInformacion(Connection connection , ObservableList<Marca>lista){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultado = statement.executeQuery("SELECT ID_MARCA, "
                                                           + "NOMBRE_MARCA "
                                                           + "FROM marca");
                while (resultado.next()) {
                    lista.add(
                            new Marca(
                                    resultado.getInt("ID_MARCA"),
                                    resultado.getString("NOMBRE_MARCA")
                            )
                    );
                }
            } catch (SQLException ex) {
                Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        public static void autocompletar(ComboBox<Marca> cmb, ObservableList<Marca>list){
            cmb.setTooltip(new Tooltip());
            cmb.getItems().addAll(list);
            new ComboBoxAutoComplete<Marca>(cmb);
        }
        @Override
        public String toString(){
            return nombre_marca.get()+"("+id_marca.get()+")";
        }
}