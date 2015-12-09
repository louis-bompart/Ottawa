package org.Ottawa.mongodb.controller;

import com.mongodb.client.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import org.Ottawa.mongodb.model.SharedVariables;
import org.bson.Document;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by louis on 09/12/2015.
 */
public class ShowStatistics implements Initializable {
    ObservableList<PieChart.Data> pieChartData;

    @FXML
    private PieChart pieChart;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
