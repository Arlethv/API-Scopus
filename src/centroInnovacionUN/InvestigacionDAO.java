package centroInnovacionUN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            System.out.println("se agrego:"+ query); 
            System.out.println("Se agregó el artículo: " + investigacion.getNombre() + " a la base de datos.");
        } catch (SQLException e) {
            throw new SQLException("Error al agregar el autor a la base de datos", e);
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
    }


}
