package org.Ottawa.mongodb.controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import org.Ottawa.common.model.Permit;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import org.Ottawa.mongodb.model.SharedVariables;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by louis on 08/12/2015.
 */
public class ResultDisplay implements MapComponentInitializedListener, Initializable {
    @FXML
    private javafx.scene.control.TableView results;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;
    private ObservableList<Permit> observableList;

    public void initialize(URL location, ResourceBundle resources) {

        observableList = FXCollections.observableArrayList();

        Block<Document> documentBlock = document -> observableList.add(new Permit(document));
        FindIterable<Document> iterable = SharedVariables.iterable;
        iterable.forEach(documentBlock);
        mapView.addMapInializedListener(this);
        results.setItems(observableList);
        initializeColumns();

    }

    private void initializeColumns() {
        TableColumn<Permit,String> id = new TableColumn<Permit, String>("ID");
        id.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().get_id().toString()));

        TableColumn address = new TableColumn("Address");

            TableColumn<Permit,String> permitStreetNumber = new TableColumn<Permit, String>("Street N°");
            permitStreetNumber.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getStreetNumber()));
            address.getColumns().add(permitStreetNumber);
            TableColumn<Permit,String> permitRoad = new TableColumn<Permit, String>("Road");
            permitRoad.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getRoad()));
            address.getColumns().add(permitRoad);
            TableColumn<Permit,String> permitSuffix = new TableColumn<Permit, String>("Road Suffix");
            address.getColumns().add(permitSuffix);
        permitSuffix.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getSuffix()));
            TableColumn<Permit,String> permitMunicipality = new TableColumn<Permit, String>("Municipality");
        permitMunicipality.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getMunicipality()));
            address.getColumns().add(permitMunicipality);
            TableColumn<Permit,String> postalCode = new TableColumn<Permit, String>("Postal Code");
        postalCode.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getPostalCode()));
            postalCode.getColumns().add(postalCode);

        results.getColumns().add(address);
        TableColumn<Permit,String> permitValue= new TableColumn<Permit, String>("Value");
        permitValue.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getTotalValue()));
        results.getColumns().add(permitValue);
        TableColumn<Permit,String> permitIssuedDate = new TableColumn<Permit, String>("Issued Date");
        permitIssuedDate.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getPermit().getIssuedDate()));
        results.getColumns().add(permitIssuedDate);

        TableColumn location = new TableColumn("Location");
            TableColumn<Permit,String> latitude = new TableColumn<Permit, String>("Lat");
        latitude.setCellValueFactory(cellData ->new SimpleObjectProperty<String>(String.valueOf(cellData.getValue().getProperties().getLatitude())));
        location.getColumns().add(latitude);
            TableColumn<Permit,String> longitude = new TableColumn<Permit, String>("Lng");
        longitude.setCellValueFactory(cellData ->new SimpleObjectProperty<String>(String.valueOf(cellData.getValue().getProperties().getLongitude())));
        location.getColumns().add(longitude);
        results.getColumns().add(location);
        TableColumn<Permit,String> contractor = new TableColumn<Permit, String>("Contractor");
        contractor.setCellValueFactory(cellData ->new SimpleObjectProperty<String>(String.valueOf(cellData.getValue().getPermit().getContractor())));
        results.getColumns().add(contractor);
        results.getColumns().add(id);
    }

    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(45.2520, -75.4143))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(9);

        map = mapView.createMap(mapOptions);
        observableList.forEach(permit -> {
            LatLong latLong = new LatLong(permit.getProperties().getLatitude(),permit.getProperties().getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLong);
            Marker marker = new Marker(markerOptions);
            map.addMarker(marker);
//            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
//            infoWindowOptions.content(permit.getPermit().getStreetNumber() + " " + permit.getPermit().getRoad() + " " +permit.getPermit().getSuffix() +
//                                        "\n" + permit.getPermit().getMunicipality() + " " + permit.getPermit().getPostalCode());
//            InfoWindow infoWindow = new InfoWindow(infoWindowOptions);
//            infoWindow.open(map,marker);
//            infoWindow.close();

        });
    }
}

