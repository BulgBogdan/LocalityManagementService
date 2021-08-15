package bulgakov.locality.service;

import bulgakov.locality.entity.Locality;

import java.util.List;

public interface LocalityService {

    void create(Locality locality);

    Locality getById(int id);

    Locality getByCityName(String cityName);

    void update(Locality locality);

    void delete(int id);

    List<Locality> getAll();
}
