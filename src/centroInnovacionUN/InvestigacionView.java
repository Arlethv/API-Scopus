package centroInnovacionUN;

import java.util.List;
import java.util.Arrays;

public class InvestigacionView {
    private InvestigacionController controller;

    public InvestigacionView() {
        controller = new InvestigacionController();
    }

    public void mostrarResultados(List<Investigacion> resultados) {
        System.out.println("Resultados de la búsqueda:\n");

        for (Investigacion investigacion : resultados) {
            System.out.println("Título: " + investigacion.getPublicacion());
            System.out.println("Autores: " + investigacion.getNombre());
            System.out.println("Fecha: " + investigacion.getEmail());
            System.out.println("Fuente: " + investigacion.getFuente());
            System.out.println("Link: " + investigacion.getLink() + "\n");
        }
    }

    public static void main(String[] args) {
        InvestigacionView vista = new InvestigacionView();
        List<String> autores = Arrays.asList("3zcL9O8AAAAJ", "vySsVoQAAAAJ");
        List<Investigacion> resultados = vista.controller.buscarInvestigaciones(autores);
        vista.mostrarResultados(resultados);
    }
}
