package Utilities;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DictionaryAPIUtility {

    public static void getWordFromSearch(String searchText) throws IOException, InterruptedException {
        String searchURL = "https://wordsapiv1.p.rapidapi.com/words/" + searchText;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(searchURL))
                .header("x-rapidapi-key", "3fe8312e84mshbade5e19de86673p182f9cjsn4659958f908e")
                .header("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());

    }
}
