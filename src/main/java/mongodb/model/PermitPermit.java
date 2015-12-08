package mongodb.model;

import org.bson.Document;

/**
 * Created by louis on 08/12/2015.
 */
public class PermitPermit {

    private String suffix;
    private String municipality;
    //month ignored
    private String applType;
    private String blgType;
    private String lot;
    //year ignored
    //fileName kinda useless
    private double unitValue;
    private String issuedDate;
    //city useless
    private int totalFT2;
    private int totalUnit;
    private String contractor;
    private String postalCode;
    //location ignored, redundancy
    private int permit;
    //housenumber ignored
    //ROAD ignored
    private double totalValue;//VALUE
    private String plan;
    private int unitFT2;
    private String ward;
    private int du;
    private double unitCost;
    private String description;
    private String keyword;
    private String streetNumber;
    private String road;

    public PermitPermit(Document document) {
        suffix=document.getString("suffix");
        municipality=document.getString("MUNICIPALITY");
        applType=document.getString("APPL_TYPE");
        blgType=document.getString("BLG_TYPE");
        du=document.getInteger("DU");
        lot=document.getString("LOT");
        issuedDate=document.getString("ISSUED_DATE");
        totalFT2=document.getInteger("FT2");
        totalUnit=document.getInteger("TOTAL_unit");
        contractor=document.getString("CONTRACTOR");
        postalCode=document.getString("PC");
        permit=document.getInteger("PERMIT");
        totalValue=document.getDouble("VALUE");
        plan=document.getString("PLAN");
        ward=document.getString("WARD");
        description=document.getString("DESCRIPTION");
        keyword=document.getString("keyword");
        streetNumber=document.getString("ST");
        road=document.getString("road");
        unitCost=document.getDouble("COST_unit");
        unitValue=document.getDouble("VALUE_unit");
        unitFT2=document.getInteger("FT2_unit");
    }
}
