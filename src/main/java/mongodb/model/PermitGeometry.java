package mongodb.model;

import org.bson.BsonArray;
import org.bson.Document;

import javax.print.Doc;

/**
 * Created by louis on 08/12/2015.
 */
public class PermitGeometry {
    private Double[] coordinates;

    public PermitGeometry(Document document) {
        coordinates = new Double[2];
        BsonArray bsonArray = (BsonArray) document.get("coordinates");
        coordinates[0]=bsonArray.get(0).asDouble().doubleValue();
        coordinates[1]=bsonArray.get(1).asDouble().doubleValue();
    }
}
