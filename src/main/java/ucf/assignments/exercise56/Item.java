package ucf.assignments.exercise56;

import javafx.beans.property.SimpleStringProperty;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;

public class Item {
    private SimpleStringProperty serialNumber;
    private SimpleStringProperty name;
    private BigDecimal value;

    public Item(String serialNumber, String name, BigDecimal value) {
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.name = new SimpleStringProperty(name);
        this.value = value;
    }

    public static boolean isValidSerialNumber(String serial) {
        if (serial.length() < 1 || serial.length() > 256) {
            return false;
        }
        for (int i = 0; i < serial.length(); i++) {
            if (!Character.isLetterOrDigit(serial.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidName(String name) {
        return name.length() > 0 && name.length() <= 256;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = new SimpleStringProperty(serialNumber);
    }

    public String getValue() {
        return value.toPlainString();
    }

    public void setValue(String value) {
        this.value = new BigDecimalStringConverter().fromString(value);
    }
}
