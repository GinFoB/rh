package com.project.controllers;

import com.project.entities.*;
import com.project.services.CvThequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by akramkhalifa on 12/07/16.
 */
@Controller
public class cvThequeController  {

    private CvThequeService cvThequeService;

    @Autowired
    public void setCvThequeService(CvThequeService cvThequeService){
        this.cvThequeService = cvThequeService;
    }

    @RequestMapping(value = "/add/cv", method = RequestMethod.GET)
    public String addCvTq(Model model){
        model.addAttribute("cv",new CvTheque());
        return "user/candidat/cvForm";
    }

    @RequestMapping(value = "/add/cv", method = RequestMethod.POST)
    public String addCvTq(CvTheque cvTheque, HttpSession session){
        User user = (User) session.getAttribute("user");
        cvTheque.setUser(user);
        cvThequeService.AddRusme(cvTheque);
        return "redirect:/uplaod/cv";
    }

    @RequestMapping(value = "/add/skill",method = RequestMethod.GET)
    public String addSkill(Model model){
        model.addAttribute("cv",new Skill());
        return "user/candidat/cvForm";
    }

    @RequestMapping(value = "/add/skill",method = RequestMethod.POST)
    public String addSkill(Skill skill, HttpSession session){
        User user = (User) session.getAttribute("user");
        skill.setUser(user);
        cvThequeService.addSkill(skill);
        return "redirect:/add/cv";
    }
    @RequestMapping(value = "/add/formation", method = RequestMethod.GET)
    public String addFormation(Model model){
        model.addAttribute("cv",new Formation());
        return "user/candidat/cvForm";
    }

    @RequestMapping(value = "/add/formation", method = RequestMethod.POST)
    public String addFormation(Formation formation, HttpSession session){
        User user = (User) session.getAttribute("user");
        formation.setUser(user);
        cvThequeService.addFormation(formation);
        return "redirect:/add/cv";
    }


    @RequestMapping(value = "/add/lang", method = RequestMethod.GET)
    public String addLang(Model model){
        model.addAttribute("cv",new Lang());
        return "user/candidat/cvForm";
    }

    @RequestMapping(value = "/add/lang", method = RequestMethod.POST)
    public String addLang(Lang lang, HttpSession session){
        User user = (User) session.getAttribute("user");
        lang.setUser(user);
        cvThequeService.addLang(lang);
        return "redirect:/add/cv";
    }


    @RequestMapping(value = "/add/experience", method = RequestMethod.GET)
    public String addExperience(Model model){
        model.addAttribute("cv",new Experience());
        return "user/candidat/cvForm";
    }

    @RequestMapping(value = "/add/experience", method = RequestMethod.POST)
    public String addExperience(Experience experience, HttpSession session){
        User user = (User) session.getAttribute("user");
        experience.setUser(user);
        cvThequeService.addExperience(experience);
        return "redirect:/add/cv";
    }

    @RequestMapping(value = "/uplaod/cv", method = RequestMethod.GET)
    public String getPageCv(Model model){

        return "user/candidat/uploadForm";
    }

    @RequestMapping(value = "/cv/get", method = RequestMethod.GET)
    public String getCvTq(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
       /* System.out.println("CvTheque+////++++///"+cvThequeService.getCvByUser(user.getId()));
        model.addAttribute("cv",cvThequeService.getCvByUser(user.getId()));*/
        return "user/candidat/displayCvForm";
    }


    @RequestMapping("/cv/name")
    public ResponseEntity<String> getCvUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        CvTheque theque = cvThequeService.getCvById(user.getId());
        String cv = theque.getCvFile();
        return new ResponseEntity<String>(cv, HttpStatus.OK);

    }

    @RequestMapping(value = "/edit/resume", method = RequestMethod.GET)
    public String editResume(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        CvTheque cvTheque =  cvThequeService.getCvById(user.getId());
        if (cvTheque !=null){
            model.addAttribute("resume",cvTheque.getResume());
            return "user/candidat/editResume";
        }
        else {
            model.addAttribute("cv",new CvTheque());
            return "user/candidat/cvForm";
        }
    }

    @RequestMapping(value = "/edit/resume", method = RequestMethod.POST)
    public String editResume(CvTheque theque , HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        String resume = request.getParameter("resume");
        theque =  cvThequeService.getCvById(user.getId());
        cvThequeService.editResume(theque,resume);
        return "redirect:/profile/candidat";
    }

    @RequestMapping(value = "/edit/skill", method = RequestMethod.GET)
    public String editSkill(Model model,HttpSession session){
        model.addAttribute("skill",new Skill());
        User user = (User) session.getAttribute("user");
        List<Skill> skills = cvThequeService.getListOfSkills(user.getId());
        model.addAttribute("skillList",skills);
        return "user/candidat/editSkill";
    }

    @RequestMapping(value = "/edit/skill", method = RequestMethod.POST)
    public String editSkill(Skill skill, HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        String idSkill = request.getParameter("idSkill");
        String skillName = request.getParameter("skillName");
        skill =  cvThequeService.getSkillById(Long.valueOf(idSkill));
        cvThequeService.editSkill(skill,skillName);
        return "redirect:/profile/candidat";
    }
    @RequestMapping(value = "/edit/formation", method = RequestMethod.GET)
    public String editFormation(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
       // model.addAttribute("formation",new Formation());
        List<Formation> formationList = cvThequeService.getListOfFormations(user.getId());
        model.addAttribute("formationList",formationList);
        return "user/candidat/editFormation";
    }

    @RequestMapping(value = "/edit/formation", method = RequestMethod.POST)
    public String editFormation(Formation formation, HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        String idFormation = request.getParameter("idFormation");
        String ecoleName = request.getParameter("ecoleName");
        String diplome = request.getParameter("diplome");
        String descriptionFormation = request.getParameter("descriptionFormation");
        formation = cvThequeService.getFormation(Long.valueOf(idFormation));
        cvThequeService.editFormation(formation,ecoleName,diplome,descriptionFormation);
        return "redirect:/profile/candidat";
    }

    @RequestMapping(value = "/edit/lang", method = RequestMethod.GET)
    public String editLang(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Lang> langList = cvThequeService.getListOfLangs(user.getId());
        model.addAttribute("langList",langList);
        return "user/candidat/editLang";
    }

    @RequestMapping(value = "/edit/lang", method = RequestMethod.POST)
    public String editLang(Lang lang, HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");

        String idLang = request.getParameter("idLang");
        String name = request.getParameter("name");
        String niveau = request.getParameter("niveau");
        lang = cvThequeService.getLangById(Long.valueOf(idLang));
        cvThequeService.editLang(lang,name,niveau);

        return "redirect:/profile/candidat";
    }


    @RequestMapping(value = "/edit/experience", method = RequestMethod.GET)
    public String editExperience(Model model,HttpSession session, HttpServletRequest request){
        model.addAttribute("exprience",new Experience());
        User user = (User) session.getAttribute("user");
        List<Experience> experienceList = cvThequeService.getListOfExperiences(user.getId());
        model.addAttribute("experienceList",experienceList);
        return "user/candidat/editExprience";
    }

    @RequestMapping(value = "/edit/experience", method = RequestMethod.POST)
    public String editExperience(Experience experience, HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");

        String idExp = request.getParameter("idExp");
        String entrepriseName  = request.getParameter("entrepriseName");
        String titre = request.getParameter("titre");
        String lieu = request.getParameter("lieu");
        System.out.print("Lieux////"+lieu);
        String description = request.getParameter("description");

        experience = cvThequeService.getExperience(Long.valueOf(idExp));
        cvThequeService.editExperience(experience,titre,entrepriseName,lieu,description);

        return "redirect:/profile/candidat";
    }


}
