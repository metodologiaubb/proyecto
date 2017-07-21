/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naivgationdrawer;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import proyecto_empresa_ii.FXMLDocumentController;
import static proyecto_empresa_ii.FXMLDocumentController.rootP;

/**
 * FXML Controller class
 *
 * @author pascaliwi
 */
public class SidePanelContentController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void changeColor(ActionEvent event) {
           JFXButton btn = (JFXButton) event.getSource();
           SingleSelectionModel<Tab> selectionModel = rootP.getSelectionModel();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
            case "Cotizaciones y productos":selectionModel.select(0);
                break;
            case "  Marcas y proveedores":selectionModel.select(1);
                break;
            case "Datos Personales":selectionModel.select(2);
                break;
        }
    }
    }

