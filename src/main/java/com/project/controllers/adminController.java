package com.project.controllers;

import com.project.entities.*;
import com.project.services.CategoryService;
import com.project.services.OffreService;
import com.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by akramkhalifa on 07/08/2016.
 */
@Controller
public class adminController {


    private CategoryService categoryService;

    @Autowired
    public void setCategoyService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    private OffreService offreService;

    @Autowired
    public void setOffreService(OffreService offreService){
        this.offreService = offreService;
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }


    @RequestMapping(value = "/new/admin", method = RequestMethod.GET)
    public String addCandidat(Model model){
        model.addAttribute("user",new User());
        return "user/admin/formadmin";
    }

    @RequestMapping(value = "/new/admin", method = RequestMethod.POST)
    public String addCandidat(@Valid User user, final BindingResult result){
        if (result.hasErrors()){
            return "user/candidat/formCandidat";
        }
        else {
            user.setAdmin(true);
            userService.create(user);
            return "redirect:/profile/admin";
        }
    }


    /*@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpSession session){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user  = userService.verifyLogin(email, password);
        if (user != null && user.isAdmin()){
                session.setAttribute("user",user);
                return "redirect:/profile/admin";

        }
        else {
            return "redirect:/";
        }

    }*/



    @ResponseBody
    @RequestMapping("/admin")
    public ResponseEntity<String> get(HttpSession session){
        return new ResponseEntity<String>("Ok Category add with Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/add/category",method = RequestMethod.GET)
    public String addCategory(Model model){
        model.addAttribute("category",new Category());
        return "user/admin/addCategory";
    }


    @RequestMapping(value = "/admin/add/category",method = RequestMethod.POST)
    public String addCategory(Category category){
        categoryService.addCategory(category);
        return "redirect:/profile/admin";
    }

    @RequestMapping("/profile/admin")
    public String setProfile(RedirectAttributes redirectAttributes, Model model, HttpSession session){

        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);

        return "user/admin/adminProfile";
    }

    @RequestMapping(value = "/admin/offres", method = RequestMethod.GET)
    public String getAdminOffres(Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        if (user !=null){
            model.addAttribute("user",user);
            List<Offre> lisOffres = offreService.lisOffres();
            model.addAttribute("offres",lisOffres);
            return "user/admin/offres";
        }
        else {
            return "redirect:/profile/admin";
        }
    }


    @RequestMapping(value = "/admin/valide/offre",method = RequestMethod.POST)
    public String offreHandle(Offre offre,HttpServletRequest request){

        String status = request.getParameter("display");
        String idOffre = request.getParameter("idOffre");

        System.out.print(idOffre);

        offre = offreService.findOffre(Long.valueOf(idOffre));
        offreService.offreValide(offre,status);

        return "redirect:/profile/admin";
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
