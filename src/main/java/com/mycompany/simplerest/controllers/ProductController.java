package com.mycompany.simplerest.controllers;

import com.mycompany.simplerest.entities.Product;
import com.mycompany.simplerest.repositories.ProductRepository;
import java.util.HashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("products")
public class ProductController {

    private static ProductRepository _repository = new ProductRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.ok(this._repository.getAll()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Product product) {
        this._repository.save(product);
        return Response.ok(product).build();
    }

    @GET
    @Path("{id}")
    public Response show(@PathParam("id") String id) {
        Product product = this._repository.findById(id);

        return Response.ok(product).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Product product) {
        Product _product = this._repository.findById(id);

        // se o produto não existe, retorna erro 404
        if (_product == null) {
            HashMap<String, Object> message = new HashMap<>();
            message.put("message", "Produto não encontrado");
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(message)
                    .build();
        }

        if (product.getName() != null) {
            _product.setName(product.getName());
        }

        if (product.getPrice() > 0) {
            _product.setPrice(product.getPrice());
        }

        this._repository.save(_product);

        return Response.ok(_product).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") String id) {
        Product product = this._repository.findById(id);

        // se o produto não existe, retorna erro 404
        if (product == null) {
            HashMap<String, Object> message = new HashMap<>();
            message.put("message", "Produto não encontrado");
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(message)
                    .build();
        }

        if (product.getPrice() > 10000) {
            HashMap<String, Object> message = new HashMap<>();
            message.put("message", "Preço alto de mais para ser deletado");
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity(message)
                    .build();
        }
        
        this._repository.delete(product);
        
        return Response.ok().build();
    }
}
