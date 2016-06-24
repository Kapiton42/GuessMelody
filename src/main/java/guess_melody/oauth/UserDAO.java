package guess_melody.oauth;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import ru.hh.nab.hibernate.Default;

public class UserDAO {
  @Default
  @Inject
  Provider<EntityManager> em;

  private Session session() {
    return em.get().unwrap(Session.class);
  }

  public void insert(UserEntity user) {
    session().save(user);
  }

  public void update(UserEntity user) {
    session().update(user);
  }

  public Optional<UserEntity> getUser(int userId) {
    UserEntity user = (UserEntity) session().get(UserEntity.class, userId);
    return Optional.ofNullable(user);
  }

  @SuppressWarnings("unchecked")
  public List<UserEntity> getAllUsers() {
    return getUsersQuery().list();
  }

  public void deleteUser(UserEntity user) {
    session().delete(user);
  }

  private Criteria getUsersQuery() {
    return session()
      .createCriteria(UserEntity.class)
      .addOrder(Order.desc("id"));
  }
}
