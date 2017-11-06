package graphicform;

import controler.Controler;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fabio
 */
public class InsWinTable extends JInternalFrame {

    InsWinTable(Controler c) {
        super("Tipos de Instrumentos", true, true, true, true);
        this.mainControl = c;
        this.tableModel = new DefaultTableModel(mainControl.getTable(), mainControl.getHeader(1)) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        config();
    }

    private void config() {
        fixes(getContentPane());
        setResizable(true);
        setSize(400, 400);
        setMinimumSize(new Dimension(400, 300));

    }

    private void fixes(Container c) {

        c.setLayout(new BorderLayout());

        menuPrincipal = new JMenuBar();
        menuPrincipal.add(editar = new JMenu("Opciones"));

        //
        editar.add(itemAgregar = new JMenuItem("Agregar"));
        editar.add(itemBorrar = new JMenuItem("Borrar"));
        editar.add(itemBuscar = new JMenuItem("Buscar"));

        //
        table = new JTable(mainControl.getTable(), mainControl.getHeader(1));
        table.setModel(tableModel);
        //

        c.add(BorderLayout.CENTER,
                mainPanel = new JPanel());
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(BorderLayout.CENTER,
                new JScrollPane(
                        table,
                         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
                )
        );

        setJMenuBar(menuPrincipal);

    }

    private JPanel mainPanel;
    private JTable table;
    private final Controler mainControl;
    private JMenuBar menuPrincipal;
    private JMenu editar;
    private JMenuItem itemBorrar;
    private JMenuItem itemAgregar;
    private JMenuItem itemBuscar;
    DefaultTableModel tableModel;

}
