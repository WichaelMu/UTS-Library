package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import au.edu.uts.ap.javafx.Controller;
import model.*;

public class RecordController extends Controller<Library>{
	@FXML private TextField ID;
	@FXML private Text message;
	@FXML private Button showRecord;
	@FXML private final ObservableList<Book> currentlyBorrowed = FXCollections.observableArrayList();	//	The observable list showing this patron's current borrowed books.
	@FXML private final ObservableList<Book> borrowingHistory = FXCollections.observableArrayList();	//	The obserable list showing this patron's borrowing history.

	/**
	 * Disables the button to show a patron's record if no patron ID is entered.
	 */

	@FXML public void initialize(){
		ID.textProperty().addListener((observable, oldValue, newValue) -> { showRecord.setDisable(IsLetter(ID.getText())); });
	}

	/**
	 * Shows this patron's library record.
	 */

	@FXML private void ShowRecord(){
		currentlyBorrowed.clear();
		borrowingHistory.clear();
		Patron p = model.getPatron(Integer.parseInt(getID()));
		currentlyBorrowed.addAll(p.currentlyBorrowed());
		borrowingHistory.addAll(p.borrowingHistory());
		message.setText(p.getId() + " " + p.getName());
	}

	/**
	 *	The list of this patron's currently borrowed books.
	 *
	 * @return An observable list of this patron's currently borrowed books.
	 */

	@FXML public ObservableList<Book> getCurrentlyBorrowed(){
		return currentlyBorrowed;
	}

	/**
	 *	The list of this patron's borrowing history.
	 *
	 * @return An observable list of this patron's borrowing history.
	 */

	@FXML public ObservableList<Book> getBorrowingHistory(){
		return borrowingHistory;
	}

	/**
	 * The ID used to represent the patron in question.
	 *
	 * @return A String ID corresponding to the patron in question.
	 */

	@FXML private String getID(){
		return ID.getText();
	}

	/**
	 * Disables the button to show a patron's library record if no patron ID is selected.
	 *
	 * @param b boolean to disable the button.
	 */

	private void disableButton(boolean b){
		showRecord.setDisable(b);
	}

	/**
	 * Checks if string test includes at least one letter.
	 *
	 * @param test String to check if it is composed of at least one letter.
	 * @return True if there is at least one letter in string test.
	 */

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
	 * @param e ActionEvent.
	 */

	@FXML private void closeWindow(ActionEvent e){
		stage.close();
	}
}
