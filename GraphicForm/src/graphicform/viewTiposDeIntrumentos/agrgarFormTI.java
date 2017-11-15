/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicform.viewTiposDeIntrumentos;

import controler.ControlTiposdeInstrumentos.ControlTdeIns;
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
public class agrgarFormTI extends JFrame {


    public agrgarFormTI(ControlTdeIns controlPrincipal) {
        super("Agregar");
        this.controlPrincipal = controlPrincipal;
        config();
    }
    

    private void config() {
        ajustes(getContentPane());
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
    }

    private void ajustes(Container c) {
        c.setLayout(new BorderLayout());
        //
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 50, 50));
        //
        mainPanel.add(id = new JLabel("Codigo"));
        mainPanel.add(idF = new JTextField());
        //
        mainPanel.add(nombre = new JLabel("Nombre"));
        mainPanel.add(nombreF = new JTextField());
        //
        mainPanel.add(unidad = new JLabel("Tipo de Unidad"));
        mainPanel.add(unidadF = new JTextField());
        //
        c.add(BorderLayout.CENTER, mainPanel);
        c.add(BorderLayout.PAGE_END, act = new JButton("Acceptar"));
        //
        act.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idF.getText().isEmpty() || nombreF.getText().isEmpty() || unidadF.getText().isEmpty()) {
                    System.err.println("Error Espacios en blanco");
                } else {
                    if (!controlPrincipal.agregarIns(idF.getText(), nombreF.getText(), unidadF.getText())) {
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
    private JLabel nombre;
    private JLabel unidad;
    private JTextField idF;
    private JTextField nombreF;
    private JTextField unidadF;
    private final ControlTdeIns controlPrincipal;
}
