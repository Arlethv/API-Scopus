package centroInnovacionUN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import serpapi.GoogleSearch;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class InvestigacionController {
	 private Investigacion model;

	    public InvestigacionController() {
	        model = new Investigacion();
	    }
	    
	    public List<Investigacion> buscarInvestigaciones(String query) {
	        Map<String, String> parameter = new HashMap<>();
	        parameter.put("engine", "google_scholar");
	        parameter.put("q", query.replace(" ", "%20"));
	        parameter.put("api_key", "c04ce9dd4864fc08aaf346a36b3853d2b2a1d9ceddf3570457147408a4d8523f");
	        parameter.put("num", "10");
	        
	        String url = "https://serpapi.com/search.json?";
	        for (Map.Entry<String, String> entry : parameter.entrySet()) {
	            String key = entry.getKey();
	            String value = entry.getValue();
	            url += key + "=" + value + "&";
	        }
	        
	        HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .GET()
	                .build();
	        GoogleSearch search = new GoogleSearch(parameter);
	        
	        

	        try {
	        	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	            JsonObject results = JsonParser.parseString(response.body()).getAsJsonObject();
	            var organicResults = results.getAsJsonArray("organic_results");

	            List<Investigacion> investigaciones = new ArrayList<>();

	            for (int i = 0; i < organicResults.size(); i++) {
	                JsonObject result = organicResults.get(i).getAsJsonObject();

	                String titulo = result.get("title").getAsString();
	                
	                JsonElement element = result.get("result_id");
	                String resultadoID = null;
	                if (element != null && !element.isJsonNull()) {
	                    resultadoID = element.getAsString();
	                }

	                List<Investigacion.Autor> autores = new ArrayList<>();
	                JsonArray authorsArray = result.getAsJsonArray("authors");
	                if (authorsArray != null && authorsArray.size() > 0) {
	                    for (int j = 0; j < authorsArray.size(); j++) {
	                        JsonObject authorObject = authorsArray.get(j).getAsJsonObject();
	                        String name = authorObject.get("name").getAsString();
	                        String link = authorObject.get("link").getAsString();
	                        Investigacion.Autor autor = new Investigacion.Autor();
	                        autor.setNombre(name);
	                        autor.setEnlace(link);
	                        autores.add(autor);
	                    }
	                }

	                JsonElement publicationElement = result.get("publication");
	                String publicacion = null;
	                if (publicationElement != null && !publicationElement.isJsonNull()) {
	                    publicacion = publicationElement.getAsString();
	                }

	                JsonElement fechaElement = result.get("publication_date");
	                String fecha = null;
	                if (fechaElement != null && !fechaElement.isJsonNull()) {
	                    fecha = fechaElement.getAsString();
	                }

	                String resumen = result.get("snippet").getAsString();

	                Investigacion investigacion = new Investigacion();
	                investigacion.setTitulo(titulo);
	                investigacion.getAutores().addAll(autores);
	                investigacion.setPublicacion(publicacion);
	                investigacion.setFecha(fecha);
	                investigacion.setResumen(resumen);
	                investigacion.setResultadoID(resultadoID);

	                investigaciones.add(investigacion);
	            }

	            return investigaciones;

	        } catch (Exception e) {
	            System.err.println("Ocurrió un error: " + e.getMessage());
	            e.printStackTrace();
	            return null;
	        }
	        
	        
	    }
	    
}