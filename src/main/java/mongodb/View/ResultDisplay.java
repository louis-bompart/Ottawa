package mongodb.View;
/**
 * Created by louis on 08/12/2015.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultDisplay extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("resultDisplay.fxml"));
        primaryStage.setTitle("Results");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
