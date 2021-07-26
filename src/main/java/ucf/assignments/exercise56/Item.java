/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
 */
package ucf.assignments.exercise56;

import javafx.beans.property.SimpleStringProperty;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;

public class Item {
    private String serialNumber;
    private String name;
    private BigDecimal value;

    public Item(String serialNumber, String name, BigDecimal value) {
        this.serialNumber = serialNumber;
        this.name = name;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getValue() {
        return value.toPlainString();
    }

    public void setValue(String value) {
        this.value = new BigDecimalStringConverter().fromString(value);
    }
}
