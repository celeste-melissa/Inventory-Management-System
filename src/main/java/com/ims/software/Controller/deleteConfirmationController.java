package com.ims.software.Controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;

/** This class is created to control the functionality behind the deleteConfirmation.fxml
 *
 */
public class deleteConfirmationController {

    @FXML public Button deleteConfirmationButton;
    @FXML public Button cancelDeleteButton;

    private boolean confirmed = false;

    /** This method controls the delete button
     * This confirms that the user wants to delete selection
     * @param event
     *  @author Celeste Catala
     */
    public void onDeleteConfirmationButtonClick(ActionEvent event) {
        confirmed = true;
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    /** This method controls the functionality of the cancel button
     * It will allow the user to return to the main application without implementing any changes
     * @param event
     * @throws IOException
     */
    public void onCancelButtonClick(ActionEvent event) {
        confirmed = false;
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    /** This boolean is meant to confirm the user input
     *
     * @return
     */
    public boolean isConfirmed(){
        return confirmed;
    }
}


