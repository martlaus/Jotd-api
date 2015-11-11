package jokeOfTheDay.rest;

import jokeOfTheDay.model.Vote;
import jokeOfTheDay.service.VoteService;

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
    private VoteService voteService;

    private SecurityContext securityContext;

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @POST
    @Path("upvote")
    @Produces(MediaType.APPLICATION_JSON)
    public Response upVote(Vote vote) {
        try {
            if (vote != null) {
                voteService.upVote(vote, securityContext);
                return Response.status(Response.Status.OK).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("downvote")
    @Produces(MediaType.APPLICATION_JSON)
    public Response downVote(Vote vote) {
        try {
            if (vote != null) {
                voteService.downVote(vote, securityContext);
                return Response.status(Response.Status.OK).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
