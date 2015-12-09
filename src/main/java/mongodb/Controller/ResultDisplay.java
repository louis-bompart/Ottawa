package mongodb.Controller;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
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
        observableList = FXCollections.observableArrayList();

        Block<Document> documentBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {
                observableList.add(new Permit(document));

            }
        };
        FindIterable<Document> iterable = SharedVariables.iterable;
        iterable.forEach(documentBlock);
        results.setItems(observableList);
        initializeColumns();

    }

    private void initializeColumns() {
        TableColumn<Permit,String> id = new TableColumn("ID");
        id.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().get_id().toString()));
        results.getColumns().add(id);

        TableColumn address = new TableColumn("Address");

            TableColumn<Permit,String> permitStreetNumber = new TableColumn("Street N°");
            permitStreetNumber.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getStreetNumber()));
            address.getColumns().add(permitStreetNumber);
            TableColumn<Permit,String> permitRoad = new TableColumn("Road");
            permitRoad.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getRoad()));
            address.getColumns().add(permitRoad);
            TableColumn<Permit,String> permitSuffix = new TableColumn("Road Suffix");
            address.getColumns().add(permitSuffix);
        permitSuffix.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getSuffix()));
            TableColumn<Permit,String> permitMunicipality = new TableColumn("Municipality");
        permitMunicipality.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getMunicipality()));
            address.getColumns().add(permitMunicipality);
            TableColumn<Permit,String> postalCode = new TableColumn("Postal Code");
        postalCode.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getPostalCode()));
            postalCode.getColumns().add(postalCode);

        results.getColumns().add(address);
        TableColumn<Permit,String> permitLot = new TableColumn("Lot");
        permitLot.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getLot()));
        results.getColumns().add(permitLot);
        TableColumn<Permit,String> permitIssuedDate = new TableColumn("Issued Date");
        permitIssuedDate.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getIssuedDate()));
        results.getColumns().add(permitIssuedDate);

        TableColumn location = new TableColumn("Location");
            TableColumn<Permit,String> latitude = new TableColumn("Lat");
        latitude.setCellValueFactory(cellData ->new SimpleObjectProperty<String>(String.valueOf(cellData.getValue().getProperties().getLatitude())));
        location.getColumns().add(latitude);
            TableColumn<Permit,String> longitude = new TableColumn("Lng");
        longitude.setCellValueFactory(cellData ->new SimpleObjectProperty<String>(String.valueOf(cellData.getValue().getProperties().getLongitude())));
        location.getColumns().add(longitude);
        results.getColumns().add(location);
        TableColumn<Permit,String> status = new TableColumn("Status");
        status.setCellValueFactory(cellData ->new SimpleObjectProperty<String>(String.valueOf(cellData.getValue().getProperties().getStatus())));
        results.getColumns().add(status);
    }
}


