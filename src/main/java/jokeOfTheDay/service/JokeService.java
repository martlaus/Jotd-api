package jokeOfTheDay.service;

import jokeOfTheDay.dao.JokeDAO;
import jokeOfTheDay.model.Joke;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by mart on 28.09.15.
 */
public class JokeService {

    @Inject
    private JokeDAO jokeDAO;

    public List<Joke> getAllJokes() {
        return jokeDAO.findAll();
    }

    public Joke saveJoke(Joke joke) {
        return jokeDAO.saveJoke(joke);
    }

    public Joke getJokeById(Long id) {
        return jokeDAO.getJokeById(id);
    }

    public void delete(Long id) {
        Joke joke = jokeDAO.getJokeById(id);
        jokeDAO.remove(joke);
    }
}
