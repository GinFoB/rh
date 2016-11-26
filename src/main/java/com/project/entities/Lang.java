package com.project.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by akramkhalifa on 29/07/16.
 */
@Entity
public class Lang implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "LANG_ID")
    private Long id;

    private String name;

    private String niveau;


    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
