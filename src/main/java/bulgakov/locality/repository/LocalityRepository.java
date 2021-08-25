package bulgakov.locality.repository;

import bulgakov.locality.entity.Locality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Integer> {

    Locality getByName(String cityName);

    @Query(value = "SELECT * FROM locality WHERE user_id = ?1",
            nativeQuery = true)
    Page<Locality> findLocs(Integer userId, Pageable pageable);
}
