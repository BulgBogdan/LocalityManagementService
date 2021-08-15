package bulgakov.locality.service;

import bulgakov.locality.entity.StatusLocality;

import java.util.List;

public interface StatusLocalityService {

    void create(StatusLocality statusLocality);

    StatusLocality getById(int id);

    void update(StatusLocality statusLocality);

    void delete(int id);

    List<StatusLocality> getAll();
}
