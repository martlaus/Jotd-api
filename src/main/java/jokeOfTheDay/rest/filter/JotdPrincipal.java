package jokeOfTheDay.rest.filter;

import jokeOfTheDay.model.AuthenticatedUser;
import jokeOfTheDay.model.User;

import java.security.Principal;

public class JotdPrincipal implements Principal {

    private AuthenticatedUser authenticatedUser;

    public JotdPrincipal(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public String getName() {
        return getUser().getEmail();
    }

    public User getUser() {
        return authenticatedUser.getUser();
    }

    public String getSecurityToken() {
        return authenticatedUser.getToken();
    }

    public boolean isUserInRole(String role) {
        return authenticatedUser != null && authenticatedUser.getUser().getRole().toString().equals(role);
    }

    public AuthenticatedUser getAuthenticatedUser() {
        return authenticatedUser;
    }
}
