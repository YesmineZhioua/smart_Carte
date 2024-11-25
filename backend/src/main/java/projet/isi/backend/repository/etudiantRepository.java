package projet.isi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.isi.backend.models.Etudiant;

import java.util.List;

@Repository
public interface etudiantRepository extends JpaRepository<Etudiant, Long> {
    List<Etudiant> findByClasse_IdClasse(Long idClasse);  // Trouver les étudiants par idClasse
}
