package graphicform;

import controler.Controler;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
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
public class InsTypeWinTable extends JFrame implements Runnable {

    InsTypeWinTable(Controler c) {
        this.mainControl = c;
        config();
    }

    private void config() {
        fixes(getContentPane());
        setResizable(true);
        setSize(400, 600);
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);

    }

    private void fixes(Container c) {

        c.setLayout(new BorderLayout());
        c.add(BorderLayout.CENTER,
                mainPanel = new JPanel());
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(BorderLayout.CENTER,
                new JScrollPane(
                        table
                        = new JTable(
                                mainControl.getTable(),
                                mainControl.getHeader(1)),
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
                )
        );

    }

    public void init() {
        setVisible(true);
    }

    @Override
    public void run() {
        init();
    }

    private JPanel mainPanel;
    private JTable table;
    private final Controler mainControl;
    private JMenuBar menuPrincipal;
    private JMenu menuVerificar;

    private JMenuItem itemGuardar;

}