package jokeOfTheDay.rest;

import jokeOfTheDay.model.Comment;
import jokeOfTheDay.service.CommentService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by mart on 30.11.15.
 */
@Path("comment")
public class CommentResource {

    @Inject
    private CommentService commentService;

    private SecurityContext securityContext;

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }


    @POST
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment comment(Comment comment) throws Exception {
        if (comment != null) {
            return commentService.addComment(comment, securityContext);
        } else {
            throw new Exception("No comment.");
        }
    }
}
