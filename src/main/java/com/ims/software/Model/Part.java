package com.ims.software.Model;

/** Part class is created with constructors, setters, and getters
 *
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** Constructor for Part object is created with parameters
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */

    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Return the part id
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /** Set the part id
     *
     * @param id
     */

   public void setId(int id) {
    this.id = id;
    }

    /** Return the part name
     *
     * @return
     */

    public String getName() {
        return this.name;
    }

    /** Set the part name
     *
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /** Return the part price
     *
     * @return
     */

    public double getPrice() {
        return this.price;
    }

    /** Set the part price
     *
     * @param price
     */

    public void setPrice(double price) {
        this.price = price;
    }

    /** Return the part stock amount
     * @return
     */

    public int getStock() {
        return this.stock;
    }

    /** Set the part stock
     *
     * @param stock
     */

    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Return the part min
     *
     * @return
     */

    public int getMin() {
        return this.min;
    }

    /** Set the part min
     *
     * @param min
     */

    public void setMin(int min) {
        this.min = min;
    }

    /** Return the part max
     *
     * @return
     */

    public int getMax() {
        return this.max;
    }

    /** Set the part max
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }
}
