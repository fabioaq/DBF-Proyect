/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicform.viewMediciones;

import controler.ControlMedidas.ControlMed;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author fabio
 */
public class ventanaMediciones extends JFrame {

    
    private ventanaMediciones(int key) {
        super("Mediciones");
        controlPrincipal = new ControlMed();
        config(key);
    }
    
    public static ventanaMediciones getInstancia(int key) throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        if (instancia == null) {
            instancia = new ventanaMediciones(key);
        }
        return instancia;

    }
    
    private void config(int key) {
        ajustes(getContentPane(), key);
        setResizable(false);
        setResizable(true);
        setSize(600, 600);
        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
    }
    
    private void ajustes(Container c, int key) {
        c.setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        //
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(borrar = new JButton("Borrar"));
        mainPanel.add(agregar = new JButton("Agregar"));
        mainPanel.add(buscar = new JButton("agregar"));
        mainPanel.add(listar = new JButton("Listar Tabla"));
        //
        c.add(BorderLayout.NORTH, mainPanel);
        c.add(BorderLayout.SOUTH, new JScrollPane(tabla = new JTable(MeditionsTable.getInstancia(key)),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        //
        agregar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                agrgarFormM w = new agrgarFormM(controlPrincipal, key);
                w.init();
            }
            
        });
        
        borrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = (int)tabla.getModel().getValueAt(tabla.getSelectedRow(), 0);
                if(!controlPrincipal.borrar(n)){
                    System.err.print("Error: Elemento inexistente");
                }
            }
            
        });
        
        listar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
            
        });
        
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
            
        });
        //
        
    }

    public void init() {
        setVisible(true);
    }

    private JButton borrar;
    private JButton agregar;
    private JButton buscar;
    private JButton listar;
    private JTable tabla;
    private final ControlMed controlPrincipal;
    private static ventanaMediciones instancia = null;
}
