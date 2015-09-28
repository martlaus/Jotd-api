package jokeOfTheDay.guice.module;

import com.google.inject.servlet.ServletModule;
import jokeOfTheDay.guice.GuiceInjector;
import org.opensaml.saml2.binding.encoding.HTTPRedirectDeflateEncoder;


@GuiceInjector.Module
public class RestModule extends ServletModule {

    @Override
    protected void configureServlets() {

        bind(HTTPRedirectDeflateEncoder.class);

    }
}
