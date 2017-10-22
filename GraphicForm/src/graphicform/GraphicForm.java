/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicform;

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
        table = new JTable();
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
        
         try {
             c.setLayout(new BorderLayout());
             c.add(BorderLayout.CENTER,
                     mainPanel = new JPanel());
             mainPanel.setLayout(new BorderLayout());
             mainPanel.add(BorderLayout.CENTER,
                     new JScrollPane(
                             table
                                     = new JTable(
                                             InformationManagement.getInstance().obtenerTabla(),
                                             InstrumentType.getDescription()),
                             JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                             JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
                     )
             );
         } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
             Logger.getLogger(GraphicForm.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    public void init(){
        setVisible(true);
    }
    
    public static void showW(){
        new GraphicForm().init();
    }
    
    
    private JPanel mainPanel;
    private JTable table;
    
    
}
