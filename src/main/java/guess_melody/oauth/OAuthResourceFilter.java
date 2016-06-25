package guess_melody.oauth;

import com.google.inject.Provider;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;
import guess_melody.oauth.authenticate.AuthService;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class OAuthResourceFilter implements ResourceFilter {
  private final SecurityRequestFilter filterInstance = new SecurityRequestFilter();

  @Inject
  private Provider<AuthService> authServiceProvider;

  @Inject
  private VkServiceApi serviceApi;

  @Inject
  public OAuthResourceFilter() {
  }

  @Override
  public ContainerRequestFilter getRequestFilter() {
    return filterInstance;
  }

  @Override
  public ContainerResponseFilter getResponseFilter() {
    return null;
  }

  public class SecurityRequestFilter implements ContainerRequestFilter {

    @Override
    public ContainerRequest filter(ContainerRequest request) {
      List<String> code = request.getQueryParameters().get("code");

      if (code != null) {
        return request;
      }

      AuthService authService = authServiceProvider.get();

      if (authService.getIdOfCurrentUser() != null) {
        return request;
      } else {
        String state = Util.getRandomString();
        authService.setState(state);
        throw new WebApplicationException(getAuthResponseBuilder(state).build());
      }
    }

    private Response.ResponseBuilder getAuthResponseBuilder(String state) {
      Response.ResponseBuilder builderRedirect;
        builderRedirect = Response.temporaryRedirect(
          URI.create(serviceApi.getAuthUrl(state)));
      return builderRedirect;
    }
  }
}
