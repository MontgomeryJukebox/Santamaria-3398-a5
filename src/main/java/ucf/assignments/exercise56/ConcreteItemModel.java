package ucf.assignments.exercise56;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ConcreteItemModel {
    ArrayList<Item> items;

    public ConcreteItemModel(ItemModel model) {
        this();
        for (Item i : model.items) {
            items.add(i);
        }
    }

    public ConcreteItemModel() {
        items = new ArrayList<Item>();
    }

    public ObservableList<Item> toObservableList() {
        ObservableList<Item> ret = FXCollections.observableArrayList();
        for (Item i : items) {
            ret.add(i);
        }
        return ret;
    }
}
