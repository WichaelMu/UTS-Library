package controller;

import au.edu.uts.ap.javafx.Controller;
import com.sun.corba.se.impl.legacy.connection.SocketFactoryContactInfoImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class ShowBooksByGenreController extends Controller<Library>{

	@FXML private ListView<Genre> genreListView;
	@FXML private final ObservableList<Genre> listOfGenres = FXCollections.observableArrayList();
	@FXML private final ObservableList<Book> listOfBooksInGenres = FXCollections.observableArrayList();

	/**
	 * Gets the list of genres that populate the library.
	 *
	 * @return An observable list of all the genres in the library.
	 */

	@FXML public final ObservableList<Genre> getListOfGenres(){
		return model.getCatalogue().getGenres();
	}

	/**
	 * Gets the list of books in the selected genre.
	 *
	 * @return An observable list of all the books in the selected genre.
	 */

	@FXML public final ObservableList<Book> getListOfBooksInGenres(){
		return listOfBooksInGenres;
	}

	/**
	 * Gets the genre that was selected by the user.
	 *
	 * @return The genre that was selected by the user.
	 */

	private Genre getSelectedGenre(){
		return genreListView.getSelectionModel().getSelectedItem();
	}

	/**
	 * Adds all the books in the selected genre into the list of books in this genre.
	 */

	@FXML private void displayBooksByGenre(){
		Genre g = getSelectedGenre();
		if (g!=null) {
			listOfBooksInGenres.clear();
			listOfBooksInGenres.addAll(model.getCatalogue().getBooksInGenre(g));
		}

		if(g==null)
			listOfBooksInGenres.clear();
	}

	/**
	 * Closes the current window.
	 */

	@FXML private void closeWindow(){
		stage.close();
	}
}
