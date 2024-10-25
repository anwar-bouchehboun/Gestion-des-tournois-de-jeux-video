package spring.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "joueur")
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le pseudo ne peut pas être vide")
    @Column(nullable = false)
    private String pseudo;

    @NotNull(message = "L'âge ne peut pas être nul")
    @Column(nullable = false)
    private int age;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    // Constructeur
    public Joueur() {}

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public String afficherJoueur() {
        String equipeNom = (equipe != null) ? equipe.getNom() : "Pas d'équipe";
        return String.format("%-5d %-20s %-15d %-15s", id, pseudo, age, equipeNom);
    }

    @Override
    public String toString() {
        return afficherJoueur();
    }
}
