package projet.isi.backend.models;

import jakarta.persistence.*;


@Entity
@Table(name = "system")

public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ou GenerationType.AUTO, selon votre base de données
    @Column(name = "id_system" , nullable = false , unique = true)
    private Long idSystem ;

    @Column(name = "status")
    private String status; // "present" or "absent"

        @ManyToOne
        @JoinColumn(name = "id_salle" , nullable = false)
        private Salle salle ;

        @ManyToOne
        @JoinColumn(name = "cin" , nullable = false)
        private Etudiant etudiant ;

        public Long getIdSystem() {
            return idSystem;
        }

        public void setIdSystem(Long idSystem) {
            this.idSystem = idSystem;
        }


        public Salle getSalle() {
            return salle;
        }

        public void setSalle(Salle salle) {
            this.salle = salle;
        }

        public Etudiant getEtudiant() {
            return etudiant;
        }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEtudiant(Etudiant etudiant) {
            this.etudiant = etudiant;
        }
    }


