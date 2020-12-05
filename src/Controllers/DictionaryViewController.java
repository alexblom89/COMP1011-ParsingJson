package Controllers;

import Models.WordContent;
import Utilities.DictionaryAPIUtility;
import Utilities.JSONFileUtility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private Button nextPageButton;

    @FXML
    private Button previousPageButton;

    @FXML
    private Button searchButton;

    @FXML
    private Label definitionTextAreaLabel;

    @FXML
    private Label partOfSpeechLabel;

    @FXML
    private Label errorLabel;

    private ArrayList<WordContent> contents = new ArrayList<>();

    private int pageCounter = 1;

    /**
     * Instantiate and set images for buttons. Set scroll buttons to invisible.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nextPageButton.setVisible(false);
        previousPageButton.setVisible(false);

        //Search button icon made by https://www.flaticon.com/authors/dimitry-miroliubov
        Image searchImage = new Image("/Icons/searchImage.png");
        //Scroll button icons made by Catalin Fertu from https://www.flaticon.com/authors/catalin-fertu
        Image next = new Image("/Icons/right-arrow-angle.png");
        Image prev = new Image("/Icons/left-arrow-line-symbol.png");

        ImageView searchView = new ImageView(searchImage);
        ImageView nextView = new ImageView(next);
        ImageView prevView = new ImageView(prev);

        searchView.setFitHeight(30);
        searchView.setPreserveRatio(true);

        nextView.setFitHeight(30);
        nextView.setPreserveRatio(true);

        prevView.setFitHeight(30);
        prevView.setPreserveRatio(true);

        nextPageButton.setGraphic(nextView);
        previousPageButton.setGraphic(prevView);
        searchButton.setGraphic(searchView);

        searchButton.setDefaultButton(true);
    }

    /**
     * Call API using search text, throw exception if search field is left blank. Call updateScene method with
     * WordContent ArrayList. Update error label with any errors.
     */
    @FXML
    private void getWordContent()
    {
        pageCounter = 1;
        definitionTextArea.clear();
        examplesTextArea.clear();
        synonymsListView.getItems().clear();
        errorLabel.setText("");
        nextPageButton.setDisable(false);

        try {
            String word = searchTextField.getText();
            if(word.isEmpty() || word.isBlank())
                throw new IllegalArgumentException("Search field cannot be blank!");
            word = word.replace(" ", "%20");

            DictionaryAPIUtility.getWordFromSearch(word);
            contents = JSONFileUtility.getWordContentList("./src/JSONFiles/wordFiles.json");

            updateScene(contents);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * Enable previous page button. Increment pageCounter variable and call updateScene method.
     */
    @FXML
    public void nextPage()
    {
        previousPageButton.setDisable(false);
        if(pageCounter < contents.size())
        {
            pageCounter++;
            updateScene(contents);
        }
    }

    /**
     * Enable next page button if it was disabled. Decrement pageCounter and call updateScene method.
     */
    @FXML
    public void previousPage()
    {
        nextPageButton.setDisable(false);
        if(pageCounter > 1)
        {
            pageCounter--;
            updateScene(contents);
        }
    }

    /**
     * Update scene with the WordContent object in the arrayList position corresponding to the pageCounter variable.
     * Disable next button if final object reached. Disable previous button if on first object.
     * @param contents
     */
    private void updateScene(ArrayList<WordContent> contents)
    {
        if (pageCounter == contents.size())
            nextPageButton.setDisable(true);
        if (pageCounter == 1)
            previousPageButton.setDisable(true);

        if (contents.size() > 1)
        {
            previousPageButton.setVisible(true);
            nextPageButton.setVisible(true);
            definitionTextAreaLabel.setText("Definition " + pageCounter + " of " + contents.size());
        }
        definitionTextArea.clear();
        examplesTextArea.clear();
        synonymsListView.getItems().clear();
        definitionTextArea.setText(contents.get(pageCounter-1).getDefinition());
        synonymsListView.getItems().addAll(contents.get(pageCounter-1).getSynonyms());
        examplesTextArea.setText(contents.get(pageCounter-1).getExamples());
        partOfSpeechLabel.setText("Part of Speech: " + contents.get(pageCounter-1).getPartOfSpeech());
    }
}
