package guess_melody;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.inject.Inject;
import com.google.inject.Provider;
import guess_melody.oauth.OAuthServiceApi;
import guess_melody.oauth.UserService;
import guess_melody.oauth.authenticate.AuthService;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class GuessMelodyResource {

    @Inject
    private UserService service;

    @Inject
    private OAuthServiceApi serviceApi;

    @Inject
    private Provider<AuthService> authServiceProvider;

    @Path("json")
    @GET
    public Response test() {
        return Response.ok().build();
    }

    @Path("index.html")
    @GET
    public Response index() throws IOException {
        AuthService authService = authServiceProvider.get();
        byte[] encoded = Files.readAllBytes(Paths.get("src/main/static/game.html"));
        String index = new String(encoded, Charset.forName("UTF-8"));
        return Response.status(Response.Status.OK).entity(index).type(MediaType.TEXT_HTML_TYPE).build();
    }

    @Path("redirect")
    @GET
    public Response vkRedirect(
            @QueryParam("state")
            String state,
            @QueryParam("code")
            String code) throws Exception {
        AuthService authService = authServiceProvider.get();
        authService.auth(state, code);
        return Response.temporaryRedirect(URI.create("http://localhost:9010/index.html")).build();
    }
}

