package spring.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Table(name = "tournoi")
public class Tournoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le titre ne peut pas être vide")
    @Column(nullable = false)
    private String titre;

    @ManyToOne
    @JoinColumn(name = "jeu_id", nullable = false)
    private Jeu jeu;

    @NotNull(message = "La date de début ne peut pas être vide")
    @Column(nullable = false)
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin ne peut pas être vide")
    @Column(nullable = false)
    private LocalDate dateFin;

    @NotNull(message = "Le nombre de spectateurs ne peut pas être nul")
    @Column(nullable = false)
    private int nombreSpectateurs;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipes;

    @NotNull(message = "La durée estimée ne peut pas être nulle")
    @Min(value = 1, message = "La durée estimée doit être d'au moins 1 minute")
    @Column(nullable = false)
    private int dureeEstimee;

    @NotNull(message = "Le temps de pause ne peut pas être nul")
    @Min(value = 0, message = "Le temps de pause ne peut pas être négatif")
    @Column(nullable = false)
    private int tempsPause;

    @NotNull(message = "Le temps de cérémonie ne peut pas être nul")
    @Min(value = 0, message = "Le temps de cérémonie ne peut pas être négatif")
    @Column(nullable = false)
    private int tempsCeremonie;

    @NotNull(message = "Le statut ne peut pas être vide")
    @Column(nullable = false)
    private String statut;

    // Constructeur par défaut
    public Tournoi() {}

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public int getNombreSpectateurs() {
        return nombreSpectateurs;
    }

    public void setNombreSpectateurs(int nombreSpectateurs) {
        this.nombreSpectateurs = nombreSpectateurs;
    }

    public Equipe getEquipes() {
        return equipes;
    }

    public void setEquipes(Equipe equipes) {
        this.equipes = equipes;
    }

    public int getDureeEstimee() {
        return dureeEstimee;
    }

    public void setDureeEstimee(int dureeEstimee) {
        this.dureeEstimee = dureeEstimee;
    }

    public int getTempsPause() {
        return tempsPause;
    }

    public void setTempsPause(int tempsPause) {
        this.tempsPause = tempsPause;
    }

    public int getTempsCeremonie() {
        return tempsCeremonie;
    }

    public void setTempsCeremonie(int tempsCeremonie) {
        this.tempsCeremonie = tempsCeremonie;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Tournoi{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", jeu=" + jeu +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", nombreSpectateurs=" + nombreSpectateurs +
                ", equipes=" + equipes +
                ", dureeEstimee=" + dureeEstimee +
                ", tempsPause=" + tempsPause +
                ", tempsCeremonie=" + tempsCeremonie +
                ", statut='" + statut + '\'' +
                '}';
    }
}
