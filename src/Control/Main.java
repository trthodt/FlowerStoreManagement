package Control;

import Tools.Menu;
import Bussiness.StoreManagement;
import java.text.ParseException;

/**
 *
 * @author Nguyen Truong Tho
 */
public class Main {
    public static void main(String[] args) throws ParseException, Exception {
        String[] options = {"1. Add a flower.","2. Find a flower.","3. Update a flower.",
            "4. Delete a flower.","5. Add an order.","6. Display orders.","7. Sort orders.",
            "8. Save data.","9. Load data.","10. Quit."};
        int choice = 0;
        StoreManagement store = new StoreManagement();
        do {
            choice = Menu.getChoice(options);
            switch (choice){
                case 1://Create a flower 
                    store.addFlower();
                    break;
                case 2://Find a flower
                    store.findFlower();
                    break;
                case 3://Update a flower
                    store.updateFlower();
                    break;
                case 4://Delete a flower
                    store.deleteFlower();
                    break;
                case 5://Add a order
                    store.addOrder();
                    break;
                case 6://Display orders
                    store.displayOrder();
                    break;
                case 7://Sort orders
                    store.sortOrderList();
                    break;
                case 8://save data
                    store.saveData();
                    break;
                case 9://Load data
                    store.loadData();
                    break;
                case 10://Quit
                    store.quitProgram();
                    break;
            }
          
        } while (choice>0&&choice<options.length+1);
    }
}
