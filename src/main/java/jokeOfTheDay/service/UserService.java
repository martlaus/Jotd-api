package jokeOfTheDay.service;

import jokeOfTheDay.dao.UserDAO;
import jokeOfTheDay.model.User;
import org.mindrot.jbcrypt.BCrypt;

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

        //secure pw
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);

        return userDAO.saveUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }
}
