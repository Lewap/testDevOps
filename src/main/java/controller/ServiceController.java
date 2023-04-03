package controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import model.DBConnection;

@Path("/")
public class ServiceController {

    //jdbc:oracle:thin:lewap/changeMe@//192.168.0.129:1521/orcl
    String URL = "jdbc:oracle:thin:@//192.168.0.129:1521/orcl";
    String USERNAME = "lewap";
    String PASSWORD = "changeMe";

    private static Logger log = LogManager.getLogger(ServiceController.class);

    private String getValFromOra(String input){

            String res="";
            try{

                DBConnection dbconn = new DBConnection(USERNAME, PASSWORD, URL);

                Statement stmt=dbconn.con.createStatement();

                ResultSet rs=stmt.executeQuery("select * from t where upper(dummy) = '" + input.toUpperCase() + "'");
                while(rs.next())
                    res = res + rs.getString(1);/*+"  "+rs.getString(2)+"  "+rs.getString(3)*/

            } catch(Exception e) {
                log.error(e);
                //System.out.println(e);
            }
            return res;
    }
    @GET
    @Path("/getSomething")
    @Produces("text/html")
    public Response sampleResponse() {
        return Response.ok().entity("Some response from the Webo Servico. DB Result = " + getValFromOra("X")).build();

    }
}