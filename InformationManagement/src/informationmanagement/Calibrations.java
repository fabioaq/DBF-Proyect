package informationmanagement;

import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public class Calibrations {

    public Calibrations(Integer id, String date) {
        this.id = id;
        this.date = date;
        this.m = new ArrayList<>();
    }
    
    
    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Meditions> getM() {
        return m;
    }
    
     public static String[] getDescription(){
        return description;
    }
    
    private static final String[] description = {
        "Id", "Date", "Meditions"
    };
    
    private final Integer id;
    private final String date;
    private final ArrayList<Meditions> m;
}
