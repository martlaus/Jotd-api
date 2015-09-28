package jokeOfTheDay.guice.module;

import com.google.inject.AbstractModule;
import jokeOfTheDay.ApplicationLauncher;
import jokeOfTheDay.ApplicationManager;
import jokeOfTheDay.guice.GuiceInjector;

@GuiceInjector.Module
public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        requestStaticInjection(ApplicationLauncher.class);
        requestStaticInjection(ApplicationManager.class);
    }
}
