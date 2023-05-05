package centroInnovacionUN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvestigacionDAO {
	
	// Método para agregar un autor a la base de datos
    public void agregarAutor(Investigacion investigacion) throws SQLException {
        String query = "INSERT INTO AUTOR (autorID, nombre, afiliacion, email) VALUES (?, ?, ?, ?)";
        Connection conexion = Investigacion.ConexionBD.getConnection();
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1,investigacion.getID());
            ps.setString(2, investigacion.getNombre());
            ps.setString(3, investigacion.getAfiliación());
            ps.setString(4, investigacion.getEmail());
            ps.executeUpdate();
            
        } catch (SQLException e) {
        	System.out.println("Error : " + e.getMessage());
            throw new SQLException("Error al agregar el autor a la base de datos", e);
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    


 // Método para agregar un tema a la base de datos
    public void agregarTema(Investigacion investigacion) throws SQLException {
        String query = "INSERT INTO TEMA (autorID, titulo, link) VALUES (?, ?, ?)";
        Connection conexion = Investigacion.ConexionBD.getConnection();
        try {
            PreparedStatement ps = conexion.prepareStatement(query);

            ArrayList<Investigacion.Tema> temas = investigacion.getTema();
            ps.setString(1,investigacion.getID());
            for (Investigacion.Tema tema : temas) {
                ps.setString(2, tema.getTitle());
                ps.setString(3, tema.getLink());
                ps.executeUpdate();
            }
            
            

        } catch (SQLException e) {
            throw new SQLException("Error al agregar el tema a la base de datos", e);
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    // Método para agregar un articulo a la base de datos
    public void agregarArticulo(Investigacion investigacion) throws SQLException {
    	String query = "INSERT INTO ARTICULO(autorID, titulo, link, cita_id, autores, publicacion, año) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conexion = Investigacion.ConexionBD.getConnection();
        try {
            PreparedStatement ps = conexion.prepareStatement(query);

            ArrayList<Investigacion.Articulo> articulos = investigacion.getArticulos();
            ps.setString(1,investigacion.getID());
            for (Investigacion.Articulo articulo : articulos) {
                ps.setString(2, articulo.getTitulo());
                ps.setString(3, articulo.getLink());
                ps.setString(4, articulo.getCita_id());
                ps.setString(5, articulo.getAutores());
                ps.setString(6, articulo.getPublicacion());
                ps.setInt(7, articulo.getAño());
               
                ps.executeUpdate();
            }
          
        } catch (SQLException e) {
        	System.out.println("Error : " + e.getMessage());
            throw new SQLException("Error al agregar el articulo a la base de datos", e);
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
    } 
}
