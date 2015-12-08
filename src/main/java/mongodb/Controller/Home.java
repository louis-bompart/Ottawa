package mongodb.Controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by louis on 07/12/2015.
 */
public class Home {

    @FXML
    public void doRequest(){

        Stage stage = new Stage();
        mongodb.View.MongoRequest mongoRequest = new mongodb.View.MongoRequest();
        try {
            mongoRequest.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void doImport(){

        Stage stage = new Stage();
        mongodb.View.DataImporter dataImporter = new mongodb.View.DataImporter();
        try {
            dataImporter.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void doSelect(){

        Stage stage = new Stage();
        mongodb.View.ChangeCollection changeCollection = new mongodb.View.ChangeCollection();
        try {
            changeCollection.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
