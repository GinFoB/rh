package com.project.controllers;

import com.project.entities.CvTheque;
import com.project.entities.User;
import com.project.services.CvThequeService;
import com.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by akramkhalifa on 12/07/16.
 */
@Controller
public class userController {

    private CvThequeService cvThequeService;

    @Autowired
    public void setCvThequeService(CvThequeService cvThequeService){
        this.cvThequeService = cvThequeService;
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "user/candidat/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpSession session){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user  = userService.verifyLogin(email, password);
        if (user != null){
            if(user.isCandidat()){
                    session.setAttribute("user",user);
                    return "redirect:/profile/candidat";
                }

            else if(user.isRecruiteur()) {
                    session.setAttribute("user",user);
                    return "redirect:/profile/recruiteur";
                }
                else {
                session.setAttribute("user",user);
                return "redirect:/profile/admin";
            }
        }
        else {
            return "redirect:/";
        }

    }

  /*  @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String loginAdmin(HttpServletRequest request, HttpSession session){
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

    @RequestMapping(value = "/edit/profile", method = RequestMethod.GET)
    public String editProfile(Model model){
        model.addAttribute("user",new User());
        return "user/edit";
    }

    @RequestMapping(value = "/edit/profile", method = RequestMethod.POST)
    public String editProfile(User user,HttpSession session){
        User user1 = (User) session.getAttribute("user");
        User userUpdate = userService.findUserById(user1.getId());
        userUpdate.setUserName(user.getUserName());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setCandidat(user1.isCandidat());
        userUpdate.setRecruiteur(user1.isRecruiteur());
        userUpdate.setPassword(user.getPassword());
        userService.editUser(userUpdate);
        return "redirect:/";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
