package jokeOfTheDay.guice.module;

import com.google.inject.servlet.ServletModule;
import jokeOfTheDay.guice.GuiceInjector;
import jokeOfTheDay.service.JokeService;
import jokeOfTheDay.service.UserService;
import jokeOfTheDay.service.VoteService;


@GuiceInjector.Module
public class RestModule extends ServletModule {

    @Override
    protected void configureServlets() {

        bind(JokeService.class);
        bind(UserService.class);
        bind(VoteService.class);

    }
}
