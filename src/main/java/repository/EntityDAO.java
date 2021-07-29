package repository;

import config.EntityManagerConnect;
import org.hibernate.HibernateException;
import service.DAO;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntityDAO<T> implements DAO<T> {

    private final Class<T> type;

    private EntityManager entityManager;

    public EntityDAO(Class<T> type) {
        super();
        this.type = type;
    }

    public void create(T t) {
        try {
            entityManager = EntityManagerConnect.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
        } catch (HibernateException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
    }

    public T getById(int id) {
        T t = null;
        try {
            entityManager = EntityManagerConnect.getEntityManager();
            entityManager.getTransaction().begin();
            t = entityManager.find(type, id);
            entityManager.getTransaction().commit();
        } catch (HibernateException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
        return t;
    }

    public void update(T t) {
        try {
            entityManager = EntityManagerConnect.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.clear();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
        } catch (HibernateException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
    }

    public boolean delete(int id) {
        boolean deleted = false;
        try {
            entityManager = EntityManagerConnect.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(type, id));
            entityManager.getTransaction().commit();
            deleted = true;
        } catch (HibernateException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
        return deleted;
    }

    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        try {
            entityManager = EntityManagerConnect.getEntityManager();
            entityManager.getTransaction().begin();
            list = entityManager.createQuery("from " + type.getName()).getResultList();
            entityManager.getTransaction().commit();
        } catch (HibernateException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
        return list;
    }
}