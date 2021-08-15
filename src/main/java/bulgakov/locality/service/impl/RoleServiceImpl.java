package bulgakov.locality.service.impl;

import bulgakov.locality.entity.Role;
import bulgakov.locality.repository.RoleRepository;
import bulgakov.locality.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void create(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role getById(int id) {
        return roleRepository.getOne(id);
    }

    @Override
    @Transactional
    public void update(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void delete(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
