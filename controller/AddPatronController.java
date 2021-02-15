package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import au.edu.uts.ap.javafx.Controller;
import model.*;

public class AddPatronController extends Controller<Library>{
	@FXML private Button close;	//	The button to close this window.

	@FXML private TextField ID;		//	The ID of the new patron to be added.
	@FXML private TextField Name;	//	The Name of the new patron to be added.
	@FXML private Text text;		//	The text that tells the user whether the patron has been added or not.

	/**
	 * Adds a patron to the library.
	 * */
	@FXML private void addPatron(ActionEvent e){
		if (model.getPatron(getID())==null) {
			model.addPatron(getID(), getName());    //	I hope this adds a patron. EDIT: I think it does.
			text.setText("Patron added.");
		}
		else
			text.setText("Patron already exists!");
	}

	/**
	 *	Gets the ID for the new patron to be added.
	 *
	 * @return The Integer ID value.
	 * */
	@FXML private int getID(){
		return Integer.parseInt(this.ID.getText());
	}

	/**
	 * The name of the patron to be added.
	 *
	 * @return The String value for the name.
	 */

	@FXML private String getName(){
		return this.Name.getText();
	}

	/**
	 * Closes the current window.
	 * @param e ActionEvent
	 */

	@FXML private void closeWindow(ActionEvent e){
		Stage s = (Stage)close.getScene().getWindow();
		s.close();
	}
}
