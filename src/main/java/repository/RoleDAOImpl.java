package repository;

import entity.Role;
import service.RoleDAO;

public class RoleDAOImpl extends EntityDAO<Role> implements RoleDAO {
    public RoleDAOImpl(Class<Role> type) {
        super(type);
    }
}
