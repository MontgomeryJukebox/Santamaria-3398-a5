package ucf.assignments.exercise56;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public class ItemModel {
    public ObservableList<Item> items;

    Exporter exporter;
    Importer importer;

    public ItemModel() {
        items = FXCollections.observableArrayList();
        exporter = new Exporter("TestIO");
        importer = new Importer("TestIO");
    }

    public void addItem(Item item) {
        assert(items != null);
        assert(item != null);
        items.add(item);
    }

    public void importJSON(String filename) {
        try {
            items = importer.importJSON(filename);
        } catch (IOException ioe) {

        }
    }

    public void importCSV(String filename) {
        try {
            items = importer.importCSV(filename);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
