package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryViewController implements Initializable {

    @FXML
    private TextField searchTextField;

    @FXML
    private TextField partOfSpeechTextField;

    @FXML
    private TextArea definitionTextArea;

    @FXML
    private ListView<?> synonymsListView;

    @FXML
    private TextArea examplesTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
