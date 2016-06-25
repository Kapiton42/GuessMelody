package guess_melody.oauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import ru.hh.nab.jersey.JsonModel;

@JsonModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class VkAudioResponseAllSongsDTO {
    @JsonProperty("items")
    private List<VkAudioItemDTO> itemDTO;

    @JsonProperty("count")
    private Integer count;

    public VkAudioResponseAllSongsDTO() {
    }

    public VkAudioResponseAllSongsDTO(List<VkAudioItemDTO> itemDTO, Integer count) {
        this.itemDTO = itemDTO;
        this.count = count;
    }

    public List<VkAudioItemDTO> getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(List<VkAudioItemDTO> itemDTO) {
        this.itemDTO = itemDTO;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
