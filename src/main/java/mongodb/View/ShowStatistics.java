package mongodb.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by louis on 09/12/2015.
 */
public class ShowStatistics extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("showStatistics.fxml"));
        primaryStage.setTitle("Statistics");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
