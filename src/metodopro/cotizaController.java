/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodopro;


import bd.consultas;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelos.Cotizacion;
import modelos.Tablaproducto;
import sesion.Sesion;


/**
 *
 * @author jerson,pascal
 */
public class cotizaController implements Initializable {
    
    //@FXML
    
    //variables de formulario tab 3
    @FXML private TextField tfnombre;
    @FXML private TextField tfapellido;
    @FXML private TextField tftelefono;
    @FXML private TextField tfcontrasena;

    
    //variables tabla 1 tab 1
    @FXML private TableColumn IDcot;
    @FXML private TableColumn FechaCot;
    @FXML private TableColumn Nproducot;
    @FXML private TableColumn descripcot;
      @FXML private Button f5boton;
      
    //variables tabla 2 tab 1
    @FXML private TableView tablapro;
    @FXML private TableColumn idpro;
    @FXML private TableColumn nombrepro;
    @FXML private TableColumn preciopro;
    @FXML private TableColumn unidadmedidapro;
    @FXML private TableColumn <Tablaproducto,String> idmarcapro;
    @FXML private TableColumn idprove;
    @FXML private TableColumn pentregapro;
    @FXML TableView<Cotizacion> tablacot;
     
    private Text actionStatus;
    private ObservableList data;
    private ObservableList<Tablaproducto> data2;


 
  
    private MetodoPro application;

    
    
    public void setApp(MetodoPro application){
        this.application = application;
        tfnombre.setText(Sesion.CurrentUser.getUSER_NOMBRE());
        tfapellido.setText(Sesion.CurrentUser.getUSER_APELLIDO());
        tftelefono.setText(Sesion.CurrentUser.getFONO_USER());
        tfcontrasena.setText(Sesion.CurrentUser.getPASS_USER());
  //listado post inicio
 llenartablacot();
 //llenartablados debe moverse a un action
        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);
    }
    //funciones actualizar tablas
    public void llenartablacot(){
        data = gettablauno();
        tablacot.setItems(data);
    }
   public void llenartablados(int rawr){
    data2 = gettablados(rawr);
    tablapro.setItems(data2);

  } 
   //abre el formulario alabado sea alaaa
   public void Abrir_Formulario_Producto(ActionEvent event) throws IOException{
        Parent formulario= FXMLLoader.load(getClass().getResource("Formulario_Productos.fxml"));
        Scene scena= new Scene(formulario);
        Stage stage = new Stage();
        stage.setScene(scena);
        stage.show();
   }
    
    //extraer datos de BD :D (tabla cotizacion, loco)
    ObservableList gettablauno() { 
        IDcot.setCellValueFactory(new PropertyValueFactory<Cotizacion,String>("ID_COT"));
        FechaCot.setCellValueFactory(new PropertyValueFactory<Cotizacion,String>("FECHA_COT"));
        Nproducot.setCellValueFactory(new PropertyValueFactory<Cotizacion,String>("N_PRODUCTOS"));
        descripcot.setCellValueFactory(new PropertyValueFactory<Cotizacion,String>("DESCRIPCION"));
        List list = new ArrayList();
        try{
        ResultSet resultSet=consultas.Select("SELECT `ID_COT`,`FECHA_COT`,`N_PRODUCTOS`,`DESCRIPCION` FROM `cotizacion`");     
    while(resultSet.next())
    {    Cotizacion papo= new Cotizacion();
    
       papo.setID_COT(resultSet.getInt("ID_COT"));    
       papo.setFECHA_COT(resultSet.getString("FECHA_COT"));
       papo.setN_PRODUCTOS(resultSet.getInt("N_PRODUCTOS"));    
       papo.setDESCRIPCION(resultSet.getString("DESCRIPCION"));
        list.add(papo);}
        }
        catch(SQLException sqlException) {
        sqlException.printStackTrace();
        System.exit(1);}     
      ObservableList data = FXCollections.observableList(list);
        return data;
    }
    //extraer datos de BD con entero sacado de la tabla cot con el ID  de la cot
        ObservableList gettablados(int miau) { 
            //NO TOCAR POR EL AMOR DE JESUS NO TOCAR
        idpro.setCellValueFactory(new PropertyValueFactory<Tablaproducto,String>("ID_PRODUCTO"));  
        nombrepro.setCellValueFactory(new PropertyValueFactory<Tablaproducto,String>("NOMBRE_PRODUCTO"));
       unidadmedidapro.setCellValueFactory(new PropertyValueFactory<Tablaproducto,String>("U_MEDIDA")); 
        preciopro.setCellValueFactory(new PropertyValueFactory<Tablaproducto,String>("PRECIO_PRODUCTO"));
        idmarcapro.setCellValueFactory(new PropertyValueFactory<Tablaproducto,String>("NOMBRE_MARCA"));
         idprove.setCellValueFactory(new PropertyValueFactory<Tablaproducto,String>("NOMBRE_PROVEEDOR"));
         pentregapro.setCellValueFactory(new PropertyValueFactory<Tablaproducto,String>("PENTREGA"));
             List list2 = new ArrayList();
        try{    
ResultSet resultSet2=consultas.Select("SELECT t1.ID_PRODUCTO,t1.NOMBRE_PRODUCTO,t1.U_MEDIDA,t1.PENTREGA ,t3.PRECIO_PRODUCTO, t4.NOMBRE_PROVEEDOR, t6.NOMBRE_MARCA FROM producto t1,pertenece t2 , tiene t3 , proveedor t4, productom t5, marca t6 WHERE t4.ID_PROVEEDOR=t3.ID_PROVEEDOR AND t3.ID_PRODUCTO= t1.ID_PRODUCTO AND t1.ID_PRODUCTO=t5.ID_PRUDUCTO AND t5.ID_MARCA= t6.ID_MARCA AND t1.ID_PRODUCTO=t2.ID_PRODUCT AND t2.ID_COT='"+miau+"';");     

    while(resultSet2.next())
    {   Tablaproducto papo2= new Tablaproducto(); 
        papo2.setID_PRODUCTO(resultSet2.getInt("ID_PRODUCTO"));    
        papo2.setNOMBRE_PRODUCTO(resultSet2.getString("NOMBRE_PRODUCTO"));
        papo2.setU_MEDIDA(resultSet2.getString("U_MEDIDA")); 
        papo2.setPRECIO_PRODUCTO(resultSet2.getInt("PRECIO_PRODUCTO"));
        papo2.setNOMBRE_MARCA(resultSet2.getString("NOMBRE_MARCA"));
        papo2.setNOMBRE_PROVEEDOR(resultSet2.getString("NOMBRE_PROVEEDOR"));
        papo2.setPENTREGA(resultSet2.getString("PENTREGA"));
         list2.add(papo2);
    }}
        catch(SQLException sqlException) {
        System.exit(1);}     
      ObservableList data2 = FXCollections.observableList(list2);
        return data2;}
    
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        //valores de objeto para la table view de cotizacion NO MOVER O ESTO EXPLOTA PAPO

        //valores de objeto para la table view de producto NO MOVER!!!!1111!!!!!
      

//eventos tabla 1
tablacot.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override//why not
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //chequea que item esta seleccionado
                if (tablacot.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = tablacot.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    tablePosition.getTableView().getSelectionModel().getTableView().getId();
                    //para tomar valor de seleccionado
        //   Object value = tablePosition.getTableColumn().getCellData(newValue);
                Cotizacion papo3 = new Cotizacion();
                papo3 =  tablacot.getSelectionModel().getSelectedItem();
   
                    //Object GetSinglevalue = tablePosition.getTableColumn().getCellData(newValue);
                    //para tomar valor de la primera columna re replica el modelo con decl para evitar parsing :-)
       //pasa id a funcion
          llenartablados(papo3.getID_COT());
                }}}
);

   
        //evento para tabla 2
        tablapro.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override//why not
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //chequea que item esta seleccionado
                if (tablapro.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = tablapro.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    tablePosition.getTableView().getSelectionModel().getTableView().getId();
             
                Object value = tablePosition.getTableColumn().getCellData(newValue);
          //      Cotizacion papo3 = new Cotizacion();
          //      papo3 =  tablapro.getSelectionModel().getSelectedItem();
   
                 //   Object GetSinglevalue = tablePosition.getTableColumn().getCellData(newValue);
                    
                    //para tomar valor de la primera columna re replica el modelo con decl para evitar parsing :-)
       //pasa id a funcion
        //  llenartablados(papo3.getID_COT());

                    System.out.println("valor de tablita producto:   " + value);
                }}});

    }
  
    
}
