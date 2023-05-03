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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class InvestigacionController {
    
    private static final String BASE_URL = "https://serpapi.com/search.json";
    private static final String API_KEY = "c04ce9dd4864fc08aaf346a36b3853d2b2a1d9ceddf3570457147408a4d8523f";
    
    public List<Investigacion> buscarInvestigaciones(List<String> autores) {
        List<Investigacion> resultados = new ArrayList<>();
        
        for (String autor : autores) {
            Map<String, String> parametros = new HashMap<>();
            parametros.put("engine", "google_scholar_author");
            parametros.put("author_id", autor);
            parametros.put("api_key", API_KEY);
            
            try {
                String url = construirUrl(BASE_URL, parametros);
                //System.out.println("URL de búsqueda: " + url);
                JsonObject resultado = obtenerJson(url);
                JsonObject author = resultado.getAsJsonObject("author");
                JsonArray items = author.getAsJsonArray("interests");

                System.out.println("Esto es author: "+author);
                
                Investigacion investigacion = new Investigacion();
                investigacion.setNombre(author.get("name").getAsString());
                investigacion.setAfiliación(author.get("affiliations").getAsString());
                investigacion.setEmail(author.get("email").getAsString());
                System.out.println("Estos son los items: "+items);
           
                for (int i = 0; i < items.size(); i++) {
                
                	JsonObject item = items.get(i).getAsJsonObject();
                    
                   investigacion.setPublicacion(item.get("title").getAsString());
                   investigacion.setLink(item.get("link").getAsString());
                 
               
                    resultados.add(investigacion);
                }
                
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
