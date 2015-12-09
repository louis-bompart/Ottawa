package org.Ottawa.mongodb.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by louis on 07/12/2015.
 */
public class ChangeCollection extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("changeCollection.fxml"));
        primaryStage.setTitle("Change collection");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
