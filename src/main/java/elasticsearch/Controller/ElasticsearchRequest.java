package elasticsearch.Controller;

import javafx.fxml.FXML;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import java.time.LocalDate;
import java.util.Objects;


import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by louis on 04/12/2015.
 */
public class ElasticsearchRequest{

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


    public void doRequest()
    {
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "ottawa") .build();
        Node node = nodeBuilder().settings(settings).node();
        Client client = node.client();

        String toRequest ="";
        String cityToSearch ="";
        String contractorToSearch ="";
        String priceToSearch = "";
        if(datePicker.getValue()!=null) {
            LocalDate date = datePicker.getValue();
            toRequest = date.getYear() + "-";
            if (date.getMonthValue() < 10) {
                toRequest += "0";
            }
            toRequest += date.getMonthValue() + "-";
            if (date.getDayOfMonth() < 10) {
                toRequest += "0";
            }
            toRequest += date.getDayOfMonth();
        }
        if(!Objects.equals(city.getText(), "")) {
            cityToSearch = city.getText();
        }
        if(!Objects.equals(contractor.getText(), "")) {
            contractorToSearch = contractor.getText();
        }
        if (!Objects.equals(price.getText(), "")){
            priceToSearch = price.getText();
        }

        SearchResponse response = client.prepareSearch("tp")
                .setTypes("ISSUED_DATE","city","CONTRACTOR","VALUE_unit")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.multiMatchQuery(toRequest,cityToSearch,contractorToSearch, priceToSearch))      // Query
                .execute()
                .actionGet();

    }
}

