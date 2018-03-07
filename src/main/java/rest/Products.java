package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.ArrayList;

import data.Product;

@Path("/products")
public class Products {
    static ArrayList<Product> products = new ArrayList<Product>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Product> getAllProducts () {
        return products;
    }

    @GET
    @Path("/{productID}")
    @Produces(MediaType.TEXT_PLAIN)
    public Object getProductsById (@PathParam("productID") int id) {
        for (Product item : products) {
            if (item.getId() == id) return item;
        }

        ResponseBuilder rb = Response.status(404);
        return rb.build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Object addProduct (@FormParam("name") String name, @FormParam("quality") int quality) {
        ResponseBuilder rb = Response.status(200);

        products.add(new Product(products.size() + 1 , name, quality));
        return rb.build();
    }

    @PUT
    @Path("{productID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object updateProduct (@PathParam("productID") int id) {
        return "{ msg: \"The product is added\"}";
    }

    @DELETE
    @Path("{productID}")
    public Object removeProduct (@PathParam("productID") int id) {
        for (Product item : products) {
            if (item.getId() == id) {
                products.remove(item);
                ResponseBuilder rb = Response.status(200);
                return rb.build();
            }
        }

        ResponseBuilder rb = Response.status(404);
        return rb.build();
    }
}
