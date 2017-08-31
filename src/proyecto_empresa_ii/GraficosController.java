/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import proyecto_empresa_ii.modelo.consultas;

/**
 * FXML Controller class
 *
 * @author balannor
 */
public class GraficosController implements Initializable {

    @FXML
    private AnchorPane ap_graficos;
    @FXML
    private BarChart barChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            int productos=FXMLDocumentController.productoS;
            int cotizacion=FXMLDocumentController.wat;
            String sql="SELECT NOMBRE_PRODUCTO,NOMBRE_PROVEEDOR,VALOR_FINAL FROM "
                    + "Producto_Com,pertenece,proveedor WHERE  "
                    + "Producto_Com.ID_PRODUCTO_PROVEEDOR= pertenece.ID_PRODUCT_PRO "
                    + "AND Producto_Com.ID_PROVEEDOR=proveedor.ID_PROVEEDOR "
                    + "AND Producto_Com.ID_PRODUCTO="+productos+" AND pertenece.ID_COT="+cotizacion+";";
            ResultSet resultado=consultas.Select(sql);
            String nombreP="";
            while (resultado.next()) {
                series.getData().add(new XYChart.Data<>(resultado.getString("NOMBRE_PROVEEDOR"), resultado.getInt("VALOR_FINAL")));
                //nombreP=resultado.getNString("NOMBRE_PRODUCTO");
                System.out.println("valor finalgghg"+resultado.getInt("VALOR_FINAL"));
            }
//       PieChart.Data sector1 = new PieChart.Data("Nicolás",  25);
//       PieChart.Data sector2 = new PieChart.Data("Jerson",   25);
//       PieChart.Data sector3 = new PieChart.Data("PASCAL",  1000);
//       PieChart.Data sector4 = new PieChart.Data("Abdel",    4000);
//
//       pieChart.getData().add(sector1);
//       pieChart.getData().add(sector2);
//       pieChart.getData().add(sector3);
//       pieChart.getData().add(sector4);
//       pieChart.setLegendSide(Side.LEFT);
         barChart.getData().add(series);
         barChart.setAccessibleHelp(nombreP);
        } catch (SQLException ex) {
            Logger.getLogger(GraficosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
