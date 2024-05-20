package expeditors.backend.adoption.service;

import expeditors.backend.adoption.AdopterService;
import expeditors.backend.adoption.classes.Adopter;
import expeditors.backend.adoption.classes.Animal;
import expeditors.backend.adoption.classes.TypePet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AdopterServiceTest {

    @Autowired
    private AdopterService adopterService;

    @Autowired
    private ApplicationContext context;

    @Test
    public void testStudentServiceInsert() {
        Adopter adopter = new Adopter("Juan", "111-1111", LocalDate.now(),
                new ArrayList<Animal>(List.of(Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").build())));

        Adopter insertedAdopter = adopterService.createAdopter(adopter);

        assertNotNull(insertedAdopter);
        assertEquals(3, adopter.getId());
    }

    @Test
    public void testDeleteExistingStudent() {
        Adopter adopter = new Adopter("Juan", "111-1111", LocalDate.now(),
                new ArrayList<Animal>(List.of(Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").build())));
        Adopter insertedAdopter = adopterService.createAdopter(adopter);

        assertNotNull(insertedAdopter);

        boolean deleted = adopterService.deleteAdopter(insertedAdopter.getId());
        assertTrue(deleted);
    }

    @Test
    public void testDeleteNonExistingStudent() {
        boolean deleted = adopterService.deleteAdopter(9999);
        assertFalse(deleted);
    }
}
