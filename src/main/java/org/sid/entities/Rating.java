package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Rating {
	

	@Id 
	@GeneratedValue
	private long id;
	private int countStars=0;
	
	@ManyToOne
	@JoinColumn(name="traing")
	private Formation ratingFormation;
	
	@ManyToOne
	@JoinColumn(name="user")
	private Client client;

	
	public Rating() {
		super();
	}
	public Rating(long id, int countStars, Formation ratingFormation, Client client) {
		this.id = id;
		this.countStars = countStars;
		this.ratingFormation = ratingFormation;
		this.client = client;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCountStars() {
		return countStars;
	}

	public void setCountStars(int countStars) {
		this.countStars = countStars;
	}

	public Formation getRatingFormation() {
		return ratingFormation;
	}

	public void setRatingFormation(Formation ratingFormation) {
		this.ratingFormation = ratingFormation;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
