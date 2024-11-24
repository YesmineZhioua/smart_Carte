package projet.isi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.isi.backend.models.Etudiant;

@Repository
public interface etudiantRepository extends JpaRepository<Etudiant, Long> {
    // Ajoutez des méthodes de requête personnalisées si nécessaire
}
