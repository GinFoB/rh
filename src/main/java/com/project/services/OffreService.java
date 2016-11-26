package com.project.services;

import com.project.entities.CvTheque;
import com.project.entities.Offre;

import java.util.List;

/**
 * Created by akramkhalifa on 13/07/16.
 */
public interface OffreService {

    public Offre addOffre(Offre offre);

    public Offre getOffreByUserId(Long aLong);

    public List<Offre> lisOffres();

    public List<Offre> listOffresById(Long id);

    public Offre editOffre(Offre offre);

    public List<Offre> findOffresByCategory(String type);

    public List<Offre> findOffresByVille(String addressEnt);

    public void deleteOffre(Long id);

    public Offre edit(Offre offreEdit,Long id);

    public Offre findOffre(Long id);

    public Offre offreValide(Offre offre, String display);

    public Offre getOffre(Long aLong);

    public List<Offre> acceptOffre();
}
