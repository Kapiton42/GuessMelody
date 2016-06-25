package guess_melody.gamelogic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import ru.hh.nab.jersey.JsonModel;

@JsonModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItunesSongsDTO {
    @JsonProperty("resultCount")
    private Integer resultCount;

    @JsonProperty("results")
    private List<ItunesSongDTO> results;

    public ItunesSongsDTO() {
    }

    public ItunesSongsDTO(Integer resultCount, List<ItunesSongDTO> results) {
        this.resultCount = resultCount;
        this.results = results;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<ItunesSongDTO> getResults() {
        return results;
    }

    public void setResults(List<ItunesSongDTO> results) {
        this.results = results;
    }
}
