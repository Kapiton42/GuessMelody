package guess_melody.oauth.authenticate;

import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.model.OAuth2AccessToken;
import guess_melody.oauth.VkServiceApi;
import guess_melody.oauth.UserService;
import guess_melody.oauth.dto.UserInfoDTO;
import javax.inject.Inject;

public class AuthService {
  @Inject
  private UserService service;

  @Inject
  private VkServiceApi serviceApi;

  @Inject
  private UserSession session;

  public void auth(String state, String code) throws Exception {
    if (session.getId() == null) {
      if (!state.equals(session.getState())) {
        throw new Exception();
      }

      OAuth2AccessToken accessTokenObj;
      try {
        accessTokenObj = serviceApi.getAccessTokenObj(code);
      } catch (OAuthException e) {
        throw new Exception();
      }

      final String accessToken = accessTokenObj.getAccessToken();

      UserInfoDTO jsonObject = serviceApi.getMe(accessToken);
      Integer id = jsonObject.getId();

      System.out.println(id);
      service.loginUser(id, jsonObject.getFirstName(), jsonObject.getLastName());
      session.setId(id);
      session.setState(null);
      session.setAccessToken(accessToken);
    }
  }

  public Integer getIdOfCurrentUser() {
    return session.getId();
  }

  public void setState(String state) {
    session.setState(state);
  }

  public String getState() {
    return session.getState();
  }
}
