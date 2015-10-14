package jokeOfTheDay.dao;

import jokeOfTheDay.model.Joke;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by mart on 28.09.15.
 */
public class JokeDAO {

    @Inject
    private EntityManager entityManager;

    public List<Joke> findAll() {
        return entityManager.createQuery("from Joke", Joke.class).getResultList();
    }

    public Joke saveJoke(Joke joke) {

        Joke merged;
        try {
            merged = entityManager.merge(joke);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            throw new RuntimeException("Duplicate token found when persisting authenticatedUser.");
        }

        return merged;
    }
}
