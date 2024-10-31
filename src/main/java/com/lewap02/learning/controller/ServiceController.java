package com.lewap02.learning.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.ResultSet;
import java.sql.Statement;

import com.lewap02.learning.model.Employee;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lewap02.learning.model.SessionFactoryProvider;
import com.lewap02.learning.model.DBConnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Path("/services")
//@Provider
public class ServiceController {
    //String URL = "jdbc:oracle:thin:@//192.168.0.129:1521/orcl";
    String URL = "jdbc:postgresql://localhost:5432/lewap";

    private static Log log = LogFactory.getLog(ServiceController.class);

    private String getValFromDB(String username, String password, String argDB){

            String res="";
            try{

                DBConnection dbconn = new DBConnection(username, password, URL);

                Statement stmt=dbconn.con.createStatement();

                //PreparedStatement ps = dbconn.con.prepareStatement("CREATE TABLE t2 (dummy TEXT)");
                //ps.executeUpdate();
                //ps = dbconn.con.prepareStatement("INSERT INTO t2 VALUES (\"X\")");
                //ps.close();

                ResultSet rs=stmt.executeQuery("select * from t where upper(dummy) = '" + argDB.toUpperCase() + "'");
                //ResultSet rs=stmt.executeQuery("select * from pg_catalog.pg_tables where schemaname='lewap'");

                log.info("Result obtained from the DB");

                while(rs.next())
                    res = res + rs.getString(1);

                log.info("Result: " + res);

            } catch(Exception e) {
                log.error(e);
            }
            return res;
    }

    public String returnSth(String inSth) {
        return "returned RET" + inSth;
    }

    @Path("/getSomething")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response sampleResponse(@FormParam("Username") String username,
                                   @FormParam("Password") String password,
                                   @FormParam("ArgDB") String argDB) {
        return Response.ok().entity("<body style=\"background-color: gray\">Some response from the Webo Servico. DB Result = " + getValFromDB(username, password, argDB) + "</body>").build();

    }

    @Path("/employee/create")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public Response createEmployee () {

        try {
            SessionFactory sessionFactory
                    = SessionFactoryProvider
                    .provideSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();

            Employee emp = new Employee(101, "John");
            //session.save(emp);
            session.persist(emp);
            t.commit();

            sessionFactory.close();
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("<body style=\"background-color: gray\">ERROR " + e + "</body>").build();
        }
        return Response.ok().entity("<body style=\"background-color: gray\">Created</body>").build();

    }

    /*@Path("/uploadPD")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response uploadPD( @FormDataParam("pd") InputStream uploadedPD ) {
        return Response.ok().entity("Some response from the Webo Servico222.").build();

    }*/

    /*@Path("/uploadPD")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response uploadPD(@FormDataParam("pd") InputStream uploadedInputStream) {
        return Response.ok().entity("File received TODO").build();

    }*/
}