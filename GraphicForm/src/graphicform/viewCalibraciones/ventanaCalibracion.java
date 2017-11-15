/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicform.viewCalibraciones;

import controler.ControlCalibracion.ControlCalib;
import graphicform.viewMediciones.ventanaMediciones;
import graphicform.viewTiposDeIntrumentos.ventanaTiposInstrumentos;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author fabio
 */
public class ventanaCalibracion extends JFrame {

    public ventanaCalibracion(String key) {
        super("Calibraciones");
        this.controlPrincipal = new ControlCalib();
        config(key);
    }
    
    public static ventanaCalibracion getInstancia(String key) throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        if (instancia == null) {
            instancia = new ventanaCalibracion(key);
        }
        return instancia;

    }

    private void config(String key) {
        ajustes(getContentPane(), key);
        setResizable(false);
        setResizable(true);
        setSize(600, 600);
        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
    }

    private void ajustes(Container c, String key) {
        c.setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        //
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(borrar = new JButton("Borrar"));
        mainPanel.add(agregar = new JButton("Agregar"));
        mainPanel.add(buscar = new JButton("agregar"));
        mainPanel.add(listar = new JButton("Listar Tabla"));
        //
        agregar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                agrgarFormC w = new agrgarFormC(controlPrincipal, key);
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
        c.add(BorderLayout.NORTH, mainPanel);
        c.add(BorderLayout.SOUTH, new JScrollPane(tabla = new JTable(CalibTable.getInstancia(key)),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        //
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 3){
                    JTable target = (JTable)e.getSource();
                    int key = (int)target.getModel().getValueAt(target.getSelectedRow(), 0);
                    try {
                        ventanaMediciones w = ventanaMediciones.getInstancia(key);
                        w.init();
                    } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
                        Logger.getLogger(ventanaTiposInstrumentos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.print(key);
                }
            }
        });
    }

    public void init() {
        setVisible(true);
    }

    private JButton borrar;
    private JButton agregar;
    private JButton buscar;
    private JButton listar;
    private JTable tabla;
    private final ControlCalib controlPrincipal;
    private static ventanaCalibracion instancia = null;
}
