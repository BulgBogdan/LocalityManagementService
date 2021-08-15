package bulgakov.locality.repository;

import bulgakov.locality.entity.StatusLocality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusLocalityRepository extends JpaRepository<StatusLocality, Integer> {
}
