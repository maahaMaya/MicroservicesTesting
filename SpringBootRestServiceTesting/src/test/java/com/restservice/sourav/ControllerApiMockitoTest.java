package com.restservice.sourav;

import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restservice.sourav.bean.Country;
import com.restservice.sourav.controller.CountryController;
import com.restservice.sourav.service.CountryService;


@ComponentScan(basePackages = "com.restservice.sourav")
@AutoConfigureMockMvc
@ContextConfiguration
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMockitoTest.class})
public class ControllerApiMockitoTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
	}
	
	List<Country> mycountries;
	Country country;
	
	@Test
	@Order(1)
	public void testapi_getAllCountries() throws Exception{
		  
		mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washington"));
		mycountries.add(new Country(3, "UK", "London"));
		
		when(countryService.getAllCountries()).thenReturn(mycountries); //Mocking
		
		this.mockMvc.perform(get("/getallcountrie"))
		.andExpect(status().isFound())
		.andDo(print());
	}
	
	@Test 
	@Order(2)
	public void testapi_getCountryById() throws Exception{
		country = new Country(2, "USA", "Washington");
		int countryId = 2;
		
		when(countryService.getCountryById(countryId)).thenReturn(country); //Mocking
		
		this.mockMvc.perform(get("/getcountry/{id}", countryId))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".countryId").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("USA"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapatical").value("Washington"))
		.andDo(print());
	}
	
	@Test
	@Order(3)
	public void testapi_getCountryByName() throws Exception{
		country = new Country(2, "USA", "Washington");
		String countryName = "USA";
		
		when(countryService.getCountryByName(countryName)).thenReturn(country); //Mocking

		this.mockMvc.perform(get("/getcountrybyname/countryname").param("name", countryName))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".countryId").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("USA"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapatical").value("Washington"))
		.andDo(print());
	}
	
	@Test 
	@Order(4)
	public void testapi_addCountry() throws Exception{
		
		country = new Country(3, "UK", "London");
		
		when(countryService.addNewCountry(country)).thenReturn(country); //Mocking
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonBody = objectMapper.writeValueAsString(country);
		
		this.mockMvc.perform(post("/addcountry")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andDo(print());
	}
	
	
	@Test 
	@Order(5)
	public void testapi_updateCountry() throws Exception{
		
		country = new Country(3, "UK", "London");
		int countryId = 3;
		
		when(countryService.getCountryById(countryId)).thenReturn(country); //Mocking
		when(countryService.updateCountry(country)).thenReturn(country); //Mocking
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonBody = objectMapper.writeValueAsString(country);
		
		this.mockMvc.perform(put("/updatecountry/{id}", countryId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("UK"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapatical").value("London"))
		.andDo(print());
	}
	
	@Test 
	@Order(6)
	public void test_deleteCountry() throws Exception{
		
		country = new Country(3, "UK", "London");
		int countryId = 3;
		
		when(countryService.getCountryById(countryId)).thenReturn(country); //Mocking
		
		this.mockMvc.perform(delete("/deletecountry/{id}", countryId))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
}
