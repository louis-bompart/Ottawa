package common.model;

import org.bson.BsonArray;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;

/**
 * Created by louis on 08/12/2015.
 */
public class PermitGeometry {
    private Double[] coordinates;

    public PermitGeometry(Document document) {
        coordinates = new Double[2];
        ArrayList<Double> arrayList = ((ArrayList<Double>) document.get("coordinates"));
        coordinates[0]=arrayList.get(0);
        coordinates[1]=arrayList.get(1);
    }
}
