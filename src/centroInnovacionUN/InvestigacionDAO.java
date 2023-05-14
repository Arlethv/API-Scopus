package centroInnovacionUN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvestigacionDAO {
	
	public void agregarAutor(Investigacion investigacion) throws SQLException {
	    String selectQuery = "SELECT * FROM AUTOR WHERE autorID = ?";
	    String insertQuery = "INSERT INTO AUTOR (autorID, nombre, afiliacion, email) VALUES (?, ?, ?, ?)";
	    String updateQuery = "UPDATE AUTOR SET nombre = ?, afiliacion = ?, email = ? WHERE autorID = ?";
	    Connection conexion = Investigacion.ConexionBD.getConnection();
	    try {
	        PreparedStatement selectPS = conexion.prepareStatement(selectQuery);
	        selectPS.setString(1, investigacion.getID());
	        ResultSet resultadoQuery = selectPS.executeQuery();
	        if (resultadoQuery.next()) {
	            // El autor ya existe, actualizar sus datos
	            PreparedStatement updatePS = conexion.prepareStatement(updateQuery);
	            updatePS.setString(1, investigacion.getNombre());
	            updatePS.setString(2, investigacion.getAfiliación());
	            updatePS.setString(3, investigacion.getEmail());
	            updatePS.setString(4, investigacion.getID());
	            updatePS.executeUpdate();
	        } else {
	            // El autor no existe, agregar uno nuevo
	            PreparedStatement insertPS = conexion.prepareStatement(insertQuery);
	            insertPS.setString(1, investigacion.getID());
	            insertPS.setString(2, investigacion.getNombre());
	            insertPS.setString(3, investigacion.getAfiliación());
	            insertPS.setString(4, investigacion.getEmail());
	            insertPS.executeUpdate();
	        }
	    } catch (SQLException e) {
	        System.out.println("Error : " + e.getMessage());
	        throw new SQLException("Error al agregar el autor a la base de datos", e);
	    } finally {
	        if (conexion != null) {
	            conexion.close();
	        }
	    }
	}

    
	public void agregarTema(Investigacion investigacion) throws SQLException {
	    String selectQuery  = "SELECT * FROM TEMA WHERE autorID = ? AND titulo = ?";
	    String insertQuery = "INSERT INTO TEMA (autorID, titulo, link) VALUES (?, ?, ?)";
	    String updateQuery = "UPDATE TEMA SET link = ? WHERE autorID = ? AND titulo = ?";
	    Connection conexion = Investigacion.ConexionBD.getConnection();
	    try {
	        PreparedStatement selectPS = conexion.prepareStatement(selectQuery);
	        PreparedStatement insertPS = conexion.prepareStatement(insertQuery);
	        PreparedStatement updatePS = conexion.prepareStatement(updateQuery);

	        ArrayList<Investigacion.Tema> temas = investigacion.getTema();
	        selectPS.setString(1, investigacion.getID());
	        for (Investigacion.Tema tema : temas) {
	        	selectPS.setString(2, tema.getTitle());
	            ResultSet resultadoQuery = selectPS.executeQuery();
	            if (resultadoQuery.next()) {
	          
	            	updatePS.setString(1, tema.getLink());
	            	updatePS.setString(2, investigacion.getID());
	            	updatePS.setString(3, tema.getTitle());
	            	updatePS.executeUpdate();
	            } else {
	                
	            	insertPS.setString(1, investigacion.getID());
	            	insertPS.setString(2, tema.getTitle());
	            	insertPS.setString(3, tema.getLink());
	            	insertPS.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        throw new SQLException("Error al agregar el tema a la base de datos", e);
	    } finally {
	        if (conexion != null) {
	            conexion.close();
	        }
	    }
	}


    public void agregarArticulo(Investigacion investigacion) throws SQLException {
    	String selectQuery = "SELECT * FROM ARTICULO WHERE autorID= ? AND titulo = ? ";
    	String updateQuery = "UPDATE ARTICULO SET titulo = ?, link = ?, cita_id = ?, autores = ?, publicacion = ?, año = ? WHERE titulo = ?";
        String insertQuery = "INSERT INTO ARTICULO (autorID, titulo, link, cita_id, autores, publicacion, año) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conexion = Investigacion.ConexionBD.getConnection();
        
        try {
        	PreparedStatement selectPS = conexion.prepareStatement(selectQuery);
	        PreparedStatement insertPS = conexion.prepareStatement(insertQuery);
	        PreparedStatement updatePS = conexion.prepareStatement(updateQuery);

            ArrayList<Investigacion.Articulo> articulos = investigacion.getArticulos();
            selectPS.setString(1,investigacion.getID());
            for (Investigacion.Articulo articulo : articulos) {
            	selectPS.setString(2, articulo.getTitulo());
            	ResultSet resultadoQuery = selectPS.executeQuery();
            	if (resultadoQuery.next()) {
            		
            		updatePS.setString(1, articulo.getTitulo());
            		updatePS.setString(2, articulo.getLink());
            		updatePS.setString(3, articulo.getCita_id());
            		updatePS.setString(4, articulo.getAutores());
            		updatePS.setString(5, articulo.getPublicacion());
            		updatePS.setInt(6, articulo.getAño());
            		updatePS.setString(7, articulo.getCita_id());
            		
            
            		
            		updatePS.executeUpdate();
            		
            	}  else {
            		insertPS.setString(1,investigacion.getID());
            		insertPS.setString(2, articulo.getTitulo());
            		insertPS.setString(3, articulo.getLink());
            		insertPS.setString(4, articulo.getCita_id());
            		insertPS.setString(5, articulo.getAutores());
            		insertPS.setString(6, articulo.getPublicacion());
            		insertPS.setInt(7, articulo.getAño());
            		
            		 
                    
            		insertPS.executeUpdate();
	            }

              
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
