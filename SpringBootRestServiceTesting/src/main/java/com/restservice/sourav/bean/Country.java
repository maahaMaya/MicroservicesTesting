package com.restservice.sourav.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Country")
public class Country {
	
	@Id
	@Column(name = "id")
	private int countryId;
	
	@Column(name = "country_name")
	private String countryName;
	
	@Column(name = "country_c apatical")
	private String countryCapatical;
	
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCapatical() {
		return countryCapatical;
	}
	public void setCountryCapatical(String countryCapatical) {
		this.countryCapatical = countryCapatical;
	}
	public Country(int countryId, String countryName, String countryCapatical) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryCapatical = countryCapatical;
	}
	
}
