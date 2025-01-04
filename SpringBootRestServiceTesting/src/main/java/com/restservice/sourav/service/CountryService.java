package com.restservice.sourav.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.restservice.sourav.bean.Country;
import com.restservice.sourav.controller.AddResponse;
import com.restservice.sourav.repository.CountryRepository;

@Component
@Service
public class CountryService {
	
	@Autowired
	CountryRepository countryRepository;
	
	public List<Country> getAllCountries() {
		 
		List<Country> countries = countryRepository.findAll();
		return countries;
	}
	
	public Country getCountryById(int id){
		List<Country> countries = countryRepository.findAll();
		
		Country country = null;
		
		for(Country coun : countries) {
			if(coun.getCountryId()== id) {
				country = coun;
			}
		}
		return country;
	}
	
	public Country getCountryByName(String countryName){
		List<Country> countries = countryRepository.findAll();
		
		Country country = null;
		
		for(Country coun : countries) {
			if(coun.getCountryName().equalsIgnoreCase(countryName)) {
				country = coun;
			}
		}
		return country;
	}
	
	public Country addNewCountry(Country country) {
		country.setCountryId(getMaxId());
		return countryRepository.save(country);
	}
	
	//Utility method to get max id
	public int getMaxId() {
		return countryRepository.findAll().size() + 1;
	}
	
	public Country updateCountry(Country country) {
		return countryRepository.save(country);
	}
	
	public void deleteCountry(Country country) {
		countryRepository.delete(country);
//		AddResponse addResponse = new AddResponse();
//		addResponse.setMsg("Country Deleted!");
//		return addResponse;
	}
}
