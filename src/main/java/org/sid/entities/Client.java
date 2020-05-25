package org.sid.entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;


@Entity
public class Client implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	private long id;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String type;
	private String Job;
	private String Address;
	private String profile;
	private String picture;
	private String expertise;
	

	@OneToMany(mappedBy = "user")
	private List<Formation> formation;
	
	@OneToMany(mappedBy = "owner")
	private List<Local> locaux;
	
	@ManyToMany
	@JoinTable(name="formationreservee",
	joinColumns = @JoinColumn(name="userId"),
	inverseJoinColumns = @JoinColumn(name="trainingId"))
	private List<Formation> formationReservee;
	

	@OneToMany(mappedBy = "client")
	private List<Commentaire> commentaires;
	
	@OneToMany(mappedBy = "client")
	private List<Rating> myRatings;
	
	

	

	public Client(long id, String nom, String prenom, String email, String password, String type, String job,
			String address, String profile, String picture, String expertise, List<Formation> formation,
			List<Local> locaux, List<Formation> formationReservee, List<Commentaire> commentaires,
			List<Rating> myRatings) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.type = type;
		Job = job;
		Address = address;
		this.profile = profile;
		this.picture = picture;
		this.expertise = expertise;
		this.formation = formation;
		this.locaux = locaux;
		this.formationReservee = formationReservee;
		this.commentaires = commentaires;
		this.myRatings = myRatings;
	}

	public Client() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJob() {
		return Job;
	}

	public void setJob(String job) {
		Job = job;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Formation> getFormation() {
		return formation;
	}

	public void setFormation(List<Formation> formation) {
		this.formation = formation;
	}

	public List<Local> getLocaux() {
		return locaux;
	}

	public void setLocaux(List<Local> locaux) {
		this.locaux = locaux;
	}

	public List<Formation> getFormationReservee() {
		return formationReservee;
	}

	public void setFormationReservee(List<Formation> formationReservee) {
		this.formationReservee = formationReservee;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public List<Rating> getMyRatings() {
		return myRatings;
	}

	public void setMyRatings(List<Rating> myRatings) {
		this.myRatings = myRatings;
	}
	
	
	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
	
	
	
	

}
