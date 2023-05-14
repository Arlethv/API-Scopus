package centroInnovacionUN;

import java.util.List;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Clase que representa la vista de la aplicación de Investigación del Centro de Innovación UN.
 * Esta clase se encarga de interactuar con el usuario y llamar al controlador para realizar las operaciones necesarias.
 * @author Yoselyn
 * @version 1.0
 * @since 2023-05-10
 */

public class InvestigacionView {
    private InvestigacionController controller;

    /**
     * Constructor de la clase InvestigacionView.
     * Se encarga de inicializar el controlador de la vista.
     */
    public InvestigacionView() {
        controller = new InvestigacionController();
    }


    /**
     * Método principal de la clase InvestigacionView.
     * Se encarga de realizar la búsqueda de autores y de investigaciones 
     * a partir del término de búsqueda dado.
     */

    public static void main(String[] args) {
        InvestigacionView vista = new InvestigacionView();
        List<String> autores = vista.controller.buscarAutores("Avances Académicos Universidad del Norte México uninorte");
        List<Investigacion> resultados = vista.controller.buscarInvestigaciones(autores);
       
        
    }
}
