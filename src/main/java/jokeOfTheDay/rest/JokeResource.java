package jokeOfTheDay.rest;

import jokeOfTheDay.model.Joke;
import jokeOfTheDay.service.JokeService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Created by mart on 28.09.15.
 */
@Path("joke")
public class JokeResource {
    @Inject
    private JokeService jokeService;

    private SecurityContext securityContext;

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Joke> getAllJokes() {
        return jokeService.getAllJokes();
    }

    @POST
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public void addJoke(Joke joke) {
        if (joke != null) {
            jokeService.saveJoke(joke, securityContext);
        }
    }

    @GET
    @Path("{id}")
    public Joke getJokeById(@PathParam("id") Long id) {
        return jokeService.getJokeById(id);
    }

    @DELETE
    @RolesAllowed("USER")
    @Path("{id}")
    public void deleteJoke(@PathParam("id") Long id) throws IllegalAccessException {
        jokeService.delete(id, securityContext);
    }
}
