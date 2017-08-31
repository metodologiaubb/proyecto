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
import java.time.format.DateTimeFormatter;
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
import proyecto_empresa_ii.modelo.PdfCreator;
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
    private TableColumn<Producto, Double>colvalor_productofinal;
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
    private JFXButton botonagregarcot;
    @FXML
    private JFXButton botoneliminarcot;
    @FXML
    private JFXButton botonagregarcot1;
    @FXML
     private JFXButton btnInforme;
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
    private JFXTextField jtftelefono_proveedor;
    @FXML
    private JFXButton btnlimpiarcamposproveedor;
    @FXML
    private JFXButton btnagregar_proveedor;
    public static int wat;
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
    
    @FXML
    private JFXButton btnCrearCuentas;
    @FXML
    private JFXButton btnguardar_marca;
    @FXML
    private JFXButton btnlimpiar_campos_marca;
    @FXML
    private JFXButton btneliminar_marca;
    @FXML
    private  ComboBox<Marca> cmbmarca;
    
    
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
    private JFXTextField jtfid_marca;
    @FXML
    private JFXTextField jtfnombre_marca;
    @FXML
    private JFXButton btnGestionarCuenta;
    @FXML
    private JFXButton btnModificar;
    @FXML
     private JFXButton btnActualizar;
    @FXML
    private JFXTextField tfDatospass;
    
    
    /*--------------------tab2--------------------------------------*/
    
        @FXML
    private void newmarca(ActionEvent event) {
 limpialistamarca();
        try {
            Stage stage1 = new Stage();
            FXMLLoader fXMLLoader = new FXMLLoader();
            Parent root1 = (Parent) fXMLLoader.load(getClass().getResource("addmarca.fxml").openStream());
            addmarcacontroller marcacontroller=(addmarcacontroller)fXMLLoader.getController();
            marcacontroller.setcomobox(cmbmarca,listamarcas1);
            stage1.initStyle(StageStyle.UTILITY);
            stage1.setTitle("Agregar Marca");
            stage1.setScene(new Scene(root1));
            /*Evento Dragg and drop*/
            root1.setOnMousePressed((MouseEvent event1) -> {
                xOffset = event1.getSceneX();
                yOffset = event1.getSceneY();
            });
            root1.setOnMouseDragged((MouseEvent event1) -> {
                stage1.setX(event1.getScreenX() - xOffset);
                stage1.setY(event1.getScreenY() - yOffset);
            });

            
            /*Fin del evento*/
            stage1.show();
      
        } catch (IOException ex) {
            Logger.getLogger(
                    FXMLDocumentController.class.getName()).log(
                            Level.SEVERE, null, ex
                    );
        } 
    }
    
    @FXML
    private void editarmarca(ActionEvent event) {
        int x=5;
   if(jtfnombre_marca.getText().length()==0 && jtfid_marca.getText().length()==0){
   
   }else{     
  x=consultas.Insert("UPDATE `marca` SET `NOMBRE_MARCA`='"+jtfnombre_marca.getText()+"' WHERE ID_MARCA='"+cmbmarca.getValue().getId_marca()+"';"); 
   }
   
   if(x==0){
    mensajesql.setText("Marca '"+cmbmarca.getValue().getId_marca()+"' editada exitosamente");
  actualizarcmbmarca();
    }
      
    }

    private void actualizarcmbmarca(){
             listamarcas1=null;
       cmbmarca.setValue(null);
  cmbmarca.getItems().clear();
    listamarcas1  =FXCollections.observableArrayList();
           Marca.llenarInformacion(conexion.getConnection(), listamarcas1);
            Marca.autocompletar(cmbmarca, listamarcas1); 
    }
    private void actualizarcmbproveedor(){
        listaproveedor=null;
      //  cmbproveedor1.setValue(null);
        cmbproveedor1.getItems().clear();
        listaproveedor=FXCollections.observableArrayList();
        Proveedor.llenarInformacion(conexion.getConnection(), listaproveedor);
        Proveedor.autocompletar(cmbproveedor1, listaproveedor);
    }
    
    
    @FXML
    private void limpialistamarca()  {
        jtfid_marca.setText(null);
        jtfnombre_marca.setText(null);
        mensajesql.setText(null);
        actualizarcmbmarca();
    
    }

    @FXML
    private void deletemarca(ActionEvent event) {
        
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Eliminar Marca");
alert.setHeaderText("");
alert.setContentText("¿Seguro que desea eliminar la marca "+cmbmarca.getValue().getId_marca()+"?");
Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){

   int x;
  x=consultas.Insert("DELETE FROM `marca` WHERE ID_MARCA='"+cmbmarca.getValue().getId_marca()+"'"); 
  if(x==0){
    mensajesql.setText("Marca '"+cmbmarca.getValue().getNombre_marca()+"' eliminada exitosamente");
actualizarcmbmarca();

    }
    
} else {
}
       
    }
  
    @FXML
    private void llenarmarcalist(ActionEvent event) {
       jtfid_marca.setText(cmbmarca.getValue().getId_marca()+"");
       jtfnombre_marca.setText(cmbmarca.getValue().getNombre_marca()+"");
    }

 
    
    @FXML
    private void llenarproveedorlist(ActionEvent event) {
     jtfid_proveedor.setText(cmbproveedor1.getValue().getId_proveedor()+"");
     jtfnombre_proveedor.setText(cmbproveedor1.getValue().getNombre_proveedor());
     jtftelefono_proveedor.setText(cmbproveedor1.getValue().getDcto_proveedor()+"");
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
     jtftelefono_proveedor.setText(null);
     jtf_c_prod_dcto_proveedor.setText(null);
     mensajesql.setText(null);
     actualizarcmbproveedor();
    }

    @FXML
    private void addnewproveedor(ActionEvent event) {
        limpialistaproveedor();
         try {
             Stage stage1 = new Stage();
            FXMLLoader fXMLLoader = new FXMLLoader();
            Parent root1 = (Parent) fXMLLoader.load(getClass().getResource("addproveedor.fxml").openStream());
            addproveedorcontroller proveedorcontroller=(addproveedorcontroller)fXMLLoader.getController();
            proveedorcontroller.setlistabaox(cmbproveedor1, listaproveedor);
            stage1.initStyle(StageStyle.UTILITY);
            stage1.setTitle("Agregar Proveedor");
            stage1.setScene(new Scene(root1));
            /*Evento Dragg and drop*/
            root1.setOnMousePressed((MouseEvent event1) -> {
                xOffset = event1.getSceneX();
                yOffset = event1.getSceneY();
            });
            root1.setOnMouseDragged((MouseEvent event1) -> {
                stage1.setX(event1.getScreenX() - xOffset);
                stage1.setY(event1.getScreenY() - yOffset);
            });

            
            /*Fin del evento*/
            stage1.show();
      
        } catch (IOException ex) {
            Logger.getLogger(
                    FXMLDocumentController.class.getName()).log(
                            Level.SEVERE, null, ex
                    );
        } 
    }
      @FXML
    private void editarproveedor(ActionEvent event) {
        int x=5;
        if(jtfnombre_proveedor.getText().length()==0 && jtftelefono_proveedor.getText().length()==0 && jtf_c_prod_dcto_proveedor.getText().length()==0 && jtfid_proveedor.getText().length()==0){
           
        }else{
  x=consultas.Insert("UPDATE `proveedor` SET `NOMBRE_PROVEEDOR`='"+jtfnombre_proveedor.getText()+"',`TELEFONO`='"+jtftelefono_proveedor.getText()+"',`C_PRO_DCTO`='"+jtf_c_prod_dcto_proveedor.getText()+"' WHERE ID_PROVEEDOR='"+cmbproveedor1.getValue().getId_proveedor()+"';"); 
        }
        
        if(x==0){
    mensajesql.setText("Proveedor '"+cmbproveedor1.getValue().getId_proveedor()+"' editado exitosamente");
      actualizarcmbproveedor();
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
  actualizarcmbproveedor();

    }
    
} else {
}}
/*-------------------------fintab2----------------------*/

    
    
    @FXML
    void agregarproducto(ActionEvent event) {//evento boton agregar
        
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(
                    getClass().getResource("Addproductoproveedor.fxml")
            );
            Parent root1 = (Parent) fXMLLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Vincular producto a proveedor");
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
    @FXML
    void informe(ActionEvent event) {
        PdfCreator pdf=new PdfCreator();
        pdf.reporteCotizacion(wat);
    }
    @FXML
    void abrirReclamos(ActionEvent event) {

    }

    @FXML
    void abrirSoporte(ActionEvent event) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(
                    getClass().getResource("Soporte.fxml")
            );
            Parent root1 = (Parent) fXMLLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Soporte");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(
                    FXMLDocumentController.class.getName()).log(
                            Level.SEVERE, null, ex
                    );
        }

    }
    
    
    
    public static Conexion                    conexion;
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
        btnInforme.disableProperty().setValue(Boolean.TRUE);
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
           listamarcas1  =FXCollections.observableArrayList();
           Marca.llenarInformacion(conexion.getConnection(), listamarcas1);
            Marca.autocompletar(cmbmarca, listamarcas1);
        
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
         tfDatospass.setText(Sesion.CurrentUser.getPASS_USER());
         if (Sesion.CurrentUser.getROL()==2){
   
             tfDatosTipoCuenta.setText("Administrador");
                 }
                else
                 {
                 tfDatosTipoCuenta.setText("Usuario"); 
                 btnCrearCuentas.setVisible(false);
                 btnGestionarCuenta.setVisible(false);
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
                String query = "UPDATE producto_proveedor SET PENTREGA= ? where ID_PRODUCTO = ?";
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
        colvalor_productofinal.setCellValueFactory(
            new PropertyValueFactory<Producto, Double>("valordcto")
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
                String query = "UPDATE producto_proveedor SET VALOR = ? where ID_PRODUCTO = ?";
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
                String query = "UPDATE producto_proveedor SET ID_PROVEEDOR = ? where ID_PRODUCTO = ?";
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
	tv_cotizacion.setEditable(true);        
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
	coldescripcion_cotizacion.setCellFactory(TextFieldTableCell.forTableColumn());
        coldescripcion_cotizacion.setOnEditCommit(data -> {
            System.out.println("Antiguo Nombre: " + data.getOldValue());   
            Cotizacion c = data.getRowValue();
            c.setDescripcion(data.getNewValue());
            System.out.println("Nuevo Nombre: " + data.getNewValue());             
            System.out.println(c);
            try {
                String query = "UPDATE cotizacion SET DESCRIPCION = ? where ID_COT = ?";
                preparedStmt = conexion.getConnection().prepareStatement(query);
                preparedStmt.setString(1, data.getNewValue());
                preparedStmt.setInt(2, c.getId_cot());
                preparedStmt.executeUpdate();
                preparedStmt.clearParameters();// no es necesario
                preparedStmt.close();
                //conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            //pepe(wat);
        });
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
                btnInforme.disableProperty().setValue(Boolean.FALSE);
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
                String pattern = "dd/MM/yyyy";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                LocalDateStringConverter converter = new LocalDateStringConverter();
                String formattedString = producto.getPentrega().format(formatter);

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (producto.getNombre_producto().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // filtro por nombre
                } else if (producto.getMarca().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filltro por marca
                }else if(producto.getProveedor().toString().toLowerCase().contains(lowerCaseFilter)){
                    return true; //fitro por proveedor
                }else if(formattedString.toLowerCase().contains(lowerCaseFilter)){
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
//                } else if (cotizacion.getFecha_cot().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; //filltro por fecha
                }else if(cotizacion.getDescripcion().toLowerCase().contains(lowerCaseFilter)){
                    return true; //fitro por descrpción
//                }else if(String.valueOf(cotizacion.getId_creador()).toLowerCase().contains(lowerCaseFilter)){
//                    return true; //filtro por id proveedor
//                }else if(String.valueOf(cotizacion.getN_productos()).toLowerCase().contains(lowerCaseFilter)){
//                    return true; // filtro por numero de productos
                
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
            stage.setResizable(false);
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
            stage.setTitle("Crear Nuevas Cuentas");
            stage.setScene(new Scene(root1));
            stage.centerOnScreen();
            stage.setResizable(false);
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
    void GestionarCuentas(ActionEvent event) {
            try {
            FXMLLoader fXMLLoader = new FXMLLoader(
            getClass().getResource("GestionarCuentas.fxml")
            );
            Parent root1 = (Parent) fXMLLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Cuentas");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
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
   
    void Actualizar(ActionEvent event) {
        int x=8;
         if (tfDatosCuenta.getText().length()!=0  && tfDatospass.getText().length()!=0 && tfDatosNombre.getText().length()!=0 && tfDatosApellidos.getText().length()!=0 && tfDatosTelefono.getText().length()!=0){ 
            x=consultas.Insert("UPDATE `user` SET `USER_USERNAME` ='"+tfDatosCuenta.getText()+"',`USER_PASS`='"+tfDatospass.getText()+"',`USER_NOMBRE`='"+tfDatosNombre.getText()+"',`USER_APELLIDO`='"+tfDatosApellidos.getText()+"',`USER_FONO`='"+tfDatosTelefono.getText()+"'WHERE ID_USER='"+Sesion.CurrentUser.getID_USER()+"';");
            System.out.println("hola");
            }
             if(x==8){
             Alert alertnull = new Alert(Alert.AlertType.INFORMATION);
             alertnull.setHeaderText("");      
             alertnull.setTitle("Datos incompletos");
             alertnull.setContentText("No se han completado todos los campos para modificar los datos, rellene los campos faltantes");
             Optional<ButtonType> result2 = alertnull.showAndWait();
            }    
       
         
         if (x==0){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
             alerta.setHeaderText("");      
             alerta.setTitle("Datos modificados");
             alerta.setContentText("La cuenta se ha modificado correctamente");
             Optional<ButtonType> result = alerta.showAndWait();
         }
    }
         /*else
        try {

            Statement statement;
            statement = conexion.getConnection().createStatement();
            ResultSet res;
            res=statement.executeQuery("SELECT ID_USER,"
                                             +"USER_USERNAME,"
                                             + "USER_PASS, "
                                             + "USER_NOMBRE,"
                                             + "USER_APELLIDO,"
                                             + "USER_FONO,"
                                             + "USER_ROL "
                                             + "FROM user "
                                             + "WHERE ID_USER = '"+Sesion.CurrentUser.getID_USER()+"'");while (res.next()){
            tfDatosCuenta.setText(res.getString("USER_USERNAME"));
            tfDatospass.setText(res.getString("USER_PASS")); 
            tfDatosNombre.setText(res.getString("USER_NOMBRE"));
            tfDatosApellidos.setText(res.getString("USER_APELLIDO"));
            tfDatosTelefono.setText(res.getString("USER_FONO"));
            }
            
            }       catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
    */
}

