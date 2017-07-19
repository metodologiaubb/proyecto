/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import proyecto_empresa_ii.modelo.Conexion;
import proyecto_empresa_ii.modelo.Cotizacion;
import proyecto_empresa_ii.modelo.Marca;
import proyecto_empresa_ii.modelo.Producto;
import proyecto_empresa_ii.modelo.Proveedor;
import proyecto_empresa_ii.modelo.consultas;

/**
 *
 * @author balannor
 */
public class FXMLDocumentController implements Initializable {
       
    @FXML
    private JFXTextField filterField; 
    @FXML
    private JFXTextField filterField1;
    @FXML
    private JFXButton btnguardar_producto;
    @FXML
    private TableColumn<Cotizacion, Number> colid_cotizacion;
    @FXML
    private TableColumn<Cotizacion, String> colfecha_cotizacion;
    @FXML
    private TableColumn<Cotizacion, Number> colnp_cotizacion;
    @FXML
    private TableColumn<Cotizacion, String> coldescripcion_cotizacion;
    @FXML
    private TableView<Cotizacion>tv_cotizacion;
//    @FXML
//    private TableColumn<Producto, Number> colid_producto;
    @FXML
    private TableColumn<Producto, String> colnombre_producto;
    @FXML
    private TableColumn<Producto, String> columedida_producto;
    @FXML
    private TableColumn<Producto, Date> colpentrega_producto;
    @FXML
    private TableColumn<Producto, Proveedor> colproveedor_producto;
    @FXML
    private TableColumn<Producto, Marca> colmarca_producto;
    @FXML
    private TableColumn<Producto, Number> colvalor_producto;
    @FXML
    private TableView<Producto> tv_productos;
    private double xOffset = 0;
    private double yOffset = 0;
    private HashMap<Integer,Integer> listaCotiza;
    thread hilo;
    @FXML
    private JFXButton btnagregar_producto11;
    @FXML
    private JFXButton btnagregar_proveedor;
    @FXML
    private JFXButton btnagregar_marca;
    @FXML
    private JFXButton btnactualizar_producto;
    @FXML
    private JFXButton btneliminar_producto;
    @FXML
    private JFXButton botonagregarcot;
    @FXML
    private JFXButton botoneliminarcot;
    @FXML
    private JFXButton botonagregarcot1;
    
    @FXML
    void agregarproducto(ActionEvent event) {//evento boton agregar
        
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(
                    getClass().getResource("AddProduct.fxml")
            );
            Parent root1 = (Parent) fXMLLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Agregar Producto");
            stage.setScene(new Scene(root1));
            /*Evento Dragg and drop*/
            root1.setOnMousePressed((MouseEvent event1) -> {
                xOffset = event1.getSceneX();
                yOffset = event1.getSceneY();
            });
            root1.setOnMouseDragged((MouseEvent event1) -> {
                stage.setX(event1.getScreenX() - xOffset);
                stage.setY(event1.getScreenY() - yOffset);
            });
            /*Fin del evento*/
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(
                    FXMLDocumentController.class.getName()).log(
                            Level.SEVERE, null, ex
                    );
        }
        
    }
        @FXML
    void eliminarcot(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Eliminar Cotización");
alert.setHeaderText("");
alert.setContentText("¿Seguro que desea eliminar la cotización "+wat+"?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
 
consultas.Insert("delete from cotizacion where ID_COT='"+wat+"';");
    
} else {
}
        
    }
    @FXML
    void agregarcotizacion(ActionEvent event) {//evento boton agregar
        
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(
                    getClass().getResource("Addcotizacion.fxml")
            );
            Parent root1 = (Parent) fXMLLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Agregar Cotización");
            stage.setScene(new Scene(root1));
            /*Evento Dragg and drop*/
            root1.setOnMousePressed((MouseEvent event1) -> {
                xOffset = event1.getSceneX();
                yOffset = event1.getSceneY();
            });
            root1.setOnMouseDragged((MouseEvent event1) -> {
                stage.setX(event1.getScreenX() - xOffset);
                stage.setY(event1.getScreenY() - yOffset);
            });
            /*Fin del evento*/
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(
                    FXMLDocumentController.class.getName()).log(
                            Level.SEVERE, null, ex
                    );
        }
        
    }
    
    private Conexion                    conexion;
    private ObservableList<Marca>       listamarcas;
    private ObservableList<Proveedor>   listaproveedor;
    private ObservableList<Producto>    listaproductos;
    private ObservableList<Cotizacion>  listacotizacion;
    private FilteredList<Producto>      filtereddataproduct;
    private FilteredList<Cotizacion>    filtereddatacotiza;
    private Main application;
     public void setApp(Main application){
        this.application = application;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Me Conecto al servidor mysql y establezco conexión*/
        conexion = new Conexion();
        conexion.establecerConexion();
        listaCotiza = new HashMap<Integer,Integer>();
        /*--------------------------------------------------*/
        listamarcas     =FXCollections.observableArrayList();
        listaproveedor  =FXCollections.observableArrayList();
        listaproductos  =FXCollections.observableArrayList();
        listacotizacion =FXCollections.observableArrayList();
        /*--------------------------------------------------*/
        
        Cotizacion.llenarInformacion( listacotizacion,listaCotiza);
        Proveedor.llenarInformacion (conexion.getConnection(), listaproveedor);
        Marca.llenarInformacion     (conexion.getConnection(), listamarcas);
        /*--------------------------------------------------*/
        
        tvCotizacion();
        tv_cotizacion.setItems(listacotizacion);
        
        tvProducto();
        tv_productos.setItems(listaproductos);
        /*--------------------------------------------------*/
        eventoTvCotizacion();
        
        filtroProducto();
        filtroCotizacion();
        hilo=new thread(this);
        hilo.start();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    /***************************************************************************
    ****************************************************************************
    ************************   Sección de metodos   ****************************
    ****************************************************************************
    ***************************************************************************/
    
    private void tvProducto(){
        colnombre_producto.setCellValueFactory(
            new PropertyValueFactory<Producto, String>("nombre_producto")
        );
        columedida_producto.setCellValueFactory(
            new PropertyValueFactory<Producto, String>("u_medida")
        );
        colpentrega_producto.setCellValueFactory(
            new PropertyValueFactory<Producto, Date>("pentrega")
        );
        colproveedor_producto.setCellValueFactory(
            new PropertyValueFactory<Producto, Proveedor>("proveedor")
        );
        colmarca_producto.setCellValueFactory(
            new PropertyValueFactory<Producto, Marca>("marca")
        );
        colvalor_producto.setCellValueFactory(
            new PropertyValueFactory<Producto, Number>("valor")
        );
        /*--------------------------------------------------------------------*/
        tv_productos.setEditable(true);
        colnombre_producto.setCellFactory(TextFieldTableCell.forTableColumn());
        /*--------------------------------------------------------------------*/
        colnombre_producto.setOnEditCommit(data -> {
            System.out.println("Nuevo Nombre: " + data.getNewValue());
            System.out.println("Antiguo Nombre: " + data.getOldValue());
           
            Producto p = data.getRowValue();
            p.setNombre_producto(data.getNewValue());                    
            System.out.println(p);

        });
        /*--------------------------------------------------------------------*/
        colmarca_producto.setCellFactory(
                ChoiceBoxTableCell.forTableColumn(listamarcas)
        );
        colmarca_producto.setOnEditCommit(data -> {
            Producto pp = data.getRowValue();
            Marca m = data.getNewValue();
            //colmarca_producto.getCellData(pp);
            //jtfnombre_marca.setText(String.valueOf(m.getNombre_marca()));
        });
        /*-----------------------------------------------FXMLDocument---------------------*/
        colproveedor_producto.setCellFactory(
                ChoiceBoxTableCell.forTableColumn(listaproveedor)
        );
        colproveedor_producto.setOnEditCommit(data -> {
            Producto pp = data.getRowValue();
            Proveedor pro = data.getNewValue();
            //colmarca_producto.getCellData(pp);
            //jtfnombre_marca.setText(String.valueOf(m.getNombre_marca()));
        });
        /*---------------------------------------------------------------------*/
       
//coleliminar_producto.setCellValueFactory(cell -> {
//    Producto p = cell.getValue();
//    return new ReadOnlyBooleanWrapper(p.getActivo());
//});
//coleliminar_producto.setCellFactory(CheckBoxTableCell.forTableColumn(coleliminar_producto));
//        
        
        
        
    }
    
    private void tvCotizacion(){
        colid_cotizacion.setCellValueFactory(
            new PropertyValueFactory<Cotizacion, Number>("id_cot")
        );
        colfecha_cotizacion.setCellValueFactory(
            new PropertyValueFactory<Cotizacion, String>("fecha_cot")
        );
        colnp_cotizacion.setCellValueFactory(
            new PropertyValueFactory<Cotizacion, Number>("n_productos")
        );
        coldescripcion_cotizacion.setCellValueFactory(
            new PropertyValueFactory<Cotizacion, String>("descripcion")
        );
    }
    
    public static int wat;
    /* 
    ** evento que detecta la cotización seleccionada y displaya los productos
    ** pertenecientes a ella
    **
    */
    private void eventoTvCotizacion(){
        tv_cotizacion.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, selectedValue) -> {
                listaproductos.removeAll(listaproductos);
                Producto.llenarInformacion  (conexion.getConnection(), 
                listaproductos, selectedValue.getId_cot());
                wat=selectedValue.getId_cot();
            }
        );
    }
    private void eventoTvProducto(){
        tv_productos.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, selectedValue) -> {
                //jtfid_producto.setText(String.valueOf(selectedValue.getId_producto()));
                //jtfnombre_producto.setText(selectedValue.getNombre_producto());
            }
                
        );
    }
    
     public ObservableList getListCotiza(){
         return listacotizacion;
    }
    public HashMap getMapCot(){
        return listaCotiza;
    }
    private void filtroProducto(){
      filtereddataproduct= new FilteredList<>(listaproductos, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filtereddataproduct.setPredicate(producto -> {
                // si el texto del filtro esta vacio, muestra a todas las personas.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (producto.getNombre_producto().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // filtro por nombre
                } else if (producto.getMarca().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filltro por marca
                }else if(producto.getProveedor().toString().toLowerCase().contains(lowerCaseFilter)){
                    return true; //fitro por proveedor
                }else if(String.valueOf(producto.getPentrega().toLocalDate()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //filtro por fecha de entrega
                }else if(producto.getU_medida().toLowerCase().contains(lowerCaseFilter)){
                    return true; // filtro por unidad de medida
                }else if(String.valueOf(producto.getValor()).toLowerCase().contains(lowerCaseFilter)){
                    return true; // filtro por valor de producto
                }return false; // Does not match.
            });
        });
        SortedList<Producto> sorteddataproduct = new SortedList<>(filtereddataproduct);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sorteddataproduct.comparatorProperty().bind(tv_productos.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tv_productos.setItems(sorteddataproduct);
      
    }
    private void filtroCotizacion(){
      filtereddatacotiza = new FilteredList<>(listacotizacion, p -> true);
        filterField1.textProperty().addListener((observable, oldValue, newValue) -> {
            filtereddatacotiza.setPredicate(cotizacion -> {
                // si el texto del filtro esta vacio, muestra a todas las personas.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(cotizacion.getId_cot()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // filtro por id
                } else if (cotizacion.getFecha_cot().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filltro por fecha
                }else if(cotizacion.getDescripcion().toLowerCase().contains(lowerCaseFilter)){
                    return true; //fitro por descrpción
                }else if(String.valueOf(cotizacion.getId_creador()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //filtro por id proveedor
                }else if(String.valueOf(cotizacion.getN_productos()).toLowerCase().contains(lowerCaseFilter)){
                    return true; // filtro por numero de productos
                
                }return false; // Does not match.
            });
        });
        SortedList<Cotizacion> sorteddatacotiza = new SortedList<>(filtereddatacotiza);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sorteddatacotiza.comparatorProperty().bind(tv_cotizacion.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tv_cotizacion.setItems(sorteddatacotiza);
      
    }


            
    
}
