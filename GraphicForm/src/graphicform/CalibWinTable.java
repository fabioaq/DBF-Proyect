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

/**
 *
 * @author fabio
 */
public class CalibWinTable extends JInternalFrame {

    CalibWinTable(Controler c) {
        super("Calibraciones", true, true, true);
        this.mainControl = c;
        config();
    }

    private void config() {
        fixes(getContentPane());
        setResizable(true);
        setSize(400, 600);
        setMinimumSize(new Dimension(400, 300));
        

    }

    private void fixes(Container c) {

        c.setLayout(new BorderLayout());
        
        menuPrincipal = new JMenuBar();
        menuPrincipal.add(editar = new JMenu("Opciones"));
        
        //
        
        editar.add(itemAgregar = new JMenuItem("Agregar"));
        editar.add(itemBorrar = new JMenuItem("Borrar"));
        editar.add(itemEditar = new JMenuItem("Editar"));
        
        //
        
        c.add(BorderLayout.CENTER,
                mainPanel = new JPanel());
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(BorderLayout.CENTER,
                new JScrollPane(
                        table
                        = new JTable(
                                mainControl.getTable(),
                                mainControl.getHeader(3)),
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
                )
        );
        
        setJMenuBar(menuPrincipal);

    }

    public void init() {
        setVisible(true);
    }

    

    private JPanel mainPanel;
    private JTable table;
    private final Controler mainControl;
    private JMenuBar menuPrincipal;
    private JMenu editar;
    private JMenuItem itemBorrar;
    private JMenuItem itemAgregar;
    private JMenuItem itemEditar;

}
