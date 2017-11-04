package controler;

import informationmanagement.InformationManagement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class Controler {

    
    public Controler() {
       
    }
    
    public Object[][] getTable(){
        try {
            return InformationManagement.getInstance().obtenerTabla();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
