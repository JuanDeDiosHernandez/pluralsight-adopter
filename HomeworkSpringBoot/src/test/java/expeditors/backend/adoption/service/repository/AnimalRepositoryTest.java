package expeditors.backend.adoption.service.repository;

import expeditors.backend.adoption.classes.Adopter;
import expeditors.backend.adoption.classes.Animal;
import expeditors.backend.adoption.classes.TypePet;
import expeditors.backend.adoption.dao.repository.AdopterRepository;
import expeditors.backend.adoption.dao.repository.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnimalRepositoryTest {

    @Autowired
    private AdopterRepository adopterRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void testGetAAnimals() {
        List<Animal> result =  animalRepository.findAll();
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetAnimalById() {
        Animal result = animalRepository.findById(1).orElse(null);
        assertNotNull(result);
    }

    @Test
    public void testCreateAnimal() {
        List<Animal> animals = animalRepository.findAll();
        int maxId = animals.stream().mapToInt(Animal::getId).max().orElse(0);

        Adopter adopter = adopterRepository.findById(2).orElse(null);
        Animal animal = Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").adopter(adopter).build();
        animalRepository.save(animal);

        assertEquals(maxId + 1, animal.getId());
    }

    @Test
    public void testUpdateAnimal() {
        Animal animal = animalRepository.findById(1).orElse(null);
        assertNotNull(animal);
        String newName = "Leo";
        animal.setPetName(newName);

        animalRepository.save(animal);

        Animal updatedAnimal = animalRepository.findById(1).orElse(null);
        assertNotNull(updatedAnimal);
        assertEquals(newName, updatedAnimal.getPetName());
    }

    @Test
    public void testDeleteAnimal() {
        Adopter adopter = adopterRepository.findById(1).orElse(null);
        Animal animal = Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").adopter(adopter).build();
        animalRepository.save(animal);

        assertTrue(animal.getId() > 0);

        animalRepository.delete(animal);

        Animal deletedAnimal = animalRepository.findById(animal.getId()).orElse(null);
        assertNull(deletedAnimal);
    }
}
