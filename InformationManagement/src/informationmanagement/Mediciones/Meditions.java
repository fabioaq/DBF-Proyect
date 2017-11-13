package informationmanagement.Mediciones;

/**
 *
 * @author fabio
 */
public class Meditions {

    public Meditions(int number, int reference, int lecture) {
        this.number = number;
        this.lecture = lecture;
        this.reference = reference;
    }

    
    
    
    public Integer getNumber() {
        return number;
    }

    public int getLecture() {
        return lecture;
    }

    public int getReference() {
        return reference;
    }
    
     public static String[] getDescription(){
        return description;
    }
     
     @Override
    public String toString() {
        return String.format("{%d, %d %d}", number, reference, lecture);
    }
    
    private static final String[] description = {
        "Numero", "Referencia", "Lectura"
    };
    
    
    private final int number;
    private final int lecture;
    private final int reference;
}
