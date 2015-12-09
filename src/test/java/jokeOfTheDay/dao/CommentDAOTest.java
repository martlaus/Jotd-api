package jokeOfTheDay.dao;

import jokeOfTheDay.common.test.DatabaseTestBase;
import jokeOfTheDay.model.Comment;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 30.11.15.
 */
public class CommentDAOTest extends DatabaseTestBase{

    @Inject
    private CommentDAO commentDAO;

    @Test
    public void saveComment(){
        Comment comment = new Comment();
        comment.setComment("test");
        Comment returnedComment = commentDAO.saveComment(comment);

        assertNotNull(returnedComment);
        assertNotNull(returnedComment.getId());

        commentDAO.remove(returnedComment);
    }

    @Test
    public void getAll(){
        List<Comment> comments = commentDAO.findAll();

        assertEquals(2, comments.size());
    }

    @Test
    public void getCommentByJokeId() {
        List<Comment> comments = commentDAO.getCommentByJokeId(1);
        assertNotNull(comments);
        assertEquals(2, comments.size());
    }
}
