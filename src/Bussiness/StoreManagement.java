package Bussiness;

import Model.Flower;
import Tools.InputFormatter;
import java.text.ParseException;
import java.util.List;

/**
 * The StoreManagement class handles operations related to managing flowers and orders.
 *
 * @author Nguyen Truong Tho
 */
public class StoreManagement {

    private FlowerManagement flowerSet = new FlowerManagement();
    private OrderManagement orderSet = new OrderManagement();
    private final String flowerFormat = "Flower Id must be in FXXX (with X is digit) format!";
    private final String flowerRegex = "^[fF][0-9]{3,3}$";
    private final String flowerFile = "src\\file\\flowers.dat";
    private final String orderFile = "src\\file\\orders.dat";

    /**
     * Adds a flower to the collection.
     *
     * @throws Exception If an error occurs during the flower addition process.
     */
    public void addFlower() throws Exception {
        System.out.println("==========ADD FLOWER==========");
        flowerSet.addFlower();
        if (InputFormatter.getBoolean("Do you want to add more flower? (Y/N): ")) {
            addFlower();
        }
    }

    /**
     * Finds a flower in the collection.
     *
     * @throws Exception If an error occurs during the flower finding process.
     */
    public void findFlower() throws Exception {
        System.out.println("==========FIND FLOWER==========");
        flowerSet.findFlower();
    }

    /**
     * Updates a flower in the collection.
     *
     * @throws Exception If an error occurs during the flower updating process.
     */
    public void updateFlower() throws Exception {
        System.out.println("==========UPDATE FLOWER==========");
        String name = InputFormatter.getString("Enter Name: ");
        List<Flower> list = flowerSet.findByName(name);
        if (list.isEmpty()) {
            System.out.println("The flower does not exist");
        } else {
            flowerSet.displayFlower(list);
            int index = 1;
            if (list.size() > 1) {
                index = InputFormatter.getInt("Choose flower you want to update (Enter 0 to exit): ",
                        String.format("Your choice must be from 1 to %d", list.size()), 0, list.size());
            }
            if (index != 0) {
                flowerSet.updateFlower(list.get(index - 1));
            }
        }
    }

    /**
     * Deletes a flower from the collection.
     */
    public void deleteFlower() {
        System.out.println("==========DELETE FLOWER==========");
        String flowerId = InputFormatter.getString("Enter Flower's Id (FXXX): ", flowerFormat, flowerRegex).toUpperCase();
        if (flowerSet.isExist(flowerId)) {
            if (!orderSet.isInOrder(flowerSet.getFlowerById(flowerId))) {
                if (InputFormatter.getBoolean("Do you want to delete this flower? (Y/N): ")) {
                    flowerSet.removeFlower(flowerSet.getFlowerById(flowerId));
                    System.out.println("Delete success!");
                } else {
                    System.out.println("Delete failure!");
                }
            } else {
                System.out.println("Cannot be deleted because this flower is being ordered!");
            }
        } else {
            System.out.println("The flower does not exist!");
        }
    }

    /**
     * Adds an order to the collection.
     *
     * @throws Exception If an error occurs during the order addition process.
     */
    public void addOrder() throws Exception {
        System.out.println("==========ADD ORDER==========");
        if (!flowerSet.isEmpty()) {
            orderSet.addOrder(flowerSet);
            if (InputFormatter.getBoolean("Do you want to add more order? (Y/N): ")) {
                addOrder();
            }
        } else {
            System.out.println("There is no flower in store!");
        }
    }

    /**
     * Displays the orders in the collection.
     *
     * @throws ParseException If an error occurs during the order display
     * process.
     */
    public void displayOrder() throws ParseException {
        System.out.println("==========DISPLAY ORDER==========");
        orderSet.displayOrder(orderSet.showOrder());
        InputFormatter.pressEnterKey();
    }

    /**
     * Sorts the order list based on a chosen field.
     *
     * @throws Exception If an error occurs during the sorting process.
     */
    public void sortOrderList() throws Exception {
        System.out.println("==========SORT ORDER==========");
        System.out.printf("SORT BY:\n1. ORDER ID\n2. ORDER DATE\n3. CUSTOMER NAME\n4. ORDER TOTAL\n5. BACK TO MENU\n");
        int field = InputFormatter.getInt("Enter your choice: ", "Choose from 1 to 4, please!", 1, 5);
        if (field != 5) {
            orderSet.displayOrder(orderSet.sortByField(field));
            InputFormatter.pressEnterKey();
        }
    }

    /**
     * Saves the flower and order data to files.
     */
    public void saveData() {
        System.out.println("==========SAVE DATA==========");
        flowerSet.saveData(flowerFile);
        orderSet.saveData(orderFile);
    }

    /**
     * Loads flower and order data from files.
     */
    public void loadData() {
        System.out.println("==========LOAD DATA==========");
        flowerSet.loadData(flowerFile);
        orderSet.loadData(orderFile);
    }

    /**
     * Quits the program.
     */
    public void quitProgram() {
        System.out.println("==========QUIT PROGRAM==========");
        if (InputFormatter.getBoolean("Do you want to quit? (Y/N): ")) {
            if (!flowerSet.isIsSaved() && !orderSet.isIsSaved()) {
                if (InputFormatter.getBoolean("Data has been changed! Do you to save before exit? (Y/N): ")) {
                    saveData();
                }
            }
            System.out.println("Goodbye, have a nice day!");
            System.exit(0);
        } else {
            System.out.println("Continue...");
        }
    }
}
