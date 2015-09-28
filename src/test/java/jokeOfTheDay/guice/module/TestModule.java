package jokeOfTheDay.guice.module;

import com.google.inject.AbstractModule;
import jokeOfTheDay.common.test.ResourceIntegrationTestBase;
import jokeOfTheDay.guice.GuiceInjector.Module;

@Module()
public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        requestStaticInjection(ResourceIntegrationTestBase.class);
    }
}
