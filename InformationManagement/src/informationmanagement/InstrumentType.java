package informationmanagement;

import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public class InstrumentType {

    public InstrumentType(String name, String code, String unitType) {
        this.name = name;
        this.code = code;
        this.unitType = unitType;
        this.l = new ArrayList<>();
    }

    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public ArrayList<Instrument> getL() {
        return l;
    }
    
    
    
    
    
    private String name;
    private final String code;
    private String unitType;
    private ArrayList<Instrument> l;
}
