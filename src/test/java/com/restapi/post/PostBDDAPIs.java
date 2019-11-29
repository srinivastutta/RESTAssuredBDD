package com.restapi.post;
import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostBDDAPIs {
	
	//1. Approach
	@Test
	public void Post_Thu_Json_String_Test() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		given().log().all()
			.contentType(ContentType.JSON)
			.body("	{\"username\" : \"admin\", \"password\" : \"password123\"}")
		.when().log().all()
				.post("auth")
		.then().log().all()
			.assertThat()
				.statusCode(200);
		
	}
	//2. Approach
	@Test
	public void Post_Thu_Json_File_Test() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenID = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File ("E:\\Selenium\\Workspace\\APIRestAssured\\src\\"
					+ "main\\java\\DataFiles\\CredentialsJsonFile.json"))
		.when().log().all()
				.post("auth")
		.then().log().all()
			.extract().path("token");
		System.out.println(tokenID);
		
		Assert.assertNotNull(tokenID);
	}
	@Test
	public void createUser_withJsonFile() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW")
			.body(new File("E:\\Selenium\\Workspace\\APIRestAssured\\src\\main\\java\\DataFiles\\CreateUser.json"))
		.when()
			.post("/public-api/users")
		.then()
		.assertThat()
		.body("_meta.success", equalTo(true));
			
	}
}
