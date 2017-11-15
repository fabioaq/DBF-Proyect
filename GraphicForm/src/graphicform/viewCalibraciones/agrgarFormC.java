package graphicform.viewCalibraciones;

import controler.ControlCalibracion.ControlCalib;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author fabio
 */
public class agrgarFormC extends JFrame{

    
    public agrgarFormC(ControlCalib controlPrincipal, String key) {
        super("Agregar");
        this.controlPrincipal = controlPrincipal;
        config(key);
    }
    
    private void config(String key) {
        ajustes(getContentPane(), key);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
    }
    
    private void ajustes(Container c, String key) {
        c.setLayout(new BorderLayout());
        //
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2, 50, 50));
        //
        mainPanel.add(id = new JLabel("Codigo Calibracion"));
        mainPanel.add(idF = new JTextField());
        //
        c.add(BorderLayout.CENTER, mainPanel);
        c.add(BorderLayout.PAGE_END, act = new JButton("Acceptar"));
        //
        act.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idF.getText().isEmpty()) {
                    System.err.println("Error Espacios en blanco");
                } else {
                    if (!controlPrincipal.agregarCal(Integer.parseInt(idF.getText()), key)) {
                        System.err.println("Elemento Invalido");
                    }
                    setVisible(false);
                    dispose();

                }
            }

        });
    }
    
    public void init() {
        setVisible(true);
    }

    private JButton act;
    private JLabel id;
    private JTextField idF;
    private final ControlCalib controlPrincipal;

    
}
