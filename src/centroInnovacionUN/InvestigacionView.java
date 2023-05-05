package centroInnovacionUN;

import java.util.List;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvestigacionView {
    private InvestigacionController controller;

    public InvestigacionView() {
    	
    	
    	
    	
    	
    	
    	
    	
        controller = new InvestigacionController();
    }

    public void mostrarResultados(List<Investigacion> resultados) {
    	/* System.out.println("Resultados de la búsqueda:\n");

        for (Investigacion investigacion : resultados) {
            System.out.println("ID Autor: " + investigacion.getID());
            System.out.println("Autor: " + investigacion.getNombre());
            System.out.println("Email: " + investigacion.getEmail());
            System.out.println("Afiliacion: " + investigacion.getAfiliación() + "\n");
            
            
            ArrayList<Investigacion.Tema> temas = investigacion.getTema();
            
     
          
            //for (Investigacion.Tema tema : temas) {
                //System.out.println("Título del tema: " + tema.getTitle());
                //System.out.println("Enlace del tema: " + tema.getLink()+ "\n");
            
           // }

        }
        */
 
    }


    public static void main(String[] args) {
        InvestigacionView vista = new InvestigacionView();
        List<String> autores = Arrays.asList("3zcL9O8AAAAJ", "vySsVoQAAAAJ","MvqhthMAAAAJ", "VBd6xSMAAAAJ", "vXkJXeYAAAAJ",
        		"SmpTWYwAAAAJ","GbDRjAQAAAAJ","SE1yAnAAAAAJ","k9QWwTcAAAAJ", "phtwX-wAAAAJ");
        List<Investigacion> resultados = vista.controller.buscarInvestigaciones(autores);
        vista.mostrarResultados(resultados);
        
    }
}
