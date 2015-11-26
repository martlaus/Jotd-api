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

    public void upVote(Vote vote, SecurityContext securityContext) throws Exception {
        vote.setId(null);
        vote.setIsUpvote(true);

        //set user
        JotdPrincipal jotdPrincipal = (JotdPrincipal) securityContext.getUserPrincipal();
        vote.setUser(jotdPrincipal.getUser());

        //check if duplicate
        List<Vote> votes = voteDAO.getVotesByJokeAndUser(vote.getJoke(), jotdPrincipal.getUser());
        if(votes.size() > 0 && votes.get(0).isUpvote()) {
            throw new Exception("Duplicate upvote");
        } else if (votes.size() > 0 && !votes.get(0).isUpvote()) {
            //delete downvote when adding upvote
            voteDAO.remove(votes.get(0));
        }

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

    public void downVote(Vote vote, SecurityContext securityContext) throws Exception {
        vote.setId(null);
        vote.setIsUpvote(false);

        //set user
        JotdPrincipal jotdPrincipal = (JotdPrincipal) securityContext.getUserPrincipal();
        vote.setUser(jotdPrincipal.getUser());

        //check if duplicate
        List<Vote> votes = voteDAO.getVotesByJokeAndUser(vote.getJoke(), jotdPrincipal.getUser());
        if(votes.size() > 0 && !votes.get(0).isUpvote()) {
            throw new Exception("Duplicate downvote");
        } else if (votes.size() > 0 && votes.get(0).isUpvote()) {
            //delete upvote when downvoteing
            voteDAO.remove(votes.get(0));
        }

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
