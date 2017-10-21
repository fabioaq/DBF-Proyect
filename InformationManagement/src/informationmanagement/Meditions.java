package informationmanagement;

/**
 *
 * @author fabio
 */
public class Meditions {

    public Meditions(Integer number, String lecture, String reference) {
        this.number = number;
        this.lecture = lecture;
        this.reference = reference;
    }

    
    
    
    public Integer getNumber() {
        return number;
    }

    public String getLecture() {
        return lecture;
    }

    public String getReference() {
        return reference;
    }
    
    
    
    
    private final Integer number;
    private final String lecture;
    private final String reference;
}
