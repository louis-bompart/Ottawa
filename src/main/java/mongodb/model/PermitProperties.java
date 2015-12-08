package mongodb.model;

import org.bson.Document;

/**
 * Created by louis on 08/12/2015.
 */
public class PermitProperties {
    private boolean status;
    private String location;
    private double latitude;
    private double longitude;
    private int accuracy;

    public PermitProperties(Document document) {
        status=document.getBoolean("ok",false);
        location=document.getString("location");
        latitude=document.getDouble("lat");
        longitude=document.getDouble("lng");
        accuracy=document.getInteger("accuracy");
    }
}
