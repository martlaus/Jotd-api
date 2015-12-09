package jokeOfTheDay.service;

import jokeOfTheDay.dao.CommentDAO;
import jokeOfTheDay.dao.JokeDAO;
import jokeOfTheDay.model.Comment;
import jokeOfTheDay.model.Joke;
import jokeOfTheDay.model.User;
import jokeOfTheDay.rest.filter.JotdPrincipal;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.SecurityContext;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 9.12.15.
 */
@RunWith(EasyMockRunner.class)
public class CommentServiceTest {

    @TestSubject
    private CommentService commentService = new CommentService();

    @Mock
    private CommentDAO commentDAO;

    @Mock
    private JokeDAO jokeDAO;

    @Test
    public void addComment() {
        Comment comment = new Comment();
        Joke joke = new Joke();
        joke.setId(1l);
        comment.setJoke(joke);

        SecurityContext securityContext = createMock(SecurityContext.class);
        JotdPrincipal jotdPrincipal = createMock(JotdPrincipal.class);

        expect(securityContext.getUserPrincipal()).andReturn(jotdPrincipal);
        expect(jotdPrincipal.getUser()).andReturn(new User());
        expect(jokeDAO.getJokeById(joke.getId())).andReturn(joke);
        expect(commentDAO.saveComment(comment)).andReturn(comment);

        replay(commentDAO, jokeDAO, securityContext, jotdPrincipal);

        Comment returnedComment = commentService.addComment(comment, securityContext);

        assertNotNull(returnedComment);
        assertNotNull(returnedComment.getJoke());

        verify(commentDAO, jokeDAO, securityContext, jotdPrincipal);
    }
}
