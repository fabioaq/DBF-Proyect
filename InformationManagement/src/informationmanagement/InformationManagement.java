
package informationmanagement;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class InformationManagement {

    public InformationManagement() throws
        InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        db = DBgestor.obtenerGestorBD(
                DBgestor.GBD.MYSQL_SERVER,
                DBgestor.SERVIDOR_POR_DEFECTO);
    }
    
    public static InformationManagement getInstance() throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        if (intance == null) {
            intance = new InformationManagement();
        }
        return intance;
        
    }
    
    public List<InstrumentType> create(){
        List<InstrumentType> r = new ArrayList<>();
        InstrumentType t1 = new InstrumentType("Tubos de Ensayo", "2001", "Mililitros");
        InstrumentType t2 = new InstrumentType("Probetas", "2002", "Mililitros");
        InstrumentType t3 = new InstrumentType("Reglas", "2003", "Centimetros");
        
        r.add(t1);
        r.add(t2);
        r.add(t3);
        
        return r;
        
    }
    
    public Object[][] obtenerTabla() {
        List<InstrumentType> insType = create();
        Object[][] r = new Object[insType.size()][4];
        int i = 0;
        for (InstrumentType e : insType) {
            r[i][0] = e.getCode();
            r[i][1] = e.getUnitType();
            r[i][2] = e.getName();
            r[i][3] = e.getAmmount();
            i++;
        }
        return r;
    }
    
    
    
    private static InformationManagement intance = null;
    private final DBgestor db;
    
}
