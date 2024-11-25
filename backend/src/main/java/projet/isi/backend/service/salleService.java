package projet.isi.backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.isi.backend.models.Etudiant;
import projet.isi.backend.models.Salle;
import projet.isi.backend.models.System;  // Votre classe personnalisée

import projet.isi.backend.repository.SystemRepository;
import projet.isi.backend.repository.salleRepository;
import projet.isi.backend.repository.etudiantRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class salleService {

    @Autowired
    private salleRepository salleRepository;
    @Autowired
    private etudiantRepository etudiantRepository;
    @Autowired
    private SystemRepository systemRepository;


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


    //liaison entre salle etudiant et system

    // Méthode pour ajouter une salle et mettre à jour automatiquement le système
    public Salle addSalle(Salle salle) {
        // Sauvegarder la salle
        Salle savedSalle = salleRepository.save(salle);

        // Récupérer les étudiants de la même classe
        List<Etudiant> etudiants = etudiantRepository.findByClasse_IdClasse(salle.getClasse().getIdClasse());

        // Ajouter chaque étudiant à la table système avec cette salle
        for (Etudiant etudiant : etudiants) {
            System system = new System();
            system.setEtudiant(etudiant);
            system.setSalle(savedSalle);
            system.setStatus("absent");  // Valeur par défaut
            systemRepository.save(system);
        }

        return savedSalle;
    }

    // Ajoutez cette méthode pour obtenir les étudiants par salle
    public List<Etudiant> getEtudiantsBySalle(Long idSalle) {
        // Trouver tous les systèmes pour une salle donnée
        List<System> systems = systemRepository.findBySalleId(idSalle);

        // Extraire les étudiants à partir de la liste des systèmes
        return systems.stream()
                .map(System::getEtudiant)
                .collect(Collectors.toList());
    }

}
