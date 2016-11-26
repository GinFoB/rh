package com.project.controllers;

import com.project.entities.Category;
import com.project.entities.Offre;
import com.project.entities.User;
import com.project.services.CategoryService;
import com.project.services.OffreService;
import com.project.services.UserService;
import com.project.utils.SmtpMailSender;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akramkhalifa on 18/07/16.
 */
@Controller
public class offreController {

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

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Autowired
    private SmtpMailSender smtpMailSender;

    @RequestMapping(value = "/add/offre",method = RequestMethod.GET)
    public String addOffre(Model model){
        model.addAttribute("offre", new Offre());
        List<Category> categoryList = categoryService.categoryList();
        model.addAttribute("categories", categoryList);
        return "user/recruiteur/addOffre";
    }

    @RequestMapping(value = "/add/offre",method = RequestMethod.POST)
    public String addOffre(Offre offre,HttpSession session,HttpServletRequest request){
        User user = (User)session.getAttribute("user");
        String id = request.getParameter("ctg");
        Long idLong = Long.parseLong(id);
        Category category = categoryService.findCategory(idLong);
        offre.setCategory(category);
        offre.setUser(user);
        offreService.addOffre(offre);
        return "redirect:/profile/recruiteur";
    }

    @RequestMapping(value = "/offres", method = RequestMethod.GET)
    public String getAllOffres(Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        model.addAttribute("user",user);
        List<Offre> lisOffres = offreService.acceptOffre();
        model.addAttribute("offres",lisOffres);
        return "offres";
    }

    @RequestMapping(value = "/offre/searchByCategory", method = RequestMethod.GET)
    public String searchJobByCategory(Model model){
        model.addAttribute("category",new Category());
        return "serachOffres";
    }


    @RequestMapping(value = "/offre/searchByCategory", method = RequestMethod.POST)
    public String searchJobByCategory(Offre offre, Category category,Model model,HttpServletRequest request){
        String type = request.getParameter("type");
        String typeFind = type.toUpperCase();
        List<Offre> offreList = offreService.findOffresByCategory(typeFind);
        model.addAttribute("offres",offreList);
        return "serachOffres";

    }


    @RequestMapping(value = "/offre/search", method = RequestMethod.GET)
    public String searchJobByAddress(Model model){
        model.addAttribute("user",new User());
        return "serachOffresByAdd";
    }

    @RequestMapping(value = "/offre/search", method = RequestMethod.POST)
    public String searchJobByAddress(Offre offre,User user,Model model,HttpServletRequest request){
        String addressEnt = request.getParameter("addressEnt");
        String lll = addressEnt.toUpperCase();
        List<Offre> offreList = offreService.findOffresByVille(lll);
        model.addAttribute("offres",offreList);
        return "serachOffresByAdd";

    }

    @RequestMapping("/postuler")
    public String postuler(User user,HttpSession session){
        User user1 = (User) session.getAttribute("user");
        try {
            smtpMailSender.send(user1.getEmail(), "Valide votre compte", "<h2>Bonjour</h2><br /><p>Merci pour votre confiance</p><br /><a href='http://localhost:8080/login'>Acces à la Platforme </a><br /><p>Bien à vous</p>");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "postule";
    }



}
