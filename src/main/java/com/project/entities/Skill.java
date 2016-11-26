package com.project.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by akramkhalifa on 29/07/16.
 */
@Entity
public class Skill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "SKILL_ID")
    private Long id;

    private  String skillName;



    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName.toUpperCase();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
