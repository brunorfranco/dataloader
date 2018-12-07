package com.example.dataloader.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Wine")
public class Wine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idwine")
	private Integer idWine;
	
	@Column(name="namewine")
	private String wineName;
	
	private String designation;
	
	double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idwinery", nullable=true)
    private Winery winery;
	
	@OneToOne	(fetch = FetchType.LAZY)
    @JoinColumn(name = "variety", nullable=true)
    private WineVariety wineVariety;

	public Integer getIdWine() {
		return idWine;
	}

	public void setIdWine(Integer idWine) {
		this.idWine = idWine;
	}

	public String getWineName() {
		return wineName;
	}

	public void setWineName(String wineName) {
		this.wineName = wineName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Winery getWinery() {
		return winery;
	}

	public void setWinery(Winery winery) {
		this.winery = winery;
	}

	public WineVariety getWineVariety() {
		return wineVariety;
	}

	public void setWineVariety(WineVariety wineVariety) {
		this.wineVariety = wineVariety;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Wine(String wineName, String designation, double price, Winery winery, WineVariety wineVariety) {
		super();
		this.wineName = wineName;
		this.designation = designation;
		this.price = price;
		this.winery = winery;
		this.wineVariety = wineVariety;
	}

	public Wine() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wineName == null) ? 0 : wineName.hashCode());
		result = prime * result + ((wineVariety == null) ? 0 : wineVariety.hashCode());
		result = prime * result + ((winery == null) ? 0 : winery.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wine other = (Wine) obj;
		if (wineName == null) {
			if (other.wineName != null)
				return false;
		} else if (!wineName.equals(other.wineName))
			return false;
		if (wineVariety == null) {
			if (other.wineVariety != null)
				return false;
		} else if (!wineVariety.equals(other.wineVariety))
			return false;
		if (winery == null) {
			if (other.winery != null)
				return false;
		} else if (!winery.equals(other.winery))
			return false;
		return true;
	}

}
