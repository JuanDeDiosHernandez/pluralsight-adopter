package expeditors.backend.adoption.service;

import expeditors.backend.adoption.classes.Adopter;
import expeditors.backend.adoption.classes.AdopterPets;
import expeditors.backend.adoption.dao.AdopterDAO;
import expeditors.backend.adoption.dao.repository.AdopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//("adoptionService")
public class AdopterService {

    @Autowired
    private AdopterDAO adopterDAO;
//    private AdopterDAO adopterDAO = new JPAAdopterDAO();

    public Adopter createAdopter(Adopter adopter) {
        return adopterDAO.insert(adopter);
    }

    public Adopter createAdopterWithPet(AdopterPets adopterPets) {
        return adopterDAO.insertWithPet(adopterPets);
    }

    public boolean deleteAdopter(int id) {
        return adopterDAO.delete(id);
    }

    public boolean updateAdopter(Adopter adopter) {
        return adopterDAO.update(adopter);
    }

    public Adopter getAdopter(int id) {
        return adopterDAO.findById(id);
    }

    public List<Adopter> getAdopters() { return adopterDAO.findAll(); }

    public AdopterDAO getAdopterDAO() {
        return adopterDAO;
    }

    public void setAdopterDAO(AdopterDAO adopterDAO) {
        this.adopterDAO = adopterDAO;
    }

    public Adopter getAdopterByName(String name) {
        return adopterDAO.findByName(name);
    }

    public List<Adopter> getAdoptersSortedByName() {
        return adopterDAO.findAllSortedByName();
    }
}
