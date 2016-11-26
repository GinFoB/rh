package com.project.services;

import com.project.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by akramkhalifa on 12/07/16.
 */
@Service
@Transactional
public class CvThequeServiceImp implements CvThequeService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public CvTheque AddRusme(CvTheque cvTheque) {
        em.persist(cvTheque);
        return cvTheque;
    }

    @Override
    public Skill addSkill(Skill skill) {
        em.persist(skill);
        return skill;
    }

    @Override
    public Experience addExperience(Experience experience) {
        em.persist(experience);
        return experience;
    }

    @Override
    public Lang addLang(Lang lang) {
        em.persist(lang);
        return lang;
    }

    @Override
    public Formation addFormation(Formation formation) {
        em.persist(formation);
        return formation;
    }

    @Override
    public void AddCvFile(CvTheque cvTheque, String cvFile) {
        cvTheque.setCvFile(cvFile);
        em.merge(cvTheque);
    }

    @Override
    public void editResume(CvTheque cvTheque, String resume) {
        cvTheque.setResume(resume);
         em.merge(cvTheque);
    }

    @Override
    public Skill editSkill(Skill skill, String skillName) {
        skill.setSkillName(skillName);
        return em.merge(skill);
    }

    @Override
    public Lang editLang(Lang lang, String name, String niveau) {
        lang.setName(name);
        lang.setNiveau(niveau);
        return em.merge(lang);
    }

    @Override
    public Formation editFormation(Formation formation, String ecoleName, String diplome, String descriptionFormation) {
        formation.setEcoleName(ecoleName);
        formation.setDiplome(diplome);
        formation.setDescriptionFormation(descriptionFormation);
        return em.merge(formation);
    }

    @Override
    public Experience editExperience(Experience experience, String titre, String entrepriseName, String lieu, String description) {
        experience.setTitre(titre);
        experience.setEntrepriseName(entrepriseName);
        experience.setLieu(lieu);
        experience.setDescription(description);
        return em.merge(experience);
    }

    @Override
    public CvTheque getCvById(Long aLong) {
        try {
            Query query = em.createQuery("select cv from CvTheque cv where cv.user.id = :aLong");
            query.setParameter("aLong",aLong);
            CvTheque cvTheque = (CvTheque)query.getSingleResult();
            return cvTheque;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<CvTheque> search(String skill, String formation) {
        try {
            Query query = em.createQuery("SELECT cv FROM CvTheque cv where cv.skill = :skill and cv.formation = :formation");
            query.setParameter("skill",skill);
            query.setParameter("formation",formation);
            List<CvTheque> cvTheques = query.getResultList();
            return cvTheques;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Skill> getListOfSkills(Long idUser) {
        try {
            Query query = em.createQuery("select skill from Skill skill where skill.user.id = :idUser");
            query.setParameter("idUser",idUser);
            List<Skill> skillList = query.getResultList();
            return skillList;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Lang> getListOfLangs(Long idUser) {
        try {
            Query query = em.createQuery("select lang from Lang lang where lang.user.id = :idUser");
            query.setParameter("idUser",idUser);
            List<Lang> langList = query.getResultList();
            return langList;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Formation> getListOfFormations(Long idUser) {
        try {
            Query query = em.createQuery("select formation from Formation formation where formation.user.id = :idUser");
            query.setParameter("idUser",idUser);
            List<Formation> formationList = query.getResultList();
            return formationList;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Experience> getListOfExperiences(Long idUser) {
        try {
            Query query = em.createQuery("select experience from Experience experience where experience.user.id = :idUser");
            query.setParameter("idUser",idUser);
            List<Experience> experienceList = query.getResultList();
            return experienceList;
        }
        catch(Exception e) {
            return null;
        }
    }


    @Override
    public Skill getSkillById(Long id) {
        try {
            Query query = em.createQuery("select skill from Skill skill where skill.id = :id");
            query.setParameter("id",id);
            Skill skill = (Skill) query.getSingleResult();
            return skill;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public Lang getLangById(Long aLong) {
        try {
            Query query = em.createQuery("select lang from Lang lang where lang.id = :aLong");
            query.setParameter("aLong",aLong);
            Lang lang = (Lang) query.getSingleResult();
            return lang;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public Formation getFormation(Long aLong) {
        try {
            Query query = em.createQuery("select formation from Formation formation where formation.id = :aLong");
            query.setParameter("aLong",aLong);
            Formation formation = (Formation) query.getSingleResult();
            return formation;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public Experience getExperience(Long aLong) {
        try {
            Query query = em.createQuery("select exp from Experience exp where exp.id = :aLong");
            query.setParameter("aLong",aLong);
            Experience experience = (Experience) query.getSingleResult();
            return experience;
        }
        catch(Exception e) {
            return null;
        }
    }


    @Override
    public void deleteSkill(Long idSkill) {
        em.remove(em.find(Skill.class,idSkill));
    }

    @Override
    public void deleteFormation(Long idFormation) {
        em.remove(em.find(Formation.class,idFormation));
    }

    @Override
    public void deleteExperience(Long idExperience) {
        em.remove(em.find(Experience.class,idExperience));
    }
    @Override
    public void deleteLang(Long idLang) {
        em.remove(em.find(Lang.class,idLang));
    }
}
