package elasticsearch.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.bson.Document;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by juhel on 09/12/2015.
 */
public class Dataimporter {

    private Node node;
    private Client client;
    private final FileChooser fileChooser = new FileChooser();
    private final DirectoryChooser elasticChooser = new DirectoryChooser();
    @FXML
    private javafx.scene.control.TextField filePath;
    @FXML
    private javafx.scene.control.TextField database;
    @FXML
    private javafx.scene.control.TextField collection;
    @FXML
    private javafx.scene.control.TextField elasticPath;


    /**
     * Handle import button
     */
    @FXML
    public void ImportData() {
        if(checkData()) {
            Settings settings = Settings.settingsBuilder()
                    .put("cluster.name", database.getText())
                    .put("path.home",elasticPath.getText()).build();
            node = nodeBuilder().settings(settings).node();
            client = node.client();
            readFile(filePath.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            CountResponse response = client.prepareCount("tp").execute().actionGet();
            alert.setTitle("Data imported");
            alert.setHeaderText("Data successfully imported !");
            String string = "";
            string += response.toString();
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
        return (!filePath.getText().isEmpty() && !collection.getText().isEmpty() && !database.getText().isEmpty() && !elasticPath.getText().isEmpty());
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
     *  Handle elasticChooser button
     */
    @FXML
    private void openElasticChooser() {
        Stage stage = new Stage();
        File directory = elasticChooser.showDialog(stage);
        if (directory != null) {
            elasticPath.setText(directory.getAbsolutePath());
        }
    }

    /**
     * @param fileName : Path to the JSON source file.
     * @throws IOException : exception excepted if the file doesn't exist.
     */

    private void readFile(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(new Consumer<String>() {
                String first="";
                boolean doImport = false;
                @Override
                public void accept(String s) {

                    if(doImport)
                    {
                        importData(first, s);
                    }
                    else
                    {
                        first =s;
                    }
                    doImport= !doImport;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param header : Index, type and id of the following JSON document.
     * @param body : body of the JSON document.
     */
    private void importData(String header, String body) {
        Document document = Document.parse(header);
        document = (Document) document.get("index");
        IndexRequest indexRequest =new IndexRequest("tp","ottawa",String.valueOf(document.get("_id")));
        indexRequest.source(body);
        client.index(indexRequest);

    }


}
