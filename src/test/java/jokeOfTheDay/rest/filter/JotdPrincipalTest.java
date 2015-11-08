package jokeOfTheDay.rest.filter;

import jokeOfTheDay.model.AuthenticatedUser;
import jokeOfTheDay.model.Role;
import jokeOfTheDay.model.User;
import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(EasyMockRunner.class)
public class JotdPrincipalTest {

    AuthenticatedUser authenticatedUser = getUser();

    @TestSubject
    private JotdPrincipal jotdPrincipal = new JotdPrincipal(authenticatedUser);

    @Test
    public void getName() {
        assertEquals("admin@admin.kz", jotdPrincipal.getName());
    }

    @Test
    public void getToken() {
        assertNull(jotdPrincipal.getSecurityToken());
    }

    @Test
    public void isUserInRole() {
        assertTrue(jotdPrincipal.isUserInRole("USER"));
    }

    private AuthenticatedUser getUser() {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        User user = new User();
        user.setEmail("admin@admin.kz");
        user.setRole(Role.USER);
        authenticatedUser.setUser(user);

        return authenticatedUser;
    }
}
