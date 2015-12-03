package jokeOfTheDay.service;

import jokeOfTheDay.dao.CommentDAO;
import jokeOfTheDay.dao.JokeDAO;
import jokeOfTheDay.model.Comment;
import jokeOfTheDay.model.Joke;
import jokeOfTheDay.rest.filter.JotdPrincipal;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by mart on 30.11.15.
 */
public class CommentService {

    @Inject
    private JokeDAO jokeDAO;

    @Inject
    private CommentDAO commentDAO;

    public Comment addComment(Comment comment, SecurityContext securityContext) {
        JotdPrincipal jotdPrincipal = (JotdPrincipal) securityContext.getUserPrincipal();
        comment.setUser(jotdPrincipal.getUser());

        long jokeId = comment.getJoke().getId();
        Joke joke = jokeDAO.getJokeById(jokeId);
        comment.setJoke(joke);
        comment.setAdded(DateTime.now());

        return commentDAO.saveComment(comment);
    }
}
