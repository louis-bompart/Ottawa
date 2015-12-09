package org.Ottawa.mongodb.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by louis on 07/12/2015.
 */
public class Home {

    @FXML
    public void doRequest(){

        Stage stage = new Stage();
        org.Ottawa.mongodb.view.MongoRequest mongoRequest = new org.Ottawa.mongodb.view.MongoRequest();
        try {
            mongoRequest.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void doImport(){

        Stage stage = new Stage();
        org.Ottawa.mongodb.view.DataImporter dataImporter = new org.Ottawa.mongodb.view.DataImporter();
        try {
            dataImporter.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void doSelect(){

        Stage stage = new Stage();
        org.Ottawa.mongodb.view.ChangeCollection changeCollection = new org.Ottawa.mongodb.view.ChangeCollection();
        try {
            changeCollection.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void doStats(){

        Stage stage = new Stage();
        org.Ottawa.mongodb.view.ShowStatistics showStatistics = new org.Ottawa.mongodb.view.ShowStatistics();
        try {
            showStatistics.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
