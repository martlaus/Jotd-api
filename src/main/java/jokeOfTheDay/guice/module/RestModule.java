package jokeOfTheDay.guice.module;

import com.google.inject.servlet.ServletModule;
import jokeOfTheDay.guice.GuiceInjector;
import jokeOfTheDay.service.JokeService;


@GuiceInjector.Module
public class RestModule extends ServletModule {

    @Override
    protected void configureServlets() {

        bind(JokeService.class);

    }
}
