package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "logiciel")
public class Logiciel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String description;
    private String envirenement;

    public Logiciel(int id, String nom, String description, String envirenement) {
        super();
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.envirenement = envirenement;
    }

    public Logiciel() {

    }

    public Logiciel(int id) {
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

    public String getEnvirenement() {
        return envirenement;
    }

    public void setEnvirenement(String envirenement) {
        this.envirenement = envirenement;
    }

}
