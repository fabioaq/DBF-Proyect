/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicform;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    private void fixes(Container c){
        
            c.setLayout(new BorderLayout());
            
           
        
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
