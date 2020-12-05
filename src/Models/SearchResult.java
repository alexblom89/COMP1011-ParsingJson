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

    /**
     * If the word is null, the search field was left empty - throw exception. Otherwise, set word.
     * @param word
     */
    public void setWord(String word) {
        if (word.isBlank() || word.isEmpty())
            throw new IllegalArgumentException("Search field cannot be empty!");
        else
            this.word = word;
    }

    public WordContent[] getContents() {
        return contents;
    }

    public void setContents(WordContent[] contents) {
        this.contents = contents;
    }
}
