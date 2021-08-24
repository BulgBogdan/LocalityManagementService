package bulgakov.locality.service;

import bulgakov.locality.entity.Locality;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LocalityService {

    void create(Locality locality);

    Locality getById(int id);

    Locality getByCityName(String cityName);

    void update(Locality locality);

    void delete(int id);

    List<Locality> getAll();

    Page<Locality> getLocalities(Integer userId, int page, int pageSize);
}
