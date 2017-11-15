package graphicform.viewCalibraciones;

import informationmanagement.Calibraciones.CalibGestor;
import informationmanagement.Calibraciones.Calibration;
import java.sql.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author fabio
 */
public class CalibTable extends AbstractTableModel{
    private CalibTable(String key) {
        try {
            calibraciones = CalibGestor.getInstancia().obtenerTabla(key);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException ex) {
            System.err.printf("%s%n", ex.getMessage());
        }
    }

    public static CalibTable getInstancia(String key) {
        if (instancia == null) {
            instancia = new CalibTable(key);
        }
        return instancia;
    }

    @Override
    public int getRowCount() {
        return calibraciones.length;
    }

    @Override
    public int getColumnCount() {
        return Calibration.getDescription().length;
    }

    @Override
    public String getColumnName(int i) {
        return Calibration.getDescription()[i];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        return calibraciones[fila][columna];
    }

    @Override
    public void setValueAt(Object valor, int fila, int columna) {
        System.out.printf(
                "Intenta actualizar el valor en la posición [%d,%d]: '%s'%n",
                fila, columna, valor);
        calibraciones[fila][columna] = valor;
        Calibration c = new Calibration(
                (Integer) calibraciones[fila][0],
                calibraciones[fila][1].toString(),
                (Date) calibraciones[fila][2]
        );
        try {
            CalibGestor.getInstancia()
                    .actualizar(c);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException ex) {
            System.out.printf("Excepción: %s%n", ex.getMessage());
        }
    }

    private static CalibTable instancia = null;
    private Object[][] calibraciones;
}
