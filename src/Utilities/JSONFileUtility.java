package Utilities;

import Models.SearchResult;
import Models.WordContent;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class JSONFileUtility {

    public static ArrayList<WordContent> getWordContentList(String jsonFileName)
    {
        ArrayList<WordContent> contents = new ArrayList<>();
        Gson gson = new Gson();

        try (
                FileReader fileReader = new FileReader(jsonFileName);
                JsonReader jsonReader = new JsonReader(fileReader);
        ) {
            SearchResult searchResult = gson.fromJson(jsonReader, SearchResult.class);
            if (searchResult.getWord() != null)
                contents.addAll(Arrays.asList(searchResult.getContents()));
            else
                throw new IllegalArgumentException("Cannot find word!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contents;
    }

}
