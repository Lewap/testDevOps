import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
public class testService {
    @GET
    @Path("/getSomething")
    @Produces("text/html")
    public Response sampleResponse() {
        return Response.ok().entity("Some response from the WebService").build();

    }
}