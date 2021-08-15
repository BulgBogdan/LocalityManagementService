package bulgakov.locality.service.impl;

import bulgakov.locality.entity.Infrastructure;
import bulgakov.locality.repository.InfrastructureRepository;
import bulgakov.locality.service.InfrastructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InfrastructureServiceImpl implements InfrastructureService {

    private InfrastructureRepository infrastructureRepository;

    @Autowired
    public InfrastructureServiceImpl(InfrastructureRepository infrastructureRepository) {
        this.infrastructureRepository = infrastructureRepository;
    }

    @Override
    @Transactional
    public void create(Infrastructure infrastructure) {
        infrastructureRepository.save(infrastructure);
    }

    @Override
    @Transactional
    public Infrastructure getById(int id) {
        return infrastructureRepository.getOne(id);
    }

    @Override
    @Transactional
    public void update(Infrastructure infrastructure) {
        infrastructureRepository.save(infrastructure);
    }

    @Override
    @Transactional
    public void delete(int id) {
        infrastructureRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Infrastructure> getAll() {
        return infrastructureRepository.findAll();
    }
}
