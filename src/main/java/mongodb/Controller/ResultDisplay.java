package mongodb.Controller;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import mongodb.model.Permit;
import mongodb.model.SharedVariables;
import org.bson.Document;

import java.util.Objects;

/**
 * Created by louis on 08/12/2015.
 */
public class ResultDisplay {
    @FXML
    private javafx.scene.control.TableView results;
    private ObservableList<Permit> observableList;

    public void initialize() {
        Block<Document> documentBlock = document -> observableList.add(new Permit(document));
        FindIterable<Document> iterable = SharedVariables.iterable;
        iterable.forEach(documentBlock);
    }
}


