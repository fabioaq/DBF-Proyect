package graphicform.viewMediciones;

import informationmanagement.Mediciones.MedGestor;
import informationmanagement.Mediciones.Meditions;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author fabio
 */
public class MeditionsTable extends AbstractTableModel{
      private MeditionsTable(int key) {
        try {
            medidas = MedGestor.getInstancia().obtenerTabla(key);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException ex) {
            System.err.printf("%s%n", ex.getMessage());
        }
    }

    public static MeditionsTable getInstancia(int key) {
        if (instancia == null) {
            instancia = new MeditionsTable(key);
        }
        return instancia;
    }

    @Override
    public int getRowCount() {
        return medidas.length;
    }

    @Override
    public int getColumnCount() {
        return Meditions.getDescription().length;
    }

    @Override
    public String getColumnName(int i) {
        return Meditions.getDescription()[i];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        return medidas[fila][columna];
    }

    @Override
    public void setValueAt(Object valor, int fila, int columna) {
        System.out.printf(
                "Intenta actualizar el valor en la posición [%d,%d]: '%s'%n",
                fila, columna, valor);
        medidas[fila][columna] = valor;
        Meditions e = new Meditions(
                (Integer) medidas[fila][0],
                (Integer) medidas[fila][1],
                (Integer) medidas[fila][2]
        );
        try {
            MedGestor.getInstancia()
                    .actualizar(e);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException ex) {
            System.out.printf("Excepción: %s%n", ex.getMessage());
        }
    }

    private static MeditionsTable instancia = null;
    private Object[][] medidas;
}
