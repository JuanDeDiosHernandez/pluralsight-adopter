package expeditors.backend.adoption.dao.repository;

import expeditors.backend.adoption.classes.Adopter;
import expeditors.backend.adoption.classes.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter, Integer> {

    @Query("select a from Adopter a left join fetch a.animal an")
    List<Adopter> findAllWithAnimal();

//    @Query("select a.name, a.dateAdoption, an.typePet from Adopter a join a.animal an")
//    List<Object []> findSmall();

    @Query("select a from Animal a join fetch a.adopter ad where UPPER(ad.name) = UPPER(:name)")
    Adopter findByName(String name);

    @Query("select a from Animal a join a.adopter ad order by a.petName")
    List<Adopter> findAllWithAnimalSortedByName();

//    @Query("select new ttl.larku.domain.SmallClassDTO(sc.startDate, sc.endDate, c.code) from Adopter sc join sc.animal c")
//    List<SmallClassDTO> findSmallDTO();
}
