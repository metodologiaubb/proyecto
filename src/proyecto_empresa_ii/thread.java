/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import proyecto_empresa_ii.modelo.Cotizacion;


/**
 *
 * @author jerson
 */
public class thread extends Thread{
    private final long espera; 
    private ObservableList<Cotizacion> data;
    HashMap<Integer,Integer> listaCot;
    private FXMLDocumentController cot;
    public thread(FXMLDocumentController cotiza) {
        this.data = cotiza.getListCotiza();
        this.cot=cotiza;
        espera=20000;
        this.listaCot=cotiza.getMapCot();
    }

    @Override
    public void run() {
        try {
                while(true){
                    thread.sleep(espera);
                    System.out.println("entro al hilo");
                    ResultSet res=Cotizacion.Cotizaciones();
                    while(res.next()){
                        if(!listaCot.containsKey(Cotizacion.llenaOne(res).getId_cot())){
                            System.out.println("encontro una nueva cotizacion");
                            data.add(Cotizacion.llenaOne(res));
                            listaCot.put(Cotizacion.llenaOne(res).getId_cot(), data.indexOf(Cotizacion.llenaOne(res)));
                        }
                        
                    }
        }
            } catch (InterruptedException ex) {
                Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
            } 
        catch (SQLException ex) {
            Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
