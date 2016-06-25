package guess_melody.oauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.nab.jersey.JsonModel;

@JsonModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class VkAudioItemDTO {
    @JsonProperty("url")
    private String url;

    public VkAudioItemDTO() {
    }

    public VkAudioItemDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
