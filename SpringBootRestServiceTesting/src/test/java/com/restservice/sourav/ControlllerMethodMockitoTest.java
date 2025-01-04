package com.restservice.sourav;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.restservice.sourav.bean.Country;
import com.restservice.sourav.controller.CountryController;
import com.restservice.sourav.service.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMockitoTest.class})
public class ControlllerMethodMockitoTest {
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> mycountries;
	Country country;
	
	@Test
	@Order(1)
	public void test_getAllCountries(){
		mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washington"));
		mycountries.add(new Country(3, "UK", "London"));
		
		when(countryService.getAllCountries()).thenReturn(mycountries); //Mocking
		
		ResponseEntity<List<Country>> res = countryController.getCountries();
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(3,res.getBody().size());
	}


	@Test 
	@Order(2)
	public void test_getCountryById(){
		country = new Country(2, "USA", "Washington");
		int countryId = 2;
		
		when(countryService.getCountryById(countryId)).thenReturn(country); //Mocking
		ResponseEntity<Country> res = countryController.getCountryById(countryId);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(2,res.getBody().getCountryId());
	}
	
	
	@Test
	@Order(3)
	public void test_getCountryByName(){
		country = new Country(2, "USA", "Washington");
		String countryName = "USA";
		
		when(countryService.getCountryByName(countryName)).thenReturn(country); //Mocking
		ResponseEntity<Country> res = countryController.getCountryByName(countryName);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(2,res.getBody().getCountryId());
	}
	
	
	@Test 
	@Order(4)
	public void test_addCountry(){
		
		country = new Country(3, "UK", "London");
		
		when(countryService.addNewCountry(country)).thenReturn(country); //Mocking
		
		ResponseEntity<Country> responseEntity = countryController.addCountry(country);
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(country,responseEntity.getBody());

	}
	
	
	@Test 
	@Order(5)
	public void test_updateCountry(){
		
		country = new Country(3, "UK", "London");
		int countryId = 3;
		
		when(countryService.getCountryById(countryId)).thenReturn(country); //Mocking
		when(countryService.updateCountry(country)).thenReturn(country); //Mocking
		
		ResponseEntity<Country> responseEntity = countryController.updateCountry(countryId, country);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(countryId,responseEntity.getBody().getCountryId());
		assertEquals( "UK",responseEntity.getBody().getCountryName());
		assertEquals("London",responseEntity.getBody().getCountryCapatical());
	}
	
	
	@Test 
	@Order(6)
	public void test_deleteCountry(){
		
		country = new Country(3, "UK", "London");
		int countryId = 3;
		
		when(countryService.getCountryById(countryId)).thenReturn(country); //Mocking
		
		ResponseEntity<Country> responseEntity = countryController.deleteCountry(countryId);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(countryId,responseEntity.getBody().getCountryId());
		assertEquals( "UK",responseEntity.getBody().getCountryName());
		assertEquals("London",responseEntity.getBody().getCountryCapatical());
	}
}
