/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii.modelo;

import java.util.HashMap;
import java.util.Map;
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
            Conexion con=new Conexion();
            con.establecerConexion();
            JasperReport cotizacion = (JasperReport) JRLoader.loadObject("src/proyecto_empresa_ii/modelo/Cotizacion.jasper");
            Map cotiza=new HashMap();
            cotiza.put("cotizacion", Cotizacion);
            JasperPrint p= JasperFillManager.fillReport(cotizacion, cotiza,con.getConnection());
            JasperViewer jv=new JasperViewer(p);
            jv.setTitle("Informe Cotización");
            jv.setVisible(true);
            jv.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                
            }
        });
            con.cerrarConexion();
        } catch (Exception e) {
            System.err.print(e);
        }
  
    }
   
}
