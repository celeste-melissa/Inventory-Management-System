package com.ims.software.Model;

/** This InHouse class extends the Part class
 * Setters and getters are set for the machineID
 */
public class InHouse extends Part {
    private int machineId;

    /** Parameters are set for the InHouse class
     * Super constructor initializes the attributes from the Part class
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
        this.setMin(min);
        this.setMax(max);
        this.machineId = machineId;
    }

    /** Set Machine ID
     *
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /** Get Machine ID
     *
     * @return
     */
    public int getMachineId() {
        return this.machineId;
    }
}
