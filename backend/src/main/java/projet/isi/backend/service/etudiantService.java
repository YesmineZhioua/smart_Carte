package projet.isi.backend.service;

import projet.isi.backend.models.Classe;
import projet.isi.backend.models.Salle;
import projet.isi.backend.models.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.isi.backend.models.Etudiant;
import projet.isi.backend.repository.SystemRepository;
import projet.isi.backend.repository.etudiantRepository;
import projet.isi.backend.repository.salleRepository;

import java.util.List;
import java.util.Optional;

@Service

public class etudiantService {

        @Autowired
        private etudiantRepository etudiantRepository;
    @Autowired
    private SystemRepository systemRepository;

    @Autowired
    private salleRepository salleRepository;

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




       //ajouter etudant dans system

    public Etudiant addEtudiantWithSystem(Etudiant etudiant) {
        try {
            // Log initial de l'objet étudiant
            java.lang.System.out.println("Etudiant reçu : " + etudiant);

            // Sauvegarder l'étudiant
            Etudiant savedEtudiant = etudiantRepository.save(etudiant);
            java.lang.System.out.println("Etudiant sauvegardé avec ID : " + savedEtudiant.getCin());

            // Récupérer la classe
            Classe classe = savedEtudiant.getClasse();
            if (classe == null) {
                java.lang.System.err.println("Erreur : Classe non associée à l'étudiant");
                throw new IllegalArgumentException("Classe est null");
            }
            java.lang.System.out.println("Classe associée : " + classe);

            // Récupérer la salle
            Salle salle = salleRepository.findById(classe.getIdClasse()).orElse(null);
            if (salle == null) {
                java.lang.System.err.println("Erreur : Salle non trouvée pour la classe");
                throw new IllegalArgumentException("Salle non trouvée");
            }
            java.lang.System.out.println("Salle trouvée : " + salle);

            // Créer un système pour l'étudiant
            System system = new System();
            system.setEtudiant(savedEtudiant);
            system.setStatus("Actif");
            system.setSalle(salle);
            systemRepository.save(system);

            java.lang.System.out.println("Système créé pour l'étudiant : " + system);

            return savedEtudiant;
        } catch (Exception e) {
            java.lang.System.err.println("Erreur lors de l'ajout de l'étudiant : " + e.getMessage());
            throw e;
        }
    }



}


