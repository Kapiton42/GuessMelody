package guess_melody.gamelogic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.nab.jersey.JsonModel;

@JsonModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItunesSongDTO {
    @JsonProperty("trackName")
    private String trackName;

    public ItunesSongDTO() {
    }

    public ItunesSongDTO(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
}
