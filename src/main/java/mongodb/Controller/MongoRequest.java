package mongodb.Controller;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import mongodb.model.SharedVariables;
import org.bson.Document;

import java.util.Objects;

/**
 * Created by louis on 04/12/2015.
 */
public class MongoRequest {

    private MongoCollection<Document> mongoCollection;
    @FXML
    private javafx.scene.control.ComboBox<String> comboBoxPrice;
    @FXML
    private javafx.scene.control.ComboBox<String> comboBoxDate;
    @FXML
    private javafx.scene.control.TextField contractor;
    @FXML
    private javafx.scene.control.TextField city;
    @FXML
    private javafx.scene.control.TextField price;
    @FXML
    private javafx.scene.control.DatePicker date;

    /**
     *
     */
    public void initialize() {
        ObservableList<String> stringObservableValue = FXCollections.observableArrayList(
                "=",
                "<",
                ">"
        );
        comboBoxPrice.setItems(stringObservableValue);
        comboBoxDate.setItems(stringObservableValue);
        mongoCollection = SharedVariables.mongoCollection;
    }

    public void doRequest()
    {
        Document request = new Document();
        if (!Objects.equals(price.getText(), "")) {
            switch (comboBoxDate.getSelectionModel().getSelectedItem()){
                case "=":
                    request.append("permits.COST_unit",Double.parseDouble(price.getText()));
                    break;
                case "<":
                    request.append("permits.COST_unit",new Document("$lt",Double.parseDouble(price.getText())));
                    break;
                case ">":
                    request.append("permits.COST_unit",new Document("$gt",Double.parseDouble(price.getText())));
                    break;
            }
        }
        if(!Objects.equals(city.getText(), ""))
            request.append("permits.city",city.getText());
        if(!Objects.equals(contractor.getText(), ""))
            request.append("permits.CONTRACTOR", contractor.getText());

        if(!Objects.equals(date.getPromptText(), ""))
        {
            //TODO DANG IT ! DO IT FAGGOT !
            System.out.println("Oups, date not implemented yet");
        }
        SharedVariables.iterable = mongoCollection.find(request);
        Stage stage = new Stage();
        mongodb.View.ResultDisplay resultDisplay = new mongodb.View.ResultDisplay();
        try {
            resultDisplay.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((Stage)city.getScene().getWindow()).close();
    }
}

