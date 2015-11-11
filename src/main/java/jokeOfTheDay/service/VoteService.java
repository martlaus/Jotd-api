package jokeOfTheDay.service;

import jokeOfTheDay.dao.JokeDAO;
import jokeOfTheDay.dao.VoteDAO;
import jokeOfTheDay.model.Joke;
import jokeOfTheDay.model.Vote;
import jokeOfTheDay.rest.filter.JotdPrincipal;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Created by mart on 11.11.15.
 */
public class VoteService {

    @Inject
    private VoteDAO voteDAO;

    @Inject
    private JokeDAO jokeDAO;

    public void upVote(Vote vote, SecurityContext securityContext) {
        vote.setId(null);
        vote.setIsUpvote(true);

        //check if duplicate

        //set user
        JotdPrincipal jotdPrincipal = (JotdPrincipal) securityContext.getUserPrincipal();
        vote.setUser(jotdPrincipal.getUser());

        //set joke
        Joke joke = jokeDAO.getJokeById(vote.getJoke().getId());
        vote.setJoke(joke);

        voteDAO.saveVote(vote);

        //get already inserted votes size to edit count
        List<Vote> upVoteList = voteDAO.getUpVotesByJoke(joke);
        int upVotes = upVoteList.size();
        joke.setUpvotes(upVotes);

        jokeDAO.saveJoke(joke);
    }

    public void downVote(Vote vote, SecurityContext securityContext) {
        vote.setId(null);
        vote.setIsUpvote(false);

        //check if duplicate

        //set user
        JotdPrincipal jotdPrincipal = (JotdPrincipal) securityContext.getUserPrincipal();
        vote.setUser(jotdPrincipal.getUser());

        //set joke
        Joke joke = jokeDAO.getJokeById(vote.getJoke().getId());
        vote.setJoke(joke);

        voteDAO.saveVote(vote);

        //get already inserted votes size to edit count
        List<Vote> downVoteList = voteDAO.getDownVotesByJoke(joke);
        int downVotes = downVoteList.size();
        joke.setDownvotes(downVotes);

        jokeDAO.saveJoke(joke);
    }
}
