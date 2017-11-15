package informationmanagement.Calibraciones;

import java.sql.Date;

/**
 *
 * @author fabio
 */
public class Calibration {

    public Calibration(int id, String instrument, Date date) {
        this.id = id;
        this.date = date;
        this.instrument = instrument;
    }
    
    
    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
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
        s.append("}\n");
        return s.toString();
    }
    
    private static final String[] description = {
        "Id", "Instrumento", "Fecha"
    };
    
    private final int id;
    private final Date date;
    private final String instrument;
}
