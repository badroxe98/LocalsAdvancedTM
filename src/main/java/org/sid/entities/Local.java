package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Local implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String intitulee;
	private double superficie;
	private String description;
	private String ville;
	private Long NbPlaces;
	private String adresse;
	private String category;
	private double prixParHeure;
	private java.sql.Date disponibiliteA;
	private java.sql.Date disponibiliteFrom;
	private int prises;
	private int chairs;
	private int micro;
	private int projecteur;
	private String picture1;
	private String picture2;
	private String picture3;
	private String picture4;
	private String picture5;
	private String picture6;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private Client owner;
	
	@OneToMany(mappedBy = "Local")
	private List<Formation> formations;
	
	
	
	
	
	
	public int getPrises() {
		return prises;
	}
	public void setPrises(int prises) {
		this.prises = prises;
	}
	public int getChairs() {
		return chairs;
	}
	public void setChairs(int chairs) {
		this.chairs = chairs;
	}
	public int getMicro() {
		return micro;
	}
	public void setMicro(int micro) {
		this.micro = micro;
	}
	public int getProjecteur() {
		return projecteur;
	}
	public void setProjecteur(int projecteur) {
		this.projecteur = projecteur;
	}
	public Long getNbPlaces() {
		return NbPlaces;
	}
	public void setNbPlaces(Long nbPlaces) {
		NbPlaces = nbPlaces;
	}
	public List<Formation> getFormations() {
		return formations;
	}
	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	public Client getOwner() {
		return owner;
	}
	public void setOwner(Client owner) {
		this.owner = owner;
	}
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	public String getPicture2() {
		return picture2;
	}
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}
	public String getPicture3() {
		return picture3;
	}
	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}
	public String getPicture4() {
		return picture4;
	}
	public void setPicture4(String picture4) {
		this.picture4 = picture4;
	}
	public String getPicture5() {
		return picture5;
	}
	public void setPicture5(String picture5) {
		this.picture5 = picture5;
	}
	public String getPicture6() {
		return picture6;
	}
	public void setPicture6(String picture6) {
		this.picture6 = picture6;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIntitulee() {
		return intitulee;
	}
	public void setIntitulee(String intitulee) {
		this.intitulee = intitulee;
	}
	public double getSuperficie() {
		return superficie;
	}
	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public double getPrixParHeure() {
		return prixParHeure;
	}
	public void setPrixParHeure(double prixParHeure) {
		this.prixParHeure = prixParHeure;
	}
	public java.sql.Date getDisponibiliteFrom() {
		return disponibiliteFrom;
	}
	public void setDisponibiliteFrom(java.sql.Date disponibliteAParir) {
		this.disponibiliteFrom = disponibliteAParir;
	}
	public java.sql.Date getDisponibiliteA() {
		return disponibiliteA;
	}
	public void setDisponibiliteA(java.sql.Date disponibiliteA) {
		this.disponibiliteA = disponibiliteA;
	}
	
	
	public Local(Long id, String intitulee, double superficie, String description, String ville, Long nbPlaces,
			String adresse, String category, double prixParHeure, java.sql.Date disponibiliteA,
			java.sql.Date disponibiliteFrom, int prises, int chairs, int micro, int projecteur, String picture1,
			String picture2, String picture3, String picture4, String picture5, String picture6, Client owner,
			List<Formation> formations) {
		this.id = id;
		this.intitulee = intitulee;
		this.superficie = superficie;
		this.description = description;
		this.ville = ville;
		NbPlaces = nbPlaces;
		this.adresse = adresse;
		this.category = category;
		this.prixParHeure = prixParHeure;
		this.disponibiliteA = disponibiliteA;
		this.disponibiliteFrom = disponibiliteFrom;
		this.prises = prises;
		this.chairs = chairs;
		this.micro = micro;
		this.projecteur = projecteur;
		this.picture1 = picture1;
		this.picture2 = picture2;
		this.picture3 = picture3;
		this.picture4 = picture4;
		this.picture5 = picture5;
		this.picture6 = picture6;
		this.owner = owner;
		this.formations = formations;
	}
	public Local() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
