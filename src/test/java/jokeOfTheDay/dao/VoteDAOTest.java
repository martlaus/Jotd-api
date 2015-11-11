package jokeOfTheDay.dao;

import jokeOfTheDay.common.test.DatabaseTestBase;
import jokeOfTheDay.model.Joke;
import jokeOfTheDay.model.User;
import jokeOfTheDay.model.Vote;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 10.11.15.
 */
public class VoteDAOTest extends DatabaseTestBase {

    @Inject
    private VoteDAO voteDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private JokeDAO jokeDAO;

    @Test
    public void findAll() {
        List<Vote> votes = voteDAO.findAll();

        assertNotNull(votes.get(0));
    }

    @Test
    public void findByUserAndJoke() {
        Joke joke = jokeDAO.getJokeById(1);
        User user = userDAO.getUserByEmail("admin@admin.kz");

        List<Vote> votes = voteDAO.getVotesByJokeAndUser(joke, user);

        assertNotNull(votes.get(0));
        assertEquals(1, votes.get(0).getId().intValue());
    }
}
