package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import javafx.event.ActionEvent;

import java.util.*;

public class PlaceHoldController extends Controller<Library>{
	@FXML private Button selector;	//	Button used to select the patron using ID.
	@FXML private Button hold;	//	The button used to place a hold on a book for a patron.
	@FXML private Text message;	//	The message saying the current state for placing a hold.
	@FXML private TextField ID;	//	The text field used to capture the patron's ID.
	@FXML private ListView<Book> listOfBooks;	//	The list of books used to place a hold for the patron.
	@FXML private final ObservableList<Book> availableBooks = FXCollections.observableArrayList();	//	The observable list of books that are available to this patron.

	/**
	 * Disables the hold button if no book is selected.
	 * Displays all the available books for this patron.
	 */

	@FXML private void initialize(){
		listOfBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldBook, newBook) -> hold.setDisable( newBook == null));
		ID.textProperty().addListener((observable, oldValue, newValue) -> { selector.setDisable(IsLetter(ID.getText())); });
		availableBooks.addAll(model.getCatalogue().getAllBooks());
	}

	/**
	 * Shows the available books for a patron.
	 */

	@FXML private void selectedPatron(){
		availableBooks.clear();
		availableBooks.addAll(model.getCatalogue().getBorrowableBooks(model.getPatron(Integer.parseInt(getID()))));
	}

	/**
	 *	Shows the available books for this patron.
	 *
	 * @return An observable list of books that are available to the patron in question.
	 */

	@FXML public ObservableList<Book> getAvailableBooks(){
		return availableBooks;
	}

	/**
	 * The ID representing the patron in question.
	 *
	 * @return The String ID for the patron.
	 */

	@FXML public String getID(){
		return ID.getText();
	}

	/**
	 *	Places a hold on a book for the patron in question.
	 *
	 * @param e ActionEvent
	 */

	@FXML public void holdBook(ActionEvent e){
		Book b = listOfBooks.getSelectionModel().getSelectedItem();
		Patron p = model.getPatron(Integer.parseInt(getID()));
		if (b.patronWithFirstHold() == p){
			message.setText(p.getName() + " has already placed a book on " + b.getTitle());
			return;
		}
		b.addHold(p);
		selectedPatron();
		message.setText("Hold placed on " + b.getTitle() + " for " + p.getName());
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
	 */

	@FXML private void closeWindow(){
		stage.close();
	}
}
