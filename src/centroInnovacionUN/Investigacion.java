package centroInnovacionUN;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Investigacion {
	private String ID;
    private String nombre;
    private String afiliación;
    private String email;
    private String publicacion;
    private String fecha;
    private String resumen;
    private String link;
    private ArrayList<Tema> tema;
    private ArrayList<Articulo> articulos;
    
    public Investigacion() {
        tema = new ArrayList<Tema>();
        articulos=new ArrayList<Articulo>();
    }
    
    public class ConexionBD {
    	private static final String driver = "com.mysql.cj.jdbc.Driver";

    	private static final String url = "jdbc:mysql://localhost:3306/centro_innovacion_un";
    	private static final String usuario = "root";
    	private static final String password="MySQL123";

        public static Connection getConnection() throws SQLException {
            Connection conexion = null;
            try {
                Class.forName(driver);
                conexion = DriverManager.getConnection(url,usuario,password);
               
                
            } catch (ClassNotFoundException e) {
                throw new SQLException("No se pudo cargar el driver JDBC", e);
            } catch (SQLException e) {
                throw new SQLException("No se pudo establecer la conexión a la base de datos", e);
            }
            return conexion;
        }
        
    }
    

    public class Tema{
        private String title;
        private String link;


        // Constructor
        public Tema(String title, String link) {
            this.title = title;
            this.link = link;
        }

        // Getters y setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

      

        
    }
    

    public class Articulo{
        private String titulo;
        private String link;
        private String cita_id;
        private String autores;
        private String publicacion;
        private int  año;


        // Constructor
        public Articulo(String titulo, String link, String cita_id, String autores, String publicacion, int año) {
            this.titulo=titulo;
            this.link = link;
            this.cita_id= cita_id;
            this.autores=autores;
            this.publicacion= publicacion;
            this.año= año;
        }

 
		public String getTitulo() {
			return titulo;
		}


		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}


		public String getLink() {
			return link;
		}


		public void setLink(String link) {
			this.link = link;
		}


		public String getCita_id() {
			return cita_id;
		}


		public void setCita_id(String cita_id) {
			this.cita_id = cita_id;
		}


		public String getAutores() {
			return autores;
		}


		public void setAutores(String autores) {
			this.autores = autores;
		}


		public String getPublicacion() {
			return publicacion;
		}


		public void setPublicacion(String publicacion) {
			this.publicacion = publicacion;
		}


		public int getAño() {
			return año;
		}


		public void setAño(int año) {
			this.año = año;
		}

      
    }


    //Getters y Setters de Autor

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
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public ArrayList<Tema> getTema() {
		return tema;
	}
	public void setTema(ArrayList<Tema> tema) {
		this.tema = tema;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}
	public void setArticulos(ArrayList<Articulo> articulos) {
		this.articulos = articulos;
	}




	
}
