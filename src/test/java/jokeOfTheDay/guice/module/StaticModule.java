package jokeOfTheDay.guice.module;

import com.google.inject.AbstractModule;
import jokeOfTheDay.guice.GuiceInjector.Module;
import jokeOfTheDay.server.EmbeddedJettyTest;

@Module
public class StaticModule extends AbstractModule {

    @Override
    protected void configure() {
        requestStaticInjection(EmbeddedJettyTest.class);
    }
}
