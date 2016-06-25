package guess_melody.gamelogic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.nab.jersey.JsonModel;

@JsonModel
public class ArtistDTO {
    @JsonProperty("artist")
    private String artist;

    @JsonProperty("right")
    private Boolean right;

    public ArtistDTO() {

    }

    public ArtistDTO(String artist, Boolean right) {
        this.artist = artist;
        this.right = right;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Boolean getRight() {
        return right;
    }

    public void setRight(Boolean right) {
        this.right = right;
    }
}
