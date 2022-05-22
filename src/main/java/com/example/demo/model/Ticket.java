package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String description;
	private String statut;
	private int priorite;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Bug bug;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User dev;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User user;
	public Ticket( String nom, String description, int priorite, User user,Bug bug,User dev) {
		this.dev=user;
		this.nom = nom;
		this.description = description;
		this.priorite = priorite;
		this.user = dev;

		this.bug=bug;
	}
	public Ticket( String nom, String description, int priorite, User user,Bug bug,User dev,String statut) {
		this.dev=user;
		this.nom = nom;
		this.description = description;
		this.priorite = priorite;
		this.user = dev;
		this.statut=statut;
		this.bug=bug;
	}
	public Ticket( String nom, String description, int priorite, User user,Bug bug) {
		this.user=user;
		this.nom = nom;
		this.description = description;
		this.priorite = priorite;
		this.bug=bug;
	}
	public Ticket(int id, String nom, String description, int priorite, User user,Bug bug) {
		this.id=id;
		this.user=user;
		this.nom = nom;
		this.description = description;
		this.priorite = priorite;
		this.bug=bug;
	}
	public Ticket(int id) {
		this.id=id;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public Ticket() {
		
	}

	public User getDev() {
		return dev;
	}

	public void setDev(User dev) {
		this.dev = dev;
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
	public int getPriorite() {
		return priorite;
	}
	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
