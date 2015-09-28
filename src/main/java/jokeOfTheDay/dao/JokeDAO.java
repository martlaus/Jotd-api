package jokeOfTheDay.dao;

import jokeOfTheDay.model.Joke;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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
}
