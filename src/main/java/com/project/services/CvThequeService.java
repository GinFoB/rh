package com.project.services;

import com.project.entities.*;

import java.util.List;

/**
 * Created by akramkhalifa on 12/07/16.
 */
public interface CvThequeService {

    public CvTheque AddRusme(CvTheque cvTheque);

    public Skill addSkill(Skill skill);
    public Experience addExperience(Experience experience);
    public Lang addLang(Lang lang);
    public Formation addFormation(Formation formation);

    public void AddCvFile(CvTheque cvTheque, String cvFile);

    public CvTheque getCvById(Long aLong);

    public List<CvTheque> search(String skill, String formation);

    public List<Skill> getListOfSkills(Long idUser);
    public List<Lang> getListOfLangs(Long idUser);
    public List<Formation> getListOfFormations(Long idUser);
    public List<Experience> getListOfExperiences(Long idUser);

    public Skill getSkillById(Long id);
    public Lang getLangById(Long aLong);
    public Formation getFormation(Long aLong);
    public Experience getExperience(Long aLong);

    public void editResume(CvTheque cvTheque,String resume);
    public Skill editSkill (Skill skill, String skillName);
    public Lang editLang(Lang lang, String name, String niveau);
    public Formation editFormation(Formation formation, String ecoleName,String diplome, String descriptionFormation);
    public Experience editExperience(Experience experience, String titre, String entrepriseName, String lieu, String description);

    public void deleteSkill(Long idSkill);
    public void  deleteFormation(Long idFormation);
    public void deleteExperience(Long idExperience);
    public void deleteLang(Long idLang);
}
