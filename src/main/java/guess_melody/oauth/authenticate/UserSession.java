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

  public void setAccessToken(String state) {
    getHttpSession().setAttribute("AccessToken", state);
  }

  private HttpSession getHttpSession() {
    if(httpSession == null) {
      Object response = RequestScope.currentRequest();
      httpSession = ((HttpServletRequest) response).getSession();
    }

    return httpSession;
  }
}
