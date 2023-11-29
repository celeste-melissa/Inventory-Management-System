package com.ims.software.Model;

/** This class is created for outsourced parts
 * It extends the Part class
 * Setters and getters are set for the outsource company name
 */
public class Outsourced extends Part {

    private String companyName;

    /** Parameters are set for the Outsourced class
     * Super constructor initializes the attributes from the Part class
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
        this.setMin(min);
        this.setMax(max);
        this.companyName = companyName;
    }

    /** Set the company name
     *
     * @param companyName
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** Return the company name
     *
     * @return
     */

    public String getCompanyName() {
        return this.companyName;
    }
}
