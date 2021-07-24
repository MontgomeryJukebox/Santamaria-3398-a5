package ucf.assignments.exercise56;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.BigDecimalStringConverter;

import javax.swing.*;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainWindowController implements Initializable {
    ItemModel iModel;

    public MainWindowController() {
        iModel = new ItemModel();
    }

    @FXML
    private TableView<Item> itemsTableView;

    @FXML
    private TableColumn<Item, String> itemsSerialNumberColumn;

    @FXML
    private TableColumn<Item, String> itemsNameColumn;

    @FXML
    private TableColumn<Item, String> itemsValueColumn;

    @FXML
    private TextField itemSerialNumberTextField;

    @FXML
    private TextField itemsNameTextField;

    @FXML
    private TextField itemsPriceTextField;

    @FXML
    private TextField searchField;

    @FXML
    private TextField deleteItemSerialNumberTextField;

    @FXML
    private MenuItem saveAsMenuItem;

    @FXML
    void addNewItemButtonClicked(ActionEvent event) {
        String serial, name, price;
        serial = itemSerialNumberTextField.getText();

        if (!Item.isValidSerialNumber(serial)) {
            JOptionPane.showMessageDialog(null, "Invalid Serial Number for item entry - a serial number must be between 1 and 256 characteres (inclusive), and be in the format XXXXXXXXXX where X is a letter or digit");
            return;
        }

        name = itemsNameTextField.getText();

        if (!Item.isValidName(name)) {
            JOptionPane.showMessageDialog(null, "Invalid Name for item entry - a name must be between 2 and 256 characters in length (inclusive)");
            return;
        }

        price = itemsPriceTextField.getText();
        BigDecimalStringConverter converter = new BigDecimalStringConverter();
        BigDecimal itemPrice = converter.fromString(price);
        addNewItem(serial, name, itemPrice);
    }

    public void addNewItem(String sn, String name, BigDecimal price) {
        Item item = new Item(sn, name, price);
        if (!iModel.uniqueSerialNumber(sn)) {
            JOptionPane.showMessageDialog(null, "An item already exists with that serial number.");
            return;
        }
        iModel.addItem(item);
        itemsTableView.setItems(iModel.items);
    }

    @FXML
    void saveAsButtonClicked(ActionEvent event) {
        String filename = JOptionPane.showInputDialog(null, "Please provide the path for the file (relative paths will be with respect to the TestIO directory)");
        String type = JOptionPane.showInputDialog(null, "Save as json, csv, or html? (cas insensitive)");

        if (filename == null || type == null) {
            return;
        }

        if (type.equalsIgnoreCase("json")) {
            saveAsJSON(filename);
        }
        if (type.equalsIgnoreCase("csv")) {
            saveAsCSV(filename);
        }
        if (type.equalsIgnoreCase("html")) {
            saveAsHTML(filename);
        }
    }

    public void saveAsCSV(String filename) {
        iModel.exportCSV(filename);
    }

    public void saveAsJSON(String filename) {
        iModel.exportJSON(filename);
    }

    public void saveAsHTML(String filename) {
        iModel.exportHTML(filename);
    }

    public void initialize(URL url, ResourceBundle rb) {
        itemsSerialNumberColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("SerialNumber"));
        itemsNameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("Name"));
        itemsValueColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("Value"));

        itemsTableView.setEditable(true);
        itemsSerialNumberColumn.setEditable(true);
        itemsNameColumn.setEditable(true);
        itemsValueColumn.setEditable(true);

        itemsSerialNumberColumn.setCellFactory(TextFieldTableCell.<Item>forTableColumn());
        itemsSerialNumberColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, String> event) {
                        String serialNumber = event.getNewValue();
                        if (!Item.isValidSerialNumber(serialNumber)) {
                            JOptionPane.showMessageDialog(null, "Invalid Serial Number");
                        }
                        if (!iModel.uniqueSerialNumber(serialNumber)) {
                            JOptionPane.showMessageDialog(null, "There is already an item with that serial number");
                            itemsTableView.setItems(iModel.items);
                            return;
                        }
                        ((Item) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setSerialNumber(serialNumber);
                    }
                }
        );

        itemsNameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, String> event) {
                        ((Item) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setName(event.getNewValue());
                    }
                }
        );

        itemsValueColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, String> event) {
                        ((Item) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setValue(event.getNewValue());
                    }
                }
        );
    }

    public void searchItemFieldTyped(KeyEvent keyEvent) {
        if (searchField.getText().length() == 0) {
            itemsTableView.setItems(iModel.items);
        }
        Pattern filter = Pattern.compile(searchField.getText(), Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        ObservableList<Item> tmp = FXCollections.observableArrayList();
        for (Item i : iModel.items) {
            matcher = filter.matcher(i.getName());
            if (matcher.find()) {
                tmp.add(i);
                continue;
            }
            matcher = filter.matcher(i.getSerialNumber());
            if (matcher.find()) {
                tmp.add(i);
            }
        }
        itemsTableView.setItems(tmp);
    }

    public void deleteItemButtonClicked(ActionEvent actionEvent) {
        String serialNumber = deleteItemSerialNumberTextField.getText();
        if (serialNumber == null) {
            JOptionPane.showMessageDialog(null, "Invalid serial number");
        }
        boolean found = false;
        for (Item i : iModel.items) {
            if (i.getSerialNumber().equals(serialNumber)) {
                found = true;
                iModel.items.remove(i);
                itemsTableView.setItems(iModel.items);
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "No item was found with that serial number");
        }
    }

    public void loadMenuItemClicked(ActionEvent actionEvent) {
        String filename = JOptionPane.showInputDialog(null, "Please enter the name of the file to import from: ");
        if (filename == null) {
            JOptionPane.showMessageDialog(null, "Invalid filename given");
        }

        if (!Files.exists(Path.of("TestIO/" + filename))) {
            JOptionPane.showMessageDialog(null, "Could not find file " + filename);
        }
        String extension = filename.substring(filename.indexOf('.') + 1);
        System.out.println("extension is: " + extension);
        if (extension.equalsIgnoreCase("json")) {
            iModel.importJSON(filename);
            itemsTableView.setItems(iModel.items);
        }
        if (extension.equalsIgnoreCase("txt")) {
            iModel.importCSV(filename);
            itemsTableView.setItems(iModel.items);
        }
        if (extension.equalsIgnoreCase("html")) {
            iModel.importHTML(filename);
            itemsTableView.setItems(iModel.items);
        }
    }
}