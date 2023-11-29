package com.ims.software.Controller;

import com.ims.software.softwareMain;
import com.ims.software.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class is intended to allow the user to add a product to the inventory.
 * This controller is made to control the addProduct.fxml
 *  @author Celeste Catala
 */

public class addProductController implements Initializable {


    @FXML
    private TextField addProductNameText;
    @FXML
    private TextField addProductPriceText;
    @FXML
    private TextField addProductInventoryLevelText;
    @FXML
    private TextField addMaxProductText;
    @FXML
    private TextField addMinProductText;
    @FXML
    private Button addProductSaveButton;
    @FXML
    private Button cancelAddProductButton;
    @FXML
    private TextField searchAllParts;


    private ObservableList<Part> associatedPartList = FXCollections.observableArrayList();
    public TableView allPartsTable;
    public TableColumn partIDColumn;
    public TableColumn partNameColumn;
    public TableColumn partInventoryLevelColumn;
    public TableColumn partPriceColumn;

    public Button addPartsButton;

    public TableView associatedPartsTable;
    public TableColumn associatedPartIDColumn;
    public TableColumn associatedPartNameColumn;
    public TableColumn associatedPartInventoryLevelColumn;
    public TableColumn associatedPartPriceColumn;

    public Button removeAssociatedPartsButton;

    private Product selectedProduct;

    /**This method sets the associated parts list
     *
     */

    public addProductController() {

    }

    public void setAssociatedPartList(ObservableList<Part> associatedPartList) {
        this.associatedPartList = associatedPartList;
    }

    /** This method will add a part to the assocaited parts list
     *
     * @param event
     */
    public void onAddPartToProduct(ActionEvent event) {
        Part selectedPart = (Part) allPartsTable.getSelectionModel().getSelectedItem();
        associatedPartList.add(selectedPart);
        associatedPartsTable.setItems(associatedPartList);
    }

    /** This method removes a part from the associated part list
     *
     * @param event
     */

    public void onRemovePartFromProduct(ActionEvent event) {
        Part selectedPart = (Part) associatedPartsTable.getSelectionModel().getSelectedItem();
        associatedPartList.remove(selectedPart);
        associatedPartsTable.refresh();
    }

    /**This method saves the product information that was inputted by the user
     * Saves the data and inserts it into the product table of the main application scene
     * The data will be checked with if else statements before it is saved
     * @param event
     * @throws IOException
     */

    public void onSaveProductButtonClick(ActionEvent event) throws IOException {
        try {
            /** When the save button is clicked the user will return to the main scene
             * The new product information will  be displayed on the product table list of the softwareMain
             */

            Stage stage = (Stage) addProductSaveButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("softwareMain.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 945, 463);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();

            String name = addProductNameText.getText();
            double price = Double.parseDouble(addProductPriceText.getText());
            int stock = Integer.parseInt(addProductInventoryLevelText.getText());
            int min = Integer.parseInt(addMinProductText.getText());
            int max = Integer.parseInt(addMaxProductText.getText());

            //checks if the values meet this criteria
            if (stock < 0) {
                throw new IllegalArgumentException("Stock cannot be negative.");
            }
            if (price <= 0) {
                throw new IllegalArgumentException("Price must be greater than 0.");
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

            /** A new product will be created and the data associated with it will be saved
             *
             */
            Product newProduct = new Product(Inventory.getNextId(), name, price, stock, min, max);
            Inventory.addProduct(newProduct);

            for (Part part : associatedPartList) {
                newProduct.addAssociatedPart(part);
            }

        } catch (IllegalArgumentException err) {
            Alert alert = new Alert(Alert.AlertType.ERROR, err.getMessage());
            alert.showAndWait();
        }
    }

    /** This method controls the functionality of the cancel button
     * It will allow the user to return to the main application without implementing any changes
     * @param event
     * @throws IOException
     */

    public void onCancelButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelAddProductButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("softwareMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 945, 463);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /** This methods controls the search field of the part table
     * The user may use ID or name to search for a part
     */
    private void onSearchAllParts() {
        String searchText = searchAllParts.getText();
        if (searchText.trim().isEmpty()) {
            allPartsTable.setItems(Inventory.getAllParts());
        } else {
            try {
                int id = Integer.parseInt(searchText);
                Part partLocated = Inventory.lookupPart(id);
                if (partLocated != null) {
                    allPartsTable.setItems(FXCollections.observableArrayList(partLocated));
                } else {
                    allPartsTable.setItems((FXCollections.emptyObservableList()));
                }

            } catch (NumberFormatException err) {
                ObservableList<Part> filteredList = Inventory.lookupPart(searchText);
                allPartsTable.setItems(filteredList);
            }
        }
        allPartsTable.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        allPartsTable.setItems(Inventory.getAllParts());
        allPartsTable.refresh();

        associatedPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartsTable.refresh();
        searchAllParts.setOnAction(event -> onSearchAllParts());
        System.out.println("Intialization complete. Ready for user interaction.");

    }
}