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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** @FUTURE ENHANCEMENT - consider making the application responsive on different screen sizes
 * @author Celeste Catala
 */

/** This class will control the main application entry point
 * This is what the user sees when the program is first run
 */

public class softwareMainController implements Initializable {

    /** These are the values that are presented under the parts table
     *
     */
    @FXML private Label warningLabel;
    @FXML private Label partsTableHeader;
    @FXML private TextField partsSearchBar;
    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> partsIDColumn;
    @FXML private TableColumn<Part, String> partsNameColumn;
    @FXML private TableColumn<Part, Integer> partsInventoryLevelColumn;
    @FXML private TableColumn<Part, Double> partsPriceColumn;
    @FXML private Button addPartButton;
    @FXML private Button modifyPartButton;
    @FXML private Button deletePartButton;

    /** These are the values that are presented under the products table
     *
     */
    @FXML private Label productsTableHeader;
    @FXML private TextField productsSearchBar;
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, Integer> productsIDColumn;
    @FXML private TableColumn<Product, String> productsNameColumn;
    @FXML private TableColumn<Product, Integer> productsInventoryLevelColumn;
    @FXML private TableColumn<Product, Double> productsPriceColumn;
    @FXML private Button addProductButton;
    @FXML private Button modifyProductButton;
    @FXML private Button deleteProductButton;
    @FXML private Button exitButton;

    private ObservableList associatedParts = FXCollections.observableArrayList();

    public softwareMainController() {}

    /**This method Sets associated parts.
     * @param associatedParts
     */
    public void setAssociatedParts(ObservableList associatedParts) {this.associatedParts = associatedParts;}

    @FXML private void onAddPartButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("addPart.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) addPartButton.getScene().getWindow();
        stage.setTitle("A PART MUST BE ADDED!");
        stage.setScene(scene);
        stage.show();
        System.out.println("Add a part button has been clicked.");
    }

    /**
     * This method controls modify part button.
     * @param actionEvent unassigned
     * @throws IOException
     */
    @FXML private void onModifyPartButtonClick(ActionEvent actionEvent) throws IOException {
        warningLabel.setVisible(false);
        Part selectedPart;
        partsTable.refresh();
        selectedPart = partsTable.getSelectionModel().getSelectedItem();

        if(selectedPart == null) {
            System.out.println("A PART MUST BE SELECTED!");
        }

        else {
            FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("modifyPart.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) modifyPartButton.getScene().getWindow();
            stage.setTitle("Modify Part");
            stage.setScene(scene);
            stage.show();
            modifyPartController modifyPartController = fxmlLoader.getController();
            modifyPartController.setSelectedPart(selectedPart);
            modifyPartController.setAssociatedPartList(associatedParts);
            modifyPartController.gatherPartData();
            modifyPartController.setSelectedIndex(partsTable.getSelectionModel().getSelectedIndex());
            System.out.println("Modify Part Button has been clicked.");
            System.out.println("Part ID: " + selectedPart.getId());
            System.out.println("Part ID Index: " + partsTable.getSelectionModel().getSelectedIndex());
        }
    }

    /** This method deletes the selected part
     *
     * @throws IOException
     */
    @FXML private void onDeletePartButtonClick() throws IOException {
        Part toBeDeleted = partsTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("deleteConfirmation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        deleteConfirmationController deleteConfirmationController = fxmlLoader.getController();
        Stage stage = (Stage) new Stage();
        stage.setTitle("Successfully Deleted.");
        stage.setScene(scene);
        stage.showAndWait();
        if (deleteConfirmationController.isConfirmed()) {
            Inventory.getAllParts().remove(toBeDeleted);
            partsTable.getItems().remove(toBeDeleted);
            partsTable.refresh();
            System.out.println("DELETED");
        } else {
            System.out.println("Delete Canceled");
        }
    }



    /** This method controls the add product button
     * This will bring up the new scene that will add a part
     * @throws IOException
     */

    @FXML private void onAddProductButtonClick() throws IOException {
        warningLabel.setVisible(false);
        FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("addProduct.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) addProductButton.getScene().getWindow();
        stage.setTitle("A PRODUCT MUST BE ADDED!");
        stage.setScene(scene);
        stage.show();
        System.out.println("Add Product Button Clicked");
    }

    /** This method controls the modify product button
     * This will bring up the modify product scene
     * @throws IOException
     */

    @FXML private void onModifyProductButtonClick() throws IOException {
        warningLabel.setVisible(false);
        Product selectedProduct;
        productsTable.refresh();
        selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            System.out.println("Need To select a product!!");
        }
        else {

            FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("modifyProduct.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) modifyProductButton.getScene().getWindow();
            stage.setTitle("Modify Product");
            stage.setScene(scene);
            stage.show();

            //Pass Data
            modifyProductController modifyProductController = fxmlLoader.getController();
            modifyProductController.setSelectedProduct(selectedProduct);
            modifyProductController.setSelectedIndex(selectedProduct.getId());
            modifyProductController.gatherPartData();
            modifyProductController.setSelectedIndex(productsTable.getSelectionModel().getSelectedIndex());
            stage.setOnHidden(e -> {
                     ObservableList<Part> associatedPartList = modifyProductController.getAssociatedParts();
                     setAssociatedParts(associatedPartList);
                     System.out.println("Modify Part Button Clicked");
            });
            System.out.println("Modify Product Button Clicked");
        }
    }



    /**
     * This method controls the delete button.
     *  Deletes products and checks to make sure that there are no associated parts first.
     * @throws IOException
     */
   @FXML private void onDeleteProductButtonClick() throws IOException {
       Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
       if (!selectedProduct.getAllAssociatedParts().isEmpty()) {
           warningLabel.setVisible(true);
           System.out.println("Remove Associated Parts First");
           return;
       }
       FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("deleteConfirmation.fxml"));
       Scene scene = new Scene(fxmlLoader.load());
       deleteConfirmationController deleteConfirmationController = fxmlLoader.getController();
       Stage stage = (Stage) new Stage();
       stage.setTitle("Delete Confirmation");
       stage.setScene(scene);
       stage.showAndWait();
       if (deleteConfirmationController.isConfirmed()) {
           Inventory.getAllProducts().remove(selectedProduct);
           productsTable.getItems().remove(selectedProduct);
           productsTable.refresh();
           System.out.println("Deleted");
       } else {
           System.out.println("Delete Canceled");
       }

   }


    /** This method controls the functionality of the exit button.
     * The application will close
     * @param event
     */
    @FXML private void onExitButtonClick(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        System.out.println("Exit button was clicked.");
    }

    /**
     * This method controls the search by part text field.
     * It allows the user to search for a part by ID or by name.
     */
    @FXML
    private void onSearchByPartTextField() {
        String searchText = partsSearchBar.getText();
        if (searchText.trim().isEmpty()) {
            partsTable.setItems(Inventory.getAllParts());
        } else {
            try {
                int id = Integer.parseInt(searchText);
                Part foundPart = Inventory.lookupPart(id);
                if (foundPart != null) {
                    partsTable.setItems(FXCollections.observableArrayList(foundPart));
                } else {
                    partsTable.setItems(FXCollections.emptyObservableList());
                }
            } catch (NumberFormatException e) {
                ObservableList<Part> filteredList = Inventory.lookupPart(searchText);
                partsTable.setItems(filteredList);
            }
        }
        partsTable.refresh();
    }

    /**
     * This method controls the search by product text field.
     * This allows the user to search for a product by ID or by name.
     */
    @FXML
    private void onSearchByProductTextField() {
        String searchText = productsSearchBar.getText();
        if (searchText.trim().isEmpty()) {
            productsTable.setItems(Inventory.getAllProducts());
        } else {
            try {
                int id = Integer.parseInt(searchText);
                Product foundProduct = Inventory.lookupProduct(id);
                if (foundProduct != null) {
                    productsTable.setItems(FXCollections.observableArrayList(foundProduct));
                } else {
                    productsTable.setItems(FXCollections.emptyObservableList());
                }
            } catch (NumberFormatException e) {
                ObservableList<Product> filteredList = Inventory.lookupProduct(searchText);
                productsTable.setItems(filteredList);
            }
        }
        productsTable.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(Inventory.getAllParts());
        partsTable.refresh();

        productsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.setItems(Inventory.getAllProducts());
        productsTable.refresh();

        partsSearchBar.setOnAction(event -> onSearchByPartTextField());
        productsSearchBar.setOnAction(event -> onSearchByProductTextField());

    }

}



