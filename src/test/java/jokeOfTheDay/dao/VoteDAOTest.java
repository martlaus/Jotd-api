package jokeOfTheDay.dao;

import jokeOfTheDay.common.test.DatabaseTestBase;
import jokeOfTheDay.model.Vote;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 10.11.15.
 */
public class VoteDAOTest extends DatabaseTestBase {

    @Inject
    private VoteDAO voteDAO;

    @Test
    public void findAll() {
        List<Vote> votes = voteDAO.findAll();

        assertNotNull(votes.get(0));
    }
}
