package com.project.services;

import com.project.entities.Category;
import com.project.entities.Offre;
import com.project.entities.Skill;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by akramkhalifa on 13/07/16.
 */
@Service
@Transactional
public class OffreServiceImp implements OffreService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Offre editOffre(Offre offre) {
        em.merge(offre);
        return offre;
    }

    @Override
    public Offre addOffre(Offre offre) {
        em.persist(offre);
        return offre;
    }

    @Override
    public List<Offre> lisOffres() {
        try {
            Query query = em.createQuery("SELECT offre FROM Offre offre");
            List<Offre> offres = query.getResultList();
            return offres;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Offre> acceptOffre() {
        try {
            Query query = em.createQuery("SELECT offre FROM Offre offre where offre.display = 'ACCEPTE' ");
            List<Offre> offres = query.getResultList();
            return offres;
        }
        catch (Exception e){
            return null;
        }    }

    @Override
    public List<Offre> listOffresById(Long id) {
        try {
            Query query = em.createQuery("SELECT offre FROM Offre offre where offre.user.id = :id");
            query.setParameter("id",id);
            List<Offre> offres = query.getResultList();
            return offres;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public Offre getOffreByUserId(Long aLong) {
        try {
            Query query = em.createQuery("select offre from Offre offre where offre.user.id = :aLong");
            query.setParameter("aLong",aLong);
            Offre offre = (Offre)query.getSingleResult();
            return offre;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Offre> findOffresByCategory(String type) {
        try {
            Query query = em.createQuery("select offre from Offre offre where offre.display = 'ACCEPTE' and offre.category.type = :type");
            query.setParameter("type",type);
            List<Offre> offreList = query.getResultList();
            return offreList;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Offre> findOffresByVille(String addressEnt) {
        try {
            Query query = em.createQuery("select offre from Offre offre where offre.display = 'ACCEPTE' and offre.user.addressEnt like CONCAT('%', :addressEnt, '%')");
            query.setParameter("addressEnt",addressEnt);
            List<Offre> offreList = query.getResultList();
            return offreList;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public void deleteOffre(Long id) {
        em.remove(em.find(Offre.class,id));
    }

    @Override
    public Offre edit(Offre offreEdit,Long id) {
        Offre offre = em.find(Offre.class,id);
        if (offre != null){
           return offre = em.merge(offreEdit);
        }
        else {
            em.persist(offreEdit);
            return offreEdit;
        }
    }

    @Override
    public Offre findOffre(Long id) {
       return em.find(Offre.class,id);
    }

    @Override
    public Offre offreValide(Offre offre, String display) {
        offre.setDisplay(display);
        return  em.merge(offre);
    }

    @Override
    public Offre getOffre(Long aLong) {
        try {
            Query query = em.createQuery("select offre from Offre offre where offre.id = :aLong");
            query.setParameter("aLong",aLong);
            Offre offre = (Offre) query.getSingleResult();
            return offre;
        }
        catch(Exception e) {
            return null;
        }
    }
}
