package controler;

import informationmanagement.Calibrations;
import informationmanagement.InformationManagement;
import informationmanagement.Instrument;
import informationmanagement.InstrumentType;
import informationmanagement.Meditions;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class Controler {

    public Controler() {

    }

    public Object[][] getTable() {
        try {
            return InformationManagement.getInstance().obtenerTabla();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String[] getHeader(int h) {
        switch (h) {
            case 1:
                return InstrumentType.getDescription();
            case 2:
                return Instrument.getDescription();
            case 3:
                return Calibrations.getDescription();
            default:
                return Meditions.getDescription();
        }
    }
}
