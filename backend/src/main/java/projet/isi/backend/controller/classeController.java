package projet.isi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.isi.backend.models.Classe;
import projet.isi.backend.service.classeService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/classes")
public class classeController {

    private static final Logger logger = Logger.getLogger(classeController.class.getName());

    @Autowired
    private classeService classeService;

    // Endpoint pour récupérer toutes les classes
    @GetMapping
    public ResponseEntity<List<Classe>> getAllClasses() {
        List<Classe> classes = classeService.getAllClasses();
        if (classes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Retourne 204 si aucune classe n'est trouvée
        }
        return ResponseEntity.ok(classes);
    }

    // Endpoint pour récupérer une classe par ID
    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClasseById(@PathVariable Long id) {
        Optional<Classe> classe = classeService.getClasseById(id);
        return classe.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveOrUpdateClasse(@RequestBody Classe classe) {
        try {
            if (classe.getNomClasse() == null || classe.getNomClasse().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de la classe ne peut pas être vide ou null.");
            }

            Classe updatedClasse;
            if (classe.getIdClasse() != null) {
                Optional<Classe> existingClasse = classeService.getClasseById(classe.getIdClasse());
                if (existingClasse.isPresent()) {
                    updatedClasse = existingClasse.get();
                    updatedClasse.setNomClasse(classe.getNomClasse());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classe introuvable avec l'ID spécifié.");
                }
            } else {
                updatedClasse = new Classe();
                updatedClasse.setNomClasse(classe.getNomClasse());
            }

            Classe savedClasse = classeService.saveOrUpdateClasse(updatedClasse);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedClasse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'enregistrement de la classe : " + e.getMessage());
        }
    }



    // Endpoint pour supprimer une classe par ID
    @DeleteMapping("/{idClasse}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long idClasse) {
        Optional<Classe> classe = classeService.getClasseById(idClasse);
        if (classe.isPresent()) {
            classeService.deleteClasse(idClasse);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
