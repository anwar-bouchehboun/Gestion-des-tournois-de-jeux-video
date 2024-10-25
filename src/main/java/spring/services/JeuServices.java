package spring.services;

import spring.interfaces.impl.JeuImp;
import spring.models.Jeu;
import java.util.List;
import java.util.Optional;

public class JeuServices {

    private JeuImp jeuImp = new JeuImp();

    public JeuServices() {
    }

    public Jeu creerJeu(Jeu jeu) {
        return jeuImp.creer(jeu);
    }

    public Jeu modifierJeu(Jeu jeu) {
        return jeuImp.modifier(jeu);
    }


    public Optional<Jeu> trouverJeuParId(Long id) {
        return jeuImp.trouverParId(id);
    }

    public List<Jeu> trouverTousLesJeux() {
        return jeuImp.trouverTous();
    }
}
