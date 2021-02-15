package controller;

import au.edu.uts.ap.javafx.Controller;
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

public class ReturnController extends Controller<Library>{
	@FXML private Button selector;
	@FXML private Button _return;
	@FXML private TextField ID;
	@FXML private ListView<Book> listOfBorrowedBooks;
	@FXML private final ObservableList<Book> borrowedBooks = FXCollections.observableArrayList();

	/**
	 * Disables the button to return a book if there is no book selected.
	 */

	@FXML private void initialize(){
		listOfBorrowedBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldBook, newBook) -> _return.setDisable( newBook == null));
		ID.textProperty().addListener((observable, oldValue, newValue) -> { selector.setDisable(IsLetter(ID.getText())); });
	}

	/**
	 * Shows the list of currently borrowed books by the patron in question.
	 */

	@FXML private void selectedPatron(){
		borrowedBooks.clear();
		borrowedBooks.addAll(model.getPatron(Integer.parseInt(getID())).currentlyBorrowed());
	}

	/**
	 *	The list of borrowed books by the patron in question.
	 *
	 * @return An observable list of books that represent the patron's currently borrowed books.
	 */

	@FXML public ObservableList<Book> getBorrowedBooks(){
		return borrowedBooks;
	}

	/**
	 * The ID representing the patron in question.
	 *
	 * @return The String ID corresponding to the patron.
	 */

	@FXML public String getID(){
		return ID.getText();
	}

	/**
	 * Returns the selected book from the selected patron.
	 *
	 * @param e ActionEvent
	 */

	@FXML public void returnBook(ActionEvent e){
		Book b = listOfBorrowedBooks.getSelectionModel().getSelectedItem();
		Patron p = model.getPatron(Integer.parseInt(getID()));
		model.getCatalogue().returnBookFromPatron(b, p);
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
