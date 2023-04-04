package model;

import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBConnection {

    private String username;
    private String password;
    private String url;

    public Connection con = null;
    private static Log log = LogFactory.getLog(DBConnection.class);

    public DBConnection (String inUsername, String inPassword, String inUrl) {
        this.username = inUsername;
        this.password = inPassword;
        this.url = inUrl;
        try {
            con=DriverManager.getConnection(
                    url, username, password
            );

            log.info("DBConnection object created");

        } catch (Exception e) {
            log.error(e);
        }
    }
}