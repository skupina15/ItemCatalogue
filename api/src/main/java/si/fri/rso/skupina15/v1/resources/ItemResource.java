package si.fri.rso.skupina15.v1.resources;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.rso.skupina15.beans.CDI.ItemBean;
import si.fri.rso.skupina15.beans.config.RestProperties;
import si.fri.rso.skupina15.entities.Item;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log
@ApplicationScoped
@Path("items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {

    @Inject
    private RestProperties restProperties;
    private Logger log = Logger.getLogger(ItemResource.class.getName());

    @Inject
    private ItemBean itemBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getItems() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long count = itemBean.itemsCount(query);
        List<Item> items = itemBean.findAllItems(query);

        // Testing configurations
        log.info(restProperties.getPrint());
        log.info(System.getenv().get("GREET"));

        return Response.ok(items).header("X-Total-Count", count).build();
    }

    @GET
    @Path("{id}")
    public Response returnItems(@PathParam("id") Integer id){
        Item i = itemBean.findItem(id);
        if (i != null){
            return Response.ok(i).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response addItem(Item i){
        Item item = itemBean.createItem(i);
        if(item == null){
            log.info("Invalid API input.");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    @PUT
    @Path("{id}")
    public Response UpdateItem(@PathParam("id") Integer id, Item i){
        Item item = itemBean.updateItem(id, i);
        if(item == null){
            log.info("Item for update does not exist");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteItem(@PathParam("id") Integer id){
        Integer item = itemBean.deleteItem(id);
        if(item == null){
            log.info("Item does not exist");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(item).build();
    }
}
