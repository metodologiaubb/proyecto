package proyecto_empresa_ii.modelo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
public class Ftp {
    private FTPClient  ftp;

    public boolean conectar(){		
        boolean conect=true;
        ftp = new FTPClient();
        try {
            ftp.connect("ftp.dealers-master.com",21);
            ftp.login("MS@dealers-master.com", "123456rt");
        } catch (IOException e) {
                System.out.println("IOException ="+e.getMessage());
                conect=false;
        }	    
        return conect;
    }

    public boolean cd(String directorio){
        try {
            ftp.changeWorkingDirectory(directorio);
            return true;
        } catch (IOException e) {
            System.out.println("IOException ="+e.getMessage());
        }
        return false;
    }

    public boolean crearFichero(String rutaFicheroFtp,String rutaFichero){
        try {
            ftp.storeFile(rutaFicheroFtp,new FileInputStream(new File(rutaFichero)));
            return true;
        } catch (IOException e) {
            System.out.println("IOException ="+e.getMessage());
        }
        return false;
    }

    public boolean eliminarFichero(String rutaFichero){
        try {
            ftp.deleteFile(rutaFichero);
            return true;
        } catch (IOException e) {
            System.out.println("IOException ="+e.getMessage());
        }
        return false;
    }

    public boolean desconectar(){
        try {
            ftp.disconnect();
            ftp = null;
            return true;
        } catch (IOException e) {
            System.out.println("IOException ="+e.getMessage());
            return false;
        }
    }

    public String directorioActual(){
        try {
            return ftp.printWorkingDirectory();
        } catch (IOException e) {
            System.out.println("IOException ="+e.getMessage());
            return null;
        }
    }
    //-----------------------------------------------------------------

    public boolean getFichero(String rutaFichero, String rutaLocal){
        try {
            InputStream in = ftp.retrieveFileStream(rutaFichero);
            byte[] b = new byte[in.available()];
            in.read(b);
            FileOutputStream file = new FileOutputStream(new File(rutaLocal));
            file.write(b);
            file.close();
            in.close();
            return true;
        } catch (IOException e) {
            
            //listar archivos no es necesario, por que si da la excepcion, el archivo no está
            System.out.println("IOException ="+e.getMessage());
            return false;
        }

    }
    //-----------------------------------------------------------------
    public boolean listarArchivos(){
        String[] lista;
        int i,respuesta;
        respuesta = ftp.getReplyCode();
        try {
            if(FTPReply.isPositiveCompletion(respuesta) == true ) {
                System.out.println("LISTANDO ARCHIVOS");
                lista = ftp.listNames();
                for(i=0; i<lista.length; i++) {
                    System.out.println(lista[i]); 
                    if("update.zip".equals(lista[i])){
                        System.out.println("Si está");
                        return true;
                    }
                }
            // Si no, avisamos
            }else { 
                System.out.println("ERROR DE CONEXION"); 
            }    
        } catch (IOException ex) {
            Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("No está");
        return false;
    }
}
