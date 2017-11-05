package graphicform;

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
public class MedForm extends JInternalFrame{

    public MedForm() {
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
        
    }
    
    
    private JPanel mainPanel;
    private JButton submmit;
    
    private JTextField num;
    private JTextField lec;
    private JTextField ref;
    
    private JLabel numL;
    private JLabel lecL;
    private JLabel refL;
}
