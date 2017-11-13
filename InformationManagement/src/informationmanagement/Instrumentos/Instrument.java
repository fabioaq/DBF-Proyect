package informationmanagement.Instrumentos;

/**
 *
 * @author fabio
 */
public class Instrument {

    public Instrument(String id, String type, String name,  int min, int max, int tol) {
        this.name = name;
        this.id = id;
        this.min = min;
        this.max = max;
        this.tol = tol;
        this.type = type;
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
    
    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getTol() {
        return tol;
    }
    
    public String getType() {
        return type;
    }
    
    @Override
    public String toString(){
        return String.format("{%s, %s ,%s , %d, %d ,%d}", id, type,
                name,min,max,tol);
    }
    
     public static String[] getDescription(){
        return description;
    }
    
    private static final String[] description = {
        "Id", "Name", "Ammount", "Min", "Max", "Tolerance"
    };
    
    
    
    
    private String name;
    private final String id;
    private final String type;
    private final int min;
    private final int max;
    private final int tol;
}
