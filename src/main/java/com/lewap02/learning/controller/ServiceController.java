package com.lewap02.learning.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.lewap02.learning.model.SessionFactoryProvider;
import com.lewap02.learning.model.DBConnection;
import com.lewap02.learning.util.Util;
import com.lewap02.learning.util.TooManyGettersForSameFileException;
import com.lewap02.learning.util.NoGetterForFieldException;

import com.lewap02.learning.model.dao.Employee;
import com.lewap02.learning.model.dao.Address;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jakarta.persistence.Column;

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

                ResultSet rs=stmt.executeQuery("select * from t where upper(dummy) = '" + argDB.toUpperCase() + "'");

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
        return Response.ok().entity(Util.makeResponse(getValFromDB(username, password, argDB))).build();

    }

    private List<Employee> findAllEmployees (Session session) {
        return session.createQuery("SELECT a FROM Employee a", Employee.class).getResultList();
    }

    @Path("/employee/create")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createEmployee (@FormParam("empName") String empName) throws TooManyGettersForSameFileException, NoGetterForFieldException {

        List<Employee> allEmployees = null;

        try {
            SessionFactory sessionFactory
                    = SessionFactoryProvider
                    .provideSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();

            Employee emp = new Employee(empName);
            //session.save(emp);
            session.persist(emp);
            t.commit();

            allEmployees = findAllEmployees(session);

            sessionFactory.close();
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Util.makeResponse("ERROR" + e)).build();
        }

        return Response.ok().entity(Util.makeResponse(Util.makeTable(allEmployees))).build();

    }

    private List<Address> findAllAddresses (Session session) {
        return session.createQuery("SELECT a FROM Address a", Address.class).getResultList();
    }

    @Path("/address/create")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createAddress (@FormParam("street") String street,
                                   @FormParam("houseNumber") String houseNumber,
                                   @FormParam("city") String city
    ) throws TooManyGettersForSameFileException, NoGetterForFieldException {

        List<Address> allAddresses = null;

        try {
            SessionFactory sessionFactory
                    = SessionFactoryProvider
                    .provideSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();

            Address address = new Address(street,
                                        houseNumber,
                                        city);
            //session.save(emp);
            session.persist(address);
            t.commit();

            allAddresses = findAllAddresses(session);

            sessionFactory.close();
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Util.makeResponse("ERROR" + e)).build();
        }

        return Response.ok().entity(Util.makeResponse(Util.makeTable(allAddresses))).build();

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