package Bussiness;

import Model.Flower;
import Tools.FileIO;
import Tools.InputFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * This class represents a collection of Flower objects.
 * @author Nguyen Truong Tho
 */
public class FlowerManagement extends HashSet<Flower> {

    private final String flowerFormat = "Flower Id must be in FXXX (with X is digit) format!";
    private final String flowerRegex = "^[fF][0-9]{3,3}$";
    private boolean isSaved = true;

    /**
     * Returns the value indicating whether the data has been saved.
     *
     * @return True if the data has been saved, false otherwise.
     */
    public boolean isIsSaved() {
        return isSaved;
    }

    /**
     * Sets the value indicating whether the data has been saved.
     *
     * @param isSaved True if the data has been saved, false otherwise.
     */
    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    /**
     * Retrieves the flower with the specified flower ID.
     *
     * @param flowerId The flower ID to search for.
     * @return The Flower object with the specified ID, or null if not found.
     */
    public Flower getFlowerById(String flowerId) {
        for (Flower f : this) {
            if (f.getFlowerId().equalsIgnoreCase(flowerId)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Checks if a flower with the specified ID exists in the collection.
     *
     * @param flowerId The flower ID to check.
     * @return True if the flower with the specified ID exists, false otherwise.
     */
    public boolean isExist(String flowerId) {
        return this.contains(getFlowerById(flowerId));
    }

    /**
     * Retrieves a flower ID that already exists in the collection.
     *
     * @return The flower ID that already exists.
     */
    public String getFlowerIdExist() {
        boolean flag = true;
        String flowerId = null;
        while (flag) {
            flowerId = InputFormatter.getString("Enter Flower's Id (FXXX): ", flowerFormat, flowerRegex).toUpperCase();
            if (isExist(flowerId)) {
                flag = false;
            } else {
                System.out.println("The flower does not exist!");
            }
        }
        return flowerId;
    }

    /**
     * Adds a new flower to the collection.
     *
     * @throws ParseException If there is an error parsing the input.
     * @throws Exception If there is an exception during the execution of the
     * method.
     */
    public void addFlower() throws ParseException, Exception {
        String flowerId = InputFormatter.getString("Enter Flower's Id (FXXX): ", flowerFormat, flowerRegex).toUpperCase();
        if (!isExist(flowerId)) {
            String name = InputFormatter.getString("Enter Flower's Name: ", "The length of the description field must be from 3 to 50 characters.", "^[a-zA-Z0-9 ]{3,50}$");
            Date date = InputFormatter.getDate("Enter Import Date: ");
            double unitPrice = InputFormatter.getDouble("Enter Unit Price: ", "The unit price field must be a positive number", 0, Double.MAX_VALUE);
            String category = InputFormatter.getString("Enter Category: ");
            this.add(new Flower(flowerId, name, date, unitPrice, category));
            System.out.println(name + " has been added to store!");
            setIsSaved(false);
        } else {
            System.out.println("The flower already exist!");
        }
    }

    /**
     * Allows the user to find a flower based on the specified criteria.
     *
     * @throws Exception If there is an exception during the execution of the
     * method.
     */
    public void findFlower() throws Exception {
        int choice = InputFormatter.getInt("FIND BY:\n1. NAME\n2. ID\n3. BACK TO MENU\nYOUR CHOICE: ", "Your choice must be 1 or 2", 1, 3);
        switch (choice) {
            case 1:
                String name = InputFormatter.getString("Enter Name: ");
                displayFlower(findByName(name));
                InputFormatter.pressEnterKey();
                break;
            case 2:
                String id = InputFormatter.getString("Enter Id (FXXX): ", flowerFormat, flowerRegex).toUpperCase();
                displayFlower(findById(id));
                InputFormatter.pressEnterKey();
                break;
            case 3:
                break;
        }
    }

    /**
     * Finds flowers with the specified flower ID.
     *
     * @param flowerId The flower ID to search for.
     * @return A list of flowers with the specified ID.
     */
    public List<Flower> findById(String flowerId) {
        List<Flower> list = new ArrayList<>();
        for (Flower f : this) {
            if (f.getFlowerId().equals(flowerId)) {
                list.add(f);
            }
        }
        return list;
    }

    /**
     * Finds flowers with the specified name.
     *
     * @param name The name to search for.
     * @return A list of flowers with the specified name.
     */
    public List<Flower> findByName(String name) {
        List<Flower> list = new ArrayList<>();
        for (Flower f : this) {
            if (f.getName().toLowerCase().contains(name.toLowerCase())) {
                list.add(f);
            }
        }
        return list;
    }

    /**
     * This method updates a flower.
     *
     * @param f The flower object.
     * @throws Exception If there is an error.
     */
    public void updateFlower(Flower f) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        String name = InputFormatter.getString("Enter Flower's Name: ");
        String date = InputFormatter.getString("Enter Import Date: ");
        String unitPrice = InputFormatter.getString("Enter Unit Price: ");
        String category = InputFormatter.getString("Enter Category: ");
        try {
            if (!name.isEmpty() && !name.matches("^[a-zA-Z0-9 ]{3,50}$")) {
                throw new Exception("The length of the Nsme field must be from 3 to 50 characters.");
            }
            if (!date.isEmpty() && !InputFormatter.isValidDate(date)) {
                throw new Exception("The import date field must be a valid date format.");
            }
            if (!unitPrice.isEmpty() && Double.parseDouble(unitPrice) < 0) {
                throw new Exception("The unit price field must be a positive number.");
            }
        } catch (Exception ex) {
            System.out.println("Wrong format, " + ex.getMessage());
            System.out.println("Failure!");
        } finally {
            if (!name.isEmpty()) {
                f.setName(name);
            }
            if (!date.isEmpty()) {
                f.setImportDate(sdf.parse(date));
            }
            if (!unitPrice.isEmpty()) {
                f.setUnitPrice(Double.parseDouble(unitPrice));
            }
            if (!category.isEmpty()) {
                f.setCategory(category);
            }
            System.out.println("Update success!");
            setIsSaved(false);
        }
    }

    /**
     * This method removes a flower from the list.
     *
     * @param f The flower object.
     */
    public void removeFlower(Flower f) {
        this.remove(f);
        this.setIsSaved(false);
    }

    /**
     * This method displays a list of flowers.
     *
     * @param list The list of flowers.
     */
    public void displayFlower(List<Flower> list) {
        if (!list.isEmpty()) {
            System.out.println("____________________________________________________________________________________");
            System.out.println("|  No. | Flower ID |    Flower's Name     |  Import Date |  Unit Price  | Category |");
            System.out.println("------------------------------------------------------------------------------------");
            int no = 1;
            for (Flower f : list) {
                System.out.printf("|%6d", no++);
                System.out.println(f);
            }
            System.out.println("____________________________________________________________________________________");
        } else {
            System.out.println("The flower does not exist!");
        }
    }

    /**
     * This method saves data to a file.
     *
     * @param fileName The name of the file.
     */
    public void saveData(String fileName) {
        if (FileIO.saveData(new ArrayList(this), fileName)) {
            System.out.println("Flower's list has been saved!");
            setIsSaved(true);
        } else {
            System.out.println("Failure!");
        }
    }

    /**
     * This method loads data from a file.
     *
     * @param fileName The name of the file.
     */
    public void loadData(String fileName) {
        List<Flower> list = FileIO.loadData(fileName);
        if (!list.isEmpty()) {
            if (!this.isEmpty()) {
                this.clear();
            }
            this.addAll(list);
            System.out.println("Flower's list has been loaded!");
        } else {
            System.out.println("Empty file!");
        }
    }

}
