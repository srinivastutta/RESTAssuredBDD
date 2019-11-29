package com.restapi.put;

import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.post.UserPojoTemplate;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UpdateBDDAPI {

	//create a user with post call
	//update the info with put call
	//1. other attributes should remain same
	//2. the attribute which has been changed need to validate
	
	@Test
	public void CreateUser_with_POJO_Test() {
		UserPojoTemplate user = new UserPojoTemplate("ram1", "sharma", "male", "30-06-1979", "ram1@gmail.com", "9704966588", "http://www.naveenautomationlbas.com", "Frankurt, Germany", "active");
		
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
		
			//call put call through setters
			
		user.setFirst_name("ram2");

		//Convert this Pojo to JSON -- using JACKSON API - ObjectMapper
	
		
		String UpdatedUserJason =null;
		try {
			UpdatedUserJason 	= mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
				
		System.out.println(UpdatedUserJason);
		
			given().log().all()
				.contentType(ContentType.JSON)
					.header("Authorization", "Bearer sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW")
						.body(UpdatedUserJason)
		.when().log().all()
			.put("public-api/users/"+userId)
				.then().log().all()
					.assertThat()
					.body("result.email", equalTo(user.getEmail()))
					.body("result.id", equalTo(userId))
					.body("result.first_name", equalTo(user.getFirst_name()));	
			
			//Get Call for update
			
			given().log().all()
				.param("first_name", user.getFirst_name())
			.header("Authorization", "Bearer sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW")
			.when().log().all()
				.get("https://gorest.co.in/public-api/users")
				.then().log().all()
					.assertThat()
						.statusCode(200);
						//	.body("result.first_name", equalTo(user.getFirst_name()));
				
					}
}