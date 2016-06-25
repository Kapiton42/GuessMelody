package guess_melody.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.nab.jersey.JsonModel;

@JsonModel
public class CheckedAnswerDTO {

    @JsonProperty("right")
    private Boolean right;

    public CheckedAnswerDTO() {
    }

    public CheckedAnswerDTO(Boolean right) {
        this.right = right;
    }

    public Boolean getRight() {
        return right;
    }

    public void setRight(Boolean right) {
        this.right = right;
    }
}
