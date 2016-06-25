package guess_melody.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.apis.VkontakteApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import guess_melody.oauth.dto.UserInfoDTO;
import guess_melody.oauth.dto.UsersDTO;
import guess_melody.oauth.dto.VkAudiosResponseDTO;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VkServiceApi {

  private OAuth20Service service = new ServiceBuilder()
          .apiKey("5002607")
          .apiSecret("pniHP2LZuo0BtAlGIPjf")
          .callback("http://localhost:9010/redirect")
          .grantType("authorization_code")
          .scope("audio")
          .debug()
          .build(VkontakteApi.instance());

  public VkServiceApi() {
  }

  public String getAuthUrl(String state) {
    Map<String, String> additionalParam = new HashMap<>();
    additionalParam.put("state", state);
    return service.getAuthorizationUrl(additionalParam);
  }

  public OAuth2AccessToken getAccessTokenObj(String code) {
    return service.getAccessToken(code);
  }

  public UserInfoDTO getMe(String accessToken) throws IOException {
    Response response = getResponse(accessToken, Verb.GET, "https://api.vk.com/method/users.get?params[name_case]=Nom&params[v]=5.52");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(response.getBody(), UsersDTO.class).getUserInfoDTOs().get(0);
  }

  public String getSongUrl(String accessToken, String searchString) throws IOException {
    Response response = getResponse(accessToken, Verb.GET,
            String.format("https://api.vk.com/method/audio.search?q=%s&v=5.52&access_token=%s", searchString, accessToken).replace(" ", "%20"));

    ObjectMapper mapper = new ObjectMapper();
    System.out.println(String.format("https://api.vk.com/method/audio.search?q=%s&v=5.52&access_token=%s", searchString, accessToken));
    System.out.println(response.getBody());
    return mapper.readValue(response.getBody(), VkAudiosResponseDTO.class).getResponseAllSongsDTO().getItemDTO().get(0).getUrl();
  }

  private Response getResponse(String accessToken, Verb method, String apiUrl) {
    OAuth2AccessToken accessTokenObj = new OAuth2AccessToken(accessToken);

    OAuthRequest request = new OAuthRequest(method, apiUrl, service);
    service.signRequest(accessTokenObj, request);
    return request.send();
  }

  public OAuth20Service getService() {
    return service;
  }
}
