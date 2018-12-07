package com.example.dataloader.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Province")
public class Province {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idprovince")
	private Integer idProvince;
	
	@Column(name="provincename")
	private String provinceName;
	
	@Column(name="region_1")
	private String region1;
	
	@Column(name="region_2")
	private String region2;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_Name")
    private Country country;
	
	public Province(String provinceName, String region1, String region2, Country country) {
		super();
		this.provinceName = provinceName;
		this.region1 = region1;
		this.region2 = region2;
		this.country = country;
	}
	public Province() {
		super();
	}
	public Integer getIdProvince() {
		return idProvince;
	}
	public void setIdProvince(Integer idProvince) {
		this.idProvince = idProvince;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getRegion1() {
		return region1;
	}
	public void setRegion1(String region1) {
		this.region1 = region1;
	}
	public String getRegion2() {
		return region2;
	}
	public void setRegion2(String region2) {
		this.region2 = region2;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((provinceName == null) ? 0 : provinceName.hashCode());
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
		Province other = (Province) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (provinceName == null) {
			if (other.provinceName != null)
				return false;
		} else if (!provinceName.equals(other.provinceName))
			return false;
		return true;
	}
	
}
