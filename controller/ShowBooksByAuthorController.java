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

public class ShowBooksByAuthorController extends Controller<Library>{

	@FXML private ListView<Author> authorListView;
	@FXML private final ObservableList<Author> listOfAuthors = FXCollections.observableArrayList();
	@FXML private final ObservableList<Book> listOfBooksByAuthors = FXCollections.observableArrayList();

	/**
	 * Gets the list of authors that contributed at least one book to the library.
	 *
	 * @return An observable list of all the authors that are contributors to the library.
	 */

	@FXML public final ObservableList<Author> getListOfAuthors(){
		return model.getCatalogue().getAuthors();
	}

	/**
	 * Shows all the books by the selected author.
	 *
	 * @return An observable list of all the books by this author that are in this library.
	 */

	@FXML public final ObservableList<Book> getListOfBooksByAuthors(){
		return listOfBooksByAuthors;
	}

	/**
	 * The author that was selected by the user.
	 *
	 * @return The author that was selected by the user.
	 */

	private Author getSelectedAuthor(){
		return authorListView.getSelectionModel().getSelectedItem();
	}

	/**
	 * Adds all the books by the selected author to the list of books by this specific author.
	 */

	@FXML private void displayBooksByAuthor(){
		Author a = getSelectedAuthor();
		if (a!=null) {
			listOfBooksByAuthors.clear();
			listOfBooksByAuthors.addAll(model.getCatalogue().getBooksByAuthor(a));
		}

		if (a==null)
			listOfBooksByAuthors.clear();
	}

	/**
	 * Closes the current window.
	 */

	@FXML private void closeWindow(){
		stage.close();
	}
}
