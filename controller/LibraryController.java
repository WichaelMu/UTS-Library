package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import com.sun.corba.se.impl.legacy.connection.SocketFactoryContactInfoImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Library;

import javafx.event.ActionEvent;

import java.io.IOException;

public class LibraryController extends Controller<Library> {

    @FXML private Button exit;

    @FXML
    public void initialize() {

    }

    public final Library getLibrary() {
        return model;
    }

    @FXML private void showCatalogue() throws IOException {
        ViewLoader.showStage(getLibrary(), "/view/catalogue.fxml", "Catalogue", new Stage());
    }

    @FXML private void showPatronRecord()throws IOException{
        ViewLoader.showStage(getLibrary(), "/view/record.fxml", "Patron Record", new Stage());
    }

    @FXML private void showFavouriteBooks()throws IOException{
        ViewLoader.showStage(getLibrary(), "/view/favourites.fxml", "Favourites", new Stage());
    }

    @FXML private void showAdmin(ActionEvent e)throws IOException{
        ViewLoader.showStage(getLibrary(), "/view/admin.fxml", "Administration Menu", new Stage());
    }

    @FXML private void exit(ActionEvent e){ //  Exits the application.
        System.out.println("Exit by command.");
        System.exit(0);
    }
}
