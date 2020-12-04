package Utilities;

import Models.SearchResult;
import Models.WordContent;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class DictionaryAPIUtility {

    public static void getWordFromSearch(String searchText) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String searchURL = "https://wordsapiv1.p.rapidapi.com/words/" + searchText;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(searchURL))
                .header("x-rapidapi-key", "3fe8312e84mshbade5e19de86673p182f9cjsn4659958f908e")
                .header("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<Path> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofFile(Paths.get("./src/JSONFiles/wordFiles.json")));
    }
}
