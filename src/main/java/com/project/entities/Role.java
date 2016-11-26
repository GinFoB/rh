package com.project.entities;

import javax.persistence.*;

/**
 * Created by akramkhalifa on 11/07/16.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ROLE_ID")
    private Long id;

    private String name;
}
