/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Integer;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import proyecto_empresa_ii.modelo.Conexion;
import proyecto_empresa_ii.modelo.Cotizacion;
import proyecto_empresa_ii.modelo.Marca;
import proyecto_empresa_ii.modelo.Producto;
import proyecto_empresa_ii.modelo.Proveedor;
import proyecto_empresa_ii.modelo.consultas;
import sesion.Sesion;

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
    private TableColumn<Producto, LocalDate> colpentrega_producto;
    @FXML
    private TableColumn<Producto, Proveedor> colproveedor_producto;
    @FXML
    private TableColumn<Producto, Marca> colmarca_producto;
    @FXML
    private TableColumn<Producto, Integer> colvalor_producto;
    @FXML
    private TableView<Producto> tv_productos;
    private PreparedStatement preparedStmt;
    private double xOffset = 0;
    private double yOffset = 0;
    private HashMap<Integer,Integer> listaCotiza;
    thread hilo;
    @FXML
    private JFXButton btnagregar_producto11;
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
    private JFXButton btnprodacot;
    
    /*-----------------------tab2----------------------*/
    @FXML
    private ComboBox<Proveedor> cmbproveedor1;
        private ObservableList<Marca>       listamarcas1;
    private ObservableList<Proveedor>   listaproveedor1;
    @FXML
    private JFXButton btneditarthisproveedor;
    @FXML
    private JFXTextField jtfid_proveedor;
    @FXML
    private JFXTextField jtfnombre_proveedor;
    @FXML
    private JFXTextField jtf_c_prod_dcto_proveedor;
    @FXML
    private JFXTextField jtfdcto_proveedor;
    @FXML
    private JFXButton btnlimpiarcamposproveedor;
    @FXML
    private JFXButton btnagregar_proveedor;
    public static int wat;
    @FXML
    private Label excepcion_proveedor;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    public static TabPane rootP;
    @FXML
    private TabPane root;
    @FXML
    private Label mensajesql;
    @FXML
    private JFXButton btndelproveedor;
    
    //-------------------------------------tab 3------------------------------
        @FXML
    private JFXTextField tfDatosNombre;

    @FXML
    private JFXTextField tfDatosApellidos;

    @FXML
    private JFXTextField tfDatosCuenta;

    @FXML
    private JFXTextField tfDatosTelefono;

    @FXML
    private JFXTextField tfDatosTipoCuenta;
    
    
    @FXML
    private JFXButton btnCrearCuentas;
    
    
  @FXML
    private void llenarproveedorlist(ActionEvent event) {
     jtfid_proveedor.setText(cmbproveedor1.getValue().getId_proveedor()+"");
     jtfnombre_proveedor.setText(cmbproveedor1.getValue().getNombre_proveedor());
     jtfdcto_proveedor.setText(cmbproveedor1.getValue().getDcto_proveedor()+"");
     jtf_c_prod_dcto_proveedor.setText(cmbproveedor1.getValue().getC_pro_dcto()+"");
    }
    @FXML
    void evento_grafico_test(ActionEvent event) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(
                getClass().getResource("Graficos.fxml")
            );
            Parent root1 = (Parent) fXMLLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Graficos");
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
    private void limpialistaproveedor() {
     jtfid_proveedor.setText(null);
     jtfnombre_proveedor.setText(null);
     jtfdcto_proveedor.setText(null);
     jtf_c_prod_dcto_proveedor.setText(null);
     mensajesql.setText(null);
    }

    @FXML
    private void addnewproveedor(ActionEvent event) {
        limpialistaproveedor();
         try {
            FXMLLoader fXMLLoader = new FXMLLoader(
                    getClass().getResource("addproveedor.fxml")
            );
            Parent root1 = (Parent) fXMLLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Agregar Proveedor");
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
    private void editarproveedor(ActionEvent event) {int x;
    
  x=consultas.Insert("UPDATE `proveedor` SET `NOMBRE_PROVEEDOR`='"+jtfnombre_proveedor.getText()+"',`DCTO_PROVEEDOR`='"+jtfdcto_proveedor.getText()+"',`C_PRO_DCTO`='"+jtf_c_prod_dcto_proveedor.getText()+"' WHERE ID_PROVEEDOR='"+cmbproveedor1.getValue().getId_proveedor()+"';"); 
  if(x==0){
    mensajesql.setText("Proveedor '"+cmbproveedor1.getValue().getId_proveedor()+"' editado exitosamente");
    }
    }
    
     @FXML
    private void deleteproveedor(ActionEvent event) {
       
        
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Eliminar Proveedor");
alert.setHeaderText("");
alert.setContentText("¿Seguro que desea eliminar al proveedor "+cmbproveedor1.getValue().getNombre_proveedor()+"?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
 
   int x;
    
  x=consultas.Insert("DELETE FROM `proveedor` WHERE ID_PROVEEDOR='"+cmbproveedor1.getValue().getId_proveedor()+"';"); 
  if(x==0){
    mensajesql.setText("Proveedor '"+cmbproveedor1.getValue().getNombre_proveedor()+"' eliminado exitosamente");
    }
    
} else {
}}
/*-------------------------fintab2----------------------*/

    
    
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
        /*-----------------------tab2-----------------------*/
             listaproveedor1  =FXCollections.observableArrayList();
        Proveedor.llenarInformacion (conexion.getConnection(), listaproveedor1);
        Proveedor.autocompletar(cmbproveedor1, listaproveedor1);
        /*-----------------------hamb-----------------------*/
                rootP = root;
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            
            if(drawer.isShown())
            {
                drawer.close();
            }else
                drawer.open();
        });
        
        // tab3 ------------------llenar textfield----------------
         tfDatosNombre.setText(Sesion.CurrentUser.getUSER_NOMBRE());
         tfDatosTelefono.setText(String.valueOf(Sesion.CurrentUser.getFONO_USER()));
         tfDatosApellidos.setText(Sesion.CurrentUser.getUSER_APELLIDO());
         tfDatosCuenta.setText(Sesion.CurrentUser.getUSER_USERNAME());
         if (Sesion.CurrentUser.getROL()==2){
   
             tfDatosTipoCuenta.setText("Administrador");
                 }
                else
                 {
                 tfDatosTipoCuenta.setText("Usuario"); 
                  btnCrearCuentas.setVisible(false);
                   }
    }

        
        
        
        
        
    
    
    
    
    
    /***************************************************************************
    ****************************************************************************
    ************************   Sección de metodos   ****************************
    ****************************************************************************
    ***************************************************************************/
    
    private void tvProducto(){
       
        tv_productos.setEditable(true);//activo edición sobre tabla
        /*--------------------------------------------------------------------*/
        colnombre_producto.setCellValueFactory(
            new PropertyValueFactory<Producto, String>("nombre_producto")
        );
        colnombre_producto.setCellFactory(TextFieldTableCell.forTableColumn());
        colnombre_producto.setOnEditCommit(data -> {
            System.out.println("Antiguo Nombre: " + data.getOldValue());   
            Producto p = data.getRowValue();
            p.setNombre_producto(data.getNewValue());
            System.out.println("Nuevo Nombre: " + data.getNewValue());             
            System.out.println(p);
            try {
                String query = "UPDATE producto SET NOMBRE_PRODUCTO = ? where ID_PRODUCTO = ?";
                preparedStmt = conexion.getConnection().prepareStatement(query);
                preparedStmt.setString(1, data.getNewValue());
                preparedStmt.setInt(2, p.getId_producto());
                preparedStmt.executeUpdate();
                preparedStmt.clearParameters();// no es necesario
                preparedStmt.close();
                //conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            pepe(wat);
        });
        /*--------------------------------------------------------------------*/
        //Evento modificar y actualizar U Medida
        columedida_producto.setCellValueFactory(
            new PropertyValueFactory<Producto, String>("u_medida")
        );
        columedida_producto.setCellFactory(TextFieldTableCell.forTableColumn());
        columedida_producto.setOnEditCommit(data -> {
            System.out.println("Antiguo Nombre: " + data.getOldValue());   
            Producto p = data.getRowValue();
            p.setNombre_producto(data.getNewValue());
            System.out.println("Nuevo Nombre: " + data.getNewValue());             
            System.out.println(p);
            try {
                String query = "UPDATE producto SET U_MEDIDA = ? where ID_PRODUCTO = ?";
                preparedStmt = conexion.getConnection().prepareStatement(query);
                preparedStmt.setString(1, data.getNewValue());
                preparedStmt.setInt(2, p.getId_producto());
                preparedStmt.executeUpdate();
                preparedStmt.clearParameters();// no es necesario
                preparedStmt.close();
                //conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }      
            pepe(wat);
        });        
        /*--------------------------------------------------------------------*/
        colpentrega_producto.setCellValueFactory(
            new PropertyValueFactory<Producto,LocalDate>("pentrega")
        );
        LocalDateStringConverter converter = new LocalDateStringConverter();
        colpentrega_producto.setCellFactory(TextFieldTableCell.<Producto, LocalDate>forTableColumn(converter));
        colpentrega_producto.setOnEditCommit(data -> { 
            System.out.println("Antiguo Nombre: " + data.getOldValue());   
            Producto p = data.getRowValue();
            p.setPentrega(data.getNewValue());
            System.out.println("Nuevo Nombre: " + data.getNewValue());             
            System.out.println(p);
            Date date = java.sql.Date.valueOf(p.getPentrega());
            try {
                String query = "UPDATE producto SET PENTREGA= ? where ID_PRODUCTO = ?";
                preparedStmt = conexion.getConnection().prepareStatement(query);
                preparedStmt.setDate(1,date);
                preparedStmt.setInt(2, p.getId_producto());
                preparedStmt.executeUpdate();
                preparedStmt.clearParameters();// no es necesario
                preparedStmt.close();
                //conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }      
            pepe(wat);
        });
        /*--------------------------------------------------------------------*/
        colproveedor_producto.setCellValueFactory(
            new PropertyValueFactory<Producto, Proveedor>("proveedor")
        );
        colmarca_producto.setCellValueFactory(
            new PropertyValueFactory<Producto, Marca>("marca")
        );
        /*--------------------------------------------------------------------*/
        colvalor_producto.setCellValueFactory(
            new PropertyValueFactory<Producto, Integer>("valor")
        );
        IntegerStringConverter converter2 = new IntegerStringConverter();
        colvalor_producto.setCellFactory(TextFieldTableCell.<Producto, Integer>forTableColumn(converter2));
        colvalor_producto.setOnEditCommit(data -> {
            System.out.println("Antiguo Nombre: " + data.getOldValue());   
            Producto p = data.getRowValue();
            p.setValor(data.getNewValue());
            System.out.println("Nuevo Nombre: " + data.getNewValue());             
            System.out.println(p);
            try {
                String query = "UPDATE producto SET VALOR = ? where ID_PRODUCTO = ?";
                preparedStmt = conexion.getConnection().prepareStatement(query);
                preparedStmt.setDouble(1, data.getNewValue());
                preparedStmt.setInt(2, p.getId_producto());
                preparedStmt.executeUpdate();
                preparedStmt.clearParameters();// no es necesario
                preparedStmt.close();
                //conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }      
            pepe(wat);
        });
        /*--------------------------------------------------------------------*/

        
        /*--------------------------------------------------------------------*/
        colmarca_producto.setCellFactory(
                ChoiceBoxTableCell.forTableColumn(listamarcas)
        );
        colmarca_producto.setOnEditCommit(data -> {
            Producto p = data.getRowValue();
            Marca m = data.getNewValue();
            p.setMarca(m);
            try {
                String query = "UPDATE producto SET ID_MARCA = ? where ID_PRODUCTO = ?";
                preparedStmt = conexion.getConnection().prepareStatement(query);
                preparedStmt.setInt(1, m.getId_marca());
                preparedStmt.setInt(2, p.getId_producto());
                preparedStmt.executeUpdate();
                preparedStmt.clearParameters();// no es necesario
                preparedStmt.close();
                //conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }      
            pepe(wat);
            
            //colmarca_producto.getCellData(pp);
            //jtfnombre_marca.setText(String.valueOf(m.getNombre_marca()));
        });
        

        
        
        /*-----------------------------------------------FXMLDocument---------------------*/
        colproveedor_producto.setCellFactory(
                ChoiceBoxTableCell.forTableColumn(listaproveedor)
        );
        colproveedor_producto.setOnEditCommit(data -> {
            Producto p = data.getRowValue();
            Proveedor pro = data.getNewValue();
            p.setProveedor(pro);
        try {
                String query = "UPDATE producto SET ID_PROVEEDOR = ? where ID_PRODUCTO = ?";
                preparedStmt = conexion.getConnection().prepareStatement(query);
                preparedStmt.setInt(1, pro.getId_proveedor());
                preparedStmt.setInt(2, p.getId_producto());
                preparedStmt.executeUpdate();
                preparedStmt.clearParameters();// no es necesario
                preparedStmt.close();
                //conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }      
            pepe(wat);
            
            
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
    private void pepe(int wat){
       listaproductos.removeAll(listaproductos);
       Producto.llenarInformacion  (conexion.getConnection(), 
       listaproductos, wat);          
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
                }else if(String.valueOf(producto.getPentrega()).toLowerCase().contains(lowerCaseFilter)){
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

    @FXML
    private void agregarprodacot(ActionEvent event) {
         
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(
                    getClass().getResource("addproductocotizacion.fxml")
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
        
  @FXML
  private void NuevasCuentas (ActionEvent event){
       try {
            FXMLLoader fXMLLoader = new FXMLLoader(
            getClass().getResource("NuevasCuentas.fxml")
            );
            Parent root1 = (Parent) fXMLLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("NuevasCuentas");
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
}

