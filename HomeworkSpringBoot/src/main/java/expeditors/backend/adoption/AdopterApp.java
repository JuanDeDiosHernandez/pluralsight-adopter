package expeditors.backend.adoption;

import expeditors.backend.adoption.classes.Adopter;
import expeditors.backend.adoption.classes.Animal;
import expeditors.backend.adoption.classes.TypePet;

import java.time.LocalDate;
import java.util.List;

public class AdopterApp {
//    AdopterService adopterService = new AdopterService();
//    AnimalService animalService = new AnimalService();

    public static void main(String[] args) {
        AdopterApp app = new AdopterApp();
        app.postAdopter();
        app.getAllAdopters();
        app.getAdopterByName("Juan");
        app.getAdoptersSortedByName();
    }

    public void postAdopter() {
//        Animal animal = Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").build();
//        Animal animal2 = Animal.builder().typePet(TypePet.TURTLE).build();
//        Adopter adopter = Adopter.builder().name("Juan").phone("111-1111").dateAdoption(LocalDate.now()).animal_id(1).build();
//        Adopter adopter2 = Adopter.builder().name("Erick").phone("222-2222").animal_id(2).build();
//        animalService.createAnimal(animal);
//        animalService.createAnimal(animal2);
//        adopterService.createAdopter(adopter);
//        adopterService.createAdopter(adopter2);
    }

    public void getAllAdopters() {
//        List<Adopter> adopters = adopterService.getAdopters();
//        adopters.forEach(System.out::println);
    }

    public void getAdopterByName(String name) {
//        Adopter adopter = adopterService.getAdopterByName(name);
//        System.out.println(adopter.toString());
    }

    public void getAdoptersSortedByName() {
//        List<Adopter> adopters = adopterService.getAdoptersSortedByName();
//        adopters.forEach(System.out::println);
    }
}
