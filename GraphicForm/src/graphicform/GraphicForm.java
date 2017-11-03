
package graphicform;

import informationmanagement.InformationManagement;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author fabio
 */
public class GraphicForm extends JFrame{

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
                   public void run(){
                        showW();
                    }
        });
    }

    public GraphicForm() {
        config();
    }
    
    
    
    
    private void config(){
        fixes(getContentPane());
        setResizable(true);
        setSize(400, 600);
         setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    private void fixes(Container c){
        
             c.setLayout(new BorderLayout());
             c.add(BorderLayout.CENTER,
                     mainPanel = new JPanel());
             mainPanel.setLayout(new BorderLayout());
             b = new JButton("Ver Tipos de instrumento");
             b.addActionListener(new ActionListener(){
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     try {
                         if(InformationManagement.getInstance().areTypes()){
                             table = new InsWinTable(InformationManagement.getInstance());
                         }
                     } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
                         Logger.getLogger(GraphicForm.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 
             });
             mainPanel.add(BorderLayout.CENTER, b);
             
        
    }
    
    public void init(){
        setVisible(true);
    }
    
    public static void showW(){
        new GraphicForm().init();
    }
    
    private InsWinTable table;
    private JPanel mainPanel;
    private JButton b;
    private JTextField t;
    
}
