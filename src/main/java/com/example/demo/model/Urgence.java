package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "urgence")
public class Urgence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String description;

    public Urgence(int id, String nom, String description) {
        super();
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Urgence() {

    }

    public Urgence(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
