package si.fri.rso.skupina15.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.rso.skupina15.entities.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("demo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DemoResource {

    @GET
    public Response getItems() {
        return null;
    }
}
