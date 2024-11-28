package com.lewap02.learning.model;

import java.sql.*;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBConnection {

    private Connection con = null;
    private static final Log log = LogFactory.getLog(DBConnection.class);

    public Connection getCon() {
        return this.con;
    }

    public DBConnection (String inUsername, String inPassword, String inUrl) {
        Properties props = new Properties();
        props.setProperty("user", inUsername);
        props.setProperty("password", inPassword);
        try {
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection(
                    inUrl, props
            );

            log.info("DBConnection object created");

        } catch (Exception e) {
            log.error(e);
        }
    }
}