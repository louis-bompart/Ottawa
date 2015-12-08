package mongodb.model;

import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;

/**
 * Created by louis on 08/12/2015.
 */
public class Permit {
    private ObjectId _id;
    private PermitGeometry geometry;
    private PermitProperties properties;
    private PermitPermit permit;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public PermitGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(PermitGeometry geometry) {
        this.geometry = geometry;
    }

    public PermitProperties getProperties() {
        return properties;
    }

    public void setProperties(PermitProperties properties) {
        this.properties = properties;
    }

    public PermitPermit getPermit() {
        return permit;
    }

    public void setPermit(PermitPermit permit) {
        this.permit = permit;
    }

    public Permit(Document document) {
        this._id=document.getObjectId("_id");
        geometry = new PermitGeometry((Document)document.get("geometry"));
        properties = new PermitProperties((Document)document.get("properties"));
        permit = new PermitPermit((Document)document.get("permits"));
    }

}
