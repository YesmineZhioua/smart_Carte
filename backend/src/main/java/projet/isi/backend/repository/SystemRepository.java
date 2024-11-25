package projet.isi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.isi.backend.models.Salle;
import projet.isi.backend.models.System;

import java.util.List;

@Repository
public interface SystemRepository  extends JpaRepository<System, Long> {


    // Récupérer tous les systèmes pour une salle donnée
    @Query("SELECT s FROM System s WHERE s.salle.id = :id")
    List<System> findBySalleId(@Param("id") Long id);
    List<System> findByEtudiantCin(Long cin);  // Utiliser 'cin' et non 'id'


    }





