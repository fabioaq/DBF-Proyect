/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationmanagement.DBgestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fabio
 */
public class MySQLgestor extends DBgestor{
    // <editor-fold defaultstate="collapsed" desc="constructores">
    MySQLgestor(String servidor)
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        super("Gestor MySQL", servidor);
        Class.forName(MANEJADOR_DB).newInstance();
    }

    MySQLgestor()
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        this(SERVIDOR_POR_DEFECTO);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="métodos">
    @Override
    public Connection obtenerConexion(String baseDatos,
            String usuario, String claveAcceso)
            throws SQLException {
        cerrarConexion();
        String URL_conexion
                = String.format("%s//%s/%s", PROTOCOLO, URL_servidor, baseDatos);

        cnx = DriverManager.getConnection(URL_conexion, usuario, claveAcceso);
        return cnx;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="atributos">
    // Parámetros para la conexión a un servidor de base de datos MySQL.
    private static final String MANEJADOR_DB = "com.mysql.jdbc.Driver";
    private static final String PROTOCOLO = "jdbc:mysql:";
    // </editor-fold>
}
