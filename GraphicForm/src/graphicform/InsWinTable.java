package graphicform;

import controler.Controler;
import informationmanagement.InformationManagement;
import informationmanagement.InstrumentType;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author fabio
 */
public class InsWinTable extends JFrame implements Runnable {

    InsWinTable(Controler c) {
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
                                InstrumentType.getDescription()),
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

}
