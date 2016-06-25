package guess_melody.gamelogic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import ru.hh.nab.jersey.JsonModel;

@JsonModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItunesArtistsDTO {
    @JsonProperty("resultCount")
    private Integer resultCount;

    @JsonProperty("results")
    private List<ItunesArtistDTO> results;

    public ItunesArtistsDTO() {
    }

    public ItunesArtistsDTO(Integer resultCount, List<ItunesArtistDTO> results) {
        this.resultCount = resultCount;
        this.results = results;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<ItunesArtistDTO> getResults() {
        return results;
    }

    public void setResults(List<ItunesArtistDTO> results) {
        this.results = results;
    }
}
