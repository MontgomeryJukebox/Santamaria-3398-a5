package ucf.assignments.exercise56;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemModelTest {

    @Test
    void addItem() {
        ItemModel model = new ItemModel();
        Item i = new Item("hello", "abc", new BigDecimal(213));
        model.addItem(i);
        assert(model.items.get(0).equals(i));
    }

    @Test
    void uniqueSerialNumber() {
        ItemModel model = new ItemModel();
        Item i = new Item("hello", "abc", new BigDecimal(213));
        model.addItem(i);
        assert(!model.uniqueSerialNumber("hello"));
        assert(model.uniqueSerialNumber("goodbye"));
    }
}