package ucf.assignments.exercise56;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.BigDecimalStringConverter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteItemModelTest {

    @Test
    void toObservableList() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(new Item("123", "abc", new BigDecimalStringConverter().fromString("123")));
        items.add(new Item("234", "bcd", new BigDecimalStringConverter().fromString("123")));
        ItemModel model = new ItemModel();
        model.items = items;
        for (Item i : new ConcreteItemModel(model).toObservableList()) {
            assert(items.contains(i));
        }
    }
}