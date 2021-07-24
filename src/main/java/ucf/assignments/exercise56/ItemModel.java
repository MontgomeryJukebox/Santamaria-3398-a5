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

    public ItemModel(ConcreteItemModel concreteModel) {
        this();
        for (Item i : concreteModel.items) {
            items.add(i);
        }
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
            ioe.printStackTrace();
        }
    }

    public void importCSV(String filename) {
        try {
            items = importer.importCSV(filename);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void importHTML(String filename) {
        try {
            items = importer.importHTML(filename);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void exportJSON(String filename) {
        try {
            exporter.exportJSON(this, filename + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportCSV(String filename) {
        try {
            exporter.exportCSV(this, filename + ".txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportHTML(String filename) {
        try {
            exporter.exportHTML(this, filename + ".html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean uniqueSerialNumber(String serialNumber) {
        for (Item i : items) {
            if (serialNumber.equals(i.getSerialNumber())) {
                return false;
            }
        }
        return true;
    }
}
