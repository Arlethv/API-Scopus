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

import centroInnovacionUN.Investigacion.Tema;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class InvestigacionController {
    
    private static final String BASE_URL = "https://serpapi.com/search.json";
    private static final String API_KEY = "88e11cd7749ee225f7e9f48f5b4a2c5435def63ee89fece3a6ede9d1b071a623";
    
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
                JsonArray items = author.getAsJsonArray("interests");
                JsonObject id = resultado.getAsJsonObject("search_parameters");

           
                Investigacion investigacion = new Investigacion();
                investigacion.setNombre(author.get("name").getAsString());
                investigacion.setAfiliación(author.get("affiliations").getAsString());
                investigacion.setEmail(author.get("email").getAsString());
                investigacion.setID(id.get("author_id").getAsString());
                
                
               
             
                ArrayList<Investigacion.Tema> temas = new ArrayList<Investigacion.Tema>();

           
                for (int i = 0; i < items.size(); i++) {
                
                	JsonObject item = items.get(i).getAsJsonObject();
                 
                	String title = item.get("title").getAsString();
                    String link = item.get("link").getAsString();
                    Investigacion.Tema nuevoTema = investigacion.new Tema(title, link);
                    temas.add(nuevoTema);
                   
                    
                }
               
                investigacion.setTema(temas);
                dao.agregarAutor(investigacion);
                resultados.add(investigacion);
            } catch (Exception e) {
            	  
                System.out.println("Ocurrió un error al procesar la consulta: " + e.getMessage());
            }
        }
        
        return resultados;
    }
    
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
