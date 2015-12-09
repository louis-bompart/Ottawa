package org.Ottawa.mongodb.controller;

import com.mongodb.client.MongoCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.Ottawa.mongodb.model.SharedVariables;
import org.bson.Document;

import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by louis on 04/12/2015.
 */
public class MongoRequest implements Initializable{

    private MongoCollection<Document> mongoCollection;
    @FXML
    private javafx.scene.control.ComboBox<String> comboBoxPrice;
    @FXML
    private javafx.scene.control.TextField contractor;
    @FXML
    private javafx.scene.control.TextField city;
    @FXML
    private javafx.scene.control.TextField price;
    @FXML
    private javafx.scene.control.DatePicker datePicker;

    /**
     *
     */
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> stringObservableValue = FXCollections.observableArrayList(
                "=",
                "<",
                ">"
        );
        comboBoxPrice.setItems(stringObservableValue);
        mongoCollection = SharedVariables.mongoCollection;

    }

    public void doRequest()
    {
        Document request = new Document();
        if (!Objects.equals(price.getText(), "")) {
            switch (comboBoxPrice.getSelectionModel().getSelectedItem()){
                case "=":
                    request.append("permits.VALUE",Double.parseDouble(price.getText()));
                    break;
                case "<":
                    request.append("permits.VALUE",new Document("$lt",Double.parseDouble(price.getText())));
                    break;
                case ">":
                    request.append("permits.VALUE",new Document("$gt",Double.parseDouble(price.getText())));
                    break;
            }
        }
        if(!Objects.equals(city.getText(), ""))
            request.append("permits.city",city.getText());
        if(!Objects.equals(contractor.getText(), ""))
            request.append("permits.CONTRACTOR", contractor.getText());

        if(datePicker.getValue()!=null)
        {
            LocalDate date = datePicker.getValue();
            String toRequest = date.getYear() +"-";
            if(date.getMonthValue()<10)
            {
                toRequest+="0";
            }
            toRequest+=date.getMonthValue()+"-";
            if(date.getDayOfMonth()<10)
            {
                toRequest+="0";
            }
            toRequest+=date.getDayOfMonth();
            request.append("permits.ISSUED_DATE",toRequest);
        }
        SharedVariables.iterable = mongoCollection.find(request);
        Stage stage = new Stage();
        org.Ottawa.mongodb.view.ResultDisplay resultDisplay = new org.Ottawa.mongodb.view.ResultDisplay();
        try {
            resultDisplay.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((Stage)city.getScene().getWindow()).close();
    }
}

