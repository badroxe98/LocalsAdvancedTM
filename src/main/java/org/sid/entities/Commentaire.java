package org.sid.entities;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Commentaire implements Serializable {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String contenu;
	
	
	private java.sql.Date dateEcriture; 	

	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;

	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public java.sql.Date getDateEcriture() {
		return dateEcriture;
	}
	public void setDateEcriture(java.sql.Date dateEcriture) {
		this.dateEcriture = dateEcriture;
	}
	
	
	public Commentaire() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Commentaire(String contenu, java.sql.Date dateEcriture, Client personne) {
		super();
		this.contenu = contenu;
		this.dateEcriture = dateEcriture;
		
		this.client = personne;
	}
	
	
	
	
}
