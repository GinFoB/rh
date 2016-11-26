package com.project.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by akramkhalifa on 13/07/16.
 */
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String type;

    @ManyToOne
    @JoinColumn(name="OFFRE_ID")
    private Offre offre;

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.toUpperCase();
    }
}
