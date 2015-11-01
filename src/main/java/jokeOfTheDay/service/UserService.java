package jokeOfTheDay.service;

import jokeOfTheDay.dao.UserDAO;
import jokeOfTheDay.model.User;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by mart on 25.10.15.
 */
public class UserService {

    @Inject
    private UserDAO userDAO;

    public User saveUser(User user) {
        //check if data is according to business rules

        return userDAO.saveUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }
}
