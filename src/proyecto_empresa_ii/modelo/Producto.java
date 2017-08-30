package proyecto_empresa_ii.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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




public class Producto{
	private IntegerProperty id_producto;
	private StringProperty nombre_producto;
	private StringProperty u_medida;
	private LocalDate pentrega;
	private Proveedor proveedor;
	private Marca marca;
	private IntegerProperty valor;

	public Producto(int id_producto, String nombre_producto, String u_medida, 
                LocalDate pentrega, Proveedor proveedor, Marca marca, int valor) { 
		this.id_producto = new SimpleIntegerProperty(id_producto);
		this.nombre_producto = new SimpleStringProperty(nombre_producto);
		this.u_medida = new SimpleStringProperty(u_medida);
		this.pentrega = pentrega;
		this.proveedor = proveedor;
		this.marca = marca;
		this.valor = new SimpleIntegerProperty(valor);
	}

	//Metodos atributo: id_producto
	public int getId_producto() {
		return id_producto.get();
	}
	public void setId_producto(int id_producto) {
		this.id_producto = new SimpleIntegerProperty(id_producto);
	}
	public IntegerProperty Id_productoProperty() {
		return id_producto;
	}
	//Metodos atributo: nombre_producto
	public String getNombre_producto() {
		return nombre_producto.get();
	}
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = new SimpleStringProperty(nombre_producto);
	}
	public StringProperty Nombre_productoProperty() {
		return nombre_producto;
	}
	//Metodos atributo: u_medida
	public String getU_medida() {
		return u_medida.get();
	}
	public void setU_medida(String u_medida) {
		this.u_medida = new SimpleStringProperty(u_medida);
	}
	public StringProperty U_medidaProperty() {
		return u_medida;
	}
	//Metodos atributo: pentrega
	public LocalDate getPentrega() {
		return pentrega;
	}
	public void setPentrega(LocalDate pentrega) {
		this.pentrega = pentrega;
	}
	//Metodos atributo: proveedor
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	//Metodos atributo: marca
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	//Metodos atributo: valor
	public int getValor() {
		return valor.get();
	}
	public void setValor(int valor) {
		this.valor = new SimpleIntegerProperty(valor);
	}
	public IntegerProperty ValorProperty() {
		return valor;
	}
        public static void llenarInformacion(Connection connection, 
                ObservableList<Producto> lista, int n){
            System.out.println(n);
            try {
                Statement statement = connection.createStatement();
                ResultSet resultado =statement.executeQuery(
                              "SELECT "
                                      + "prc.ID_PRODUCTO,"
                                      + "prc.NOMBRE_PRODUCTO,"
                                      + "prc.U_MEDIDA,prc.PENTREGA ,"
                                      + "prc.ID_PROVEEDOR"
                                      + ",prc.ID_MARCA,prc.VALOR,prv."
                                      + "NOMBRE_PROVEEDOR,prv.DCTO_PROVEEDOR,"
                                      + "prv.C_PRO_DCTO,mr.NOMBRE_MARCA"
                                      + " FROM Producto_Com prc,proveedor prv,"
                                      + "marca mr,pertenece per "
                                      + "WHERE prc.ID_PROVEEDOR="
                                      + "prv.ID_PROVEEDOR AND "
                                      + "prc.ID_MARCA=mr.ID_MARCA "
                                      + "AND prc.ID_PRODUCTO_PROVEEDOR="
                                      + "per.ID_PRODUCT_PRO"
                                      + " AND per.ID_COT="+n
                );
                while (resultado.next()) {        
                    lista.add(
                            new Producto(
                                    resultado.getInt("prc.ID_PRODUCTO"), 
                                    resultado.getString("prc.NOMBRE_PRODUCTO"), 
                                    resultado.getString("prc.U_MEDIDA"), 
                                    resultado.getDate("prc.PENTREGA").toLocalDate(),
                                    new Proveedor(
                                            resultado.getInt("prc.ID_PROVEEDOR"), 
                                            resultado.getString("prv.NOMBRE_PROVEEDOR"), 
                                            resultado.getInt("prv.DCTO_PROVEEDOR"), 
                                            resultado.getInt("prv.C_PRO_DCTO")
                                    ),
                                    new Marca(
                                            resultado.getInt("prc.ID_MARCA"), 
                                            resultado.getString("mr.NOMBRE_MARCA")
                                    ),
                                    resultado.getInt("prc.VALOR")
                            )
                    );
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        
        }
               public static void llenarInformacion(
                ObservableList<Producto> lista){
            try {
                ResultSet resultado =consultas.Select(
                   "SELECT "
+ "prc.ID_PRODUCTO,prc.NOMBRE_PRODUCTO,"
+ "prc.U_MEDIDA,prc.PENTREGA,prc.ID_PROVEEDOR ,"
+ "prc.ID_MARCA,prc.VALOR,prv.NOMBRE_PROVEEDOR,"
+ "prv.DCTO_PROVEEDOR,prv.C_PRO_DCTO,mr.NOMBRE_MARCA "
+ "FROM Producto_Com prc,proveedor prv,marca mr WHERE "
+ "prc.ID_PROVEEDOR=prv.ID_PROVEEDOR AND prc.ID_MARCA=mr.ID_MARCA; "

                );
             while (resultado.next()) {        
                    lista.add(
                            new Producto(
                                    resultado.getInt("prc.ID_PRODUCTO"), 
                                    resultado.getString("prc.NOMBRE_PRODUCTO"), 
                                    resultado.getString("prc.U_MEDIDA"), 
                                    resultado.getDate("prc.PENTREGA").toLocalDate(),
                                    new Proveedor(
                                            resultado.getInt("prc.ID_PROVEEDOR"), 
                                            resultado.getString("prv.NOMBRE_PROVEEDOR"), 
                                            resultado.getInt("prv.DCTO_PROVEEDOR"), 
                                            resultado.getInt("prv.C_PRO_DCTO")
                                    ),
                                    new Marca(
                                            resultado.getInt("prc.ID_MARCA"), 
                                            resultado.getString("mr.NOMBRE_MARCA")
                                    ),
                                    resultado.getInt("prc.VALOR")
                            )
                    );
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        
        }

        @Override
        public String toString(){
        return id_producto.get()+"("+nombre_producto.get()+")";
        }
                public static void autocompletar(ComboBox<Producto> cmb, ObservableList<Producto>list){
            cmb.setTooltip(new Tooltip());
            cmb.getItems().addAll(list);
            new ComboBoxAutoComplete<Producto>(cmb);
        }
                   public static void autocompletar1(ComboBox<Producto1> cmb, ObservableList<Producto1>list){
            cmb.setTooltip(new Tooltip());
            cmb.getItems().addAll(list);
            new ComboBoxAutoComplete<Producto1>(cmb);
        }
           
    public boolean getActivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}