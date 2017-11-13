/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicform.viewInstrumentos;

import controler.ControlInstrumentos.ControlIns;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.*;

/**
 *
 * @author fabio
 */
public class agrgarFormI extends JFrame {

    public agrgarFormI(ControlIns c) {
        super("editar");
        this.controlPrincipal = c;
        config();
    }
    
    public static agrgarFormI getInstancia(ControlIns c) throws
        InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        if (instancia == null) {
            instancia = new agrgarFormI(c);
        }
        return instancia;
        
    }

    private void config() {
         ajustes(getContentPane());
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
    }

    private void ajustes(Container c) {
        c.setLayout(new BorderLayout());
    }

    public void init() {
        setVisible(true);
    }
    
    
    private JButton act;
    private final ControlIns controlPrincipal;
    private static agrgarFormI instancia;
}
