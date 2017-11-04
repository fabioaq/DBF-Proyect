package graphicform;

import controler.Controler;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
        setSize(400, 600);
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void fixes(Container c) {

        c.setLayout(new BorderLayout());
        c.add(BorderLayout.CENTER,
                mainPanel = new JPanel());
        mainPanel.setLayout(new BorderLayout());
        menuPrincipal = new JMenuBar();
        menuPrincipal.add(menuVerificar = new JMenu("Test"));
        menuVerificar.add(itemGuardar = new JMenuItem("Hola"));

        setJMenuBar(menuPrincipal);
        b = new JButton("Ver Tipos de instrumento");
        mainPanel.add(BorderLayout.CENTER, b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (mainControl.getTable() != null) {
                    System.out.print("entro \n");
                    Thread thread = new Thread(new InsTypeWinTable(mainControl));
                    thread.start();

                }

            }

        });

    }

    public void init() {
        setVisible(true);
    }

    public static void showW() {
        new GraphicForm(new Controler()).init();
    }

    private JPanel mainPanel;
    private JButton b;
    private JTextField t;
    private final Controler mainControl;
    private JMenuBar menuPrincipal;
    private JMenu menuVerificar;
    private JMenuItem itemGuardar;

}
