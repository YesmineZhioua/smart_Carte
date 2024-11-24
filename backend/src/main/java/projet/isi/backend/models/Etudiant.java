package projet.isi.backend.models;

import jakarta.persistence.*;

@Entity
public class Etudiant {  // Respecter la convention de nommage CamelCase pour les classes

    @Id
    @Column(name = "cin", nullable = false, unique = true)
    private Long cin;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "id_carte", nullable = false, unique = true)
    private long idCarte;

    @ManyToOne
    @JoinColumn(name = "id_classe")
    private Classe classe;  // Nom de la classe doit commencer par une majuscule

    // Getters et setters
    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public long getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(long idCarte) {
        this.idCarte = idCarte;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
