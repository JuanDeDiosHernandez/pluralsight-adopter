package expeditors.backend.adoption.dao.repository;

import expeditors.backend.adoption.classes.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
