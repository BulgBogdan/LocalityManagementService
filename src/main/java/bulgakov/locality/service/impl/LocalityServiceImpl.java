package bulgakov.locality.service.impl;

import bulgakov.locality.entity.Locality;
import bulgakov.locality.repository.LocalityRepository;
import bulgakov.locality.service.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocalityServiceImpl implements LocalityService {

    private LocalityRepository localityRepository;

    @Autowired
    public LocalityServiceImpl(LocalityRepository localityRepository) {
        this.localityRepository = localityRepository;
    }

    @Override
    @Transactional
    public void create(Locality locality) {
        localityRepository.save(locality);
    }

    @Override
    @Transactional
    public Locality getById(int id) {
        return localityRepository.getOne(id);
    }

    @Override
    public Locality getByCityName(String cityName) {
        return localityRepository.getByName(cityName);
    }

    @Override
    @Transactional
    public void update(Locality locality) {
        localityRepository.save(locality);
    }

    @Override
    @Transactional
    public void delete(int id) {
        localityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Locality> getAll() {
        return localityRepository.findAll();
    }
}
