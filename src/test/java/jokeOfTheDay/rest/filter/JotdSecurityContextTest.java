package jokeOfTheDay.rest.filter;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(EasyMockRunner.class)
public class JotdSecurityContextTest {

    @Mock
    private JotdPrincipal jotdPrincipal;

    @Mock
    private UriInfo uriInfo;

    @Test
    public void isUserInRoleNull() {
        JotdSecurityContext securityContext = getJotdSecurityContext(null, uriInfo);

        boolean response = securityContext.isUserInRole("USER");

        assertFalse(response);
    }

    @Test
    public void isUserInRole() {
        JotdSecurityContext securityContext = getJotdSecurityContext(jotdPrincipal, uriInfo);

        expect(jotdPrincipal.isUserInRole("USER")).andReturn(true);

        replay(jotdPrincipal, uriInfo);

        assertTrue(securityContext.isUserInRole("USER"));

        verify(jotdPrincipal, uriInfo);

    }

    @Test
    public void isSecureFalse() throws URISyntaxException {
        JotdSecurityContext securityContext = getJotdSecurityContext(jotdPrincipal, uriInfo);
        URI uri = new URI("http://random.org");

        expect(uriInfo.getRequestUri()).andReturn(uri);

        replay(jotdPrincipal, uriInfo);

        assertFalse(securityContext.isSecure());

        verify(jotdPrincipal, uriInfo);
    }

    @Test
    public void isSecure() throws URISyntaxException {
        JotdSecurityContext securityContext = getJotdSecurityContext(jotdPrincipal, uriInfo);
        URI uri = new URI("https://random.org");

        expect(uriInfo.getRequestUri()).andReturn(uri);

        replay(jotdPrincipal, uriInfo);

        assertTrue(securityContext.isSecure());

        verify(jotdPrincipal, uriInfo);
    }

    private JotdSecurityContext getJotdSecurityContext(JotdPrincipal jotdPrincipal, UriInfo uriInfo) {
        return new JotdSecurityContext(jotdPrincipal, uriInfo);
    }
}
