package com.singht.springboot.controller;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.singht.springboot.firstspringboot_proj.App;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class, 
				webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@LocalServerPort
	private int port;
	
	@Test
	public void testRetrieveSurveyQuestion() {
		//fail("Not yet implemented");
		System.out.println("Local port = " + port);
		String url = "http://localhost:"+ port +"/surveys/Survey1/questions/Question1"; 
		// invoke this url.
		TestRestTemplate restTemplate = new TestRestTemplate();
		 
		// convert the responspe from xml to json. 
		// TestRestTemplate doesnt support suppling headers effeciently.
		// so use HttpEntity for headers.
		
		HttpHeaders httpheader = new HttpHeaders();
		httpheader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity entity = new HttpEntity<String>(null, httpheader);
		ResponseEntity<String> response = restTemplate.exchange(url, 
											HttpMethod.GET, 
											entity, 
											String.class);
		
		//just converting the result into string(2-nd param).
		//String output = restTemplate.getForObject(url,  String.class);
		
		System.out.println("Test Response: " + response.getBody());
		assertTrue(response.getBody().contains("\"id\":\"Question1\""));
		
		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}

}
