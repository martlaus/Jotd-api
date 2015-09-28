package jokeOfTheDay.dao;

import jokeOfTheDay.common.test.DatabaseTestBase;
import jokeOfTheDay.model.Joke;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mart on 28.09.15.
 */
public class JokeDAOTest extends DatabaseTestBase {

    @Inject
    private JokeDAO jokeDAO;

    @Test
    public void findAll() {
        List<Joke> jokes = jokeDAO.findAll();

        assertEquals(2, jokes.size());
        assertValidJoke(jokes.get(0));
        assertValidJoke(jokes.get(1));
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
