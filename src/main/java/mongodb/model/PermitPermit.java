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
    private int totalValue;//VALUE
    private String plan;
    private int unitFT2;
    private String ward;
    private int du;
    private double unitCost;
    private String description;
    private String keyword;
    private String streetNumber;

    public String getSuffix() {
        return suffix;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getApplType() {
        return applType;
    }

    public String getBlgType() {
        return blgType;
    }

    public String getLot() {
        return lot;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public int getTotalFT2() {
        return totalFT2;
    }

    public int getTotalUnit() {
        return totalUnit;
    }

    public String getContractor() {
        return contractor;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public int getPermit() {
        return permit;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public String getPlan() {
        return plan;
    }

    public int getUnitFT2() {
        return unitFT2;
    }

    public String getWard() {
        return ward;
    }

    public int getDu() {
        return du;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public String getDescription() {
        return description;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getRoad() {
        return road;
    }

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
        totalValue=document.getInteger("VALUE");
        plan=document.getString("PLAN");
        ward=document.getString("WARD");
        description=document.getString("DESCRIPTION");
        keyword=document.getString("keyword");
        streetNumber=document.getString("ST");
        road=document.getString("road");
        if(document.get("COST_unit")!=null)
            unitCost=document.getDouble("COST_unit");
        if(document.get("VALUE_UNIT")!=null)
            unitValue=document.getDouble("VALUE_unit");
        if(document.get("FT2_unit")!=null)
            unitFT2=document.getInteger("FT2_unit");
    }
}
