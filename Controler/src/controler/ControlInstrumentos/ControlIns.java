/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.ControlInstrumentos;

import controler.ControlMedidas.ControlMed;
import informationmanagement.Calibraciones.CalibGestor;
import informationmanagement.Instrumentos.InstGestor;
import informationmanagement.Instrumentos.Instrument;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class ControlIns {

    public boolean agregarIns(String code, String desc, String tipo, int max, int min, int t) {
        Instrument i = new Instrument(code, tipo, desc, min, max, t);
        try {
            InstGestor.getInstancia().agregar(i);
            return true;
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(ControlIns.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean borrar(String n) {
        try {
           InstGestor.getInstancia().eliminar(Integer.parseInt(n));
            return true;
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(ControlMed.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
