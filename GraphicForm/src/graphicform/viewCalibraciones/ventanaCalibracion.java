/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicform.viewCalibraciones;

import controler.ControlCalibracion.ControlCalib;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author fabio
 */
public class ventanaCalibracion extends JFrame{

    public ventanaCalibracion() {
        super("Calibraciones");
        this.controlPrincipal = new ControlCalib();
        
    }
    private void config(){
        ajustes(getContentPane());
        setResizable(false);
        setResizable(true);
        setSize(600, 600);
        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
    }
    
    private void ajustes(Container c){
        
    }
    
    public void init(){
        setVisible(true);
    }
    
    
    
    
    
    
    
    private JButton borrar;
    private JButton agregar;
    private JButton buscar;
    private JButton listar;
    private final ControlCalib controlPrincipal;
}
