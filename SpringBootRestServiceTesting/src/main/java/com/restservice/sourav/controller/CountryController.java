package com.restservice.sourav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Country getCountryById(@PathVariable(value = "id") int cid){
		return countryService.getCountryById(cid);
	}
	
	@GetMapping("/getcountrybyname/countryname")
	public Country getCountryByName(@RequestParam(value = "name") String cname){
		return countryService.getCountryByName(cname);
	}
	
	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country) {
		return countryService.addNewCountry(country);
	}
	
	@PutMapping("/updatecountry")
	public Country updateCountry(@RequestBody Country country) {
		return countryService.updateCountry(country);
	}
	
	@DeleteMapping("/deletecountry/{id}")
	public AddResponse deleteCountry(@PathVariable(value = "id") int cid) {
		return countryService.deleteCountry(cid);
	}
}
