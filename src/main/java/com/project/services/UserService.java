package com.project.services;

import com.project.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by akramkhalifa on 11/07/16.
 */
public interface UserService {


    public void create(User user);

    public User getAllUserList(String email);

    public boolean exists(String username);

    public User getUser(String username);

    public User verifyLogin(String email, String password);

    public User emailVerify(String access);

    public User editUser(User user);

    public User editUser(User user, String nameEntreprise, String DesEntreprise, String numEmploye, String addressEnt, String firstName, String lastName, String email);


    public User findUserById(Long id);

    public User addBackImage(User user,String file);

    public User addProfileImage(User user);

    public void setImageBg(String bg, Long aLong);

    public List<User> userTalenet(String skillName);

}
