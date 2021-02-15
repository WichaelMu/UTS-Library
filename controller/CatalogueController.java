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

public class CatalogueController extends Controller<Library> {
	@FXML private Button close;

	private Library getLibrary(){
		return model;
	}

	@FXML private void showAllBooks()throws IOException{
		ViewLoader.showStage(getLibrary(), "/view/showAllBooks.fxml", "Complete Catalogue", new Stage());
	}

	@FXML private void showAvailableBooks()throws IOException{
		ViewLoader.showStage(model, "/view/showAvailableBooks.fxml", "Available Books", new Stage());
	}

	@FXML private void showBooksByGenre()throws IOException{
		ViewLoader.showStage(model, "/view/showBooksByGenre.fxml", "Browse by Genre", new Stage());
	}

	@FXML private void showBooksByAuthors()throws IOException{
		ViewLoader.showStage(model, "/view/showBooksByAuthor.fxml", "Browse by Author", new Stage());
	}

	@FXML private void showBorrowBooks()throws IOException{
		ViewLoader.showStage(model, "/view/borrow.fxml", "Borrow a Book", new Stage());
	}

	@FXML private void showReturnBooks()throws IOException{
		ViewLoader.showStage(model, "/view/return.fxml", "Return a Book", new Stage());
	}

	@FXML private void showPlaceHold() throws IOException {
		ViewLoader.showStage(model, "/view/placeHold.fxml", "Place a Hold", new Stage());
	}

	private void cantShow(String s){
		System.out.println("Can't show " + s);
	}

	@FXML private void closeWindow(ActionEvent e){
		Stage s = (Stage)close.getScene().getWindow();
		s.close();
	}
}
