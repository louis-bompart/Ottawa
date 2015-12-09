package org.Ottawa.common.model;

import org.bson.Document;

/**
 * Created by louis on 08/12/2015.
 */
public class PermitProperties {
    private final boolean status;
    private final String location;
    private double latitude;
    private double longitude;
    private String accuracy;

    public PermitProperties(Document document) {
        status=document.getBoolean("ok",false);
        location=document.getString("location");
        if(document.getDouble("lat")!=null)
        latitude=document.getDouble("lat");
        if(document.getDouble("lng")!=null)
        longitude=document.getDouble("lng");
        if(document.get("accuracy")!=null)
        accuracy=String.valueOf(document.get("accuracy"));
    }

    public boolean getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAccuracy() {
        return accuracy;
    }
}
