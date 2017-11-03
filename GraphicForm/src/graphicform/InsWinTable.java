package graphicform;

import informationmanagement.InformationManagement;
import informationmanagement.InstrumentType;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author fabio
 */
public class InsWinTable extends JFrame {

    InsWinTable(InformationManagement inf) {
        this.info = inf;
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
                                info.obtenerTabla(),
                                InstrumentType.getDescription()),
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
                )
        );

    }

    public void init() {
        setVisible(true);
    }

    private final InformationManagement info;
    private JPanel mainPanel;
    private JTable table;

}
