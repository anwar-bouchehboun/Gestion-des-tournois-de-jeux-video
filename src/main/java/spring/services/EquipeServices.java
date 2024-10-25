package spring.services;

import spring.interfaces.impl.EquipeImp;
import spring.models.Equipe;

import java.util.List;
import java.util.Optional;

public class EquipeServices {

    private EquipeImp equipeImp = new EquipeImp();

    public EquipeServices() {
    }

    public Equipe creerEquipe(Equipe equipe) {
        return equipeImp.creer(equipe);
    }

    public Equipe modifierEquipe(Equipe equipe) {
        return equipeImp.modifier(equipe);
    }

    public void supprimerEquipe(Long id) {
        equipeImp.supprimer(id);
    }

    public Optional<Equipe> trouverEquipeParId(Long id) {
        return equipeImp.trouverParId(id);
    }

    public List<Equipe> trouverToutesLesEquipes() {
        return equipeImp.trouverTous();
    }

    public void ajouterJoueur(Long id, Equipe equipe) {
        equipeImp.ajouter(id, equipe);
    }

    public void retirerJoueur(Long id, Equipe equipe) {
        equipeImp.retirer(id, equipe);
    }
}