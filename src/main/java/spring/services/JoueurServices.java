package spring.services;

import spring.interfaces.impl.JoueurImp;
import spring.models.Joueur;

import java.util.List;
import java.util.Optional;

public class JoueurServices {

    private final JoueurImp joueurImp =new JoueurImp();

    public JoueurServices() {


    }

    public Joueur creerJoueur(Joueur joueur) {
        return joueurImp.creer(joueur);
    }

    public Joueur modifierJoueur(Joueur joueur) {
        return joueurImp.modifier(joueur);
    }

    public void supprimerJoueur(Long id) {
        joueurImp.supprimer(id);
    }

    public Optional<Joueur> trouverJoueurParId(Long id) {
        return joueurImp.trouverParId(id);
    }

    public List<Joueur> trouverTousLesJoueurs() {
        return joueurImp.trouverTous();
    }
}
