package projet.isi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.isi.backend.models.Classe;
import projet.isi.backend.models.Etudiant;
import projet.isi.backend.models.Salle;
import projet.isi.backend.service.salleService;
import projet.isi.backend.service.classeService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salles")
public class salleController {

    @Autowired
    private salleService salleService;

    @Autowired
    private classeService classeService ;

    // CREATE : Ajouter une nouvelle salle
    @PostMapping("/add")
    public ResponseEntity<?> saveOrUpdateSalle(@RequestBody Salle salle) {
        try {
            // Vérification des champs obligatoires
            if (salle.getJour() == null || salle.getCours() == null || salle.getCours().trim().isEmpty() ||
                    salle.getDateDebut() == null ) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Les champs jour, cours, début sont obligatoires.");
            }

            Salle updatedSalle;

            // Vérification si la salle existe déjà (mise à jour)
            Optional<Salle> existingSalle = salleService.getSalleById(salle.getIdSalle());
            if (existingSalle.isPresent()) {
                // Mise à jour de la salle existante
                updatedSalle = existingSalle.get();
                updatedSalle.setJour(salle.getJour());
                updatedSalle.setCours(salle.getCours());
                updatedSalle.setDateDebut(salle.getDateDebut());
                updatedSalle.setClasse(salle.getClasse());
            } else {
                // Création d'une nouvelle salle
                updatedSalle = new Salle();
                updatedSalle.setJour(salle.getJour());
                updatedSalle.setCours(salle.getCours());
                updatedSalle.setDateDebut(salle.getDateDebut());
                updatedSalle.setClasse(salle.getClasse());
            }

            // Sauvegarder la salle
            Salle savedSalle = salleService.saveOrUpdateSalle(updatedSalle);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedSalle);

        } catch (Exception e) {
            // Gestion des erreurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'enregistrement de la salle : " + e.getMessage());
        }
    }



    // READ : Récupérer toutes les salles
    @GetMapping
    public List<Salle> getAllSalles() {
        return salleService.getAllSalles();
    }

    // READ : Récupérer une salle par ID
    @GetMapping("/{id}")
    public ResponseEntity<Salle> getSalleById(@PathVariable Long id) {
        return salleService.getSalleById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // DELETE : Supprimer une salle par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable Long id) {
        try {
            salleService.deleteSalle(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
