package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.ArrayList;

import core.Product;
import core.Message;
@Path("/products")
public class Products {
    static ArrayList<Product> products = new ArrayList<Product>();


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Product> getAllProducts () {
        return products;
    }

    @GET
    @Path("/{productID}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProductsById (@PathParam("productID") int id) {
        for (Product item : products) {
            if (item.getId() == id){
                return Response.ok(item, MediaType.APPLICATION_JSON).build();
            }
        }

        return Response.status(404).build();
    }

    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct (Product newProduct) {
        if (products.isEmpty()) {
            newProduct.setId(0);
            products.add(newProduct);

            return Response.status(200).entity(newProduct).build();
        }

        int lastUserIndex = products.size() - 1;
        int nextAvailableID = products.get(lastUserIndex).getId() + 1;

        newProduct.setId(nextAvailableID);
        products.add(newProduct);

        return Response.status(200).entity(newProduct).build();
    }

    @PUT
    @Path("{productID}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateProduct (@PathParam("productID") int id, Product newProduct) {

        for (Product item : products) {
            if (item.getId() == id){
                products.set(id, newProduct);
                Response.status(200).entity(newProduct).build();
            }
        }

        return Response.status(404).build();
    }

    @DELETE
    @Path("{productID}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response removeProduct (@PathParam("productID") int id) {
        for (Product item : products) {
            if (item.getId() == id) {
                products.remove(item);

                Message resMessage = new Message("{ msg: \"The item was removed successfully\" }");
                return Response.status(200).entity(resMessage.getMessage()).build();
            }
        }

        return Response.status(404).build();
    }
}
