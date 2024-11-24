package projet.isi.backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.isi.backend.models.Salle;
import projet.isi.backend.repository.salleRepository;

import java.util.List;
import java.util.Optional;

@Service

public class salleService {

        @Autowired
        private salleRepository salleRepository;

        // add : Ajouter une nouvelle salle
        public Salle saveOrUpdateSalle(Salle salle) {
            return salleRepository.save(salle);
        }


        // READ : Récupérer toutes les salles
        public List<Salle> getAllSalles() {
            return salleRepository.findAll();
        }

        // READ : Récupérer une salle par ID
        public Optional<Salle> getSalleById(Long id) {
            return salleRepository.findById(id);
        }



        // DELETE : Supprimer une salle par ID
        public void deleteSalle(Long cin) {
            salleRepository.deleteById(cin);
        }


    }


