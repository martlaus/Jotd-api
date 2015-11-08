package jokeOfTheDay.rest.filter;

import jokeOfTheDay.model.AuthenticatedUser;
import jokeOfTheDay.service.AuthenticatedUserService;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static jokeOfTheDay.guice.GuiceInjector.getInjector;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    private static final int HTTP_AUTHENTICATION_TIMEOUT = 419;

    private UriInfo uriInfo;
    private HttpServletRequest request;

    public SecurityFilter(@Context UriInfo uriInfo, @Context HttpServletRequest request) {
        this.uriInfo = uriInfo;
        this.request = request;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = request.getHeader("Authentication");

        if (token != null) {
            AuthenticatedUserService authenticatedUserService = newAuthenticatedUserService();
            AuthenticatedUser authenticatedUser = authenticatedUserService.getAuthenticatedUserByToken(token);
            if (authenticatedUser != null && isCorrectUser(authenticatedUser)) {
                JotdPrincipal principal = new JotdPrincipal(authenticatedUser);
                JotdSecurityContext securityContext = new JotdSecurityContext(principal, uriInfo);
                requestContext.setSecurityContext(securityContext);
            } else {
                requestContext.abortWith(Response.status(HTTP_AUTHENTICATION_TIMEOUT).build());
            }
        }

    }

    protected AuthenticatedUserService newAuthenticatedUserService() {
        return getInjector().getInstance(AuthenticatedUserService.class);
    }

    private boolean isCorrectUser(AuthenticatedUser authenticatedUser) {
        return authenticatedUser.getUser().getEmail().equals(request.getHeader("Email"));
    }

}
