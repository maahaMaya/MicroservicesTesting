package com.restservice.sourav.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.restservice.sourav.bean.Country;
import com.restservice.sourav.controller.AddResponse;

@Component
public class CountryService {
	
	static HashMap<Integer, Country> countryIdMap;
	
	public CountryService() {
		
		countryIdMap = new HashMap<Integer, Country>();
		
		Country indiaCountry = new Country(1, "India", "Delhi");
		Country usaCountry = new Country(2, "USA", "Washington");
		Country ukCountry = new Country(3, "Uk", "London");
		
		countryIdMap.put(1, indiaCountry);
		countryIdMap.put(2, usaCountry);
		countryIdMap.put(3, ukCountry);
	}
	
	public List<Country> getAllCountries() {
		 
		List<Country> countries = new ArrayList(countryIdMap.values());
		return countries;
	}
	
	public Country getCountryById(int id){
		return countryIdMap.get(id);
	}
	
	public Country getCountryByName(String countryName){
		Country country = null;
		for(int i : countryIdMap.keySet()) {
			if(countryIdMap.get(i).getCountryName().equals(countryName)) {
				country = countryIdMap.get(i);
			}
		}
		return country;
	}
	
	public Country addNewCountry(Country country) {
		country.setCountryId(getMaxId());
		countryIdMap.put(country.getCountryId(), country);
		return country;
		
	}
	
	//Utility method to get max id
	public static int getMaxId() {
		int max = 0;
		for(int id: countryIdMap.keySet()){
			if(max <= id)
				max =  id;
		}
		return max+1;
	}
	
	public Country updateCountry(Country country) {
		if(country.getCountryId() > 0) 
			countryIdMap.put(country.getCountryId(), country);
		return country;
	}
	
	public AddResponse deleteCountry(int id) {
		countryIdMap.remove(id);
		
		AddResponse addResponse = new AddResponse();
		addResponse.setId(id);
		addResponse.setMsg("Country Deleted...");
		return addResponse;
	}
}
