package projet.isi.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "classe")  // Nom de la table dans la base de données
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_classe")
    private Long idClasse;  // Respecter la convention camelCase

    @Column(name = "nom_classe" , nullable = false)
    private String nomClasse;  // Respecter la convention camelCase

    // Si vous souhaitez garder la relation avec Etudiant, vous pouvez décommenter cette partie
    /*@OneToMany(mappedBy = "classe", fetch = FetchType.EAGER)
    private List<Etudiant> etudiants;  // Relation avec l'entité Etudiant*/

    // Getters et setters
    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        if (nomClasse == null || nomClasse.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la classe ne peut pas être vide ou null.");
        }
        this.nomClasse = nomClasse;
    }
    /* public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    } */
}
