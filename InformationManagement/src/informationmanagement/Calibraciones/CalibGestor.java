/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationmanagement.Calibraciones;

import informationmanagement.DBgestores.DBgestor;
import informationmanagement.Mediciones.MedGestor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class CalibGestor {
    private CalibGestor() throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        bd = DBgestor.obtenerGestorBD(
                DBgestor.GBD.MYSQL_SERVER,
                DBgestor.SERVIDOR_POR_DEFECTO);
    }

    public static CalibGestor getInstancia() throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        if (instancia == null) {
            instancia = new CalibGestor();
        }
        return instancia;
    }

    // (C)reate
    public boolean agregar(Calibration nuevaCalibracion) {
        boolean exito = false;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
                stm.clearParameters();
                stm.setInt(1, nuevaCalibracion.getId());
                stm.setString(2, nuevaCalibracion.getInstrument());
                stm.setDate(3, nuevaCalibracion.getDate());
                //preguntar 
                int r = stm.executeUpdate();
                exito = (r == 1);
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return exito;
    }

    // (R)etrieve
    public Calibration recuperar(String id) {
        Calibration r = null;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        r = new Calibration(
                                rs.getInt("numCalibracion"),
                                rs.getString("instrumento"),
                                rs.getDate("lectura")
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return r;
    }

    // (U)pdate
    public boolean actualizar(Calibration c) {
        boolean exito = false;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
                stm.clearParameters();
                stm.setInt(1, c.getId());
                stm.setString(2, c.getInstrument());
                stm.setDate(3, c.getDate());
                int r = stm.executeUpdate();
                exito = (r == 1);
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return exito;
    }
    
    //Search Foreign Key
    public List<Calibration> FKS(String key){
        List<Calibration> r = new ArrayList<>();
         try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_BUSCAR_FKEY);) {
                stm.clearParameters();
                stm.setString(1, key);
                ResultSet rs = stm.executeQuery();
                
                while (rs.next()) {
                    r.add(new Calibration(
                            rs.getInt("numCalibracion"),
                            rs.getString("instrumento"),
                            rs.getDate("fecha")
                    ));
                }
                
                
             }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
         return r;
    }

    // (D)elete
    public boolean eliminar(int numCalibracion) {
        boolean exito = false;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
                stm.clearParameters();
                stm.setInt(1, numCalibracion);
                int r = stm.executeUpdate();
                exito = (r == 1);
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return exito;
    }
    

    public List<Calibration> listaMedidas() {
        List<Calibration> r = new ArrayList<>();
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(CMD_LISTAR)) {

                while (rs.next()) {
                    r.add(new Calibration(
                            rs.getInt("numCalibracion"),
                            rs.getString("instrumento"),
                            rs.getDate("fecha")
                    ));
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return r;
    }

    public Object[][] obtenerTabla(String key) {
        List<Calibration> calibraciones = FKS(key);
        Object[][] r = new Object[calibraciones.size()][3];
        int i = 0;
        for (Calibration c : calibraciones) {
            r[i][0] = c.getId();
            r[i][1] = c.getInstrument();
            r[i][2] = c.getDate();
            i++;
        }
        return r;
    }
    
    public Object[][] obtenerTabla() {
        List<Calibration> calibraciones = listaMedidas();
        Object[][] r = new Object[calibraciones.size()][3];
        int i = 0;
        for (Calibration c : calibraciones) {
            r[i][0] = c.getId();
            r[i][1] = c.getInstrument();
            r[i][2] = c.getDate();
            i++;
        }
        return r;
    }

    private static final String BASE_DATOS = "SILBD";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private static final String CMD_LISTAR
            = "SELECT numCalibracion, instrumento, fecha FROM Calibraciones ORDER BY numCalibracion;";
    private static final String CMD_AGREGAR
            = "INSERT INTO Calibraciones (numCalibracion, instrumento, fecha) VALUES (?, ?, ?);";
    private static final String CMD_RECUPERAR
            = "SELECT numCalibracion, instrumento, fecha FROM Calibraciones WHERE numCalibracion=?; ";
    private static final String CMD_ACTUALIZAR
            = "UPDATE Calibraciones SET instrumento=?, fecha=? WHERE numCalibracion=?;";
    private static final String CMD_ELIMINAR
            = "DELETE FROM Calibraciones WHERE numCalibracion=?; ";
    private static final String CMD_BUSCAR_FKEY
            = "SELECT numCalibracion, instrumento, fecha FROM Calibraciones WHERE instrumento=?";
    private static CalibGestor instancia = null;
    private final DBgestor bd;
}
