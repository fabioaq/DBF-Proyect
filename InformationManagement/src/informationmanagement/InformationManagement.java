
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
        
        return ins;
        
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
    
    public static void addInstrumentType(InstrumentType i){
        ins.add(i);
    }
    
    public static boolean areTypes(){
        return !ins.isEmpty();
    }
    
    private static final List<InstrumentType> ins = new ArrayList<>();;
    private static InformationManagement intance = null;
    private final DBgestor db;
    
}
