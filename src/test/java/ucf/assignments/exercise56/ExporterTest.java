package ucf.assignments.exercise56;

import javafx.collections.ObservableList;
import javafx.util.converter.BigDecimalStringConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExporterTest {

    @Test
    void exportJSON() {
        ItemModel model = new ItemModel();
        model.addItem(new Item("abc", "123", new BigDecimalStringConverter().fromString("123")));
        model.addItem(new Item("hello", "123", new BigDecimalStringConverter().fromString("123")));
        model.addItem(new Item("world", "123", new BigDecimalStringConverter().fromString("123")));
        model.addItem(new Item("test", "123", new BigDecimalStringConverter().fromString("123")));
        model.exportJSON("test");
        ObservableList<Item> tmp = model.items;
        model.importJSON("test.json");
        assert(model.items.get(0).getSerialNumber().equals("abc"));
    }

    @Test
    void exportCSV() {
        ItemModel model = new ItemModel();
        model.addItem(new Item("abc", "123", new BigDecimalStringConverter().fromString("123")));
        model.addItem(new Item("hello", "123", new BigDecimalStringConverter().fromString("123")));
        model.addItem(new Item("world", "123", new BigDecimalStringConverter().fromString("123")));
        model.addItem(new Item("test", "123", new BigDecimalStringConverter().fromString("123")));
        model.exportCSV("test");
        ObservableList<Item> tmp = model.items;
        model.importCSV("test.json");
        assert(model.items.get(0).getSerialNumber().equals("abc"));
    }

    @Test
    void exportHTML() {
    }
}