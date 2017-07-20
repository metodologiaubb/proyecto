/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    HashMap<Integer,Integer> listaCot;//lista de cotizaciones locales 
    HashMap<Integer,Integer> listaCotBD;// lista de cotizaciones de la base de datos 
    Cotizacion cotizacion;
    int i;
    private FXMLDocumentController cot;
    public thread(FXMLDocumentController cotiza) {
        this.data = cotiza.getListCotiza();
        this.cot=cotiza;
        espera=7000;
        this.listaCot=cotiza.getMapCot();
        this.listaCotBD=new HashMap<Integer,Integer>();
    }

    @Override
    public void run() {
        try {
                while(true){
                    i=0;
                    thread.sleep(espera);
                    System.out.println("entro al hilo");
                    ResultSet res=Cotizacion.Cotizaciones();
                    while(res.next()){
                        cotizacion=Cotizacion.llenaOne(res);
                        if(!listaCot.containsKey(cotizacion.getId_cot())){
                            System.out.println("encontro una nueva cotizacion");
                            data.add(cotizacion);
                            listaCot.put(cotizacion.getId_cot(), data.indexOf(cotizacion));
                        }
                        listaCotBD.put(cotizacion.getId_cot(), i);
                        i++;
                        
                    }
                    //
                    if(listaCot.size()>listaCotBD.size()){
                        for (HashMap.Entry<Integer, Integer> entry : listaCot.entrySet()) {
                            if(!listaCotBD.containsKey(entry.getKey())){
                                System.out.println("se elimino una cotizacion");
                                data.remove(data.get(entry.getValue()));
                                listaCot.remove(entry.getKey());
                                break;
                            }
                        }
                       
                    }
                    
                    //
                    listaCotBD.clear();
                    
        }
            } catch (InterruptedException ex) {
                Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
            } 
        catch (SQLException ex) {
            Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
