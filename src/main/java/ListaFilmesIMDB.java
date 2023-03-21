import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONObject;

public class ListaFilmesIMDB {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        // Uma pequena ajuda do CHATGPT
        Dotenv dotenv = Dotenv.load();
        String api_key = dotenv.get("APIKEY");
        String url = "https://imdb-api.com/en/API/Top250TVs/" + api_key;

        URL imdbApiUrl = new URL(url);

        HttpURLConnection con = (HttpURLConnection) imdbApiUrl.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", "My Java Application");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Convertendo de volta para JSON pois não consegui iterar sobre StringBuilder
        JSONObject json = new JSONObject(response.toString());
        JSONArray results = json.getJSONArray("items");

        for (int i = 0; i < 250; i++) {
            JSONObject result = results.getJSONObject(i);
            String title = result.getString("title");
            String image = result.getString("image");

            System.out.println("TITLE: " + title);
            System.out.println("IMAGEM: " + image);

        }
    }
}

