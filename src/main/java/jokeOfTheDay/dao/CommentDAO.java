package jokeOfTheDay.dao;

import jokeOfTheDay.model.Comment;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mart on 30.11.15.
 */
public class CommentDAO {

    @Inject
    private EntityManager entityManager;

    public List<Comment> findAll() {
        return entityManager.createQuery("from Comment", Comment.class).getResultList();
    }


    public Comment saveComment(Comment comment) {

        Comment merged;
        try {
            merged = entityManager.merge(comment);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            throw new RuntimeException("Exception when persisting joke.");
        }

        return merged;
    }

    public List<Comment> getCommentByJokeId(long id) {
        TypedQuery<Comment> findByCode = entityManager
                .createQuery("SELECT u FROM Comment u WHERE u.joke.id = :id", Comment.class);

        List<Comment> comments = null;
        try {
            comments = findByCode.setParameter("id", id).getResultList();
        } catch (NoResultException ex) {
            // ignore
        }

        return comments;
    }
}
