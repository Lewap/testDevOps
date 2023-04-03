package model;

import java.sql.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DBConnection {

    //public static volatile DBConnection instance;
    private String username;
    private String password;
    private String url;

    public Connection con = null;
    private static Logger log = LogManager.getLogger(DBConnection.class);

    public DBConnection (String inUsername, String inPassword, String inUrl) {
        this.username = inUsername;
        this.password = inPassword;
        this.url = inUrl;
        try {
            con=DriverManager.getConnection(
                    //"jdbc:oracle:thin:@192.168.0.129:1521:orclcdb","lewap","changeMe"
                    //"jdbc:oracle:thin:lewap/changeMe@//192.168.0.129:1521/orcl"
                    url, username, password
            );
        } catch (Exception e) {
            log.error(e);
        }
    }
}