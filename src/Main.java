import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./Views/dictionaryView.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@1,500&display=swap");
        //<div>Icon made by https://www.flaticon.com/authors/good-ware
        stage.getIcons().add(new Image(getClass().getResource("Icons/book.png").toString()));
        stage.setTitle("English Dictionary");
        stage.setScene(scene);
        stage.show();
    }
}
