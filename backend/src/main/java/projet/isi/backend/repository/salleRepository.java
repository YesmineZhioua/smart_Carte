package projet.isi.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import projet.isi.backend.models.Salle;
public interface salleRepository extends JpaRepository<Salle, Long> {

    }

