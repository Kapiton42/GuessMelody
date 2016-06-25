package guess_melody.oauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.nab.jersey.JsonModel;

@JsonModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class VkAudiosResponseDTO {
    @JsonProperty("response")
    private VkAudioResponseAllSongsDTO responseAllSongsDTO;

    public VkAudiosResponseDTO() {
    }

    public VkAudiosResponseDTO(VkAudioResponseAllSongsDTO responseAllSongsDTO) {
        this.responseAllSongsDTO = responseAllSongsDTO;
    }

    public VkAudioResponseAllSongsDTO getResponseAllSongsDTO() {
        return responseAllSongsDTO;
    }

    public void setResponseAllSongsDTO(VkAudioResponseAllSongsDTO responseAllSongsDTO) {
        this.responseAllSongsDTO = responseAllSongsDTO;
    }
}
