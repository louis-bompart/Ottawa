package elasticsearch.Controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.elasticsearch.client.Client;

/**
 * Created by louis on 07/12/2015.
 */
public class Home {

    private org.elasticsearch.node.Node node;
    @FXML
    public void doRequest(){

        Stage stage = new Stage();
        elasticsearch.View.ElasticsearchRequest elasticRequest = new elasticsearch.View.ElasticsearchRequest();
        try {
            elasticRequest.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void doImport(){

        Stage stage = new Stage();
        elasticsearch.View.DataImporter dataImporter = new elasticsearch.View.DataImporter();
        try {
            dataImporter.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
