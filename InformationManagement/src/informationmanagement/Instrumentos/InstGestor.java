package informationmanagement.Instrumentos;

import informationmanagement.DBgestores.DBgestor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InstGestor {
   private InstGestor() throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        bd = DBgestor.obtenerGestorBD(
                DBgestor.GBD.MYSQL_SERVER,
                DBgestor.SERVIDOR_POR_DEFECTO);
    }

    public static InstGestor getInstancia() throws
            InstantiationException,
            ClassNotFoundException,
            IllegalAccessException {
        if (instancia == null) {
            instancia = new InstGestor();
        }
        return instancia;
    }

    // (C)reate
    public boolean agregar(Instrument nuevoInstrumento) {
        boolean exito = false;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
                stm.clearParameters();
                stm.setString(1, nuevoInstrumento.getId());
                stm.setString(2, nuevoInstrumento.getType());
                stm.setString(3, nuevoInstrumento.getName());
                stm.setInt(4, nuevoInstrumento.getMin());
                stm.setInt(5, nuevoInstrumento.getMax());
                stm.setInt(6, nuevoInstrumento.getTol());
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
    public Instrument recuperar(String id) {
        Instrument r = null;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        r = new Instrument(
                                rs.getString("serie"),
                                rs.getString("tipo"),
                                rs.getString("descripcion"),
                                rs.getInt("minimo"),
                                rs.getInt("maximo"),
                                rs.getInt("tolerancia")
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
    public boolean actualizar(Instrument i) {
        boolean exito = false;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
                stm.clearParameters();
                stm.setString(1, i.getId());
                stm.setString(2, i.getType());
                stm.setString(3, i.getName());
                stm.setInt(4, i.getMin());
                stm.setInt(5, i.getMax());
                stm.setInt(6, i.getTol());
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
    public boolean eliminar(int serie) {
        boolean exito = false;
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
                stm.clearParameters();
                stm.setInt(1, serie);
                int r = stm.executeUpdate();
                exito = (r == 1);
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return exito;
    }

    public List<Instrument> listaMedidas() {
        List<Instrument> r = new ArrayList<>();
        try {
            try (Connection cnx = bd.obtenerConexion(BASE_DATOS, USUARIO, CLAVE);
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(CMD_LISTAR)) {
                while (rs.next()) {
                    r.add(new Instrument(
                            rs.getString("serie"),
                            rs.getString("tipo"),
                            rs.getString("descripcion"),
                            rs.getInt("minimo"),
                            rs.getInt("maximo"),
                            rs.getInt("tolerancia")
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
        List<Instrument> instrumentos = listaMedidas();
        Object[][] r = new Object[instrumentos.size()][6];
        int i = 0;
        for (Instrument in : instrumentos) {
            r[i][0] = in.getId();
            r[i][1] = in.getType();
            r[i][2] = in.getName();
            r[i][3] = in.getMin();
            r[i][4] = in.getMax();
            r[i][5] = in.getTol();
            i++;
        }
        return r;
    }

    private static final String BASE_DATOS = "SILBD";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private static final String CMD_LISTAR
            = "SELECT serie, tipo, descripcion, minimo, maximo,"
            + " tolerancia FROM Instrumentos ORDER BY serie;";
    private static final String CMD_AGREGAR
            = "INSERT INTO Instrumentos (serie, tipo, descripcion,"
            + " minimo, maximo, tolerancia) VALUES (?, ?, ?);";
    private static final String CMD_RECUPERAR
            = "SELECT serie, tipo, descripcion, minimo, maximo,"
            + " tolerancia FROM Instrumentos WHERE serie=?; ";
    private static final String CMD_ACTUALIZAR
            = "UPDATE Instrumentos SET tipo=?, descripcion=?, "
            + "minimo=?, maximo=?, tolerancia=? WHERE serie=?;";
    private static final String CMD_ELIMINAR
            = "DELETE FROM Instrumentos WHERE serie=?; ";
    private static InstGestor instancia = null;
    private final DBgestor bd;
}
