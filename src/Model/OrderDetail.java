package Model;

import java.io.Serializable;

/**
 * This class represents an order detail object.
 * @author Nguyen Truong Tho
 */
public class OrderDetail implements Serializable {

    private String orderDetailId;
    private String flowerID;
    private int quantity;
    private double flowerCost;

    /**
     * Constructor for the OrderDetail class.
     *
     * @param orderDetailId The ID of the order detail.
     * @param flowerID The ID of the flower.
     * @param quantity The quantity of flowers in the order detail.
     * @param flowerCost The cost of the flowers in the order detail.
     */
    public OrderDetail(String orderDetailId, String flowerID, int quantity, double flowerCost) {
        this.orderDetailId = orderDetailId;
        this.flowerID = flowerID;
        this.quantity = quantity;
        this.flowerCost = flowerCost;
    }

    /**
     * Set the value of orderDetailId
     *
     * @param orderDetailId new value of orderDetailId
     */
    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * Get the value of flowerID
     *
     * @return the value of flowerID
     */
    public String getFlowerId() {
        return flowerID;
    }

    /**
     * Get the value of quantity
     *
     * @return the value of quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Get the value of flowerCost
     *
     * @return the value of flowerCost
     */
    public double getFlowerCost() {
        return flowerCost;
    }
}
