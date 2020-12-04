package Controllers;

import Models.WordContent;
import Utilities.DictionaryAPIUtility;
import Utilities.JSONFileUtility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DictionaryViewController implements Initializable {

    @FXML
    private TextField searchTextField;

    @FXML
    private TextArea definitionTextArea;

    @FXML
    private TextArea examplesTextArea;

    @FXML
    private ListView<String> synonymsListView;

    @FXML
    private Button pageButton;

    @FXML
    private Label definitionTextAreaLabel;

    @FXML Label partOfSpeechLabel;

    private ArrayList<WordContent> contents = new ArrayList<>();

    private int pageCounter = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pageButton.setVisible(false);
    }

    @FXML
    private void getWordContent()
    {
        pageCounter = 1;
        definitionTextArea.clear();
        examplesTextArea.clear();
        synonymsListView.getItems().clear();
        pageButton.setDisable(false);

        try {
            String word = searchTextField.getText();
            word = word.replace(" ", "%20");

            DictionaryAPIUtility.getWordFromSearch(word);
            contents = JSONFileUtility.getWordContentList("./src/JSONFiles/wordFiles.json");

            updateScene(contents, pageCounter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void nextPage()
    {
        if(pageCounter < contents.size())
        {
            pageCounter++;
            updateScene(contents, pageCounter);
        }
    }

    private void updateScene(ArrayList<WordContent> contents, int pageNum)
    {
        if (pageCounter == contents.size())
            pageButton.setDisable(true);
        if (contents.size() > 1)
        {
            pageButton.setVisible(true);
            definitionTextAreaLabel.setText("Definition " + pageNum + " of " + contents.size());
        }
        definitionTextArea.clear();
        examplesTextArea.clear();
        synonymsListView.getItems().clear();
        definitionTextArea.setText(contents.get(pageNum-1).getDefinition());
        synonymsListView.getItems().addAll(contents.get(pageNum-1).getSynonyms());
        examplesTextArea.setText(contents.get(pageNum-1).getExamples());
        partOfSpeechLabel.setText("Part of Speech: " + contents.get(pageNum-1).getPartOfSpeech());
    }
}
