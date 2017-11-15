/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.ControlMedidas;

import informationmanagement.Mediciones.MedGestor;
import informationmanagement.Mediciones.Meditions;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class ControlMed {

    public boolean agregarMed(int n, int r, int l, int k) {
        Meditions m = new Meditions(n, r, l);
        try {
            MedGestor.getInstancia().agregar(m, k);
            return true;
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(ControlMed.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean borrar(int i){
        try {
            MedGestor.getInstancia().eliminar(i);
            return true;
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(ControlMed.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
