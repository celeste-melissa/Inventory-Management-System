package com.ims.software.Controller;

import com.ims.software.softwareMain;
import com.ims.software.Model.Inventory;
import com.ims.software.Model.Part;
import com.ims.software.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class is intended to allow the user to modify an existing product
 * This controller is made to control the modifyProduct.fxml
 *  @author Celeste Catala
 */

public class modifyProductController implements Initializable {
    @FXML private TextField modifyProductIDText;
    @FXML private TextField modifyProductNameText;
    @FXML private TextField modifyProductPriceText;
    @FXML private TextField modifyProductInventoryLevelText;
    @FXML private TextField modifyMinProductText;
    @FXML private TextField modifyMaxProductText;

    @FXML private Button modifyProductSaveButton;
    @FXML private Button cancelModifyProductButton;

    @FXML private TextField searchAllParts;
    private ObservableList<Part> associatedPartList = FXCollections.observableArrayList();
    public TableView allPartsTable;
    public TableColumn partIDColumn;
    public TableColumn partNameColumn;
    public TableColumn partInventoryLevelColumn;
    public TableColumn partPriceColumn;

    public TableView associatedPartsTable;
    public TableColumn associatedPartIDColumn;
    public TableColumn associatedPartNameColumn;
    public TableColumn associatedPartInventoryLevelColumn;
    public TableColumn associatedPartPriceColumn;

    public Button addPartsButton;
    public Button removeAssociatedPartsButton;

    private Product selectedProduct;
    private int selectedIndex;

    public modifyProductController() {
    }

    /** This method gets the associated parts
     *
     * @return
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedPartList;
    }

    /** This method sets the associated parts
     *
     * @param associatedPartList
     */
    public void setAssociatedParts(ObservableList<Part> associatedPartList) {
        this.associatedPartList = associatedPartList;
    }

    /**This method sets the selected product
     *
     * @param selectedProduct
     */
    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    /** This method gathers the data the input for the values required
     * The user may select which values they seek to modify
     */

    public void gatherPartData() {
        modifyProductIDText.setPromptText(String.valueOf(selectedProduct.getId()));
        modifyProductNameText.setText(String.valueOf(selectedProduct.getName()));
        modifyProductInventoryLevelText.setText(String.valueOf(selectedProduct.getStock()));
        modifyProductPriceText.setText(String.valueOf(selectedProduct.getPrice()));
        modifyMinProductText.setText(String.valueOf(selectedProduct.getMin()));
        modifyMaxProductText.setText(String.valueOf(selectedProduct.getMax()));
        associatedPartList = selectedProduct.getAllAssociatedParts();
        associatedPartsTable.setItems(associatedPartList);
        associatedPartsTable.refresh();
    }

    /** This method sets selected index.
     * Sets index to be selected.
     * @param selectedIndex
     */
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    /** This method controls the add part button
     * The part is added to the associated part list
     * @param event
     */
    public void onAddPartToProduct(ActionEvent event) {
        Part selectedPart = (Part) allPartsTable.getSelectionModel().getSelectedItem();
        associatedPartList.add(selectedPart);
    }

    /** This method controls the removal of a part from the associated part list
     *
     * @param event
     */

    public void onRemovePartFromProduct(ActionEvent event) {
        Part selectedPart = (Part) associatedPartsTable.getSelectionModel().getSelectedItem();
        associatedPartList.remove(selectedPart);
        associatedPartsTable.setItems(associatedPartList);
    }

    /** This method controls save product button
     * The modifed product information will be saved to the product list of the softwareMain scene
     * @param event
     * @throws IOException
     */

    public void onSaveProductButtonClick(ActionEvent event) throws IOException {

        try {

            /** When the save button is clicked the user will return to the main scene
             * The new product information will  be displayed on the product table list of the softwareMain
             */
            Stage stage = (Stage) modifyProductSaveButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("softwareMain.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 945, 463);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();

            String name = modifyProductNameText.getText();
            int stock = Integer.parseInt(modifyProductInventoryLevelText.getText());
            double price = Double.parseDouble(modifyProductPriceText.getText());
            int min = Integer.parseInt(modifyMinProductText.getText());
            int max = Integer.parseInt(modifyMaxProductText.getText());

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
            Product newProduct = new Product(selectedProduct.getId(), name, price, stock, min, max);
            Inventory.updateProduct(selectedIndex, newProduct);
            System.out.println(selectedProduct.getAllAssociatedParts().toString());

            for (Part part : selectedProduct.getAllAssociatedParts()) {
                newProduct.addAssociatedPart(part);
            }
        } catch (IllegalArgumentException err) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Parts associated with this Product");
            alert.setContentText("Delete all associated parts first!");
            alert.showAndWait();
        }
    }

    /** This method controls the functionality of the cancel button
     * It will allow the user to return to the main application without implementing any changes
     * @param event
     * @throws IOException
     */
    public void onCancelButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelModifyProductButton.getScene().getWindow();
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
        }
        else {
            try {
                int id = Integer.parseInt(searchText);
                Part foundPart = Inventory.lookupPart(id);
                if (foundPart != null) {
                    allPartsTable.setItems(FXCollections.observableArrayList(foundPart));
                }
                else {
                    allPartsTable.setItems((FXCollections.emptyObservableList()));
                }
            }
            catch (NumberFormatException err) {
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