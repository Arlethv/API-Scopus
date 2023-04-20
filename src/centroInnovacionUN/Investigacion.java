package centroInnovacionUN;

import java.util.List;
import java.util.ArrayList;
public class Investigacion {
    private String titulo;
    private String resultadoID;
    private List<Investigacion.Autor> autores;
    private String publicacion;
    private String fecha;
    private String resumen;
    
    public Investigacion() {
        this.autores = new ArrayList<>();
    }

    public static class Autor {
        private String nombre;
        private String enlace;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEnlace() {
            return enlace;
        }

        public void setEnlace(String enlace) {
            this.enlace = enlace;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getResultadoID() {
        return resultadoID;
    }

    public void setResultadoID(String resultadoID) {
        this.resultadoID = resultadoID;
    }
}
