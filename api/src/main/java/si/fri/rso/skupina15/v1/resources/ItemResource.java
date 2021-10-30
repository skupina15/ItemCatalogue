package si.fri.rso.skupina15.v1.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;


@ApplicationScoped
@Path("images")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {
    private Logger log = Logger.getLogger(ItemResource.class.getName());

    //@Inject
    //private ImageMetadataBean imageMetadataBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getImageMetadata() {

        //List<ImageMetadata> imageMetadata = imageMetadataBean.getImageMetadataFilter(uriInfo);

        //return Response.status(Response.Status.OK).entity(imageMetadata).build();
        return null;
    }
}
