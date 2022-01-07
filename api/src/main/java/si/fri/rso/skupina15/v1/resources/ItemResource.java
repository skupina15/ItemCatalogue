package si.fri.rso.skupina15.v1.resources;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.fri.rso.skupina15.beans.CDI.ItemBean;
import si.fri.rso.skupina15.beans.CDI.RentBean;
import si.fri.rso.skupina15.beans.config.RestProperties;
import si.fri.rso.skupina15.entities.Item;
import si.fri.rso.skupina15.entities.Rent;

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
import java.util.ArrayList;
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

    @Inject
    private RentBean rentBean;

    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Returns a list of items.", summary = "List of items", tags = "item", responses = {
            @ApiResponse(responseCode = "200",
                    description = "List of items",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Item.class))),
                    headers = {@Header(name = "X-Total-Count", schema = @Schema(type = "integer"))}
            )})
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

    @Operation(description = "Returns a list of not borrowed items.", summary = "List of not borrowed items", tags = "item", responses = {
            @ApiResponse(responseCode = "200",
                    description = "List of not borrowed items items",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Item.class))),
                    headers = {@Header(name = "X-Total-Count", schema = @Schema(type = "integer"))}
            )})
    @GET
    @Path("/available")
    public Response getAvailableItems() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Item> items = itemBean.findAllItems(query);

        List<Rent>  rents = rentBean.findAllRent(QueryParameters.query("").build());

        List<Item> available = new ArrayList<>();

        for (Item item : items){
            int id_item = item.getId_item();
            boolean found = false;
            for(Rent rent : rents){
                if(id_item == rent.getItem().getId_item()){
                    found = true;
                    break;

                }
            }
            if(!found){
                available.add(item);
            }
        }

        return Response.ok(available).header("X-Total-Count", available.size()).build();
    }

    @Operation(description = "Returns selected item.", summary = "Selected item", tags = "item", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Selected item",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Item.class))
            )})
    @GET
    @Path("{id}")
    public Response returnItems(@Parameter(description = "The id that needs to be fetched", required = true)
                                    @PathParam("id") Integer id){
        Item i = itemBean.findItem(id);
        if (i != null){
            return Response.ok(i).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Add item.", summary = "New item", tags = "item", responses = {
            @ApiResponse(responseCode = "200",
                    description = "New item",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Item.class))
            )})
    @POST
    public Response addItem(@RequestBody(description = "Created item object", required = true,
            content = @Content(schema = @Schema(implementation = Item.class))) Item i){
        Item item = itemBean.createItem(i);
        if(item == null){
            log.info("Invalid API input.");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    @Operation(description = "Change selected item.", summary = "Change item", tags = "item", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Changed item",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Item.class))
            )})
    @PUT
    @Path("{id}")
    public Response UpdateItem(@Parameter(description = "The id that needs to be updated", required = true)
                                   @PathParam("id") Integer id, @RequestBody(description = "Created item object",
            required = true, content = @Content(schema = @Schema(implementation = Item.class))) Item i){
        Item item = itemBean.updateItem(id, i);
        if(item == null){
            log.info("Item for update does not exist");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    @Operation(description = "Delete choosen item.", summary = "Deleted item", tags = "item", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Deleted item",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class))
            )})
    @DELETE
    @Path("{id}")
    public Response deleteItem(@Parameter(description = "The id that needs to be deleted", required = true)
                                   @PathParam("id") Integer id){
        Integer item = itemBean.deleteItem(id);
        if(item == null){
            log.info("Item does not exist");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(item).build();
    }
}
