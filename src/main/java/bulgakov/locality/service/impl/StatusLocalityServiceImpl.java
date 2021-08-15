package bulgakov.locality.service.impl;

import bulgakov.locality.entity.StatusLocality;
import bulgakov.locality.repository.StatusLocalityRepository;
import bulgakov.locality.service.StatusLocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatusLocalityServiceImpl implements StatusLocalityService {

    private StatusLocalityRepository statusLocalityRepository;

    @Autowired
    public StatusLocalityServiceImpl(StatusLocalityRepository statusLocalityRepository) {
        this.statusLocalityRepository = statusLocalityRepository;
    }

    @Override
    @Transactional
    public void create(StatusLocality statusLocality) {
        statusLocalityRepository.save(statusLocality);
    }

    @Override
    @Transactional
    public StatusLocality getById(int id) {
        return statusLocalityRepository.getOne(id);
    }

    @Override
    @Transactional
    public void update(StatusLocality statusLocality) {
        statusLocalityRepository.save(statusLocality);
    }

    @Override
    @Transactional
    public void delete(int id) {
        statusLocalityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<StatusLocality> getAll() {
        return statusLocalityRepository.findAll();
    }
}
