package jokeOfTheDay.dao;

import jokeOfTheDay.model.Vote;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by mart on 10.11.15.
 */
public class VoteDAO {

    @Inject
    private EntityManager entityManager;

    public List<Vote> findAll() {
        return entityManager.createQuery("from Vote", Vote.class).getResultList();
    }
}
