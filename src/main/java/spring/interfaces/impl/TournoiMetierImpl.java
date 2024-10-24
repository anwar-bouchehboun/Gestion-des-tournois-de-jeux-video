package spring.interfaces.impl;

import spring.interfaces.TournoiMetier;
import spring.interfaces.TournoiDao;

public class TournoiMetierImpl implements TournoiMetier {

    private final TournoiDao tournoiDao;

    public TournoiMetierImpl(TournoiDao tournoiDao) {
        this.tournoiDao = tournoiDao;
    }

    @Override
    public int obtenirdureeEstimeeTournoi(Long tournoiId) {
        return tournoiDao.calculerdureeEstimeeTournoi(tournoiId);
    }
}
