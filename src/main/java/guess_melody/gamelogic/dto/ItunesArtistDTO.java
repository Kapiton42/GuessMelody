package guess_melody.gamelogic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.nab.jersey.JsonModel;

@JsonModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItunesArtistDTO {
    @JsonProperty("artistId")
    private Integer artistid;

    public ItunesArtistDTO() {
    }

    public ItunesArtistDTO(Integer artistid) {
        this.artistid = artistid;
    }

    public Integer getArtistid() {
        return artistid;
    }

    public void setArtistid(Integer artistid) {
        this.artistid = artistid;
    }
}
