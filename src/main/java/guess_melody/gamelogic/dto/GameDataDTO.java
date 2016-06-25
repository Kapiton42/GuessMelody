package guess_melody.gamelogic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import ru.hh.nab.jersey.JsonModel;

@JsonModel
public class GameDataDTO {
    @JsonProperty("songs")
    private List<String> songs;

    @JsonProperty("url")
    private String playSong;

    public GameDataDTO() {
    }

    public GameDataDTO(List<String> songs, String playSong) {
        this.songs = songs;
        this.playSong = playSong;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public String getPlaySong() {
        return playSong;
    }

    public void setPlaySong(String playSong) {
        this.playSong = playSong;
    }
}
