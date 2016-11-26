package com.project.services;

import com.project.entities.CvTheque;
import com.project.entities.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;

/**
 * Created by akramkhalifa on 11/07/16.
 */
@Service
@Transactional
public class UserServiceImp implements UserService {


  /*  @Autowired
    private SessionFactory sessionFactory;

    public Session session(){
        return sessionFactory.getCurrentSession();
    }*/

    @PersistenceContext
    private EntityManager em;


    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public boolean exists(String username) {
        return false;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public User verifyLogin(String email, String password) {
        try {
            Query query = em.createQuery("SELECT user FROM User user WHERE user.email = :email AND user.password = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            User user = (User) query.getSingleResult();
            return user;

        }
        catch (Exception e){
           return null;
        }
    }


    @Override
    public User getAllUserList(String email) {
        try {
            Query query = em.createQuery("SELECT user FROM User user WHERE user.email = :email");
            query.setParameter("email", email);
            User user = (User) query.getSingleResult();
            return user;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public User findUserById(Long id) {
       return em.find(User.class,id);
    }

    @Override
    public User emailVerify(String access) {
        return null;
    }

    @Override
    public User editUser(User user) {
        return em.merge(user);
    }


    @Override
    public User editUser(User user, String nameEntreprise, String DesEntreprise, String numEmploye, String addressEnt,String firstName, String lastName, String email) {
        user.setNameEntreprise(nameEntreprise);
        user.setDesEntreprise(DesEntreprise);
        user.setNumEmploye(numEmploye);
        user.setAddressEnt(addressEnt);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        return  em.merge(user);
    }

    @Override
    public User addBackImage(User user, String file) {
        user.setImageBackground(file);
        return user;
    }

    @Override
    public void setImageBg(@Param("bg") String bg,@Param("aLong") Long aLong) {
        em.createQuery("update User u set u.imageBackground = :bg where u.id = :aLong");
    }

    @Override
    public User addProfileImage(User user) {
        em.merge(user);
        return user;
    }

    @Override
    public List<User> userTalenet(String skillName) {
        try {
            Query query = em.createQuery("SELECT user FROM User user WHERE user.cvTheque.skillName = :skillName");
            query.setParameter("skillName", skillName);
            List<User> userList = query.getResultList();
            return userList;
        }
        catch (Exception e){
            return null;
        }
    }
}
