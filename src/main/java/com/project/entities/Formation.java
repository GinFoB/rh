package com.project.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by akramkhalifa on 29/07/16.
 */
@Entity
public class Formation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "FORMATION_ID")
    private Long id;

    private String ecoleName;
    private String diplome;
    private String descriptionFormation;


    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEcoleName() {
        return ecoleName;
    }

    public void setEcoleName(String ecoleName) {
        this.ecoleName = ecoleName;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getDescriptionFormation() {
        return descriptionFormation;
    }

    public void setDescriptionFormation(String descriptionFormation) {
        this.descriptionFormation = descriptionFormation;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
