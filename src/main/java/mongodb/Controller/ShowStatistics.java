package mongodb.Controller;

import com.mongodb.Block;
import com.mongodb.client.*;
import common.model.Permit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.shape.Line;
import javafx.util.Pair;
import mongodb.model.SharedVariables;
import org.bson.Document;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;
import static java.util.Arrays.asList;

/**
 * Created by louis on 09/12/2015.
 */
public class ShowStatistics implements Initializable {
    ObservableList<PieChart.Data> pieChartData;
    XYChart.Series series= new XYChart.Series();

    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart lineChart;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializePieChart();
        initializeLineChart();
    }

    private void initializeLineChart() {
//        series.setName("Number of permits per value");
//        MapReduceIterable<Document> iterable = SharedVariables.mongoCollection.mapReduce("" +
//                        "function() {" +
//                        "   emit(this.permits.VALUE, 1);" +
//                        "}",
//                "function(key,values) {" +
//                        "   return Array.sum(values)" +
//                        "}");
//        iterable.forEach(new Consumer<Document>() {
//            @Override
//            public void accept(Document document) {
//            }
//        });
    }

    private void initializePieChart() {
        pieChartData = FXCollections.observableArrayList();
        MapReduceIterable<Document> iterable = SharedVariables.mongoCollection.mapReduce("" +
                        "function() {" +
                        "   emit(this.permits.MUNICIPALITY, 1);" +
                        "}",
                "function(key,values) {" +
                        "   return Array.sum(values)" +
                        "}");
        iterable.forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                String id = document.getString("_id");
                double value = document.getDouble("value");
                pieChartData.add(new PieChart.Data(id,value));
            }
        });
        pieChart.setData(pieChartData);
        pieChart.setTitle("Distribution of permits per municipalities");
    }
}
