package com.restservice.sourav;

import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import com.restservice.sourav.bean.Country;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class ControllerIntegrationTest {

	@Test
	@Order(1)
	void getAllCountriesIntegrationTest() throws JSONException {
		
		String expectedValue ="[\r\n"
				+ "    {\r\n"
				+ "        \"countryId\": 1,\r\n"
				+ "        \"countryName\": \"UK\",\r\n"
				+ "        \"countryCapatical\": \"London\"\r\n"
				+ "    }\r\n"
				+ "]";
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:8081/getallcountrie", String.class);
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());
		
		JSONAssert.assertEquals(expectedValue, responseEntity.getBody(), false);
	}
	
	@Test
	@Order(2)
	void getCountryByIdIntegrationTest() throws JSONException {
		
		String expectedValue ="{\r\n"
				+ "        \"countryId\": 1,\r\n"
				+ "        \"countryName\": \"UK\",\r\n"
				+ "        \"countryCapatical\": \"London\"\r\n"
				+ "    }";
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:8081/getcountry/1", String.class);
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());
		
		JSONAssert.assertEquals(expectedValue, responseEntity.getBody(), false);
	}
	
	@Test
	@Order(3)
	void getCountryByNameIntegrationTest() throws JSONException {
		
		String expectedValue ="{\r\n"
				+ "        \"countryId\": 1,\r\n"
				+ "        \"countryName\": \"UK\",\r\n"
				+ "        \"countryCapatical\": \"London\"\r\n"
				+ "    }";
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:8081/getcountrybyname/countryname?name=UK", String.class);
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());
		
		JSONAssert.assertEquals(expectedValue, responseEntity.getBody(), false);
	}
	
	@Test
	@Order(4)
	void addNewCountryIntegrationTest() throws JSONException {
		
		Country country = new Country(1, "USA", "Washington");
		
		String expectedValue ="{\r\n"
				+ "        \"countryId\": 1,\r\n"
				+ "        \"countryName\": \"UK\",\r\n"
				+ "        \"countryCapatical\": \"London\"\r\n"
				+ "    }";
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Country> httpEntity = new HttpEntity<Country>(country, httpHeaders);
		ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("http://localhost:8081/updatecountry/1", httpEntity, String.class);
		
		System.out.println(responseEntity.getBody());
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		JSONAssert.assertEquals(expectedValue, responseEntity.getBody(), false);
	}
	
	@Test
	@Order(5)
	void updateCountryIntegrationTest() throws JSONException {
		
		Country country = new Country(1, "USA", "Washington");
		
		String expectedValue ="{\r\n"
				+ "        \"countryId\": 1,\r\n"
				+ "        \"countryName\": \"UK\",\r\n"
				+ "        \"countryCapatical\": \"London\"\r\n"
				+ "    }";
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Country> httpEntity = new HttpEntity<Country>(country, httpHeaders);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange("http://localhost:8081/addcountry",HttpMethod.PUT, httpEntity, String.class);
		
		System.out.println(responseEntity.getBody());
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		JSONAssert.assertEquals(expectedValue, responseEntity.getBody(), false);
	}
	
	@Test
	@Order(6)
	void deleteCountryIntegrationTest() throws JSONException {
		
	Country country = new Country(1, "USA", "Washington");
	
	String expectedValue ="{\r\n"
			+ "        \"countryId\": 1,\r\n"
			+ "        \"countryName\": \"UK\",\r\n"
			+ "        \"countryCapatical\": \"London\"\r\n"
			+ "    }";
	
	TestRestTemplate testRestTemplate = new TestRestTemplate();
	HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	
	HttpEntity<Country> httpEntity = new HttpEntity<Country>(country, httpHeaders);
	ResponseEntity<String> responseEntity = testRestTemplate.exchange("http://localhost:8081/deletecountry/1",HttpMethod.DELETE, httpEntity, String.class);
	
	System.out.println(responseEntity.getBody());
	
	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	JSONAssert.assertEquals(expectedValue, responseEntity.getBody(), false);
}
}
