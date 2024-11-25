package projet.isi.backend.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.isi.backend.service.salleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projet.isi.backend.models.Etudiant;

import java.util.List;

@RestController
@RequestMapping("/api/systems")


public class systemController {

    @Autowired
    private salleService salleService;

    @GetMapping("/etudiants/{idSalle}")
    public ResponseEntity<?> getEtudiantsBySalle(@PathVariable Long idSalle) {
        List<Etudiant> etudiants = salleService.getEtudiantsBySalle(idSalle);
        if (etudiants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aucun étudiant trouvé pour cette salle.");
        }

        return ResponseEntity.ok(etudiants);  // Retourner la liste des étudiants
    }
}
