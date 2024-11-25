package projet.isi.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.isi.backend.models.Salle;

import java.util.List;


@Repository
public interface salleRepository extends JpaRepository<Salle, Long> {
    List<Salle> findByClasse_IdClasse(Long idClasse);  // Trouver les salles par idClasse

    }

