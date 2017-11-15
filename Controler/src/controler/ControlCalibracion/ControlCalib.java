/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.ControlCalibracion;

import controler.ControlMedidas.ControlMed;
import informationmanagement.Calibraciones.CalibGestor;
import informationmanagement.Calibraciones.Calibration;
import informationmanagement.Mediciones.MedGestor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class ControlCalib {

    public boolean agregarCal(int id, String ins) {
        Calibration c = new Calibration(id, ins, new java.sql.Date(new java.util.Date().getTime()));
        try {
            CalibGestor.getInstancia().agregar(c);
            return true;
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(ControlCalib.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean borrar(int n) {
        try {
           CalibGestor.getInstancia().eliminar(n);
            return true;
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(ControlMed.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
