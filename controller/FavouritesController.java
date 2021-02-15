package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import au.edu.uts.ap.javafx.Controller;
import model.*;

public class FavouritesController extends Controller<Library>{
	@FXML private Button close;	//	The button used to close the current window.
	@FXML private TextField ID;	//	The ID for the patron in question.
	@FXML private Text message;	//	The message saying the current state of the patron selection.
	@FXML private Button showFavourites;	//	The button to show the favourite books of the patron in question.
	@FXML private final ObservableList<Book> favourite = FXCollections.observableArrayList();	//	The observable list that shows the patron's favourite books.

	/**
	 * Disables the button to show favourites if no ID is provided.
	 */

	@FXML public void initialize(){
		ID.textProperty().addListener((observable, oldValue, newValue) -> { showFavourites.setDisable(IsLetter(ID.getText())); });
	}

	/**
	 * Shows the favourite books for the patron in question.
	 */

	@FXML private void ShowFavourites(){
		favourite.clear();
		Patron p = model.getPatron(Integer.parseInt(getID()));
		favourite.addAll(p.favourites());
		message.setText(p.getName() + "'s favourite books:");
	}

	/**
	 *	The favourite books for the patron in question.
	 *
	 * @return An observable list of the patron's favourite books.
	 */

	@FXML public ObservableList<Book> getFavourite(){
		return favourite;
	}

	/**
	 * The ID for the patron in question.
	 * @return A String value for the ID representing the patron in question.
	 */

	@FXML private String getID(){
		return ID.getText();
	}

	/**
	 * Disables the button to show the favourites.
	 * @param b boolean to disable the button.
	 */

	private void disableButton(boolean b){
		showFavourites.setDisable(b);
	}

	private boolean IsLetter(String test){
		if (test.length() == 0)
			return true;
		String Letters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		for (int i=0;i<test.length();i++)
			for (int k=0;k<Letters.length();k++)
				if (test.charAt(i) == Letters.charAt(k))
					return true;
		return false;
	}

	/**
	 * Closes the current window.
	 *
	 * @param e ActionEvent
	 */

	@FXML private void closeWindow(ActionEvent e){
		Stage s = (Stage)close.getScene().getWindow();
		s.close();
	}
}
