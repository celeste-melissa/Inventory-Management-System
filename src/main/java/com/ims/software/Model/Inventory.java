package com.ims.software.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Locale;
public class Inventory {
    /** ObservableList is used to store parts and products
     * This is suitable for JavaFx
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static int nextId = 0;

    public static int getNextId() {
        return ++ nextId;
    }

    /** Method adds a new part
     *
     * @param newPart
     */

    public static void addPart(Part newPart) {
        newPart.setId(getNextId());
        allParts.add(newPart);
    }

    /** Method adds a new product
     *
     * @param newProduct
     */

    public static void addProduct(Product newProduct) {
        newProduct.setId(getNextId());
        allProducts.add(newProduct);
    }

    /** Part lookup enabled using partId
     * Returns null if there is no match found
     * @param partId
     * @return
     */

    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /** Part lookup enabled using productId
     * Returns null if there is no match found
     * @param productId
     * @return
     */

    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId){
                return product;
            }
        }
        return null;
    }

    /** Allow users to lookup parts via their name
     *
     * @param partName
     * @return
     */

    public static ObservableList<Part> lookupPart(String partName) {
       ObservableList<Part> filteredList = FXCollections.observableArrayList();
       for (Part part : allParts) {
           if(part.getName().toLowerCase().contains(partName.toLowerCase())) {
               filteredList.add(part);
           }
       }
       return filteredList;
    }

    /** Allow users to lookup products via their product name
     *
     * @param productName
     * @return
     */

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> filteredList = FXCollections.observableArrayList();
        for(Product product : allProducts) {
            if(product.getName().toLowerCase(Locale.ROOT).contains(productName) || product.getName().toUpperCase(Locale.ROOT).contains(productName)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    /** Allow the user to update the product
     * Index will position the product in alignment with pre-exisiting products
     *
     * @param index
     * @param newProduct
     */

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /** Allow the user to update the part positioning it using index
     *
     * @param index
     * @param selectedPart
     */

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /** Delete the part that is selected
     * A boolean is returned indicating if the deletion was successful
     * @param selectedPart
     * @return
     */

    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /** Delete the product that is selected
     * A boolean is returned indicating if the deletion was successful
     * @param selectedProduct
     * @return
     */


    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /** Returns the list of parts
     *
     * @return
     */

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /** Returns the list of products
     *
     * @return
     */

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
