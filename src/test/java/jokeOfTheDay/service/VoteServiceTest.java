package jokeOfTheDay.service;

import jokeOfTheDay.dao.JokeDAO;
import jokeOfTheDay.dao.VoteDAO;
import jokeOfTheDay.model.Joke;
import jokeOfTheDay.model.User;
import jokeOfTheDay.model.Vote;
import jokeOfTheDay.rest.filter.JotdPrincipal;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Created by mart on 12.11.15.
 */
@RunWith(EasyMockRunner.class)
public class VoteServiceTest {

    @TestSubject
    private VoteService voteService = new VoteService();

    @Mock
    private VoteDAO voteDAO;

    @Mock
    private JokeDAO jokeDAO;

    @Mock
    private SecurityContext securityContext;

    @Test
    public void upVote() throws Exception {
        Joke joke = new Joke();
        Vote vote = new Vote();
        User user = new User();
        joke.setId(1L);
        vote.setJoke(joke);

        JotdPrincipal jotdPrincipal = createMock(JotdPrincipal.class);
        List<Vote> votes = new ArrayList<>();

        expect(securityContext.getUserPrincipal()).andReturn(jotdPrincipal);
        expect(jotdPrincipal.getUser()).andReturn(user).anyTimes();
        expect(jokeDAO.getJokeById(1L)).andReturn(joke);
        expect(voteDAO.saveVote(vote)).andReturn(vote);
        expect(voteDAO.getVotesByJokeAndUser(joke, user)).andReturn(new ArrayList<>());

        replay(voteDAO, jokeDAO, securityContext, jotdPrincipal);

        voteService.upVote(vote, securityContext);

        verify(voteDAO, jokeDAO, securityContext, jotdPrincipal);
    }

    @Test
    public void downVote() throws Exception {
        Joke joke = new Joke();
        Vote vote = new Vote();
        User user = new User();
        joke.setId(1L);
        vote.setJoke(joke);

        JotdPrincipal jotdPrincipal = createMock(JotdPrincipal.class);
        List<Vote> votes = new ArrayList<>();

        expect(securityContext.getUserPrincipal()).andReturn(jotdPrincipal);
        expect(jotdPrincipal.getUser()).andReturn(user).anyTimes();
        expect(jokeDAO.getJokeById(1L)).andReturn(joke);
        expect(voteDAO.saveVote(vote)).andReturn(vote);
        expect(voteDAO.getVotesByJokeAndUser(joke, user)).andReturn(new ArrayList<>());

        replay(voteDAO, jokeDAO, securityContext, jotdPrincipal);

        voteService.upVote(vote, securityContext);

        verify(voteDAO, jokeDAO, securityContext, jotdPrincipal);
    }
}
