package com.mycompany.simplerest.controllers;

import com.mycompany.simplerest.entities.User;
import com.mycompany.simplerest.repositories.UserRepository;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
public class UserController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find() {
        UserRepository userRepository = new UserRepository();

        return Response
                .ok(userRepository.findAll())
                .build();
    }

    @POST
    public Response create(
            //QUERYPARAM = PARÂMETRO DA ABA "QUERY" DO INSOMNIA 
            @QueryParam("name") String name,
            @QueryParam("email") String email,
            @QueryParam("gender") String gender,
            @QueryParam("age") int age
    ) {
        User user = new User();
        user.setAge(age);
        user.setEmail(email);
        user.setGender(gender);
        user.setName(name);

        UserRepository userRepository = new UserRepository();
        userRepository.save(user);

        return Response.ok(user).build();
    }

    @GET
    @Path("{id}")
    public Response show(@PathParam("id") String id) {
        UserRepository userRepository = new UserRepository();
        User user = userRepository.findOne(id);

        return Response.ok(user).build();
    }
}
