package jokeOfTheDay.guice.module;

import com.google.inject.servlet.ServletModule;
import jokeOfTheDay.guice.GuiceInjector;
import jokeOfTheDay.service.JokeService;
import jokeOfTheDay.service.UserService;


@GuiceInjector.Module
public class RestModule extends ServletModule {

    @Override
    protected void configureServlets() {

        bind(JokeService.class);
        bind(UserService.class);

    }
}
