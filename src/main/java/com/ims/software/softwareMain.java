package com.ims.software;
import com.ims.software.Model.*;
import com.ims.software.Controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/** This class is created to serve as starting point for the javaFx application
 * This class extends the 'javafx.application.Application'
 * 'javafx.application.Application' provides the infrastructure for the JavaFx Applicaton
 */

/** JavaDoc located within com.ims.software.JavaDoc
 * 
 */
public class softwareMain extends Application {
    /** This method is created to insert data into the main application screen
     * This is the first data you will view when you 'run' the application
     * Four items are added to the Part table
     * Four items are added to the Product table
     */
    public static void insertData() {
        Inventory.addPart(new Outsourced(Inventory.getNextId(), "Mountain Bike Wheel", 160.99, 20, 1, 20, "Amazonia"));
        Inventory.addPart(new Outsourced(Inventory.getNextId(), "Training Wheels Set for Kids Bike", 19.99, 15, 1, 15, "Bike4kIDS"));
        Inventory.addPart(new InHouse(Inventory.getNextId(), "Mountain Bike Brakes", 40.90, 15, 1, 15, 101101));
        Inventory.addPart(new InHouse(Inventory.getNextId(),"Electric Bicycle LCD Display", 46.09, 10, 1, 10, 102777));

        Inventory.addProduct(new Product(Inventory.getNextId(), "Disney Kids Bike", 78.97, 10, 1, 10));
        Inventory.addProduct(new Product(Inventory.getNextId(), "BXM Sports Bike ", 269.99, 8, 1, 8));
        Inventory.addProduct(new Product(Inventory.getNextId(), "Electric 32Ah Bike", 649.49, 5, 1, 5));
    }
    /** This method launches the first scene that appears when the application is 'run'
     * The scene size is stated within the screen - this can be modified
     */

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(softwareMain.class.getResource("softwareMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 945, 463);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /** This main method is the entry point for the java application
     *
     */
    public static void main(String[] args) {
        insertData();
        launch();

    }

}


