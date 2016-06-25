package guess_melody.gamelogic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import guess_melody.dto.CheckedAnswerDTO;
import guess_melody.gamelogic.dto.ArtistDTO;
import guess_melody.gamelogic.dto.GameDataDTO;
import guess_melody.gamelogic.dto.ItunesArtistsDTO;
import guess_melody.gamelogic.dto.ItunesSongsDTO;
import guess_melody.oauth.VkServiceApi;
import guess_melody.oauth.authenticate.UserSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class GameLogicService {

    @Inject
    private Provider<UserSession> userSessionProvider;

    @Inject
    private VkServiceApi serviceApi;

    ObjectMapper mapper = new ObjectMapper();

    public ArtistDTO checkArtist(String artist) throws UnirestException, IOException {
        UserSession userSession = userSessionProvider.get();
        String response = Unirest.get("https://itunes.apple.com/search")
                .queryString("term", artist)
                .queryString("entity", "musicArtist")
                .asString().getBody();

        ItunesArtistsDTO itunesArtistsDTO = mapper.readValue(response, ItunesArtistsDTO.class);

        if(itunesArtistsDTO.getResultCount() == 0) {
            return new ArtistDTO(artist, false);
        }

        Integer artistId = itunesArtistsDTO.getResults().get(0).getArtistid();

        response = Unirest.get("https://itunes.apple.com/lookup")
                .queryString("id", artistId)
                .queryString("entity", "song")
                .asString().getBody();

        ItunesSongsDTO itunesSongsDTO = mapper.readValue(response, ItunesSongsDTO.class);

        if(itunesSongsDTO.getResultCount() < 25) {
            return new ArtistDTO(artist, false);
        }

        userSession.setArtistId(artistId);

        return new ArtistDTO(artist, true);
    }

    public GameDataDTO getGameData() throws IOException, UnirestException {
        final Random random = new Random();
        UserSession userSession = userSessionProvider.get();

        Integer artistId = userSession.getArtistId();
        String artist = userSession.getArtist();
        String accessToken = userSession.getAccessToken();

        String response = Unirest.get("https://itunes.apple.com/lookup")
                .queryString("id", artistId)
                .queryString("entity", "song")
                .queryString("limit", 200)
                .asString().getBody();

        ItunesSongsDTO itunesSongsDTO = mapper.readValue(response, ItunesSongsDTO.class);

        Set<Integer> temp = new HashSet<>();
        while (temp.size() < 5) {
            temp.add(random.nextInt(itunesSongsDTO.getResultCount()));
        }

        List<String> songs = temp.stream().map(id -> itunesSongsDTO.getResults().get(id).getTrackName()).collect(Collectors.toList());

        String rightSong = songs.get(random.nextInt(5));

        userSession.setRightSong(rightSong);

        return new GameDataDTO(songs, serviceApi.getSongUrl(accessToken, String.format("%s %s", artist, rightSong)));
    }

    public CheckedAnswerDTO checkAnswer(String chosenSong) {
        return new CheckedAnswerDTO(chosenSong.equals(userSessionProvider.get().getRightSong()));
    }

    public void beginGame(String artist) {
        UserSession userSession = userSessionProvider.get();

        userSession.setArtist(artist);
    }
}
