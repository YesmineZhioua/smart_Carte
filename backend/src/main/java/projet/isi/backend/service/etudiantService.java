package projet.isi.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.isi.backend.models.Etudiant;
import projet.isi.backend.repository.etudiantRepository;

import java.util.List;
import java.util.Optional;

@Service

public class etudiantService {

        @Autowired
        private etudiantRepository etudiantRepository;

        public List<Etudiant> getAllEtudiants() {
            return etudiantRepository.findAll();
        }

        public Optional<Etudiant> getEtudiantByCin(Long cin) {
            return etudiantRepository.findById(cin);
        }

        public Etudiant saveOrUpdateEtudiant(Etudiant etudiant) {
            return etudiantRepository.save(etudiant);
        }

        public void deleteEtudiant(Long cin) {
            etudiantRepository.deleteById(cin);
        }
    }


