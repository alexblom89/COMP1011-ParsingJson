package Models;

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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String[] synonyms) {
        this.synonyms = synonyms;
    }

    public String[] getExamples() {
        return examples;
    }

    public void setExamples(String[] examples) {
        this.examples = examples;
    }
}