package ucf.assignments.exercise56;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void isValidSerialNumber() {
        assert(!Item.isValidSerialNumber("!@#"));
        assert(!Item.isValidSerialNumber(""));
        assert(Item.isValidSerialNumber("abc"));
    }

    @Test
    void isValidName() {
        assert(!Item.isValidName("1"));
        assert(Item.isValidName("hello"));
    }

    @Test
    void getName() {
        Item i = new Item("hello", "abc", new BigDecimal(213));
        i.setName("hello");
        assert(i.getName().equals("hello"));
    }

    @Test
    void setName() {
        Item i = new Item("world", "def", new BigDecimal(321));
        i.setName("hello123");
        assert(i.getName().equals("hello123"));
    }

    @Test
    void getSerialNumber() {
        Item i = new Item("hello", "abc", new BigDecimal(213));
        i.setSerialNumber("abc");
        assert(i.getSerialNumber().equals("abc"));
    }

    @Test
    void setSerialNumber() {
        Item i = new Item("hello", "abc", new BigDecimal(213));
        i.setSerialNumber("def");
        assert(i.getSerialNumber().equals("def"));
    }

    @Test
    void getValue() {
        Item i = new Item("hello", "abc", new BigDecimal(213));
        i.setValue("123");
        assert(i.getValue().equals("123"));
    }

    @Test
    void setValue() {
        Item i = new Item("hello", "abc", new BigDecimal(213));
        i.setValue("321");
        assert(i.getValue().equals("321"));
    }
}