package centroInnovacionUN;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Investigacion {
    private String nombre;
    private String afiliación;
    private String email;
    private String publicacion;
    private String fecha;
    private String resumen;
    private String fuente;
    private String link;
    
    public class ConexionBD {
    	private static final String driver = "com.mysql.jdbc.Driver";
    	private static final String url = "jdbc:mysql://localhost:3306/centro_innovacion_un";
    	private static final String usuario = "root";

        public static Connection getConnection() throws SQLException {
            Connection conexion = null;
            try {
                Class.forName(driver);
                conexion = DriverManager.getConnection(url,usuario,"");
            } catch (ClassNotFoundException e) {
                throw new SQLException("No se pudo cargar el driver JDBC", e);
            } catch (SQLException e) {
                throw new SQLException("No se pudo establecer la conexión a la base de datos", e);
            }
            return conexion;
        }
        
    }

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAfiliación() {
		return afiliación;
	}
	public void setAfiliación(String afiliación) {
		this.afiliación = afiliación;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}




	
}
