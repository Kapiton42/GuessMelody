package guess_melody.oauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import ru.hh.nab.jersey.JsonModel;

@JsonModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersDTO {
    @JsonProperty("response")
    List<UserInfoDTO> userInfoDTOs;

    public UsersDTO() {
    }

    public UsersDTO(List<UserInfoDTO> userInfoDTOs) {
        this.userInfoDTOs = userInfoDTOs;
    }

    public List<UserInfoDTO> getUserInfoDTOs() {
        return userInfoDTOs;
    }

    public void setUserInfoDTOs(List<UserInfoDTO> userInfoDTOs) {
        this.userInfoDTOs = userInfoDTOs;
    }
}

