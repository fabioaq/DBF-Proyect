package graphicform;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author fabio
 */
public class InsForm extends JInternalFrame{

    public InsForm (){
        super("Agregar", true, true, true, true);
        config();
    }
    
    private void config(){
        fixes(getContentPane());
        setResizable(true);
        setSize(200, 400);
        setMinimumSize(new Dimension(200, 400));
    }
    
    private void fixes(Container c){
        c.setLayout(new BorderLayout());
        
    }
    
    
    private JPanel mainPanel;
    private JButton submmit;
    
    private JTextField id;
    private JTextField name;
    private JTextField cant;
    private JTextField min;
    private JTextField max;
    private JTextField tol;
    
    private JLabel idL;
    private JLabel nameL;
    private JLabel cantL;
    private JLabel minL;
    private JLabel maxL;
    private JLabel tolL;
    
}
