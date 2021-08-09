package repository;

import config.EntityManagerConnect;
import entity.Locality;
import org.hibernate.HibernateException;
import service.LocalityDAO;

import javax.persistence.EntityManager;
import java.util.Objects;

public class LocalityDAOImpl extends EntityDAO<Locality> implements LocalityDAO {

    private EntityManager entityManager;

    public LocalityDAOImpl() {
        super(Locality.class);
    }

    @Override
    public Locality getByCityName(String cityName) {
        Locality locality = null;
        try {
            entityManager = EntityManagerConnect.getEntityManager();
            entityManager.getTransaction().begin();
            locality = (Locality) entityManager
                    .createQuery("from Locality where name = '" + cityName + "'")
                    .getResultList().stream().findFirst().orElse(null);
            entityManager.getTransaction().commit();
        } catch (HibernateException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
        return locality;
    }
}
