package expeditors.backend.adoption.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
//@Table(name = "adopters")
public class Adopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = true)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "date_adoption")
    private LocalDate dateAdoption;
//    @Transient
//    @Column(name = "animal_id")
//    private int animal_id;
    @OneToMany(mappedBy = "adopter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
//    @JsonView(Views.Adopter.class)
    @JsonManagedReference
    private List<Animal> animal = new ArrayList<>();

    public Adopter(){

    }

    public Adopter(int id, String name, String phone, LocalDate dateAdoption, List<Animal> animal) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.dateAdoption = dateAdoption;
        if (animal != null) {
            this.animal.addAll(animal);
        }
    }

    public Adopter(int id, String name, String phone, LocalDate dateAdoption, Animal animal) {
        this(id, name, phone, dateAdoption, List.of(animal));
    }

    public Adopter(String name, String phone, LocalDate dateAdoption, List<Animal> animal) {
        this(0, name, phone, dateAdoption, animal);
    }

//    public Adopter(int id, String name, String phone, LocalDate dateAdoption, Animal animal) {
//        this.id = id;
//        this.name = name;
//        this.phone = phone;
//        this.dateAdoption = dateAdoption;
//        this.animal = animal;
//    }

//    public Adopter(int id, String name, String phone, LocalDate dateAdoption, int animal_id) {
//        this.id = id;
//        this.name = name;
//        this.phone = phone;
//        this.dateAdoption = dateAdoption;
//        this.animal_id = animal_id;
////        this.animal = animal;
////        this.typePet = typePet;
////        this.petName = petName;
////        this.petBreed = petBreed;
//    }

//    public Adopter(String name, String phone, LocalDate dateAdoption, int animal_id) {
//        this(0, name, phone, dateAdoption, animal_id);
//    }

//    public Adopter(String name, String phone, Animal animal) {
//        this(0, name, phone, LocalDate.now(), animal);
//    }
//
//    public Adopter(String name, String phone, LocalDate dateAdoption, Animal animal) {
//        this(0, name, phone, dateAdoption, animal);
//    }

    @Override
    public String toString() {
        return STR."Id: \{this.id}\nName: \{this.name}\nPhone Number: \{this.phone}\nDate of Adoption: \{this.dateAdoption}\n";
//        if (this.animal != null) {
//            return STR."Id: \{this.id}\nName: \{this.name}\nPhone Number: \{this.phone}\nDate of Adoption: \{this.dateAdoption}\nAnimal: \{this.animal.toString()}\n";
//        }
//        return STR."Id: \{this.id}\nName: \{this.name}\nPhone Number: \{this.phone}\nDate of Adoption: \{this.dateAdoption}\n";
    }
}
