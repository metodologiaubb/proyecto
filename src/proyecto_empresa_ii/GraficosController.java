/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author balannor
 */
public class GraficosController implements Initializable {

    @FXML
    private AnchorPane ap_graficos;
    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       PieChart.Data sector1 = new PieChart.Data("Nicolás",  25);
       PieChart.Data sector2 = new PieChart.Data("Jerson",   25);
       PieChart.Data sector3 = new PieChart.Data("PASCAL",  1000);
       PieChart.Data sector4 = new PieChart.Data("Abdel",    4000);

       pieChart.getData().add(sector1);
       pieChart.getData().add(sector2);
       pieChart.getData().add(sector3);
       pieChart.getData().add(sector4);
       pieChart.setLegendSide(Side.LEFT);
    }    
    
}
