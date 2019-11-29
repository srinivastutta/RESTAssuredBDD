package com.schema.validator;

import static io.restassured.RestAssured.*;
import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ValidateSchemaTest {
	
	@Test
	public void Booking_Schema_Test() {
	
		given().log().all()
			.contentType(ContentType.JSON)
				.body(new File("E:\\Selenium\\Workspace\\APIRestAssured\\src\\main\\java\\DataFiles\\Bookings.json"))
		.when().log().all()
				.post("https://restful-booker.herokuapp.com/booking")
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.and()
					.body(matchesJsonSchemaInClasspath("BookingsSchema.json"));
		
		
		
	}
	
	@Test
	public void GetUserSchemaTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
			.contentType(ContentType.JSON)
				.auth()
					.oauth2("sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW")
				.when()
					.get("/public-api/users")
		.then()
			.assertThat()
					.statusCode(200)
					.and()
					.body(matchesJsonSchemaInClasspath("GetUserSchema.json"));
		
	
	}
}
