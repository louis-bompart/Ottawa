package mongodb.Controller;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import mongodb.model.SharedVariables;
import org.bson.Document;

public class ChangeCollection {
    private final SharedVariables sharedVariables = new SharedVariables();
    @FXML
    private javafx.scene.control.TextField database;
    @FXML
    private javafx.scene.control.TextField collection;


    /**
     * Handle import button
     */
    @FXML
    public void changeCollection() {
        if(checkData()) {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase(database.getText());
            SharedVariables.mongoCollection = mongoDatabase.getCollection(collection.getText());
            ((Stage)database.getScene().getWindow()).close();
        }
    }

    /**
     * @return check if the field aren't empty.
     */
    private boolean checkData() {
        return (!collection.getText().isEmpty() && !database.getText().isEmpty());
    }
}
