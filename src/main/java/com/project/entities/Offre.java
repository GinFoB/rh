package com.project.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by akramkhalifa on 13/07/16.
 */
@Entity
public class Offre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "OFFRE_ID")
    private Long id;
    private String description;
    private String profileRech;
    private String typeContrat;
    private String numBesoin;

    private String display;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileRech() {
        return profileRech;
    }

    public void setProfileRech(String profileRech) {
        this.profileRech = profileRech;
    }


    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getNumBesoin() {
        return numBesoin;
    }

    public void setNumBesoin(String numBesoin) {
        this.numBesoin = numBesoin;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
