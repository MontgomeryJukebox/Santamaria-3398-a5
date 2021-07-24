/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 David Santamaria
 */

package ucf.assignments.exercise56;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

/* Our exporter class */
public class Exporter {
    // will need an internal gson
    private Gson gson;
    // some writer for exporting
    private Writer writer;
    // and a root directory
    private String rootDir = "";


    public Exporter(String saveDir) {
        gson = new GsonBuilder().setPrettyPrinting().create();
        rootDir = saveDir;
    }

    // and some export list to json method
    void exportJSON(ItemModel model, String title) throws Exception {
        // we'll make sure we have the full path
        String fullPath = rootDir + "/" + title;

        ConcreteItemModel concrete = new ConcreteItemModel(model);
        try {
            // we'll make a new writer,
            writer = Files.newBufferedWriter(Paths.get(fullPath));
            // export to json
            gson.toJson(concrete, writer);
            // and make sure we flush before we're done with everything
            writer.flush();
            writer.close();
        } catch (IOException ioe) {
            System.out.println("ExportToJSON() log:");
            ioe.printStackTrace();
            throw new Exception(); // TODO
        }
    }

    void exportCSV(ItemModel model, String filename) throws Exception {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(rootDir + "/" + filename));
        for (Item i : model.items) {
            writer.write(i.getSerialNumber() + "\t" + i.getName() + "\t" + i.getValue() + "\n");
        }
        writer.flush();
    }

    void exportHTML(ItemModel model, String filename) throws Exception {

    }
}
