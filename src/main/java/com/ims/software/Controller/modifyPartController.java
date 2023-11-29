package com.ims.software.Controller;

import com.ims.software.softwareMain;
import com.ims.software.Model.*;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
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

/** This class is intended to allow the user to modify an existing part
 * This controller is made to control the modifyPart.fxml
 *  @author Celeste Catala
 */

public class modifyPartController implements Initializable {


    @FXML
    private RadioButton modifyPartInHouseButton;
    @FXML
    private RadioButton modifyPartOutsourceButton;

    @FXML
    private TextField modifyPartIDText;
    @FXML
    private TextField modifyPartNameText;

    @FXML
    private TextField modifyPartInventoryLevelText;

    @FXML
    private TextField modifyPartPriceText;

    @FXML
    private TextField modifyMaxPartText;

    @FXML
    private TextField modifyMinPartText;
    @FXML
    private Label machineOrCompany;
    @FXML
    private TextField machineOrCompanyText;
    @FXML
    private Button saveModifyPartButton;
    @FXML
    private Button cancelModifyPartButton;
    @FXML
    private ToggleGroup InHouseOrOutsourced;
    private Part selectedPart;
    private int selectedIndex;


    private ObservableList<Part> associatedPartList;

    /** This method sets the selected index
     *
     * @param selectedIndex
     */
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    /**This method sets an associated part list
     *
     * @param associatedPartList
     */

    public void setAssociatedPartList(ObservableList<Part> associatedPartList) {
        this.associatedPartList = associatedPartList;
    }

    /** This method sets the selected part
     *
     * @param selectedPart
     */


    public void setSelectedPart(Part selectedPart) {
        this.selectedPart = selectedPart;
    }

    /** This method gathers the necessary part data
     * This data will be populated into the selected part object
     */
    public void gatherPartData() {
        modifyPartIDText.setPromptText(String.valueOf(selectedPart.getId()));
        modifyPartNameText.setText(selectedPart.getName());
        modifyPartInventoryLevelText.setText(String.valueOf(selectedPart.getStock()));
        modifyMinPartText.setText(String.valueOf(selectedPart.getMin()));
        modifyMaxPartText.setText(String.valueOf(selectedPart.getMax()));
        modifyPartPriceText.setText(String.valueOf(selectedPart.getPrice()));

        //checks if the modification is an InHouse part

        if (selectedPart instanceof InHouse) {
            machineOrCompanyText.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
            machineOrCompanyText.setVisible(true);
            modifyPartInHouseButton.setSelected(true);
            machineOrCompany.setText("Machine ID");
        //checks if the modification is an Outsourced part
        } else {
            machineOrCompanyText.setText(String.valueOf(((Outsourced) selectedPart).getCompanyName()));
            machineOrCompanyText.setVisible(true);
            modifyPartOutsourceButton.setSelected(true);
            machineOrCompany.setText("Company Name");

        }
    }

    /** This controls the functionality of if the InHouse button is clicked
     * As a result the label will change to receive the Machine ID
     * @param event
     */
    public void onInHouseRadioClick(ActionEvent event) {
        if (modifyPartInHouseButton.isSelected()) {
            machineOrCompany.setText("Machine ID");
        }
    }

    /** This controls the functionality if the Outsouce button is clicked
     * As a result the label will change to receive the Company Name
     * @param event
     */

    public void onOutsourcedRadioClick(ActionEvent event) {
        if (modifyPartOutsourceButton.isSelected()) {
            machineOrCompany.setText("Company Name");
        }
    }

    /** This method controls the save button
     * Conditions will be observed base on if the part is InHouse or Outsourced
     * The data that is gathered will be checked  to ensure that it mets the given criteria
     * @param event
     * @throws IOException
     */



    public void onSavePartButtonClick(ActionEvent event) throws IOException {
        try {
            if (modifyPartInHouseButton.isSelected()) {
                InHouse inHousePart = new InHouse(selectedPart.getId(), "null", 0, 0, 0, 0, 0);


                if (!machineOrCompanyText.getText().isEmpty()) {
                    inHousePart.setMachineId(Integer.parseInt(machineOrCompanyText.getText()));
                } else {
                    inHousePart.setMachineId(0);
                    System.out.println(("Entry: " + machineOrCompanyText.getText()));
                }

                inHousePart.setMachineId(Integer.parseInt(machineOrCompanyText.getText()));
                inHousePart.setName(modifyPartNameText.getText());
                inHousePart.setMax(Integer.parseInt(modifyMaxPartText.getText()));
                inHousePart.setMin(Integer.parseInt(modifyMinPartText.getText()));
                inHousePart.setPrice(Double.parseDouble(modifyPartPriceText.getText()));
                inHousePart.setStock(Integer.parseInt(modifyPartInventoryLevelText.getText()));
                Inventory.updatePart(selectedIndex, inHousePart);
            }

         else if (modifyPartOutsourceButton.isSelected()) {
                    Outsourced outsourcedPart = new Outsourced(selectedPart.getId(), "", 0, 0, 0, 0, "");
                    outsourcedPart.setCompanyName(machineOrCompanyText.getText());
                    outsourcedPart.setName(modifyPartNameText.getText());
                    outsourcedPart.setMax(Integer.parseInt(modifyMaxPartText.getText()));
                    outsourcedPart.setMin(Integer.parseInt(modifyMinPartText.getText()));
                    outsourcedPart.setPrice(Double.parseDouble(modifyPartPriceText.getText()));
                    outsourcedPart.setStock(Integer.parseInt(modifyPartInventoryLevelText.getText()));
                    Inventory.updatePart(selectedIndex, outsourcedPart);
                }
        else{
                    selectedPart.setName(modifyPartNameText.getText());
                    selectedPart.setMax(Integer.parseInt(modifyMaxPartText.getText()));
                    selectedPart.setMin(Integer.parseInt(modifyMinPartText.getText()));
                    selectedPart.setPrice(Double.parseDouble(modifyPartPriceText.getText()));
                    selectedPart.setStock(Integer.parseInt(modifyPartInventoryLevelText.getText()));
                }
                // checks the criteria of the following input fields - inventory level, price, min, max
                if (Integer.parseInt(modifyPartInventoryLevelText.getText()) < 0) {
                    throw new IllegalArgumentException("Stock cannot be negative.");
                }
                if (Double.parseDouble(modifyPartPriceText.getText()) <= 0) {
                    throw new IllegalArgumentException("Price must be greater than 0.");
                }
                if (Integer.parseInt(modifyMinPartText.getText()) < 0 || Integer.parseInt(modifyMaxPartText.getText()) < 0) {
                    throw new IllegalArgumentException("Minimum and maximum values cannot be negative.");
                }
                if (Integer.parseInt(modifyMinPartText.getText()) > Integer.parseInt(modifyMaxPartText.getText())) {
                    throw new IllegalArgumentException("Minimum value must be less than or equal to maximum value.");
                }
                if (Integer.parseInt(modifyPartInventoryLevelText.getText()) < Integer.parseInt(modifyMinPartText.getText()) || Integer.parseInt(modifyMaxPartText.getText()) > Integer.parseInt(modifyMaxPartText.getText())) {
                    throw new IllegalArgumentException("Inventory must be between minimum and maximum values.");
                }
            /** The scene will take the user back to the main application scene after the save button is clicked
             *
             */
            Stage stage = (Stage) saveModifyPartButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("softwareMain.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 945, 463);
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            }

    catch(IllegalArgumentException err){
                Alert alert = new Alert(Alert.AlertType.ERROR, err.getMessage());
                alert.showAndWait();

            }

        }

    /** This method is created to control the functionality of the cancel button
     * This will allow the user to cancel modifying a part without changes
     * The user will return to the main application scene
     * @param event
     * @throws IOException
     */
        public void onCancelButtonClick (ActionEvent event) throws IOException {
            Stage stage = (Stage) cancelModifyPartButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("softwareMain.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 945, 463);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
        }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            System.out.println("Part successfully modified.");
        }
    }





