package ucf.assignments.exercise56;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        iModel.addItem(item);
        itemsTableView.setItems(iModel.items);
    }

    void saveAsButtonClicked(ActionEvent event) {
        /*

        filename = FileManager.getName();
        filetype = FileManager.getType();

        switch (filetype) {
            case CSV:
                saveAsVSC(filename);
                break;
            case JSON:
                saveAsJSON(filename);
                break;
        }
         */
    }

    public void saveAsCSV(String filename) {
        /*
        open up filename
        for each item in the item model
            write the item to file as sn \t name \t price
         close file
         */
    }

    public void saveAsJSON(String filename) {
        Exporter exporter = new Exporter("TestIO");
    }

    public void saveAsHTML(String filename) {

        /*
        open up filename
        for each item in the item model
            write the item to file
        close file
         */
    }

    public void initialize(URL url, ResourceBundle rb) {
        itemsSerialNumberColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("SerialNumber"));
        itemsNameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("Name"));
        itemsValueColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("Value"));

        itemsSerialNumberColumn.setCellFactory(TextFieldTableCell.<Item>forTableColumn());
        itemsSerialNumberColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, String> event) {
                        /* TODO check if serial number is valid */
                        ((Item) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setSerialNumber(event.getNewValue());
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

        itemsTableView.setItems(dummy());
    }

    private ObservableList<Item> dummy() {
        ObservableList<Item> ret = FXCollections.observableArrayList();
        ret.add(new Item("123", "123", new BigDecimal(123.123)));
        return ret;
    }

    public void searchItemButtonClicked(ActionEvent actionEvent) {

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
        JOptionPane.showMessageDialog(null, deleteItemSerialNumberTextField.getText());
    }
}