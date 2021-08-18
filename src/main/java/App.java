import util.JDBCUtilities;
import java.sql.Connection;
import java.sql.SQLException;
//import view.MenuLider;
import view.VistaRequerimientosReto4;
import controller.ControladorRequerimientosReto4;

public class App {
    public static void main( String[] args ) {  

        //Inicio de la aplicación con GUI
        ControladorRequerimientosReto4 controlador = new ControladorRequerimientosReto4();
        controlador.iniciarAplicacion();
    }
}
