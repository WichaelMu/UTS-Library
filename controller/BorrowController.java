package controller;

import au.edu.uts.ap.javafx.Controller;
import com.sun.corba.se.impl.legacy.connection.SocketFactoryContactInfoImpl;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import javafx.event.ActionEvent;

import java.util.*;

public class BorrowController extends Controller<Library>{

	@FXML private Button selector;	//	THe button used to select a patron using ID.
	@FXML private Button borrow;	//	The button used to borrow.
	@FXML private TextField ID;		//	The ID input for the patron to borrow.
	@FXML private ListView<Book> listOfBooks;	//	The list view used to select a desired book.
	@FXML private final ObservableList<Book> availableBooks = FXCollections.observableArrayList();	//	The observable list of books that are available for the patron in question.

	/**
	 * Disables the borrow button if there is no selected book.
	 */

	@FXML private void initialize(){
		listOfBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldBook, newBook) -> borrow.setDisable( newBook == null));
		ID.textProperty().addListener((observable, oldValue, newValue) -> { selector.setDisable(IsLetter(ID.getText())); });
	}

	/**
	 * Displays the available books for this patron using their ID.
	 */

	@FXML private void selectedPatron(){
		availableBooks.clear();
		availableBooks.addAll(model.getCatalogue().getBorrowableBooks(model.getPatron(Integer.parseInt(getID()))));
	}

	/**
	 *	Returns the list of available books.
	 *
	 * 	@return Observable List of available books in the catalogue.
	 */

	@FXML public ObservableList<Book> getAvailableBooks(){
		return availableBooks;
	}

	/**
	 * Gets the ID for the patron in question.
	 *
	 * @return The String ID for the patron in question.
	 */

	@FXML public String getID(){
		return ID.getText();
	}

	/**
	 *	Borrows the selected book under the patron in question.
	 *
	 * @param e ActionEvent
	 */

	@FXML public void borrowBook(ActionEvent e){
		Book b = listOfBooks.getSelectionModel().getSelectedItem();
		Patron p = model.getPatron(Integer.parseInt(getID()));
		model.getCatalogue().loanBookToPatron(b, p);
		selectedPatron();
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
