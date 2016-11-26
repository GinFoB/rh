package com.project.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by akramkhalifa on 11/07/16.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;
  //  @NotEmpty(message = "Pseudo ne peut pas étre vide ou déja pris")
    private String userName;
    @NotEmpty(message = "NOM ne peut pas étre vide")
    private String firstName;
    @NotEmpty(message = "Prenom ne peut pas étre vide")
    private String lastName;
    @NotEmpty(message = "Email invalide") @Email
    private String email;
    private Date birthday;
   // @NotEmpty(message = "Mot de passe doit entre 6 et 16 et inclure les caractère spécial") @Size(min = 6, max = 16)
    private String password;

    private String image;

    private String imageBackground;

    private boolean candidat;
    private boolean recruiteur;
    private boolean isAdmin;

    private boolean recruiteurInfos;

    private String nameEntreprise;
    private String DesEntreprise;
    private Date dateCreation;
    private String numEmploye;
    private String addressEnt;

    @OneToOne
    @JoinColumn(name="CV_ID")
    private CvTheque cvTheque;


    public CvTheque getCvTheque() {
        return cvTheque;
    }

    public void setCvTheque(CvTheque cvTheque) {
        this.cvTheque = cvTheque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isCandidat() {
        return candidat;
    }

    public void setCandidat(boolean candidat) {
        this.candidat = candidat;
    }

    public boolean isRecruiteur() {
        return recruiteur;
    }

    public void setRecruiteur(boolean recruiteur) {
        this.recruiteur = recruiteur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }

    public boolean isRecruiteurInfos() {
        return recruiteurInfos;
    }

    public void setRecruiteurInfos(boolean recruiteurInfos) {
        this.recruiteurInfos = recruiteurInfos;
    }

    public String getNameEntreprise() {
        return nameEntreprise;
    }

    public void setNameEntreprise(String nameEntreprise) {
        this.nameEntreprise = nameEntreprise;
    }

    public String getDesEntreprise() {
        return DesEntreprise;
    }

    public void setDesEntreprise(String desEntreprise) {
        DesEntreprise = desEntreprise;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNumEmploye() {
        return numEmploye;
    }

    public void setNumEmploye(String numEmploye) {
        this.numEmploye = numEmploye;
    }

    public String getAddressEnt() {
        return addressEnt;
    }

    public void setAddressEnt(String addressEnt) {
        this.addressEnt = addressEnt.toUpperCase();
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
