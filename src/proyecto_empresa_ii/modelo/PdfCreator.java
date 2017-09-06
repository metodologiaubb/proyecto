/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii.modelo;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jerson
 */
public class PdfCreator {
    
    public void reporteCotizacion(int Cotizacion){
        try {
            String logo="mseal.png";
            Conexion con=new Conexion();
            con.establecerConexion();
            URL in=this.getClass().getResource("/Informes/Cotizacion.jasper");
            JasperReport cotizacion = (JasperReport) JRLoader.loadObject(in);
            Map cotiza=new HashMap();
            cotiza.put("cotizacion", Cotizacion);
            cotiza.put("logo", this.getClass().getResourceAsStream(logo));
            JasperPrint p= JasperFillManager.fillReport(cotizacion, cotiza,con.getConnection());
            JasperViewer jv=new JasperViewer(p,false);
            jv.setTitle("Informe Cotización");
            jv.setVisible(true);
                
 
            con.cerrarConexion();
        } catch (Exception e) {
            System.err.print(e);
            JOptionPane.showMessageDialog(null, e, "error", JOptionPane.ERROR_MESSAGE);
        }
  
    }
   
}
