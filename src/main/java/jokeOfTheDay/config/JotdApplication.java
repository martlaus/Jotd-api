package jokeOfTheDay.config;

import jokeOfTheDay.guice.GuiceInjector;
import jokeOfTheDay.guice.provider.ObjectMapperProvider;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;

public class JotdApplication extends ResourceConfig {

    @Inject
    public JotdApplication(ServiceLocator serviceLocator) {
        // Set package to look for resources in
        packages("jokeOfTheDay");

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(GuiceInjector.getInjector());

        register(JacksonFeature.class);
        register(ObjectMapperProvider.class);
        register(RolesAllowedDynamicFeature.class);
    }
}