package com.example.dataloader.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idreview")
	private Integer idReview;

	@Column(name="descriptionreview")
	private String descriptionReview;
	
	private double points;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idwine", nullable=true)
    private Wine wine;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtaster", nullable=true)
    private Taster taster;

	public Review() {
		super();
	}

	public Review(String descriptionReview, double points, Wine wine, Taster taster) {
		super();
		this.descriptionReview = descriptionReview;
		this.points = points;
		this.wine = wine;
		this.taster = taster;
	}

	public Integer getIdReview() {
		return idReview;
	}

	public void setIdReview(Integer idReview) {
		this.idReview = idReview;
	}

	public String getDescriptionReview() {
		return descriptionReview;
	}

	public void setDescriptionReview(String descriptionReview) {
		this.descriptionReview = descriptionReview;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public Wine getWine() {
		return wine;
	}

	public void setWine(Wine wine) {
		this.wine = wine;
	}

	public Taster getTaster() {
		return taster;
	}

	public void setTaster(Taster taster) {
		this.taster = taster;
	}
	
	
}
