package com.example.dataloader.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taster")
public class Taster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idtaster")
	private Integer idTaster;
	@Column(name="nametaster")
	private String nameTaster;
	private String twitterhandletaster;

	public Taster() {
		super();
	}

	public Taster(String nameTaster, String twitterhandlertaster) {
		super();
		this.nameTaster = nameTaster;
		this.twitterhandletaster = twitterhandlertaster;
	}

	public Integer getIdTaster() {
		return idTaster;
	}

	public void setIdTaster(Integer idTaster) {
		this.idTaster = idTaster;
	}

	public String getNameTaster() {
		return nameTaster;
	}

	public void setNameTaster(String nameTaster) {
		this.nameTaster = nameTaster;
	}

	public String getTwitterhandlertaster() {
		return twitterhandletaster;
	}

	public void setTwitterhandlertaster(String twitterhandlertaster) {
		this.twitterhandletaster = twitterhandlertaster;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameTaster == null) ? 0 : nameTaster.hashCode());
		result = prime * result + ((twitterhandletaster == null) ? 0 : twitterhandletaster.hashCode());
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
		Taster other = (Taster) obj;
		if (nameTaster == null) {
			if (other.nameTaster != null)
				return false;
		} else if (!nameTaster.equals(other.nameTaster))
			return false;
		if (twitterhandletaster == null) {
			if (other.twitterhandletaster != null)
				return false;
		} else if (!twitterhandletaster.equals(other.twitterhandletaster))
			return false;
		return true;
	}

}
