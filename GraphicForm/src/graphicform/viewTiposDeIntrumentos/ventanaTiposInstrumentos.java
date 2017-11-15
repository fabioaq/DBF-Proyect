/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicform.viewTiposDeIntrumentos;

import controler.ControlTiposdeInstrumentos.ControlTdeIns;
import graphicform.viewInstrumentos.ventanaInstrumentos;
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
public class ventanaTiposInstrumentos extends JFrame {

    private ventanaTiposInstrumentos() {
        super("Tipos de Instrumentos");
        this.controlPrincipal = new ControlTdeIns();
        config();
    }

    public static ventanaTiposInstrumentos getInstancia() throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        if (instancia == null) {
            instancia = new ventanaTiposInstrumentos();
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
        c.add(BorderLayout.SOUTH, new JScrollPane(tabla = new JTable(InstrumentTypeTable.getInstancia()),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        //
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 3) {
                    JTable target = (JTable) e.getSource();
                    String key = (String) target.getModel().getValueAt(target.getSelectedRow(), 0);
                    try {
                        ventanaInstrumentos w = ventanaInstrumentos.getInstancia(key);
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
                agrgarFormTI w;
                w = new agrgarFormTI(controlPrincipal);
                w.init();
                recargar();

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

    public void recargar() {
        this.invalidate();
        this.validate();
        this.repaint();
    }

    public void init() {
        setVisible(true);
    }

    private JButton borrar;
    private JButton agregar;
    private JButton buscar;
    private JButton listar;
    private JTable tabla;
    private final ControlTdeIns controlPrincipal;
    private static ventanaTiposInstrumentos instancia;
}
