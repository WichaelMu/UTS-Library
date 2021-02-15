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

public class AdminController extends Controller<Library>{
	@FXML private Button close;

	@FXML
	private void showAddPatron(ActionEvent e)throws IOException {
		ViewLoader.showStage(getLibrary(), "/view/addPatron.fxml", "Add Patron", new Stage());
	}

	@FXML private void showRemovePatron(ActionEvent e)throws IOException{
		ViewLoader.showStage(getLibrary(), "/view/removePatron.fxml", "Remove Patron", new Stage());
	}

	@FXML private void showAddBook(ActionEvent e)throws IOException{
		ViewLoader.showStage(getLibrary(), "/view/addBook.fxml", "Add Book", new Stage());
	}

	@FXML private void showRemoveBook(ActionEvent e)throws IOException{
		ViewLoader.showStage(getLibrary(), "/view/removeBook.fxml", "Remove Book", new Stage());
	}

	private Library getLibrary(){
		return model;
	}

	@FXML private void closeWindow(ActionEvent e){  //  Closes the Administrator window.
		Stage s = (Stage)close.getScene().getWindow();
		s.close();
	}
}
