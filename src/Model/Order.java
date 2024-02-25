package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This class represents an order object.
 * @author Nguyen Truong Tho
 */
public class Order implements Serializable {

    private final String orderId;
    private final Date orderDate;
    private final String customerName;
    private final List<OrderDetail> orderDetail;
    private int quantity;
    private double flowerCost;

    /**
     * Constructor for the Order class.
     *
     * @param orderId The ID of the order.
     * @param orderDate The date the order was placed.
     * @param customerName The name of the customer who placed the order.
     * @param orderDetail A list of OrderDetail objects that contain information
     * about each item in the order.
     */
    public Order(String orderId, Date orderDate, String customerName, List<OrderDetail> orderDetail) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.orderDetail = orderDetail;
    }

    /**
     * Get the value of orderId
     *
     * @return the value of orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Get the value of orderDate
     *
     * @return the value of orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Get the value of customerName
     *
     * @return the value of customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Get the total quantity of flowers in the order.
     *
     * @return The total quantity of flowers in the order.
     */
    public int getQuantity() {
        quantity = 0;
        for (OrderDetail o : orderDetail) {
            quantity += o.getQuantity();
        }
        return quantity;
    }

    /**
     * Get the total cost of all flowers in the order.
     *
     * @return The total cost of all flowers in the order.
     */
    public double getFlowerCost() {
        flowerCost = 0;
        for (OrderDetail o : orderDetail) {
            flowerCost += o.getFlowerCost();
        }
        return flowerCost;
    }

    /**
     * Get a list of OrderDetail objects that contain information about each
     * item in the order.
     *
     * @return A list of OrderDetail objects that contain information about each
     * item in the order.
     */
    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    /**
     * Returns a string representation of this object.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return String.format("| %-9s | %12s | %-20s | %12d | %11.3f |", getOrderId(), sdf.format(getOrderDate()), getCustomerName(), getQuantity(), getFlowerCost());
    }

}
