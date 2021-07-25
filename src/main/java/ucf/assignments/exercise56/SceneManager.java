package ucf.assignments.exercise56;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    Map<String, Scene> scenes = new HashMap<>();

    void load() {
        ItemModel listModel = new ItemModel();

        SearchItemWindowController search = new SearchItemWindowController();

        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchItem.fxml"));
        loader.setController(search);

        try {
            root = loader.load();
            scenes.put("SearchItem", new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene(String name) {
        return scenes.get(name);
    }
}
