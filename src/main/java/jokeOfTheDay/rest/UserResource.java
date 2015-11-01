package jokeOfTheDay.rest;

import jokeOfTheDay.model.User;
import jokeOfTheDay.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by mart on 25.10.15.
 */
@Path("user")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        if (user != null) {
            userService.saveUser(user);

            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
