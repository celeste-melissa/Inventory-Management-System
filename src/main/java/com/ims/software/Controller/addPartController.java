package com.ims.software.Controller;

import com.ims.software.softwareMain;
import com.ims.software.Model.*;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.event.ActionEvent;
import org.controlsfx.control.action.Action;

/** This class is intended to allow the user to add a part to the inventory.
 * This controller is made to control the addPart.fxml
 *  @author Celeste Catala
 */
public class addPartController implements Initializable {


    @FXML private RadioButton partInHouseButton;
    @FXML private RadioButton partOutsourceButton;
    @FXML private ToggleGroup InHouseOrOutsourced;

    @FXML private TextField partNameText;
    @FXML private TextField partInventoryLevelText;
    @FXML private TextField partPriceText;
    @FXML private TextField maxPartText;
    @FXML private TextField minPartText;
    @FXML private Label addMachineOrCompany;
    @FXML private TextField partMachineIDText;
    @FXML private TextField partCompanyNameText;
    @FXML private Button saveAddPartButton;
    @FXML private Button cancelAddPartButton;

    public addPartController() {

    }

    /**This controls the InHouse radio button
     * These are the fields for if a user is adding a part that is InHouse
     *
     */
    public void onInHouseRadioClick(ActionEvent event) {
        if (partInHouseButton.isSelected()) {
            partMachineIDText.setVisible(true);
            partCompanyNameText.setVisible(false);
            addMachineOrCompany.setText("Machine ID");
        }
    }

    /** This controls the outsourced radio button
     * These are the fields that the user will see for an outsourced part
     * @param event
     */

    public void onOutsourcedRadioClick(ActionEvent event) {
        if (partOutsourceButton.isSelected()) {
            partMachineIDText.setVisible(false);
            partCompanyNameText.setVisible(true);
            addMachineOrCompany.setText("Company Name");
        }
    }

    /**This method will save the part information that user entered
     *
     */

    public void onSavePartButtonClick(ActionEvent event) throws IOException {
        try {

            /** When the save button is clicked the user will return to the main scene
             * The new part inforamtion will be displayed on the part table of the softwareMain
             */
            Stage stage = (Stage) saveAddPartButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("softwareMain.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 945, 463);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
            /** This is the data that will be gathered for when a part is added
             *
             */
            String name = partNameText.getText();
            int stock = Integer.parseInt(partInventoryLevelText.getText());
            double price = Double.parseDouble(partPriceText.getText());
            int max = Integer.parseInt(maxPartText.getText());
            int min = Integer.parseInt(minPartText.getText());

            /** These are the validation checks that will occur for the integer values of stock, price, min, and max
             * These checks ensure that the input values meet certain criteria before being passed through
             *
             */
            if(stock < 0) {
                throw new IllegalArgumentException("Stock cannot be a negative value.");
            }

            if (price <= 0) {
                throw new IllegalArgumentException(("Price must be greater than 0."));
            }
            if (min < 0 || max < 0) {
                throw new IllegalArgumentException("Minimum and maximum values cannot be negative.");
            }
            if (min > max) {
                throw new IllegalArgumentException("Minimum value must be less than or equal to maximum value.");
            }
            if (stock < min || stock > max) {
                throw new IllegalArgumentException("Inventory must be between minimum and maximum values.");
            }
            /** A new part will be added to the system
             *
             */
            Part newPart;

            /** Outsourced part logic
             *
             */
            if(partOutsourceButton.isSelected()) {
                newPart = new Outsourced(Inventory.getNextId(), name, price, stock, min, max, String.valueOf(partCompanyNameText.getText()));
                System.out.println("Part was outsourced to the following company: " + partCompanyNameText.getText());
            }
            /** InHouse part logic
             *
             */
            else {
                newPart = new InHouse(Inventory.getNextId(), name, price, stock, min, max, Integer.parseInt((partMachineIDText.getText())));
                System.out.println("This part is added as an In-House part. Machine ID: " + partMachineIDText.getText());
            }
            Inventory.addPart(newPart);
        }
        catch (IllegalArgumentException err) {
            Alert alert = new Alert(Alert.AlertType.ERROR, err.getMessage());
            alert.showAndWait();
        }
    }

    /** This method is created to control the functionality of the cancel button
     * This will allow the user to cancel adding a part without changes
     * The user will return to the main application scene
     * @param event
     * @throws IOException
     */
    public void onCancelButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelAddPartButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("softwareMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 945, 463);
        stage.setTitle(("Inventory Management System"));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
