/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicform.viewMediciones;

import controler.ControlMedidas.ControlMed;
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
public class agrgarFormM extends JFrame {

    agrgarFormM(ControlMed controlPrincipal, int key) {
        super("Agregar");
        this.controlPrincipal = controlPrincipal;
        config(key);
    }

    private void config(int key) {
        ajustes(getContentPane(), key);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
    }

    private void ajustes(Container c, int key) {
        c.setLayout(new BorderLayout());
        //
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 50, 50));
        //
        mainPanel.add(numero = new JLabel("Numero"));
        mainPanel.add(numeroF = new JTextField());
        //
        mainPanel.add(Referencia = new JLabel("Referencia"));
        mainPanel.add(referenciaF = new JTextField());
        //
        mainPanel.add(Lectura = new JLabel("Lectura"));
        mainPanel.add(lecturaF = new JTextField());
        //
        c.add(BorderLayout.CENTER, mainPanel);
        c.add(BorderLayout.PAGE_END, act = new JButton("Acceptar"));
        //
        act.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numeroF.getText().isEmpty() || referenciaF.getText().isEmpty() || lecturaF.getText().isEmpty()) {
                    System.err.println("Error Espacios en blanco");
                } else {
                    if (!controlPrincipal.agregarMed(Integer.parseInt(numeroF.getText()), Integer.parseInt(referenciaF.getText()), Integer.parseInt(lecturaF.getText()), key)) {
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
    private JLabel numero;
    private JLabel Referencia;
    private JLabel Lectura;
    private JTextField numeroF;
    private JTextField referenciaF;
    private JTextField lecturaF;
    private final ControlMed controlPrincipal;
}
