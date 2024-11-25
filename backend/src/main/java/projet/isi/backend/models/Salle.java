package projet.isi.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table (name = "salle")
public class Salle {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Permet l'insertion manuelle
        @Column(name = "id_salle" , unique = true , nullable = false)
        private long idSalle ;


        @JsonFormat(pattern = "yyyy-MM-dd")
        @Column(name = "jour")
        private Date jour;


        @Column ( name = " cours" )
        private  String cours;
        @JsonFormat(pattern = "HH:mm:ss")
        @Column(name = "dateDebut")
        private Time dateDebut;
        ;


        @ManyToOne
        @JoinColumn(name = "id_classe")
        private Classe classe;
        public long getIdSalle() {
                return idSalle;
        }

        public void setIdSalle(long idSalle) {
                this.idSalle = idSalle;
        }

        public Date getJour() {
                return jour;
        }

        public void setJour(Date jour) {
                this.jour = jour;
        }

        public String getCours() {
                return cours;
        }

        public void setCours(String cours) {
                this.cours = cours;
        }


        public Time getDateDebut() {
                return dateDebut;
        }

        public void setDateDebut(Time dateDebut) {
                this.dateDebut = dateDebut;
        }

        public Classe getClasse() {
                return classe;
        }

        public void setClasse(Classe classe) {
                this.classe = classe;
        }
}




