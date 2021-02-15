package controller;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import au.edu.uts.ap.javafx.Controller;
import model.*;

public class AddBookController extends Controller<Library>{
	@FXML private TextField Title;	//	The Title of the new book to be added.
	@FXML private TextField Author;	//	The Author of the new book to be added.
	@FXML private TextField Genre;	//	The Genre of the new book to be added.
	@FXML private Text text;		//	The text that tells the user whether the book has been added or not.

	/**
	 * 	Adds a book to the Library.
	 * */
	@FXML private void addBook(ActionEvent e){
		if (!model.getCatalogue().hasBook(getTitle(), getAuthor())) {
			model.getCatalogue().addBook(getTitle(), getAuthor(), getGenre());
			text.setText("Book added to Catalogue.");
		}
		else
			text.setText("That book is already in the Catalogue.");
	}

	/**
	 * 	Gets the Title of the new book to be added.
	 *
	 * @return The string title of the new book to be added.
	 * */
	@FXML private String getTitle(){
		return Title.getText();
	}

	/**
	 * 	Gets the Author of the new book to be added.
	 *
	 * @return The string Author of the new book to be added.
	 * */
	@FXML private String getAuthor(){
		return Author.getText();
	}

	/**
	 * 	Gets the Genre of the new book to be added.
	 *
	 * @return The string Genre of the new book to be added.
	 * */
	@FXML private String getGenre(){
		return Genre.getText();
	}

	/**
	 * 	Closes the current window.
	 * */
	@FXML private void closeWindow(ActionEvent e){
		stage.close();
	}
}
