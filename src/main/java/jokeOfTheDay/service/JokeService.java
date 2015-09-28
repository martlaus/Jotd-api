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
}
