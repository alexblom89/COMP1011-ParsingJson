package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class WordContent {
    private String definition;
    private String partOfSpeech;
    private String[] synonyms;
    private String[] examples;

    public WordContent(String definition, String partOfSpeech, String[] synonyms, String[] examples) {
        setDefinition(definition);
        setPartOfSpeech(partOfSpeech);
        setSynonyms(synonyms);
        setExamples(examples);
    }

    /**
     * If definition is null, display error string.
     * @return
     */
    public String getDefinition() {
        return Objects.requireNonNullElse(definition, "Definition not found.");
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * If partOfSpeech is null, display error string.
     * @return
     */
    public String getPartOfSpeech() {
        return Objects.requireNonNullElse(partOfSpeech, "Not found.");
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    /**
     * Return an ArrayList of synonyms. If synonym array is null, throw exception.
     * @return
     */
    public ArrayList<String> getSynonyms() {
        ArrayList<String> synonymsList = new ArrayList<>();
        if (synonyms != null)
            synonymsList.addAll(Arrays.asList(synonyms));
        else
            synonymsList.add("No synonyms found.");
        return synonymsList;
    }

    public void setSynonyms(String[] synonyms) {
        this.synonyms = synonyms;
    }

    /**
     * If there is one example, return it. If there are more than one, return each string in array with a new line
     * character so that textarea can be used (textarea already has text-wrapping). If null, throw exception.
     * @return
     */
    public String getExamples() {
        if (examples == null)
           return "No examples found.";
        else if (examples.length > 1)
        {
            for (String s : examples)
            {
                return String.format("%s%n", s);
            }
        }
        return examples[0];
    }

    public void setExamples(String[] examples) {
        this.examples = examples;
    }
}