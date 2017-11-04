package informationmanagement;

/**
 *
 * @author fabio
 */
public class Instrument {

    public Instrument(String name, String id, Integer cant, String min, String max, String tol) {
        this.name = name;
        this.id = id;
        this.cant = cant;
        this.min = min;
        this.max = max;
        this.tol = tol;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    

    public Integer getCant() {
        return cant;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getTol() {
        return tol;
    }

    public void setTol(String tol) {
        this.tol = tol;
    }
    
     public static String[] getDescription(){
        return description;
    }
    
    private static final String[] description = {
        "Id", "Name", "Ammount", "Min", "Max", "Tolerance"
    };
    
    
    
    
    private String name;
    private final String id;
    private Integer cant;
    private String min;
    private String max;
    private String tol;
}
