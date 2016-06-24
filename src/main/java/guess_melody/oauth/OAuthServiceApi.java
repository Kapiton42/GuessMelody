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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OAuthServiceApi {

  private OAuth20Service service = new ServiceBuilder()
          .apiKey("5002607")
          .apiSecret("pniHP2LZuo0BtAlGIPjf")
          .callback("http://localhost:9010/redirect")
          .grantType("authorization_code")
          .debug()
          .build(VkontakteApi.instance());

  public OAuthServiceApi() {
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
    System.out.println(response.getBody());
    return mapper.readValue(response.getBody(), UsersDTO.class).getUserInfoDTOs().get(0);
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
