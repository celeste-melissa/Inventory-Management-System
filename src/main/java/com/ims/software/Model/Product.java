package com.ims.software.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Product class is created with a constructors, setters, and getters
 *
 */
public class Product {

    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** Constructor for Product object is created with parameters
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = FXCollections.observableArrayList();
    }

    /** Return the product id
     *
     * @return
     */

    public int getId() {
        return id;
    }

    /** Set the product id
     *
     * @param id
     */

    public void setId(int id) {
        this.id = id;
    }

    /** Return the product name
     *
     * @return
     */

    public String getName() {
        return name;
    }

    /** Set the product name
     *
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /** Return the product price
     *
     * @return
     */

    public double getPrice() {
        return price;
    }

    /** Set the product price
     *
     * @param price
     */

    public void setPrice(double price) {
        this.price = price;
    }

    /** Return the product stock amount
     *
     * @return
     */

    public int getStock() {
        return stock;
    }

    /** Set the product stock amount
     *
     * @param stock
     */

    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Return the product min
     *
     * @return
     */

    public int getMin() {
        return min;
    }

    /** Set the product min
     *
     * @param min
     */

    public void setMin(int min) {
        this.min = min;

    }

    /** Return the product max
     *
     * @return
     */

    public int getMax() {
        return max;
    }

    /** Set the product max
     *
     * @param max
     */

    public void setMax(int max) {
        this.max = max;
    }

    /** Add part associated with the product
     *
     * @param part
     */

    public void addAssociatedPart(Part part) {
        try {
            if(part == null) {
                throw new NullPointerException("Empty entry not accepted.");
            }
            associatedParts.add(part);
        } catch (NullPointerException err) {
            System.out.println("Error:" + err + err.getMessage());
        }
    }

    /** Return all associated parts for the product
     *
     * @return
     */

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /** Enable deletion of associated part
     * Return boolean if deletion was successful or not
     *
     * @param selectedAssociatedPart
     * @return
     */

    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

}
