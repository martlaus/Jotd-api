package jokeOfTheDay.rest;

import jokeOfTheDay.common.test.ResourceIntegrationTestBase;
import jokeOfTheDay.model.Joke;
import org.joda.time.DateTime;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mart on 28.09.15.
 */
public class JokeResourceTest extends ResourceIntegrationTestBase {

    @Test
    public void getAllJokes() {
        Response response = doGet("joke");

        List<Joke> jokes = response.readEntity(new GenericType<List<Joke>>() {
        });

        assertValidJoke(jokes.get(0));
        assertValidJoke(jokes.get(1));

    }

    @Test
    public void addJoke() {
        Joke jokeBefore = new Joke();
        jokeBefore.setAdded(new DateTime(6666));
        jokeBefore.setJoke("A man in a wheelchair walks down the street...");

        Response response = doPost("joke", Entity.entity(jokeBefore, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());

    }

    private void assertValidJoke(Joke joke) {
        assertNotNull(joke.getId());
        assertNotNull(joke.getJoke());
        if (joke.getId() == 1) {
            assertEquals("yo moma so fat", joke.getJoke());
        } else if (joke.getId() == 2) {
            assertEquals("yo papa so fat", joke.getJoke());
        } else {
            fail("Joke with unexpected id.");
        }
    }
}
