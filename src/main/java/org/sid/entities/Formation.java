package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Formation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String Title;
	private int NbPlaces;
	private String difficulty;
	private String ArticleCat;
	private int Prix;
	private String Requirements;
	private String Description;
	private java.sql.Date firstDay;
	private java.sql.Date lastDay;
	private String significantPhoto;

	@ManyToOne
	@JoinColumn(name="userId")
	private Client user;

	@ManyToMany(mappedBy = "formationReservee")
	private List<Client> clientBeneficiants;

	@OneToOne
	@JoinColumn(name="local")
	private Local Local;

	@OneToMany(mappedBy = "ratingFormation")
	private List<Rating> ratings;


	public Formation() {
		super();
	}





	public Formation(Long id, String title, int nbPlaces, String difficulty, String articleCat, int prix, Local local,
			String requirements, String description, java.sql.Date firstDay, java.sql.Date lastDay,
			String significantPhoto, Client user, List<Client> clientBeneficiants,List<Rating> rating) {
		this.id = id;
		Title = title;
		NbPlaces = nbPlaces;
		this.difficulty = difficulty;
		ArticleCat = articleCat;
		Prix = prix;
		Local = local;
		Requirements = requirements;
		Description = description;
		this.firstDay = firstDay;
		this.lastDay = lastDay;
		this.significantPhoto = significantPhoto;
		this.user = user;
		this.clientBeneficiants = clientBeneficiants;
		this.ratings=rating;
	}




	public List<Rating> getRatings() {
		return ratings;
	}





	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}





	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getTitle() {
		return Title;
	}




	public void setTitle(String title) {
		Title = title;
	}




	public int getNbPlaces() {
		return NbPlaces;
	}




	public void setNbPlaces(int nbPlaces) {
		NbPlaces = nbPlaces;
	}




	public String getDifficulty() {
		return difficulty;
	}




	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}




	public String getArticleCat() {
		return ArticleCat;
	}




	public void setArticleCat(String articleCat) {
		ArticleCat = articleCat;
	}




	public int getPrix() {
		return Prix;
	}




	public void setPrix(int prix) {
		Prix = prix;
	}




	public Local getLocal() {
		return Local;
	}




	public void setLocal(Local local) {
		Local = local;
	}




	public String getRequirements() {
		return Requirements;
	}




	public void setRequirements(String requirements) {
		Requirements = requirements;
	}




	public String getDescription() {
		return Description;
	}




	public void setDescription(String description) {
		Description = description;
	}




	public java.sql.Date getFirstDay() {
		return firstDay;
	}




	public void setFirstDay(java.sql.Date firstDay) {
		this.firstDay = firstDay;
	}




	public java.sql.Date getLastDay() {
		return lastDay;
	}




	public void setLastDay(java.sql.Date lastDay) {
		this.lastDay = lastDay;
	}




	public String getSignificantPhoto() {
		return significantPhoto;
	}




	public void setSignificantPhoto(String significantPhoto) {
		this.significantPhoto = significantPhoto;
	}




	public Client getUser() {
		return user;
	}




	public void setUser(Client user) {
		this.user = user;
	}




	public List<Client> getClientBeneficiants() {
		return clientBeneficiants;
	}




	public void setClientBeneficiants(List<Client> clientBeneficiants) {
		this.clientBeneficiants = clientBeneficiants;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}













}
