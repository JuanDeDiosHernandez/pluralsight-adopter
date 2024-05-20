package expeditors.backend.adoption;

import expeditors.backend.adoption.classes.Adopter;
import expeditors.backend.adoption.classes.Animal;
import expeditors.backend.adoption.classes.TypePet;
import expeditors.backend.adoption.jconfig.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class AdopterSpringApp {
    public static void main(String[] args) {
        AdopterSpringApp adopterSpring = new AdopterSpringApp();
        adopterSpring.goAdopter();
    }

    public void goAdopter() {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.getEnvironment().setActiveProfiles("dev");
//        context.register(SpringConfig.class);
////        context.scan("expeditors.backend.adoption");
//        context.refresh();
//        AdopterService adopterService = context.getBean("adopterService", AdopterService.class);
//        AnimalService animalService = context.getBean("animalService", AnimalService.class);
//
//        Animal animal = Animal.builder().typePet(TypePet.TURTLE).petName("Donatello").petBreed("Red Eared").build();
//        Animal animal2 = Animal.builder().typePet(TypePet.TURTLE).build();
//        Adopter adopter = Adopter.builder().name("Juan").phone("111-1111").dateAdoption(LocalDate.now()).animal_id(1).build();
//        Adopter adopter2 = Adopter.builder().name("Erick").phone("222-2222").animal_id(2).build();
//        animalService.createAnimal(animal);
//        animalService.createAnimal(animal2);
//        adopterService.createAdopter(adopter);
//        adopterService.createAdopter(adopter2);
//
//        List<Adopter> adopters = adopterService.getAdopters();
//        System.out.println("adopters: " + adopters.size());
//        System.out.println(adopters);
//
//        List<Animal> animals = animalService.getAnimals();
//        System.out.println("animals: " + animals.size());
//        System.out.println(animals);
    }
}
