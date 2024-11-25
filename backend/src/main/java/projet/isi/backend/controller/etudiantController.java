package projet.isi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.isi.backend.models.Classe;
import projet.isi.backend.models.Etudiant;
import projet.isi.backend.service.etudiantService;
import projet.isi.backend.service.classeService;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/etudiants")
public class etudiantController {


        @Autowired
        private etudiantService etudiantService;

        @Autowired
        private classeService classeService;


    // Récupérer tous les étudiants
        @GetMapping
        public ResponseEntity<List<Etudiant>> getAllEtudiants() {
            List<Etudiant> etudiants = etudiantService.getAllEtudiants();
            if (etudiants.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(etudiants);
        }

        // Récupérer un étudiant par CIN
        @GetMapping("/{cin}")
        public ResponseEntity<Etudiant> getEtudiantByCin(@PathVariable Long cin) {
            Optional<Etudiant> etudiant = etudiantService.getEtudiantByCin(cin);
            return etudiant.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }

        // Ajouter ou mettre à jour un étudiant
        @PostMapping("/add")
        public ResponseEntity<?> saveOrUpdateEtudiant(@RequestBody Etudiant etudiant) {
            try {
                // Vérification des champs obligatoires
                if (etudiant.getCin() == null || etudiant.getNom() == null || etudiant.getNom().trim().isEmpty() ||
                        etudiant.getPrenom() == null || etudiant.getPrenom().trim().isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Les champs CIN, nom et prénom sont obligatoires.");
                }

                Etudiant updatedEtudiant;

                // Vérification si l'étudiant existe déjà (mise à jour)
                Optional<Etudiant> existingEtudiant = etudiantService.getEtudiantByCin(etudiant.getCin());
                if (existingEtudiant.isPresent()) {
                    // Mise à jour de l'étudiant existant
                    updatedEtudiant = existingEtudiant.get();
                    updatedEtudiant.setNom(etudiant.getNom());
                    updatedEtudiant.setPrenom(etudiant.getPrenom());
                    updatedEtudiant.setIdCarte(etudiant.getIdCarte());

                    if (etudiant.getClasse() != null && etudiant.getClasse().getIdClasse() != null) {
                        Optional<Classe> classe = classeService.getClasseById(etudiant.getClasse().getIdClasse());
                        if (!classe.isPresent()) {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body("Classe introuvable avec l'ID spécifié.");
                        }
                        updatedEtudiant.setClasse(classe.get());
                    }
                } else {
                    // Création d'un nouvel étudiant
                    updatedEtudiant = new Etudiant();
                    updatedEtudiant.setCin(etudiant.getCin());
                    updatedEtudiant.setNom(etudiant.getNom());
                    updatedEtudiant.setPrenom(etudiant.getPrenom());
                    updatedEtudiant.setIdCarte(etudiant.getIdCarte());

                    if (etudiant.getClasse() != null && etudiant.getClasse().getIdClasse() != null) {
                        Optional<Classe> classe = classeService.getClasseById(etudiant.getClasse().getIdClasse());
                        if (!classe.isPresent()) {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body("Classe introuvable avec l'ID spécifié.");
                        }
                        updatedEtudiant.setClasse(classe.get());
                    }
                }

                // Sauvegarder l'étudiant
                Etudiant savedEtudiant = etudiantService.saveOrUpdateEtudiant(updatedEtudiant);

                return ResponseEntity.status(HttpStatus.CREATED).body(savedEtudiant);

            } catch (Exception e) {
                // Gestion des erreurs
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erreur lors de l'enregistrement de l'étudiant : " + e.getMessage());
            }
        }


    // Supprimer un étudiant par CIN
        @DeleteMapping("/{cin}")
        public ResponseEntity<Void> deleteEtudiant(@PathVariable Long cin) {
            Optional<Etudiant> etudiant = etudiantService.getEtudiantByCin(cin);
            if (etudiant.isPresent()) {
                etudiantService.deleteEtudiant(cin);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }



        //ajouter etudiant dans system

    @PostMapping("/addEtuSys")
    public ResponseEntity<Etudiant> addEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant savedEtudiant = etudiantService.addEtudiantWithSystem(etudiant);
        return new ResponseEntity<>(savedEtudiant, HttpStatus.CREATED);
    }
    }


