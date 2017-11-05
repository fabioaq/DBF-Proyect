package graphicform;

import controler.Controler;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author fabio
 */
public class GraphicForm extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | UnsupportedLookAndFeelException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showW();
            }
        });
    }

    public GraphicForm(Controler c) {
        this.mainControl = c;
        config();
    }

    private void config() {
        fixes(getContentPane());
        setResizable(true);
        setSize(800, 800);
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void fixes(Container c) {

        c.setLayout(new BorderLayout());

        desk = new JDesktopPane();
        c.add(BorderLayout.CENTER, desk);
        menuPrincipal = new JMenuBar();
        menuPrincipal.add(DBwork = new JMenu("Opciones Base de Datos"));
        menuPrincipal.add(openTables = new JMenu("Tablas de Información"));

        //
        DBwork.add(itemGuardar = new JMenuItem("Guardar"));
        DBwork.add(itemCargar = new JMenuItem("Cargar"));

        //
        openTables.add(itemOpenInTy = new JMenuItem("Abrir tabla de tipos de instrumentos"));
        itemOpenInTy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    typeInsW = new InsTypeWinTable(mainControl);
                    desk.add(typeInsW);
                    typeInsW.show();
            }
        });

        setJMenuBar(menuPrincipal);

    }

    public void init() {
        setVisible(true);
    }

    public static void showW() {
        new GraphicForm(new Controler()).init();
    }

    private final Controler mainControl;
    private JMenuBar menuPrincipal;
    private JMenu DBwork;
    private JMenuItem itemGuardar;
    private JMenuItem itemCargar;
    private JMenu openTables;
    private JMenuItem itemOpenInTy;
    private JMenuItem itemOpenIns;
    private JMenuItem itemOpenCalib;
    private JMenuItem itemOpenMed;

    private JDesktopPane desk;
    
    private InsTypeWinTable typeInsW;

}
