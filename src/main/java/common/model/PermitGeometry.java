package common.model;

import org.bson.BsonArray;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;

/**
 * Created by louis on 08/12/2015.
 */
public class PermitGeometry {

    public PermitGeometry(Document document) {
        Double[] coordinates = new Double[2];
        ArrayList<Double> arrayList = ((ArrayList<Double>) document.get("coordinates"));
        if(arrayList.size()==2) {
            coordinates[0] = arrayList.get(0);
            coordinates[1] = arrayList.get(1);
        }
    }
}
