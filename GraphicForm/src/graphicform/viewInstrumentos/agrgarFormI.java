package graphicform.viewInstrumentos;

import controler.ControlInstrumentos.ControlIns;
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
public class agrgarFormI extends JFrame {

    public agrgarFormI(ControlIns c) {
        super("Agregar");
        this.controlPrincipal = c;
        config();
    }
    
    public agrgarFormI(ControlIns c, String key) {
        super("Agregar");
        this.controlPrincipal = c;
        config(key);
    }
    
    private void config() {
         ajustes(getContentPane());
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
    }
    
    private void config(String key) {
         ajustes(getContentPane(), key);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
    }

    private void ajustes(Container c) {
        c.setLayout(new BorderLayout());
        //
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 50, 50));
        //
        mainPanel.add(id = new JLabel("Codigo de Instrumento"));
        mainPanel.add(idF = new JTextField());
        //
        mainPanel.add(nombre = new JLabel("Descripción de Instrumento"));
        mainPanel.add(nombreF = new JTextField());
        //
        mainPanel.add(tipo = new JLabel("Tipo de Instrumento"));
        mainPanel.add(tipoF = new JTextField());
        //
        mainPanel.add(max = new JLabel("Maximo"));
        mainPanel.add(maxF = new JTextField());
        //
        mainPanel.add(min = new JLabel("Minimo"));
        mainPanel.add(minF = new JTextField());
        //
        mainPanel.add(tol = new JLabel("Tolerancia"));
        mainPanel.add(tolF = new JTextField());
        //
        c.add(BorderLayout.CENTER, mainPanel);
        c.add(BorderLayout.PAGE_END, act = new JButton("Acceptar"));
        //
        act.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idF.getText().isEmpty() || nombreF.getText().isEmpty() || tipoF.getText().isEmpty() || 
                        maxF.getText().isEmpty()|| minF.getText().isEmpty() || tolF.getText().isEmpty()) {
                    System.err.println("Error Espacios en blanco");
                } else {
                    if (!controlPrincipal.agregarIns(idF.getText(), nombreF.getText(), tipoF.getText(), 
                            Integer.parseInt(maxF.getText()), Integer.parseInt(minF.getText()), Integer.parseInt(tolF.getText()))) {
                        System.err.println("Elemento Invalido");
                    }
                    setVisible(false);
                    dispose();

                }
            }

        });
    }
    
    private void ajustes(Container c, String key) {
        c.setLayout(new BorderLayout());
        //
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 50, 50));
        //
        mainPanel.add(id = new JLabel("Codigo de Instrumento"));
        mainPanel.add(idF = new JTextField());
        //
        mainPanel.add(nombre = new JLabel("Descripción de Instrumento"));
        mainPanel.add(nombreF = new JTextField());
        //
        mainPanel.add(max = new JLabel("Maximo"));
        mainPanel.add(maxF = new JTextField());
        //
        mainPanel.add(min = new JLabel("Minimo"));
        mainPanel.add(minF = new JTextField());
        //
        mainPanel.add(tol = new JLabel("Tolerancia"));
        mainPanel.add(tolF = new JTextField());
        //
        c.add(BorderLayout.CENTER, mainPanel);
        c.add(BorderLayout.PAGE_END, act = new JButton("Acceptar"));
        //
        act.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idF.getText().isEmpty() || nombreF.getText().isEmpty() || 
                        maxF.getText().isEmpty()|| minF.getText().isEmpty() || tolF.getText().isEmpty()) {
                    System.err.println("Error Espacios en blanco");
                } else {
                    if (!controlPrincipal.agregarIns(idF.getText(), nombreF.getText(), key,
                            Integer.parseInt(maxF.getText()), Integer.parseInt(minF.getText()), Integer.parseInt(tolF.getText()))) {
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
    
    private JLabel id;
    private JLabel nombre;
    private JLabel tipo;
    private JLabel min;
    private JLabel max;
    private JLabel tol;
    private JTextField idF;
    private JTextField nombreF;
    private JTextField tipoF;
    private JTextField minF;
    private JTextField maxF;
    private JTextField tolF;
    private JButton act;
    private final ControlIns controlPrincipal;
    private static agrgarFormI instancia;
}
