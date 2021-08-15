package bulgakov.locality.service;

import bulgakov.locality.entity.Role;

import java.util.List;

public interface RoleService {

    void create(Role role);

    Role getById(int id);

    void update(Role role);

    void delete(int id);

    List<Role> getAll();
}
