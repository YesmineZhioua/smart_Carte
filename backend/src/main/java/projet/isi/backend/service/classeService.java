package projet.isi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.isi.backend.models.Classe;
import projet.isi.backend.repository.classeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class classeService {

    @Autowired
    private classeRepository classeRepository;

    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    public Optional<Classe> getClasseById(Long id_Classe) {
        return classeRepository.findById(id_Classe);
    }

    public Classe saveOrUpdateClasse(Classe classe) {
        return classeRepository.save(classe);
    }

    public void deleteClasse(Long id_Classe) {
        classeRepository.deleteById(id_Classe);
    }
}
