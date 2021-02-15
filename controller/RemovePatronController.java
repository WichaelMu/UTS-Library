package controller;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import au.edu.uts.ap.javafx.Controller;
import model.*;

public class RemovePatronController extends Controller<Library>{
	@FXML private ListView<Patron> patronListView;
	@FXML private ObservableList<Patron> listOfPatrons = FXCollections.observableArrayList();

	/**
	 * Removes the selected patron from the library.
	 */

	@FXML private void removePatron(){
		Patron p = getSelectedPatron();
		if (p!=null)
			model.removePatron(p);
	}

	/**
	 * The list of patrons that are enlisted in the library.
	 *
	 * @return An obserable list of all the patrons in this library.
	 */

	@FXML public final ObservableList<Patron> getListOfPatrons(){
		return model.getPatrons();
	}

	/**
	 * Gets the patron that was selected by the user.
	 *
	 * @return The patron that was selected by the user.
	 */

	private Patron getSelectedPatron(){
		return patronListView.getSelectionModel().getSelectedItem();
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
