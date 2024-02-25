package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a flower object.
 * @author Nguyen Truong Tho
 */
public class Flower implements Serializable {

    private String flowerId;

    private String name;

    private Date importDate;

    private double unitPrice;

    private String category;

    /**
     * Constructor for the Flower class.
     *
     * @param flowerId The ID of the flower.
     * @param name The name of the flower.
     * @param importDate The date the flower was imported.
     * @param unitPrice The price per unit of the flower.
     * @param category The category of the flower.
     */
    public Flower(String flowerId, String name, Date importDate, double unitPrice, String category) {
        this.flowerId = flowerId;
        this.name = name;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    /**
     * Get the value of category
     *
     * @return the value of category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the value of category
     *
     * @param category new value of category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Get the value of unitPrice
     *
     * @return the value of unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Set the value of unitPrice
     *
     * @param unitPrice new value of unitPrice
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Get the value of importDate
     *
     * @return the value of importDate
     */
    public Date getImportDate() {
        return importDate;
    }

    /**
     * Set the value of importDate
     *
     * @param importDate new value of importDate
     */
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of description
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getFlowerId() {
        return flowerId;
    }

    /**
     * Set the value of id
     *
     * @param flowerId
     */
    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
    }

    /**
     * Returns a string representation of this object.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return String.format("| %-9s | %-20s | %12s | %12.3f | %8s |", getFlowerId(), getName(), sdf.format(getImportDate()), getUnitPrice(), getCategory());
    }
}
