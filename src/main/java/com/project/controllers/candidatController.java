package com.project.controllers;

import com.project.entities.*;
import com.project.services.CvThequeService;
import com.project.services.UserService;
import com.project.utils.SmtpMailSender;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by akramkhalifa on 11/07/16.
 */
@Controller
public class candidatController {

    private UserService userService;

    @Autowired
    private SmtpMailSender smtpMailSender;


    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    private CvThequeService cvThequeService;

    @Autowired
    public void setCvThequeService(CvThequeService cvThequeService){
        this.cvThequeService = cvThequeService;
    }

    @RequestMapping("/check")
    public String chechMail(){
        return "user/candidat/check";
    }


    @RequestMapping(value = "/new/candidat", method = RequestMethod.GET)
    public String addCandidat(Model model){
        model.addAttribute("user",new User());
        System.out.println("user");
        return "user/candidat/formCandidat";
    }

    @RequestMapping(value = "/new/candidat", method = RequestMethod.POST)
    public String addCandidat(@Valid User user, final BindingResult result){
        if (result.hasErrors()){
            return "user/candidat/formCandidat";
        }
        else {
            System.out.print("here"+user);
            user.setCandidat(true);
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

    @RequestMapping("/profile/candidat")
    public String setProfile(RedirectAttributes redirectAttributes,Model model,HttpSession session){

        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);

        CvTheque theque = cvThequeService.getCvById(user.getId());
        model.addAttribute("cvTheque",theque);

        List<Skill> skillList = cvThequeService.getListOfSkills(user.getId());
        model.addAttribute("skills",skillList);

        List<Formation> formationList = cvThequeService.getListOfFormations(user.getId());
        model.addAttribute("formations",formationList);

        List<Lang> langList = cvThequeService.getListOfLangs(user.getId());
        model.addAttribute("langs",langList);

        List<Experience> experienceList = cvThequeService.getListOfExperiences(user.getId());
        model.addAttribute("experiences",experienceList);

        return "user/candidat/candidatProfile";
    }

    @RequestMapping("/xxx")
    public ResponseEntity<String> get(HttpSession session){
        User user = (User) session.getAttribute("user");
        User user1 = userService.findUserById(user.getId());
        String imageBackground = user1.getImageBackground();
        return new ResponseEntity<String>(imageBackground, HttpStatus.OK);
    }

    @RequestMapping(value = "/register/linkedin",method = RequestMethod.POST)
    @ResponseBody
    public User saveUser(@RequestBody String datas, HttpSession session){

        System.out.println("/////+++++"+datas);

        JSONObject jsonObj = new JSONObject(datas);
        //String  id = jsonObj.getString("id");
        String firstName = jsonObj.getString("firstName");

        System.out.println("///////"+firstName);

        String lastName = jsonObj.getString("lastName");
        String pictureUrl = jsonObj.getString("pictureUrl");
        String emailAddress = jsonObj.getString("emailAddress");


        User user = new User();
        user.setCandidat(true);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(emailAddress);
        user.setImage(pictureUrl);

        User userFind = userService.getAllUserList(emailAddress);

        if (userFind != null){
            session.setAttribute("user",userFind);
            return userFind;
        }
        else {
            userService.create(user);
            session.setAttribute("user",user);
            return user;
        }
    }

    @RequestMapping(value = "/talent",method = RequestMethod.GET)
    public String talent(Model model){
        model.addAttribute("skill",new Skill());
        return "talent";
    }

    @RequestMapping(value = "/talent",method = RequestMethod.POST)
    public String talent(Model model,User user,Skill skill,HttpServletRequest request){
        String skill_name = request.getParameter("skillName");
        List list = userService.userTalenet(skill_name);
        System.out.print("uuuuu"+list);
        return "talent";
    }
}
