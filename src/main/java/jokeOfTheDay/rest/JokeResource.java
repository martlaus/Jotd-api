package jokeOfTheDay.rest;

import jokeOfTheDay.model.Joke;
import jokeOfTheDay.service.JokeService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by mart on 28.09.15.
 */
@Path("joke")
public class JokeResource {


    @Inject
    private JokeService jokeService;

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
            jokeService.saveJoke(joke);
        }
    }

}
