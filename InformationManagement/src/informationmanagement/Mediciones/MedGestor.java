/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationmanagement.Mediciones;

import informationmanagement.DBgestores.DBgestor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class MedGestor {
    private MedGestor() throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        bd = DBgestor.obtenerGestorBD(
                DBgestor.GBD.MYSQL_SERVER,
                DBgestor.SERVIDOR_POR_DEFECTO);
    }

    public static MedGestor getInstancia() throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        if (instancia == null) {
            instancia = new MedGestor();
        }
        return instancia;
    }

    // (C)reate
    public boolean agregar(Meditions nuevaMedida) {
        boolean exito = false;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
                stm.clearParameters();
                stm.setInt(1, nuevaMedida.getNumber());
                stm.setInt(2, nuevaMedida.getReference());
                stm.setInt(3, nuevaMedida.getLecture());
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
    public Meditions recuperar(String id) {
        Meditions r = null;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, id);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        r = new Meditions(
                                rs.getInt("numero"),
                                rs.getInt("referencia"),
                                rs.getInt("lectura")
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
    public boolean actualizar(Meditions m) {
        boolean exito = false;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
                stm.clearParameters();
                stm.setInt(2, m.getReference());
                stm.setInt(3, m.getLecture());
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
    public boolean eliminar(int numero) {
        boolean exito = false;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
                stm.clearParameters();
                stm.setInt(1, numero);

                int r = stm.executeUpdate();
                exito = (r == 1);
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return exito;
    }
    
    public List<Meditions> listaMedidas() {
        List<Meditions> r = new ArrayList<>();
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(CMD_LISTAR)) {

                while (rs.next()) {
                    r.add(new Meditions(
                            rs.getInt("numero"),
                            rs.getInt("referencia"),
                            rs.getInt("lectura")
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
        List<Meditions> medidas = listaMedidas();
        Object[][] r = new Object[medidas.size()][3];
        int i = 0;
        for (Meditions e : medidas) {
            r[i][0] = e.getNumber();
            r[i][1] = e.getReference();
            r[i][2] = e.getReference();
            i++;
        }
        return r;
    }

    private static final String BASE_DATOS = "SILBD";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private static final String CMD_LISTAR
            = "SELECT numero, referencia, lectura FROM medidas ORDER BY numero;";
    private static final String CMD_AGREGAR
            = "INSERT INTO medidas (numero, referencia, lectura) VALUES (?, ?, ?);";
    private static final String CMD_RECUPERAR
            = "SELECT numero, referencia, lectura FROM medidas WHERE numero=?; ";
    private static final String CMD_ACTUALIZAR
            = "UPDATE medidas SET referencia=?, lectura=? WHERE numero=?;";
    private static final String CMD_ELIMINAR
            = "DELETE FROM medidas WHERE numero=?; ";
    private static MedGestor instancia = null;
    private final DBgestor bd;
}
