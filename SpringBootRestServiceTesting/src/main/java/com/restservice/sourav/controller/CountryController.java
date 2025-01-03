package com.restservice.sourav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restservice.sourav.bean.Country;
import com.restservice.sourav.service.CountryService;

@RestController 
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@GetMapping("/getallcountrie")
	public List<Country> getCountries(){
		return countryService.getAllCountries();
	}
	
	@GetMapping("/getcountry/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") int cid){
		try {
			Country country = countryService.getCountryById(cid);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getcountrybyname/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String cname){
		try {
			Country country = countryService.getCountryByName(cname);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country) {
		return countryService.addNewCountry(country);
	}
	
	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id") int cid, @RequestBody Country country) {
		try {
			Country existCountry = countryService.getCountryById(cid);
			
			existCountry.setCountryName(country.getCountryName());
			existCountry.setCountryCapatical(country.getCountryCapatical());
			
			Country update_country = countryService.updateCountry(existCountry);
			return new ResponseEntity<Country>(update_country, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deletecountry/{id}")
	public AddResponse deleteCountry(@PathVariable(value = "id") int cid) {
		return countryService.deleteCountry(cid);
	}
}
