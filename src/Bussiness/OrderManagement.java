package Bussiness;

import Model.Flower;
import Model.Order;
import Model.OrderDetail;
import Tools.FileIO;
import Tools.InputFormatter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * This class represents a collection of Order objects.
 * @author Nguyen Truong Tho
 */
public class OrderManagement extends HashSet<Order> {

    private boolean isSaved = false;

    /**
     * Checks if the data is saved.
     *
     * @return True if the data is saved, false otherwise.
     */
    public boolean isIsSaved() {
        return isSaved;
    }

    /**
     * Sets the saved status of the data.
     *
     * @param isSaved True if the data is saved, false otherwise.
     */
    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    /**
     * Checks if a flower is included in any order.
     *
     * @param flower The flower to check.
     * @return True if the flower is included in any order, false otherwise.
     */
    public boolean isInOrder(Flower flower) {
        boolean isInOrder = false;
        for (Order o : this) {
            for (OrderDetail d : o.getOrderDetail()) {
                if (flower.getFlowerId().equals(d.getFlowerId())) {
                    isInOrder = true;
                    break;
                }
            }
        }
        return isInOrder;
    }

    /**
     * Adds an order to the collection.
     *
     * @param flowerList The FlowerManagement instance containing the flower
     * list.
     * @throws ParseException If an error occurs during the parsing of the order
     * date.
     * @throws Exception If an error occurs during the order addition process.
     */
    public void addOrder(FlowerManagement flowerList) throws ParseException, Exception {
        String orderId = String.format("%04d", this.size() + 1);
        Date orderDate = InputFormatter.getDate("Enter Order Date: ");
        String custumerName = InputFormatter.getString("Enter Customer's Name: ", "Must be in name format!", "^[a-zA-Z ]+$");
        List<OrderDetail> orderDetail = inputOrderDetail(flowerList);
        this.add(new Order(orderId, orderDate, custumerName, orderDetail));
        System.out.println(custumerName + "'s order has been added to store!");
        setIsSaved(false);
    }

    /**
     * Collects the order details from the user input.
     *
     * @param flowerList The FlowerManagement instance containing the flower
     * list.
     * @return The list of order details.
     * @throws Exception If an error occurs during the order detail input
     * process.
     */
    private List<OrderDetail> inputOrderDetail(FlowerManagement flowerList) throws Exception {
        List<OrderDetail> orderDetail = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            String orderDetailId = String.format("%04d", orderDetail.size() + 1);
            String flowerId = flowerList.getFlowerIdExist();
            int quantity = InputFormatter.getInt("Enter quantity: ", "Quantity must be greater than 0!", 1, Integer.MAX_VALUE);
            double flowerCost = flowerList.getFlowerById(flowerId).getUnitPrice() * quantity;
            orderDetail.add(new OrderDetail(orderDetailId, flowerId, quantity, flowerCost));
            flag = InputFormatter.getBoolean("Do you want to buy more flower? (Y/N): ");
        }
        return orderDetail;
    }

    /**
     * Retrieves a list of orders within a specified date range.
     *
     * @return The list of orders within the specified date range.
     * @throws ParseException If an error occurs during the parsing of the start
     * and end dates.
     */
    public List showOrder() throws ParseException {
        List<Order> list = new ArrayList<>();
        Date startDay = InputFormatter.getDate("Enter Start Day: ");
        Date endDay = InputFormatter.getDateAfter("Enter End Day: ", startDay);
        for (Order o : this) {
            Date date = o.getOrderDate();
            if (date.compareTo(startDay) >= 0 && date.compareTo(endDay) <= 0) {
                list.add(o);
            }
        }
        return list;
    }

    /**
     * Sorts the order list based on a chosen field.
     *
     * @param field The field to sort the order list by.
     * @return The sorted order list.
     * @throws Exception If an error occurs during the sorting process.
     */
    public List sortByField(int field) throws Exception {
        List<Order> sortList = new ArrayList<>(this);
        switch (field) {
            case 1:
                //Collections.sort(sortList, (Patient p1, Patient p2) -> p1.getId().compareTo(p2.getId()));
                Collections.sort(sortList, (Order o1, Order o2) -> o1.getOrderId().compareTo(o2.getOrderId()));
                break;
            case 2:
                Collections.sort(sortList, (Order o1, Order o2) -> o1.getOrderDate().compareTo(o2.getOrderDate()));
                break;
            case 3:
                Collections.sort(sortList, (Order o1, Order o2) -> o1.getCustomerName().compareTo(o2.getCustomerName()));
                break;
            case 4:
                Collections.sort(sortList, (Order o1, Order o2) -> (int) (o1.getFlowerCost() - o2.getFlowerCost()));
                break;
        }
        System.out.printf("SORT ORDER:\n1. ASCENDING\n2. DESCENDING\n");
        int order = InputFormatter.getInt("Enter your choice: ", "Choose between 1 and 2, please!", 1, 2);
        if (order == 2) {
            Collections.reverse(sortList);
        }
        return sortList;
    }

    /**
     * Displays the list of orders.
     *
     * @param list The list of orders to display.
     */
    public void displayOrder(List<Order> list) {
        if (!list.isEmpty()) {
            int count = 0;
            double total = 0;
            System.out.println("_______________________________________________________________________________________");
            System.out.println("|  No. | Order ID  |  Order Date  |    Customer's Name   | Flower Count | Order Total |");
            System.out.println("---------------------------------------------------------------------------------------");
            int no = 1;
            for (Order o : list) {
                System.out.printf("|%6d", no++);
                System.out.println(o);
                count += o.getQuantity();
                total += o.getFlowerCost();

            }
            System.out.println("________________________________________________________________________________________");
            System.out.printf("|      | Total     |              |                      | %12d | %11.3f |\n", count, total);
            System.out.println("________________________________________________________________________________________");
        } else {
            System.out.println("Order's list empty!");
        }
    }

    /**
     * Saves the order list to a file.
     *
     * @param fileName The name of the file to save the order list to.
     */
    public void saveData(String fileName) {
        if (FileIO.saveData(new ArrayList(this), fileName)) {
            System.out.println("Order's list has been saved!");
            setIsSaved(true);
        } else {
            System.out.println("Failure!");
        }
    }

    /**
     * Loads the order list from a file.
     *
     * @param fileName The name of the file to load the order list from.
     */
    public void loadData(String fileName) {
        List<Order> list = FileIO.loadData(fileName);
        if (!list.isEmpty()) {
            if (!this.isEmpty()) {
                this.clear();
            }
            this.addAll(list);
            System.out.println("Order's list has been loaded!");
        } else {
            System.out.println("Empty file!");
        }
    }
}
