package centroInnovacionUN;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import com.google.gson.JsonParser;
import serpapi.GoogleSearch;
import centroInnovacionUN.Investigacion.Tema;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Clase que representa el controladoe de la aplicación de Investigación del Centro de Innovación UN.
 * Esta clase ermite realizar búsquedas de autores e investigaciones en línea a través de la API de SerpApi.
 * @author Yoselyn
 * @version 1.0
 * @since 2023-05-11
 */


public class InvestigacionController {
    
    private static final String BASE_URL = "https://serpapi.com/search.json";
    private static final String API_KEY = "b5b61605e75a1037ea2c2266f8d30058e66ba913e7c3e2046eee27437ecdfbbb";
   
    /**

    Este metodo realiza la busqueda autores en Google Scholar que correspondan con un término de búsqueda dado.
    @param terminoBusqueda el término que se va a buscar en Google Scholar
    @return una lista de ID de autores que corresponden con el término de búsqueda dado
    @throws IOException si ocurre un error al conectar con Google Scholar
    @since 2023-05-12
    @version 1.0
    */
    
    public List<String> buscarAutores(String terminoBusqueda) {
    	 List<Investigacion> resultados = new ArrayList<>();
        Map<String, String>parametros = new HashMap<>();
        parametros.put("engine", "google_scholar");
        parametros.put("q", terminoBusqueda);
        parametros.put("api_key", API_KEY);

      

        try {
            String url = construirUrl(BASE_URL, parametros);
            
            JsonObject resultado = obtenerJson(url);
            JsonArray items = resultado.getAsJsonArray("organic_results");

            List<String> autorIDs = new ArrayList<>();
            
            if(items!=null) {

            for (int i = 0; i < items.size(); i++) {
                JsonObject item = items.get(i).getAsJsonObject();
                JsonObject publicationInfo = item.getAsJsonObject("publication_info");
                JsonArray autores = publicationInfo.getAsJsonArray("authors");
             
                if(autores!=null) {
                	
                for (int j = 0; j < autores.size(); j++) {
                    JsonObject autor = autores.get(j).getAsJsonObject();
                    String autorID = autor.get("author_id").getAsString();
                    autorIDs.add(autorID);
                }
            }
            }
            }
            return autorIDs;
            
        } catch (Exception e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    
    
    
    /**
     * Busca las investigaciones relacionadas con los ID de los autores indicados.
     * @param autores la lista de autores a buscar.
     * @return una lista con las investigaciones relacionadas con los autores indicados.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    

    public List<Investigacion> buscarInvestigaciones(List<String> autores) {
        List<Investigacion> resultados = new ArrayList<>();
        InvestigacionDAO dao = new InvestigacionDAO();
        
        for (String autor : autores) {
            Map<String, String> parametros = new HashMap<>();
            parametros.put("engine", "google_scholar_author");
            parametros.put("author_id", autor);
            parametros.put("api_key", API_KEY);
            
            try {
                String url = construirUrl(BASE_URL, parametros);
               
                JsonObject resultado = obtenerJson(url);
                JsonObject author = resultado.getAsJsonObject("author");
                JsonArray articulosUN = resultado.getAsJsonArray("articles");
                JsonArray items = author.getAsJsonArray("interests");

                JsonObject id = resultado.getAsJsonObject("search_parameters");
               
           
                Investigacion investigacion = new Investigacion();
                investigacion.setNombre(author.get("name").getAsString());
                investigacion.setAfiliación(author.get("affiliations").getAsString());
                investigacion.setEmail(author.get("email").getAsString());
                investigacion.setID(id.get("author_id").getAsString());
                
                
             
                ArrayList<Investigacion.Tema> temas = new ArrayList<Investigacion.Tema>();
                
                if(items!=null) {
                for (int i = 0; i < items.size(); i++) {
                
                	JsonObject item = items.get(i).getAsJsonObject();
                 
                	String title = item.get("title").getAsString();
                    String link = item.get("link").getAsString();
                    Investigacion.Tema nuevoTema = investigacion.new Tema(title, link);
                    temas.add(nuevoTema);
                   
                    
                }
              
               
                ArrayList<Investigacion.Articulo> articulos = new ArrayList<Investigacion.Articulo>();

                for (int i = 0; i < articulosUN.size(); i++) {
                    JsonObject articulo = articulosUN.get(i).getAsJsonObject();
                    String tituloArticulo = articulo.has("title") ? articulo.get("title").getAsString() : "";
                    String linkArticulo = articulo.has("link") ? articulo.get("link").getAsString() : "";
                    String cita_id = articulo.has("citation_id") ? articulo.get("citation_id").getAsString() : "";
                    String autoresArticulo = articulo.has("authors") ? articulo.get("authors").getAsString() : "";
                    String publicacionArticulo = articulo.has("publication") ? articulo.get("publication").getAsString() : "";
                    int añoArticulo = articulo.has("year") ? articulo.get("year").getAsInt() : 0;

                    Investigacion.Articulo nuevoArticulo = investigacion.new Articulo(tituloArticulo, linkArticulo, cita_id, autoresArticulo, publicacionArticulo, añoArticulo);
                    articulos.add(nuevoArticulo);
                }

               
                investigacion.setTema(temas);
                investigacion.setArticulos(articulos);
               
                dao.agregarAutor(investigacion);
                dao.agregarTema(investigacion);
                dao.agregarArticulo(investigacion);
                
                resultados.add(investigacion);
                }  
               
            } catch (Exception e) {
            	  
            	System.out.println(e.getLocalizedMessage());
                System.out.println("Ocurrió un error al procesar la consulta: " + e.getMessage());
            }
        }
        
        return resultados;
      
    }
    
    
    
    /**
     * Construye la URL para la búsqueda de autores de investigaciones en base a los parámetros dados.
     * @param baseUrl URL base de la búsqueda.
     * @param parametros  parámetros de búsqueda.
     * @return URL completa para la búsqueda.
     * @throws UnsupportedEncodingException si la codificación de caracteres no es soportada.
     */
    
    private String construirUrl(String baseUrl, Map<String, String> parametros) throws UnsupportedEncodingException {
        StringJoiner sj = new StringJoiner("&");
        
        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            String clave = entry.getKey();
            String valor = entry.getValue();
            sj.add(clave + "=" + URLEncoder.encode(valor, "UTF-8"));
        }
        
        return baseUrl + "?" + sj.toString();
    }
    
    private JsonObject obtenerJson(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return com.google.gson.JsonParser.parseString(response.body()).getAsJsonObject();

    }
    
   


}


