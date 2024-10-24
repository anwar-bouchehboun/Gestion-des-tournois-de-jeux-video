package spring.interfaces.impl;

import spring.interfaces.GeneralInterface;
import spring.interfaces.OperationInteraface;
import spring.models.Tournoi;

import java.util.List;
import java.util.Optional;

public class TournoiImp implements GeneralInterface<Tournoi> , OperationInteraface<Tournoi> {


    public TournoiImp(){

    }
    @Override
    public Tournoi creer(Tournoi entity) {
        return null;
    }

    @Override
    public Tournoi modifier(Tournoi entity) {
        return null;
    }

    @Override
    public void supprimer(Long id) {

    }

    @Override
    public Optional<Tournoi> trouverParId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Tournoi> trouverTous() {
        return null;
    }

    @Override
    public void ajouterJoueur(Long Id, Tournoi entity) {

    }

    @Override
    public void retirerJoueur(Long Id, Tournoi entity) {

    }
}
