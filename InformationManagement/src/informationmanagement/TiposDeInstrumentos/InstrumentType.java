package informationmanagement.TiposDeInstrumentos;

import informationmanagement.Instrumentos.Instrument;
import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public class InstrumentType {

    public InstrumentType( String code, String name, String unitType) {
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
    
    public static String[] getDescription(){
        return description;
    }
    
    @Override
    public String toString() {
        return String.format("{%s, %s ,%s}", code, name, unitType);
    }
    
    private static final String[] description = {
        "Code", "Unit Type", "Name"
    };
    
    
    
    private String name;
    private final String code;
    private String unitType;
    private ArrayList<Instrument> l;
}
