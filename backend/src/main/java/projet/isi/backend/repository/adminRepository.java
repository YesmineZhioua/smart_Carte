package projet.isi.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.isi.backend.models.admin;

@Repository
public interface adminRepository  extends JpaRepository<admin ,Long > {
    admin findByLogin(String login); // Rechercher par login uniquement


}
