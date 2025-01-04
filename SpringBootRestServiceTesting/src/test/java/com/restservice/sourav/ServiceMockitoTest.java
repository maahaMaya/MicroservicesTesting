package com.restservice.sourav;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.restservice.sourav.bean.Country;
import com.restservice.sourav.repository.CountryRepository;
import com.restservice.sourav.service.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMockitoTest.class})
public class ServiceMockitoTest {

	@Mock
	CountryRepository countryRepository;
	
	@InjectMocks
	CountryService countryService;
	
	public List<Country> mycountries;
	
	@Test
	@Order(1)
	public void test_getAllCountries(){
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washington"));
		mycountries.add(new Country(3, "UK", "London"));
		
		when(countryRepository.findAll()).thenReturn(mycountries); //Mocking
		
		assertEquals(3, countryService.getAllCountries().size());
	}
	
	@Test 
	@Order(2)
	public void test_getCountryById(){
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washington"));
		mycountries.add(new Country(3, "UK", "London"));
		
		int countryId = 1;
		
		when(countryRepository.findAll()).thenReturn(mycountries); //Mocking
		
		assertEquals(countryId, countryService.getCountryById(countryId).getCountryId());
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName(){
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washington"));
		mycountries.add(new Country(3, "UK", "London"));
		
		String countryName = "India";
		
		when(countryRepository.findAll()).thenReturn(mycountries); //Mocking
		
		assertEquals(countryName, countryService.getCountryByName(countryName).getCountryName());
	}
	
	@Test 
	@Order(4)
	public void test_addCountry(){
		
		Country country = new Country(3, "UK", "London");
		
		when(countryRepository.save(country)).thenReturn(country); //Mocking
		
		assertEquals(country, countryService.addNewCountry(country));
	}
	
	@Test 
	@Order(5)
	public void test_updateCountry(){
		
		Country country = new Country(3, "UK", "London");
		
		when(countryRepository.save(country)).thenReturn(country); //Mocking
		
		assertEquals(country, countryService.updateCountry(country));
	}
	
	@Test 
	@Order(6)
	public void test_deleteCountry(){
		
		Country country = new Country(3, "UK", "London");
		
		countryService.deleteCountry(country);
		
		verify(countryRepository, times(1)).delete(country); //Mocking
	}
}
