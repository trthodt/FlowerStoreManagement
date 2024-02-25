package Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for saving and loading data from a file.
 * @author Nguyen Truong Tho
 */
public class FileIO {

    /**
     * This method saves data to a file.
     *
     * @param list The list of objects to save.
     * @param fileName The name of the file.
     * @return True if the data is saved successfully, false otherwise.
     */
    public static boolean saveData(List list, String fileName) {
        boolean check = false;
        if (!list.isEmpty()) {
            try {
                FileOutputStream f = new FileOutputStream(fileName);
                ObjectOutputStream fo = new ObjectOutputStream(f);
                for (Object obj : list) {
                    fo.writeObject(obj);
                }
                fo.writeObject(new EndOfFile());
                f.close();
                fo.close();
                check = true;
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } else {
            System.out.println("Empty List");
        }
        return check;
    }

    /**
     * This method loads data from a file.
     *
     * @param fileName The name of the file.
     * @return A list of objects loaded from the file.
     */
    public static List loadData(String fileName) {
        List list = new ArrayList();
        try {
            File f = new File(fileName);
            if (f.exists()) {
                FileInputStream fi = new FileInputStream(f);
                ObjectInputStream oj = new ObjectInputStream(fi);
                Object obj;
                while (!((obj = oj.readObject()) instanceof EndOfFile)) {
                    list.add(obj);
                }
                oj.close();
                fi.close();
            }

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return list;
    }
}

/**
 * This class is used to indicate the end of a file when saving data.
 */
class EndOfFile implements Serializable {

}
