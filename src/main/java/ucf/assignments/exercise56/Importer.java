/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 David Santamaria
 */

package ucf.assignments.exercise56;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.BigDecimalStringConverter;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* Our importer for handling TODO list imports from files */
public class Importer {
    private Gson gson;
    private Reader reader;
    private String rootDir = "";

    public Importer(String saveDirectory) {
        gson = new GsonBuilder().setPrettyPrinting().create();
        rootDir = saveDirectory;
    }

    public ObservableList<Item> importJSON(String filepath) throws IOException {
        ConcreteItemModel concrete = new ConcreteItemModel();
        String fullPath = rootDir + "/" + filepath;
        reader = Files.newBufferedReader(Paths.get(fullPath));
        assert(reader != null);
        concrete = gson.fromJson(reader, ConcreteItemModel.class);
        return concrete.toObservableList();
    }

    public ObservableList<Item> importCSV(String filename) throws IOException {
        ObservableList<Item> ret = FXCollections.observableArrayList();
        Reader reader = Files.newBufferedReader(Paths.get(rootDir + "/" + filename));
        Scanner in = new Scanner(reader);
        try {
            while (in.hasNextLine()) {
                String sn = in.next();
                String name = in.next();
                String value = in.next();
                Item i = new Item(sn, name, new BigDecimalStringConverter().fromString(value));
                ret.add(i);
            }
        } catch (NoSuchElementException e) {
            // do nothing
        }
        return ret;
    }

    public ObservableList<Item> importHTML(String filename) throws IOException {
        ObservableList<Item> ret = FXCollections.observableArrayList();
        // TODO
        return ret;
    }
}
