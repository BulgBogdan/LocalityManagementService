package repository;

import entity.Locality;
import service.LocalityDAO;

public class LocalityDAOImpl extends EntityDAO<Locality> implements LocalityDAO {
    public LocalityDAOImpl(Class<Locality> type) {
        super(type);
    }
}
