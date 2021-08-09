package repository;

import config.EntityManagerConnect;
import entity.User;
import org.hibernate.HibernateException;
import service.UserDAO;

import javax.persistence.EntityManager;
import java.util.Objects;

public class UserDAOImpl extends EntityDAO<User> implements UserDAO {

    private EntityManager entityManager;

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User getByUsername(String username) {
        User user = null;
        try {
            entityManager = EntityManagerConnect.getEntityManager();
            entityManager.getTransaction().begin();
            user = (User) entityManager
                    .createQuery("from User where username = '" + username + "'")
                    .getResultList().stream().findFirst().orElse(null);
            entityManager.getTransaction().commit();
        } catch (HibernateException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
        return user;
    }
}
