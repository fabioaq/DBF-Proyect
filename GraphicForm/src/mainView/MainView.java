package mainView;

import graphicform.viewInstrumentos.ventanaInstrumentos;
import graphicform.viewTiposDeIntrumentos.ventanaTiposInstrumentos;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author fabio
 */
public class MainView extends JFrame{

    public MainView() {
        super("Inventario");
        config();
    }
    
    private void config(){
        ajustes(getContentPane());
        setResizable(false);
        setResizable(true);
        setSize(600, 600);
        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void ajustes(Container c){
        c.setLayout(new BorderLayout());
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout());
        panelPrincipal.add(BorderLayout.EAST, tableInstrumentos = new JButton("Abrir Tabla de Instrumentos"));
        panelPrincipal.add(BorderLayout.WEST, tablaTipoInstrumentos = new JButton("Abrir Tabla de Tipos de Instrumentos"));
        c.add(BorderLayout.CENTER, panelPrincipal);
        
        //
        tableInstrumentos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Hola");
                ventanaInstrumentos v = new ventanaInstrumentos();
                v.init();
            }
            
        });
        //
        tablaTipoInstrumentos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Adios");
                ventanaTiposInstrumentos v = new ventanaTiposInstrumentos();
                v.init();
            }
            
        });
        //
        
    }
    
    public void Init(){
        setVisible(true);
    }
    
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

        SwingUtilities.invokeLater(() -> {
            new MainView().Init();
        });
    }
    
    
    private JButton tableInstrumentos;
    private JButton tablaTipoInstrumentos;
    private JPanel panelPrincipal;
    
}


