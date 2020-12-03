package Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResult {

    private String word;

    @SerializedName("results")
    private WordContent[] contents;

    public SearchResult(String word, WordContent[] contents) {
        setWord(word);
        setContents(contents);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WordContent[] getContents() {
        return contents;
    }

    public void setContents(WordContent[] contents) {
        this.contents = contents;
    }
}
