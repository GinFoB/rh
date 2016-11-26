package com.project.services;

import com.project.entities.Category;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by akramkhalifa on 13/07/16.
 */
@Service
@Transactional
public class CategoryServiceImp implements CategoryService {


    @PersistenceContext
    private EntityManager em;

    @Override
    public Category addCategory(Category category) {
        em.persist(category);
        return category;
    }

    @Override
    public List<Category> categoryList() {
        try {
            Query query = em.createQuery("SELECT c FROM Category c");
            List<Category> clients = query.getResultList();
            return clients;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public Category findCategory(Long id) {
        return em.find(Category.class,id);
    }
}
