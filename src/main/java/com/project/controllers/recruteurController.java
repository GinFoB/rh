package com.project.controllers;

import com.project.entities.CvTheque;
import com.project.entities.Offre;
import com.project.entities.User;
import com.project.services.CvThequeService;
import com.project.services.OffreService;
import com.project.services.UserService;
import com.project.utils.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by akramkhalifa on 11/07/16.
 */
@Controller
public class recruteurController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    private OffreService offreService;
    @Autowired

    public void setOffreService(OffreService offreService){
        this.offreService = offreService;
    }

    private CvThequeService cvThequeService;
    @Autowired
    public void setCvThequeService(CvThequeService cvThequeService){
        this.cvThequeService = cvThequeService;
    }

    @Autowired
    private SmtpMailSender smtpMailSender;

    @RequestMapping(value = "/edit/recruiteur", method = RequestMethod.GET)
    public String getInfosPage(Model model,HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("infos",new User());
        model.addAttribute("user",user);
        return "user/recruiteur/editForm";
    }

    @RequestMapping(value = "/edit/recruiteur", method = RequestMethod.POST)
    public String getInfosPage(User user, HttpSession session, HttpServletRequest request){

        user = (User) session.getAttribute("user");

        String nameEntreprise = request.getParameter("nameEntreprise");
        String DesEntreprise = request.getParameter("DesEntreprise");
        String numEmploye = request.getParameter("numEmploye");
        String addressEnt = request.getParameter("addressEnt");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        userService.editUser(user,nameEntreprise,DesEntreprise,numEmploye,addressEnt,firstName,lastName,email);

        return "redirect:/profile/recruiteur";
    }

    @RequestMapping(value = "/new/recruiteur", method = RequestMethod.GET)
    public String addCandidat(Model model){
        model.addAttribute("user",new User());
        return "user/recruiteur/formRecruiteur";
    }

    @RequestMapping(value = "/new/recruiteur", method = RequestMethod.POST)
    public String addCandidat(User user, final BindingResult result){

        if (result.hasErrors()){
            return "user/candidat/formCandidat";
        }
        else {
            System.out.print("here"+user);
            user.setRecruiteur(true);
            user.setRecruiteurInfos(false);
            System.out.print("whtsf"+user);
            userService.create(user);
           /* try {
                smtpMailSender.send(user.getEmail(), "Valide votre compte", "<h2>Bonjour</h2><br /><p>Merci pour votre confiance</p><br /><a href='http://localhost:8080/login'>Acces à la Platforme </a><br /><p>Bien à vous</p>");
            } catch (MessagingException e) {
                e.printStackTrace();
                return "redirect:/login";
            }*/
            return "redirect:/check";
        }
    }

    @RequestMapping("/profile/recruiteur")
    public String setProfile(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        User user1 =  userService.findUserById(user.getId());
        model.addAttribute("infos",user1);
        return "user/recruiteur/recruiteurProfile";
    }

    @RequestMapping(value = "/search/talent",method = RequestMethod.GET)
    public String searchTalent(Model model){
        model.addAttribute("cv", new CvTheque());
        return "user/recruiteur/talent";
    }

    @RequestMapping(value = "/search/talent",method = RequestMethod.POST)
    public String searchTalent(CvTheque cvTheque,Model model){
        /*String skill = cvTheque.getSkill();
        String formation = cvTheque.getFormation();*/
        /*System.out.println("skills+++++"+ skill);
        List<CvTheque> cvThequeList = cvThequeService.search(skill, formation);*/
       /* for(CvTheque theque: cvThequeList){
            model.addAttribute("username",theque.getUser().getUserName());
        }*/
        return "user/recruiteur/talentForm";
    }

    @RequestMapping("/list/offre")
    public String listOffreByUser(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Offre> list =  offreService.listOffresById(user.getId());
        System.out.print(list);
        model.addAttribute("offres",list);
        return "user/recruiteur/offresById";
    }

    @RequestMapping(value = "/remove/offre", method = RequestMethod.GET)
    public String removeOffre(Model model){
        model.addAttribute("todelete", new Offre());
        return "user/recruiteur/offresById";
    }

    @RequestMapping(value = "/remove/offre", method = RequestMethod.POST)
    public String removeOffre(Offre offre,HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        String id = request.getParameter("offre_id");
        offreService.deleteOffre(Long.parseLong(id));
        return "redirect:/list/offre";
    }

    @RequestMapping(value = "/edit/offre", method = RequestMethod.GET)
    public String editOffre(Model model){
        model.addAttribute("todelete", new Offre());
        return "user/recruiteur/offresById";
    }

    @RequestMapping(value = "/edit/offre", method = RequestMethod.POST)
    public String editOffre(Offre offre,HttpServletRequest request){
        String id = request.getParameter("offre_id");
        return "redirect:/list/offre";
    }

   /* @RequestMapping(value = "/get/offre",method = RequestMethod.GET)
    public String getOffre(Model model){
        model.addAttribute("todelete", new Offre());
        return "user/recruiteur/offresById";
    }


    @RequestMapping(value = "/get/offre",method = RequestMethod.POST)
    public String getOffre(Model model,HttpServletRequest request){
        String id = request.getParameter("offre_id");
        Offre offre =  offreService.findOffre(Long.parseLong(id));
        System.out.print(offre);
        model.addAttribute("offre",offre);
        return "offres";
    }*/




}
