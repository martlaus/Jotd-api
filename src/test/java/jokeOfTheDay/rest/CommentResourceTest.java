package jokeOfTheDay.rest;

import jokeOfTheDay.common.test.ResourceIntegrationTestBase;
import jokeOfTheDay.model.AuthenticatedUser;
import jokeOfTheDay.model.Comment;
import jokeOfTheDay.model.Joke;
import jokeOfTheDay.model.User;
import org.junit.Test;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 9.12.15.
 */
public class CommentResourceTest extends ResourceIntegrationTestBase {

    @Test
    public void comment() {
        User user = new User();
        user.setEmail("mart@mart.kz");
        user.setPassword("mart");

        Response response = doPost("signin", Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        AuthenticatedUser authenticatedUser = response.readEntity(new GenericType<AuthenticatedUser>() {
        });

        assertNotNull(authenticatedUser.getToken());
        String token = authenticatedUser.getToken();

        Joke joke = new Joke();
        joke.setId(666l);
        Comment comment = new Comment();
        comment.setComment("asdasd");
        comment.setUser(user);
        comment.setJoke(joke);

        Response response2 = getTarget("comment", new LoggedInUserFilter(token)).request().accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(comment, MediaType.APPLICATION_JSON_TYPE));

        Comment returnedComment = response2.readEntity(new GenericType<Comment>() {
        });

        assertNotNull(returnedComment);
        assertNotNull(returnedComment.getId());
    }

    @Provider
    public static class LoggedInUserFilter implements ClientRequestFilter {
        String token = null;

        public LoggedInUserFilter(String token) {
            this.token = token;
        }

        @Override
        public void filter(ClientRequestContext requestContext) throws IOException {
            List<Object> list3 = new ArrayList<>();
            list3.add(token);
            requestContext.getHeaders().put("Token", list3);

            List<Object> list4 = new ArrayList<>();
            list4.add("mart@mart.kz");
            requestContext.getHeaders().put("Email", list4);
        }
    }
}
