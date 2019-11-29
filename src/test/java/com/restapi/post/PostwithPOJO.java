package com.restapi.post;

import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostwithPOJO {
	
	//3 Approach 
	
	@Test
	public void CreateUser_with_POJO_Test() {
		UserPojoTemplate user = new UserPojoTemplate("Vedik", "Tutta", "male", "30-06-1981", "Vedik.tutta@gmail.com", "9704966588", "http://www.naveenautomationlbas.com", "Frankurt, Germany", "active");
		
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
		
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
			.contentType(ContentType.JSON)
				.header("Authorization", "Bearer sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW")
				.body(userJason)
		.when().log().all()
			.post("public-api/users")
		.then().log().all()
			.assertThat()
				.contentType(ContentType.JSON)
				.and()
				.body("result.first_name", equalTo(user.getFirst_name()))
				.body("result.last_name", equalTo(user.getLast_name()))
				.body("result.status", equalTo(user.getStatus()));
		
		
	}

}
