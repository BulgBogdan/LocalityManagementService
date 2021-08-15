package bulgakov.locality.repository;

import bulgakov.locality.entity.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Integer> {

    Locality getByName(String cityName);
}
