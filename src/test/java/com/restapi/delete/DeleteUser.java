package com.restapi.delete;


import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.post.UserPojoTemplate;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteUser {
	
	@Test
	public void CreateUser_with_POJO_Test() {
		UserPojoTemplate user = new UserPojoTemplate("lakshman1", "sharma", "male", "30-06-1979", "lakshman1@gmail.com", "9704966588", "http://www.naveenautomationlbas.com", "Frankurt, Germany", "active");
		
		//Convert POJO to Json --Converting POJO to JSON called Serialization
		//Use JACKSON MVN Dependency
		
		ObjectMapper mapper = new ObjectMapper();
		
		String userJason =null;
		try {
			userJason = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
				
		System.out.println(userJason);
		
		//write post call
		
		RestAssured.baseURI = "https://gorest.co.in";
		String userId = given().log().all()
			.contentType(ContentType.JSON)
				.header("Authorization", "Bearer sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW")
				.body(userJason)
		.when().log().all()
			.post("public-api/users")	
		.then()
			.extract().path("result.id");
			System.out.println("User Id is :::>"+userId);
	
			//delete call
			
			given()
				.contentType(ContentType.JSON)
					.header("Authorization", "Bearer sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW")
			.when()
			.delete("https://gorest.co.in/public-api/users/"+userId)
				.then()
					.assertThat()
						.contentType(ContentType.JSON)
							.body("result", equalTo(null));
			
}}
