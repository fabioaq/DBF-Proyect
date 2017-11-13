/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.ControlTiposdeInstrumentos;

import informationmanagement.TiposDeInstrumentos.InsTypeGestor;
import informationmanagement.TiposDeInstrumentos.InstrumentType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class ControlTdeIns {
    
    public boolean agregarIns(String c, String n, String u){
        InstrumentType i = new InstrumentType(c, n, u);
        try {
            InsTypeGestor.getInstancia().agregar(i);
            return true;
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(ControlTdeIns.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
