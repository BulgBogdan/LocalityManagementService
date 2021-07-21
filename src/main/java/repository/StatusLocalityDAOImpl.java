package repository;

import entity.StatusLocality;
import service.StatusLocalityDAO;

public class StatusLocalityDAOImpl extends EntityDAO<StatusLocality> implements StatusLocalityDAO {

    public StatusLocalityDAOImpl(Class<StatusLocality> type) {
        super(type);
    }
}
