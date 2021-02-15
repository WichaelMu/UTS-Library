
import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.application.Application;
import model.Library;

public class LibraryApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ViewLoader.showStage(new Library(), "/view/library.fxml", "Main menu", stage);
    }
}
