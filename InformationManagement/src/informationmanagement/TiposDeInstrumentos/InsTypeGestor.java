/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationmanagement.TiposDeInstrumentos;
import informationmanagement.DBgestores.DBgestor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsTypeGestor {
    private InsTypeGestor() throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        bd = DBgestor.obtenerGestorBD(
                DBgestor.GBD.MYSQL_SERVER,
                DBgestor.SERVIDOR_POR_DEFECTO);
    }

    public static InsTypeGestor getInstancia() throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        if (instancia == null) {
            instancia = new InsTypeGestor();
        }
        return instancia;
    }

    // (C)reate
    public boolean agregar(InstrumentType nuevoTipoInstrumento) {
        boolean exito = false;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
                stm.clearParameters();
                stm.setString(1, nuevoTipoInstrumento.getCode());
                stm.setString(2, nuevoTipoInstrumento.getName());
                stm.setString(3, nuevoTipoInstrumento.getUnitType());
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
    public InstrumentType recuperar(String id) {
        InstrumentType r = null;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        r = new InstrumentType(
                                rs.getString("codigo"),
                                rs.getString("nombre"),
                                rs.getString("unidad")
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
    public boolean actualizar(InstrumentType ti) {
        boolean exito = false;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
                stm.clearParameters();
                stm.setString(1, ti.getCode());
                stm.setString(2, ti.getName());
                stm.setString(3, ti.getUnitType());
                int r = stm.executeUpdate();
                exito = (r == 1);
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return exito;
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

    public List<InstrumentType> listaMedidas() {
        List<InstrumentType> r = new ArrayList<>();
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(CMD_LISTAR)) {
                while (rs.next()) {
                    r.add(new InstrumentType(
                            rs.getString("codigo"),
                            rs.getString("nombre"),
                            rs.getString("unidad")
                    ));
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return r;
    }

    public Object[][] obtenerTabla() {
        List<InstrumentType> tiposinstrumentos = listaMedidas();
        Object[][] r = new Object[tiposinstrumentos.size()][3];
        int i = 0;
        for (InstrumentType ti : tiposinstrumentos) {
            r[i][0] = ti.getCode();
            r[i][1] = ti.getName();
            r[i][2] = ti.getUnitType();
            i++;
        }
        return r;
    }

    private static final String BASE_DATOS = "SILDB";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private static final String CMD_LISTAR
            = "SELECT codigo, nombre, unidad FROM tiposinstrumentos ORDER BY codigo;";
    private static final String CMD_AGREGAR
            = "INSERT INTO tiposinstrumentos (codigo, nombre, unidad) VALUES (?, ?, ?);";
    private static final String CMD_RECUPERAR
            = "SELECT codigo, nombre, unidad FROM tiposinstrumentos WHERE codigo=?; ";
    private static final String CMD_ACTUALIZAR
            = "UPDATE tiposinstrumentos SET nombre=?, unidad=? WHERE codigo=?;";
    private static final String CMD_ELIMINAR
            = "DELETE FROM tiposinstrumentos WHERE codigo=?; ";
    private static InsTypeGestor instancia = null;
    private final DBgestor bd;
}

