package com.lewap02.learning.model;

import java.sql.*;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBConnection {

    private Properties props;
    private String url;

    public Connection con = null;
    private static Log log = LogFactory.getLog(DBConnection.class);

    public DBConnection (String inUsername, String inPassword, String inUrl) {
        props = new Properties();
        props.setProperty("user", inUsername);
        props.setProperty("password", inPassword);
        this.url = inUrl;
        try {
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection(
                    url, props
            );

            log.info("DBConnection object created");

        } catch (Exception e) {
            log.error(e);
        }
    }
}