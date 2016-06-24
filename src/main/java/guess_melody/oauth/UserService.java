package guess_melody.oauth;

import com.google.inject.Inject;
import ru.hh.nab.hibernate.Transactional;

public class UserService {

  private UserDAO userDAO;

  @Inject
  public UserService(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Transactional
  public void createUser(Integer id, String firstName, String lastName) {
    userDAO.insert(UserEntity.create(id, firstName, lastName));
  }

  @Transactional
  public void loginUser(Integer id, String firstName, String lastName) {
    if (!userDAO.getUser(id).isPresent()) {
      UserEntity userEntity = UserEntity.create(id, firstName, lastName);
      userDAO.insert(userEntity);
    } else {
      UserEntity userEntity = userDAO.getUser(id).get();

      userEntity.setFirstName(firstName);
      userEntity.setLastName(lastName);

      userDAO.update(userEntity);
    }
  }

  @Transactional
  public UserEntity getUser(Integer id) {
    return userDAO.getUser(id).get();
  }

}
