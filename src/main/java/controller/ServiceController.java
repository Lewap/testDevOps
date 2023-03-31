package controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static model.DBConnection.getValFromOra;

@Path("/")
public class ServiceController {
    @GET
    @Path("/getSomething")
    @Produces("text/html")
    public Response sampleResponse() {
        return Response.ok().entity("Some response from the Webo Servico. DB Result = " + getValFromOra("X")).build();

    }
}