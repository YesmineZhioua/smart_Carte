package projet.isi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.isi.backend.models.Classe;

import java.util.Optional;

@Repository
    public interface classeRepository extends JpaRepository<Classe, Long> {
        Optional<Classe> findById(Long id);

    }


