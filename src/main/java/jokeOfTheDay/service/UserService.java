package jokeOfTheDay.service;

import jokeOfTheDay.dao.AuthenticatedUserDAO;
import jokeOfTheDay.dao.UserDAO;
import jokeOfTheDay.model.AuthenticatedUser;
import jokeOfTheDay.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by mart on 25.10.15.
 */
public class UserService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private AuthenticatedUserDAO authenticatedUserDAO;

    private SecureRandom random = new SecureRandom();


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

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public AuthenticatedUser logIn(User user) throws Exception {
        User returnedUser = getUserByEmail(user.getEmail());
        AuthenticatedUser returnedAuthenticatedUser = null;

        if (returnedUser != null && BCrypt.checkpw(user.getPassword(), returnedUser.getPassword())) {
            AuthenticatedUser authenticatedUser = new AuthenticatedUser();
            authenticatedUser.setUser(returnedUser);
            authenticatedUser.setToken(new BigInteger(130, random).toString(32));

            try {
                returnedAuthenticatedUser = authenticatedUserDAO.createAuthenticatedUser(authenticatedUser);
            } catch (Exception e) {
                authenticatedUser.setToken(new BigInteger(130, random).toString(32));
                returnedAuthenticatedUser = authenticatedUserDAO.createAuthenticatedUser(authenticatedUser);
            }

        }

        return returnedAuthenticatedUser;
    }
}
