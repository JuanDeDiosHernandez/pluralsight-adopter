package expeditors.backend.adoption.service;

import expeditors.backend.adoption.AdopterService;
import expeditors.backend.adoption.classes.Adopter;
import expeditors.backend.adoption.classes.Animal;
import expeditors.backend.adoption.classes.TypePet;
import expeditors.backend.adoption.dao.AdopterDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdopterServiceMockTest {
    @Mock
    private AdopterDAO adopterDAO;

    @InjectMocks
    private AdopterService adopterService;

    @Test
    public void testGetAdopterById() {
        Adopter adopter = new Adopter("Juan", "111-1111", LocalDate.now(),
                new ArrayList<Animal>(List.of(Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").build())));
        adopter.setId(1);
        Mockito.when(adopterDAO.findById(1)).thenReturn(adopter);
        Adopter foundAdopter = adopterService.getAdopter(adopter.getId());
        assertNotNull(foundAdopter);
        Mockito.verify(adopterDAO).findById(1);
    }

    @Test
    public void testCreateAdopter() {
        Adopter adopter = new Adopter("Juan", "111-1111", LocalDate.now(),
                new ArrayList<Animal>(List.of(Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").build())));
        Mockito.when(adopterDAO.insert(adopter)).thenReturn(adopter);
        Adopter newAdopter = adopterService.createAdopter(adopter);
        assertNotNull(newAdopter);
        Mockito.verify(adopterDAO).insert(adopter);
    }

    @Test
    public void testDeleteExistingAdopter() {
        Adopter adopter = new Adopter("Juan", "111-1111", LocalDate.now(),
                new ArrayList<Animal>(List.of(Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").build())));
        Mockito.when(adopterDAO.insert(adopter)).thenReturn(adopter);
        Adopter insertedAdopter = adopterService.createAdopter(adopter);
        Mockito.when(adopterDAO.delete(insertedAdopter.getId())).thenReturn(true);
        boolean deleted = adopterService.deleteAdopter(insertedAdopter.getId());
        assertTrue(deleted);
        Mockito.verify(adopterDAO).delete(insertedAdopter.getId());
    }

    @Test
    public void testDeleteNonExistingAdopter() {
        Mockito.doReturn(false).when(adopterDAO).delete(999);
        boolean deleted = adopterService.deleteAdopter(999);
        assertFalse(deleted);
        Mockito.verify(adopterDAO).delete(999);
    }

    @Test
    public void testUpdateAdopter() {
        Adopter adopter = new Adopter("Juan", "111-1111", LocalDate.now(),
                new ArrayList<Animal>(List.of(Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").build())));
        adopter.setId(1);
        Mockito.when(adopterDAO.update(adopter)).thenReturn(true);
        boolean result = adopterService.updateAdopter(adopter);
        assertTrue(result);
        Mockito.verify(adopterDAO).update(adopter);
    }

    @Test
    public void testUpdateNonExistentAdopter() {
        Adopter adopter = new Adopter("Juan", "111-1111", LocalDate.now(),
                new ArrayList<Animal>(List.of(Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").build())));
        adopter.setId(999);
        Mockito.when(adopterDAO.update(adopter)).thenReturn(false);
        boolean result = adopterService.updateAdopter(adopter);
        assertFalse(result);
        Mockito.verify(adopterDAO).update(adopter);
    }
}
