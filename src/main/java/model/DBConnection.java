package model;

import java.sql.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DBConnection {

    private static Logger log = LogManager.getLogger(DBConnection.class);
    public static String getValFromOra(String input){
        String res="";
        try{
//step1 load the driver class
            //      Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection con=DriverManager.getConnection(
                    //"jdbc:oracle:thin:@192.168.0.129:1521:orclcdb","lewap","changeMe"
                    "jdbc:oracle:thin:lewap/changeMe@//192.168.0.129:1521/orcl"
            );

//step3 create the statement object
            Statement stmt=con.createStatement();

//step4 execute query
            ResultSet rs=stmt.executeQuery("select * from t where upper(dummy) = '" + input.toUpperCase() + "'");
            while(rs.next())
                res = res + rs.getString(1);/*+"  "+rs.getString(2)+"  "+rs.getString(3)*/


//step5 close the connection object
            con.close();

        }catch(Exception e){
            log.error(e);
            //System.out.println(e);
        }
        return res;
    }
}