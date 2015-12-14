package org.Ottawa.mongodb.controller;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.Ottawa.mongodb.model.SharedVariables;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DataImporter {
    private final FileChooser fileChooser = new FileChooser();
    private final SharedVariables sharedVariables = new SharedVariables();
    private MongoCollection<Document> mongoCollection;
    @FXML
    private javafx.scene.control.TextField filePath;
    @FXML
    private javafx.scene.control.TextField database;
    @FXML
    private javafx.scene.control.TextField collection;


    /**
     * Handle import button
     */
    @FXML
    public void ImportData() {
        if(checkData()) {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase(database.getText());
            SharedVariables.mongoDatabase = mongoDatabase;
            mongoCollection = mongoDatabase.getCollection(collection.getText());
            long nbofDocs = mongoCollection.count();
                readFile(filePath.getText());
                nbofDocs = mongoCollection.count()-nbofDocs;
                SharedVariables.mongoCollection = mongoCollection;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Data imported");
                alert.setHeaderText("Data successfully imported !");
                String string = "";
                string += nbofDocs;
                string += " documents imported";
                alert.setContentText(string);
                alert.showAndWait();
                ((Stage)filePath.getScene().getWindow()).close();
        }
    }

    /**
     * @return check if the field aren't empty.
     */
    private boolean checkData() {
        return (!filePath.getText().isEmpty() && !collection.getText().isEmpty() && !database.getText().isEmpty());
    }

    /**
     * Handle fileChooser button
     */
    @FXML
    private void openFileChooser() {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("json", "*.json"));
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            filePath.setText(file.getAbsolutePath());
        }
    }



    /**
     * @param fileName : Path to the JSON source file.
     * @throws IOException : exception excepted if the file doesn't exist.
     */
    private void readFile(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(this::importData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param string : single JSON document.
     */
    private void importData(String string) {
        Document document = Document.parse(string);
        mongoCollection.insertOne(document);
    }
}
