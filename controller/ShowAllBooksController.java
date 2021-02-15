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

public class ShowAllBooksController extends Controller<Library>{

	@FXML private TableView<Book> booksTableView;
	@FXML private final ObservableList<Book> listOfBooks = FXCollections.observableArrayList();

	/**
	 * Shows all the books in the library.
	 *
	 * @return An observable list of all the books in the library.
	 */

	@FXML public final ObservableList<Book> getListOfBooks(){
		return model.getCatalogue().getAllBooks();
	}

	/**
	 * Closes the current window.
	 */

	@FXML private void closeWindow(){
		stage.close();
	}

	@FXML public void setBooksTableView(){
		System.out.println(booksTableView.getColumns());
	}
}
