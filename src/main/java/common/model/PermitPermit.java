package common.model;

import org.bson.Document;

/**
 * Created by louis on 08/12/2015.
 */
public class PermitPermit {

    private final String suffix;
    private final String municipality;
    //month ignored
    private final String applType;
    private final String blgType;
    private final String lot;
    //year ignored
    //fileName kinda useless
    private String unitValue;
    private final String issuedDate;
    //city useless
    private final int totalFT2;
    private final int totalUnit;
    private final String contractor;
    private final String postalCode;
    //location ignored, redundancy
    private final int permit;
    //housenumber ignored
    //ROAD ignored
    private final String totalValue;//VALUE
    private final String plan;
    private String unitFT2;
    private final String ward;
    private final int du;
    private String unitCost;
    private final String description;
    private final String keyword;
    private final String streetNumber;

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

    public String getUnitValue() {
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

    public String getTotalValue() {
        return totalValue;
    }

    public String getPlan() {
        return plan;
    }

    public String getUnitFT2() {
        return unitFT2;
    }

    public String getWard() {
        return ward;
    }

    public int getDu() {
        return du;
    }

    public String getUnitCost() {
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

    private final String road;

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
        totalValue=String.valueOf(document.get("VALUE"));
        plan=document.getString("PLAN");
        ward=document.getString("WARD");
        description=document.getString("DESCRIPTION");
        keyword=document.getString("keyword");
        streetNumber=document.getString("ST");
        road=document.getString("road");
        if(document.get("COST_unit")!=null)
            unitCost=String.valueOf(document.get("COST_unit"));
        if(document.get("VALUE_UNIT")!=null)
            unitValue=String.valueOf(document.get("VALUE_unit"));
        if(document.get("FT2_unit")!=null)
            unitFT2=String.valueOf(document.get("FT2_unit"));
    }
}
