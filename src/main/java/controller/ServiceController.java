package controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import model.DBConnection;

@Path("/")
public class ServiceController {
    String URL = "jdbc:oracle:thin:@//192.168.0.129:1521/orcl";

    private static Logger log = LogManager.getLogger(ServiceController.class);

    private String getValFromOra(String username, String password, String argDB){

            String res="";
            try{

                DBConnection dbconn = new DBConnection(username, password, URL);

                Statement stmt=dbconn.con.createStatement();

                ResultSet rs=stmt.executeQuery("select * from t where upper(dummy) = '" + argDB.toUpperCase() + "'");
                while(rs.next())
                    res = res + rs.getString(1);/*+"  "+rs.getString(2)+"  "+rs.getString(3)*/

            } catch(Exception e) {
                log.error(e);
                //System.out.println(e);
            }
            return res;
    }

    @Path("/getSomething")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response sampleResponse(@FormParam("Username") String username,
                                   @FormParam("Password") String password,
                                   @FormParam("ArgDB") String argDB) {
        return Response.ok().entity("Some response from the Webo Servico. DB Result = " + getValFromOra(username, password, argDB)).build();

    }
}