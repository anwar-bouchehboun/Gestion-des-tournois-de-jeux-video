package spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;



@Entity
@Table(name = "jeux")
public class Jeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom ne peut pas être vide")
    private String nom;

    @NotNull(message = "La difficulté ne peut pas être vide")
    private String difficulte;

    @NotNull(message = "La dureeMoyenne ne peut pas être vide")
    @Min(value = 1, message = "La durée moyenne doit être d'au moins 1 minute")
    @Max(value = 300, message = "La durée moyenne ne peut pas dépasser 300 minutes")
    private int dureeMoyenne;

    // Constructeur par défaut
    public Jeu() {}



    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public int getDureeMoyenne() {
        return dureeMoyenne;
    }

    public void setDureeMoyenne(int dureeMoyenne) {
        this.dureeMoyenne = dureeMoyenne;
    }

}
