package jokeOfTheDay.rest.filter;

import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;

public class JotdSecurityContext implements SecurityContext {

    private UriInfo uriInfo;
    private JotdPrincipal jotdPrincipal;

    public JotdSecurityContext(JotdPrincipal principal, UriInfo uriInfo) {
        this.uriInfo = uriInfo;
        this.jotdPrincipal = principal;
    }

    @Override
    public Principal getUserPrincipal() {
        return jotdPrincipal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return jotdPrincipal != null && jotdPrincipal.isUserInRole(role);
    }

    @Override
    public boolean isSecure() {
        return "https".equals(uriInfo.getRequestUri().getScheme());
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.CLIENT_CERT_AUTH;
    }
}
