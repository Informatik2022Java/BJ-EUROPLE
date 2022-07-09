import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    public static String get(String query) throws IOException {
        query = query.replace(" ", "%20");
        //System.out.println();
        //System.out.println("query: " + query);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println(response.body());
        return response.body();
    }

    public static String wikiSearch(String query){
        try{
            String subject = query.toLowerCase().replace(" ", "%20");
            URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&format=json&exintro=&explaintext=&exsectionformat=plain&titles=" + subject.replace(" ", "%20"));
            String text = "";
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
                String line = null;
                while (null != (line = br.readLine())) {
                    line = line.trim();
                    if (true) {
                        text += line;
                    }
                }
            }
            catch (IOException ex){
                ex.printStackTrace();
            }

            //System.out.println("text = " + text);
            String result = text.substring(text.indexOf("extract")+10, text.length() - 5);
            //System.out.println(result);
            return result;
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        return "error";
    }
}