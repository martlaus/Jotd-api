package jokeOfTheDay.service;

import jokeOfTheDay.dao.JokeDAO;
import jokeOfTheDay.model.Joke;
import jokeOfTheDay.model.User;
import jokeOfTheDay.rest.filter.JotdPrincipal;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;
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

    public Joke saveJoke(Joke joke, SecurityContext securityContext) {
        User user = getUser(securityContext);
        joke.setUser(user);

        return jokeDAO.saveJoke(joke);
    }

    public Joke getJokeById(Long id) {
        return jokeDAO.getJokeById(id);
    }

    public void delete(Long id, SecurityContext securityContext) throws IllegalAccessException {
        User user = getUser(securityContext);
        Joke joke = jokeDAO.getJokeById(id);

        if(joke.getUser().equals(user)) {
            jokeDAO.remove(joke);
        } else throw new IllegalAccessException("Not your joke");
    }

    private User getUser(SecurityContext securityContext) {
        return ((JotdPrincipal) securityContext.getUserPrincipal()).getUser();
    }

}
