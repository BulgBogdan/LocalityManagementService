package repository;

import entity.Infrastructure;
import service.InfrastructureDAO;

public class InfrastructureDAOImpl extends EntityDAO<Infrastructure> implements InfrastructureDAO {

    public InfrastructureDAOImpl() {
        super(Infrastructure.class);
    }
}
