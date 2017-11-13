package informationmanagement.Calibraciones;

import informationmanagement.Mediciones.Meditions;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author fabio
 */
public class Calibration {

    public Calibration(Integer id, String instrument, Date date) {
        this.id = id;
        this.date = date;
        this.instrument = instrument;
        this.m = new ArrayList<>();
    }
    
    
    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Meditions> getM() {
        return m;
    }
    
     public static String[] getDescription(){
        return description;
    }
     
    public String getInstrument() {
        return instrument;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        s.append(getId());
        s.append(", ");
        s.append(getInstrument());
        s.append(", ");
        s.append(new java.util.Date(date.getTime()).toString());
        m.forEach((medicion) -> {
            s.append(", ");
            s.append(medicion.toString());
        });
        s.append("}\n");
        return s.toString();
    }
    
    private static final String[] description = {
        "Id", "Date", "Meditions"
    };
    
    private final Integer id;
    private final Date date;
    private final String instrument;
    private final ArrayList<Meditions> m;
}
