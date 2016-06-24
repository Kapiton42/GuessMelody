package guess_melody;

import com.google.inject.Scopes;
import com.sun.jersey.spi.container.ResourceFilter;
import guess_melody.oauth.OAuthResourceFilter;
import guess_melody.oauth.UserEntity;
import guess_melody.oauth.UserService;
import ru.hh.nab.NabModule;

public class AppModule extends NabModule {
    @Override
    protected void configureApp() {
        bindDataSourceAndEntityManagerAccessor(
                UserEntity.class);
        bind(UserService.class).in(Scopes.SINGLETON);

        bind(GuessMelodyResource.class).in(Scopes.SINGLETON);

        ResourceFilter resourceFilter = new OAuthResourceFilter();
        bind(ResourceFilter.class).toInstance(resourceFilter);
        addPreFilter(resourceFilter);
    }

}
