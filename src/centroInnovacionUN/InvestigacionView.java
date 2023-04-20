package centroInnovacionUN;

import java.util.List;


public class InvestigacionView {
	private InvestigacionController controller;
	
	public InvestigacionView() {
	    controller = new InvestigacionController();
	}

	public void mostrarInvestigaciones(String query) {
		 List<Investigacion> investigaciones = controller.buscarInvestigaciones(query);

		    if (investigaciones != null && !investigaciones.isEmpty()) {
		        System.out.println("Investigaciones encontradas:");
		        for (Investigacion investigacion : investigaciones) {
		            System.out.println("Resultado ID" + investigacion.getResultadoID());
		            System.out.println("Título: " + investigacion.getTitulo());
		            System.out.print("Autores: ");
		            for (Investigacion.Autor autor : investigacion.getAutores()) {
		                System.out.print(autor.getNombre() + " ");
		            }
		            System.out.println();
		            System.out.println("Publicación: " + investigacion.getPublicacion());
		            System.out.println("Fecha: " + investigacion.getFecha());
		            System.out.println("Resumen: " + investigacion.getResumen());
		            System.out.println();
		        }
		    } else {
		        System.out.println("No se encontraron investigaciones.");
		    }
	}

	public static void main(String[] args) {
	    InvestigacionView view = new InvestigacionView();

	    String consulta = "Avances Académicos Universidad del Norte México";

	    view.mostrarInvestigaciones(consulta);

	  
	}


}
