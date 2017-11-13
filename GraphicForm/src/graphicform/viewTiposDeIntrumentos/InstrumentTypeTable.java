package graphicform.viewTiposDeIntrumentos;

import informationmanagement.TiposDeInstrumentos.InsTypeGestor;
import informationmanagement.TiposDeInstrumentos.InstrumentType;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author fabio
 */
public class InstrumentTypeTable extends AbstractTableModel{
    private InstrumentTypeTable(){
        try {
            tiposinstrumentos = InsTypeGestor.getInstancia().obtenerTabla();
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException ex) {
            System.err.printf("%s%n", ex.getMessage());
        }
    }

    public static InstrumentTypeTable getInstancia() {
        if (instancia == null) {
            instancia = new InstrumentTypeTable();
        }
        return instancia;
    }

    @Override
    public int getRowCount() {
        return tiposinstrumentos.length;
    }

    @Override
    public int getColumnCount() {
        return InstrumentType.getDescription().length;
    }

    @Override
    public String getColumnName(int i) {
        return InstrumentType.getDescription()[i];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        return tiposinstrumentos[fila][columna];
    }

    @Override
    public void setValueAt(Object valor, int fila, int columna) {
        System.out.printf(
                "Intenta actualizar el valor en la posición [%d,%d]: '%s'%n",
                fila, columna, valor);
        tiposinstrumentos[fila][columna] = valor;
        InstrumentType ti = new InstrumentType(
                tiposinstrumentos[fila][0].toString(),
                tiposinstrumentos[fila][1].toString(),
                tiposinstrumentos[fila][2].toString()
        );
        try {
            InsTypeGestor.getInstancia()
                    .actualizar(ti);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException ex) {
            System.out.printf("Excepción: %s%n", ex.getMessage());
        }
    }

    private static InstrumentTypeTable instancia = null;
    private Object[][] tiposinstrumentos;
}
