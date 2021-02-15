package controller;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import au.edu.uts.ap.javafx.Controller;
import model.*;

public class RemoveBookController extends Controller<Library>{
	@FXML private ListView<Book> bookListView;
	@FXML private ObservableList<Book> listOfBooks = FXCollections.observableArrayList();

	@FXML public void initialize(){

	}

	/**
	 * Removes the selected book from the library.
	 */

	@FXML private void removeBook(){
		Book b = getSelectedBook();
		if (b!=null) {
			model.getCatalogue().removeBook(b);
			listOfBooks.clear();
			refreshListOfBooks();
		}
	}

	/**
	 * Updates the list of books from the library.
	 */

	private void refreshListOfBooks(){
		listOfBooks.addAll(model.getCatalogue().getAllBooks());
	}

	/**
	 * The list of books that can be removed from the library/
	 *
	 * @return An observable list of all the books in the library.
	 */

	@FXML public final ObservableList<Book> getListOfBooks(){
		return model.getCatalogue().getBooksOnShelf();
	}

	/**
	 * Gets the selected book from the user.
	 *
	 * @return A book that was selected by the user.
	 */

	private Book getSelectedBook(){
		return bookListView.getSelectionModel().getSelectedItem();
	}

	/**
	 * Closes the current window.
	 *
	 * @param e ActionEvent
	 */

	@FXML private void closeWindow(ActionEvent e){
		stage.close();
	}
}
