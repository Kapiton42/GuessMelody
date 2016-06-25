package guess_melody;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mashape.unirest.http.exceptions.UnirestException;
import guess_melody.dto.CheckedAnswerDTO;
import guess_melody.gamelogic.GameLogicService;
import guess_melody.gamelogic.dto.ArtistDTO;
import guess_melody.oauth.VkServiceApi;
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
    private VkServiceApi serviceApi;

    @Inject
    private Provider<AuthService> authServiceProvider;

    @Inject
    private GameLogicService logicService;

    @Path("check-answer")
    @GET
    public Response checkAnswer(
            @QueryParam("chosen_song")
            String chosenSong) {

        return Response.ok(logicService.checkAnswer(chosenSong)).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("check-artist")
    @GET
    public Response checkArtist(
            @QueryParam("artist")
            String artist) throws IOException, UnirestException {
        if (logicService.checkArtist(artist).getRight()) {
           logicService.beginGame(artist);
        }

        return Response.ok(logicService.checkArtist(artist)).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("get-songs")
    @GET
    public Response getSongs() throws IOException, UnirestException {

        return Response.ok(logicService.getGameData())
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("index")
    @GET
    public Response index() throws IOException {
        AuthService authService = authServiceProvider.get();
        byte[] encoded = Files.readAllBytes(Paths.get("src/main/static/index.html"));
        String index = new String(encoded, Charset.forName("UTF-8"));
        return Response.status(Response.Status.OK).entity(index).type(MediaType.TEXT_HTML_TYPE).build();
    }

    @Path("game")
    @GET
    public Response game() throws IOException {
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
        return Response.temporaryRedirect(URI.create("http://localhost:9010/index")).build();
    }
}

