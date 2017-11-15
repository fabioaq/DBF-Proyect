/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicform.viewInstrumentos;

import controler.ControlInstrumentos.ControlIns;
import graphicform.viewCalibraciones.ventanaCalibracion;
import graphicform.viewTiposDeIntrumentos.InstrumentTypeTable;
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
public class ventanaInstrumentos extends JFrame {

    private ventanaInstrumentos() {
        super("Instrumentos");
        this.controlPrincipal = new ControlIns();
        config();
    }

    private ventanaInstrumentos(String key) {
        super("Instrumentos");
        this.controlPrincipal = new ControlIns();
        config(key);
    }

    public static ventanaInstrumentos getInstancia() throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        if (instancia == null) {
            instancia = new ventanaInstrumentos();
        }
        return instancia;

    }

    public static ventanaInstrumentos getInstancia(String k) throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        if (instancia == null) {
            instancia = new ventanaInstrumentos(k);
        }
        return instancia;

    }

    private void config() {
        ajustes(getContentPane());
        setResizable(false);
        setResizable(true);
        setSize(600, 600);
        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
    }

    private void config(String k) {
        ajustes(getContentPane(), k);
        setResizable(false);
        setResizable(true);
        setSize(600, 600);
        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
    }

    public void closeOperation() {
        instancia = null;
    }

    private void ajustes(Container c) {
        c.setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        //
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(borrar = new JButton("Borrar"));
        mainPanel.add(agregar = new JButton("Agregar"));
        mainPanel.add(buscar = new JButton("Buscar"));
        mainPanel.add(listar = new JButton("Listar Tabla"));
        //
        c.add(BorderLayout.NORTH, mainPanel);
        c.add(BorderLayout.SOUTH, new JScrollPane(tabla = new JTable(InstrumentTable.getInstancia()),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        //
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agrgarFormI w;
                w = new agrgarFormI(controlPrincipal);
                w.init();
                tabla = new JTable(InstrumentTypeTable.getInstancia());
            }

        });

        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = (String)tabla.getModel().getValueAt(tabla.getSelectedRow(), 0);
                if(!controlPrincipal.borrar(n)){
                    System.err.print("Error: Elemento inexistente");
                }
            }

        });

        listar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

    }

    private void ajustes(Container c, String key) {
        c.setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        //
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(borrar = new JButton("Borrar"));
        mainPanel.add(agregar = new JButton("Agregar"));
        mainPanel.add(buscar = new JButton("Buscar"));
        mainPanel.add(listar = new JButton("Listar Tabla"));
        //
        c.add(BorderLayout.NORTH, mainPanel);
        c.add(BorderLayout.SOUTH, new JScrollPane(tabla = new JTable(InstrumentTable.getInstancia(key)),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        //
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 3){
                    JTable target = (JTable)e.getSource();
                    String key = (String)target.getModel().getValueAt(target.getSelectedRow(), 0);
                    try {
                        ventanaCalibracion w = ventanaCalibracion.getInstancia(key);
                        w.init();
                    } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
                        Logger.getLogger(ventanaTiposInstrumentos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.print(key);
                }
            }
        });
        
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agrgarFormI w;
                w = new agrgarFormI(controlPrincipal, key);
                w.init();
                tabla = new JTable(InstrumentTypeTable.getInstancia());
            }

        });

        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        listar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
    private final ControlIns controlPrincipal;
    private static ventanaInstrumentos instancia = null;

}
