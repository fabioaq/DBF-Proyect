package informationmanagement.DBgestores;

/**
 *
 * @author fabio
 */
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBgestor {

    protected DBgestor(String descripcion, String URL_servidor) {
        this.descripcion = descripcion;
        this.URL_servidor = (URL_servidor != null) ? URL_servidor : SERVIDOR_POR_DEFECTO;
    }

    public abstract Connection obtenerConexion(String baseDatos, String usuario, String claveAcceso)
            throws SQLException;

    public void cerrarConexion() {
        if (cnx != null) {
            try {
                cnx.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public static DBgestor obtenerGestorBD(GBD tipoServidor, String URL_servidor)
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            switch (tipoServidor) {
                case MYSQL_SERVER:
                    instancia = new MySQLgestor(URL_servidor);
                    break;
                default:
                    throw new InstantiationException("El tipo de servidor no se encuentra implementado.");
            }
        }
        return instancia;
    }

    public static DBgestor obtenerGestorBD(GBD tipoServidor)
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        return obtenerGestorBD(tipoServidor, SERVIDOR_POR_DEFECTO);
    }

    public static DBgestor obtenerGestorBD()
            throws InstantiationException {
        if (instancia == null) {
            throw new InstantiationException("Instancia inválida.");
        }
        return instancia;
    }

    public String obtenerURL() {
        return URL_servidor;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append(String.format(
                "%s%nServidor de base de datos en: %s",
                descripcion, URL_servidor));
        return r.toString();
    }

    public static final String SERVIDOR_POR_DEFECTO = "localhost";

    public enum GBD {
        MYSQL_SERVER,
        POSTGRESQL,
        MSSQL_SERVER,
        ORACLE_SERVER
    };

    protected static DBgestor instancia = null;

    protected Connection cnx = null;
    protected String URL_servidor = null;
    private String descripcion = null;
}

