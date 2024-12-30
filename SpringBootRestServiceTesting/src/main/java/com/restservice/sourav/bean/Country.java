package com.restservice.sourav.bean;

public class Country {
	
	private int countryId;
	private String countryName;
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
