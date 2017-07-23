/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import proyecto_empresa_ii.modelo.Ftp;

/**
 * FXML Controller class
 *
 * @author balannor
 */
public class SoporteController implements Initializable {

    String diractual; 
    @FXML
    private Label labelestadoupdate;
    @FXML
    private JFXButton btndescargar;
    @FXML
    void accionDescargar(ActionEvent event) {
        File miDir = new File ("."); 
        Ftp fttp =new Ftp();
        try {
            diractual = miDir.getCanonicalPath()+"/";
        } catch (IOException ex) {
            Logger.getLogger(SoporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println ("Directorio actual local: " +diractual);
        
        if(fttp.conectar()){
            System.out.println ("entré");
            fttp.cd("/updates");
            System.out.println ("Directorio actual local: " +diractual);
            System.out.println("Nueva ruta actual= "+fttp.directorioActual());
            System.out.println("Descargando = "+fttp.getFichero("update.zip", diractual+"update.zip"));
            fttp.desconectar();	

            }
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Ftp ftp = new Ftp();
        System.out.println("Intentando conectar!...");
            if(ftp.conectar()){             
                ftp.cd("/updates");
                System.out.println("ruta actual ftp= "+ftp.directorioActual());
                if(ftp.listarArchivos()){
                    labelestadoupdate.setText("Disponible");
                    labelestadoupdate.setStyle(""
                                            + "-fx-font-size: 20px;"
                                            + "-fx-font-weight: bold;"
                                            + "-fx-text-fill: #019875;"
                                            );
                }
                else{
                    labelestadoupdate.setText("No disponible");
                    labelestadoupdate.setStyle(""
                                            + "-fx-font-size: 20px;"
                                            + "-fx-font-weight: bold;"
                                            + "-fx-text-fill: #C6032F;"
                                            );
                    
                }
                ftp.desconectar();	
                ftp=null;
                
            }       
    }    
    
}
