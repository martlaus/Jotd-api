package jokeOfTheDay.rest;

import jokeOfTheDay.dao.JokeDAO;
import jokeOfTheDay.dao.VoteDAO;
import jokeOfTheDay.model.Joke;
import jokeOfTheDay.model.Vote;
import jokeOfTheDay.rest.filter.JotdPrincipal;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by mart on 11.11.15.
 */
@Path("vote")
public class VoteResource {

    @Inject
    private VoteDAO voteDAO;

    @Inject
    private JokeDAO jokeDAO;
    private SecurityContext securityContext;

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @POST
    @Path("upvote")
    @Produces(MediaType.APPLICATION_JSON)
    public Response upVote(Vote vote) {
        if (vote != null) {
            vote.setId(null);
            vote.setIsUpvote(true);

            //set user
            JotdPrincipal jotdPrincipal = (JotdPrincipal) securityContext.getUserPrincipal();
            vote.setUser(jotdPrincipal.getUser());

            //set joke
            Joke joke = jokeDAO.getJokeById(vote.getJoke().getId());
            vote.setJoke(joke);

            voteDAO.saveVote(vote);
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("downvote")
    @Produces(MediaType.APPLICATION_JSON)
    public Response downVote(Vote vote) {
        if (vote != null) {
            vote.setId(null);
            vote.setIsUpvote(false);

            //set user
            JotdPrincipal jotdPrincipal = (JotdPrincipal) securityContext.getUserPrincipal();
            vote.setUser(jotdPrincipal.getUser());

            //set joke
            Joke joke = jokeDAO.getJokeById(vote.getJoke().getId());
            vote.setJoke(joke);

            voteDAO.saveVote(vote);
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
