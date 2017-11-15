package graphicform.viewInstrumentos;

import informationmanagement.Instrumentos.InstGestor;
import informationmanagement.Instrumentos.Instrument;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Esteban
 */
public class InstrumentTable extends AbstractTableModel{
    
    private InstrumentTable() {
        try {
            instrumentos = InstGestor.getInstancia().obtenerTabla();
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException ex) {
            System.err.printf("%s%n", ex.getMessage());
        }
    }
    
    private InstrumentTable(String k) {
        key = k;
        try {
            instrumentos = InstGestor.getInstancia().obtenerTabla(k);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException ex) {
            System.err.printf("%s%n", ex.getMessage());
        }
    }

    public static InstrumentTable getInstancia() {
        if (instancia == null) {
            instancia = new InstrumentTable();
        }
        if(key.compareTo("null") != 0){
            key = "null";
        }
        return instancia;
    }
    
    public static InstrumentTable getInstancia(String key) {
        if (instancia == null) {
            instancia = new InstrumentTable(key);
        }
        return instancia;
    }

    @Override
    public int getRowCount() {
        return instrumentos.length;
    }

    @Override
    public int getColumnCount() {
        return Instrument.getDescription().length;
    }

    @Override
    public String getColumnName(int i) {
        return Instrument.getDescription()[i];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        return instrumentos[fila][columna];
    }

    @Override
    public void setValueAt(Object valor, int fila, int columna) {
        System.out.printf(
                "Intenta actualizar el valor en la posición [%d,%d]: '%s'%n",
                fila, columna, valor);
        instrumentos[fila][columna] = valor;
        Instrument in = new Instrument(
                instrumentos[fila][0].toString(),
                instrumentos[fila][1].toString(),
                instrumentos[fila][2].toString(),
                (Integer) instrumentos[fila][3],
                (Integer) instrumentos[fila][4],
                (Integer) instrumentos[fila][5]
        );
        try {
            InstGestor.getInstancia()
                    .actualizar(in);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException ex) {
            System.out.printf("Excepción: %s%n", ex.getMessage());
        }
    }

    private static InstrumentTable instancia = null;
    private static String key = "null";
    private Object[][] instrumentos;
}
