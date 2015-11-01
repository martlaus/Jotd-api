package jokeOfTheDay.dao;

import jokeOfTheDay.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by mart on 25.10.15.
 */
public class UserDAO {

    @Inject
    private EntityManager entityManager;

    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public User saveUser(User user) {

        User merged;
        try {
            merged = entityManager.merge(user);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception when persisting user.");
        }

        return merged;
    }

}
