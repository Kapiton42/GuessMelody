package guess_melody.oauth.authenticate;

import com.google.inject.servlet.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ru.hh.nab.scopes.RequestScope;

@RequestScoped
public class UserSession {
  HttpSession httpSession;

  public UserSession() {
  }

  public Integer getId() {
    return (Integer) getHttpSession().getAttribute("id");
  }

  public void setId(Integer id) {
    getHttpSession().setAttribute("id", id);
  }

  public String getState() {
    return (String) getHttpSession().getAttribute("state");
  }

  public void setState(String state) {
    getHttpSession().setAttribute("state", state);
  }

  public String getAccessToken() {
    return (String) getHttpSession().getAttribute("AccessToken");
  }

  public void setAccessToken(String accessToken) {
    getHttpSession().setAttribute("AccessToken", accessToken);
  }

  public Integer getArtistId() {
    return (Integer) getHttpSession().getAttribute("ArtistId");
  }

  public void setArtistId(Integer artist) {
    getHttpSession().setAttribute("ArtistId", artist);
  }

  public String getArtist() {
    return (String) getHttpSession().getAttribute("Artist");
  }

  public void setArtist(String artist) {
    getHttpSession().setAttribute("Artist", artist);
  }

  public String getRightSong() {
    return (String) getHttpSession().getAttribute("RightSong");
  }

  public void setRightSong(String rightSong) {
    getHttpSession().setAttribute("RightSong", rightSong);
  }

  private HttpSession getHttpSession() {
    if(httpSession == null) {
      Object response = RequestScope.currentRequest();
      httpSession = ((HttpServletRequest) response).getSession();
    }

    return httpSession;
  }
}
