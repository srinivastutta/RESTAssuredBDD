package com.different.types.auth;
import static  io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class O_auth_2 {
	//by using oauth2 Method
	
	//**if your are using:
	//1. with header: append your token with Bearer keyword
	//2.  with Oauth2 method: No need to add Bearer, just pass the token
	
	@Test
	public void Oauth2_API_Test() {
		given()
			.auth()
				.oauth2("sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW")
		.when()
			.get("https://gorest.co.in/public-api/users")
		.then()
			.assertThat()
				.statusCode(200);
		}

	//By using header method
	@Test
	public void Oauth2_API_Test_With_AuthHeader() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.param("first_name", "John")
				.header("Authorization", "Bearer sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW")
		.when().log().all()
			.get("/public-api/users")
		.then().log().all()
			.assertThat()
				.statusCode(200)
		.and()
			.header("Server", "nginx");
		}

	//WIth QueryParam
	@Test
	public void Oauth2_API_Test_With_QueryParam() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.queryParam("first_name", "John")
			.queryParam("gender", "male")
				.header("Authorization", "Bearer sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW")
		.when().log().all()
			.get("/public-api/users")
		.then().log().all()
			.assertThat()
				.statusCode(200)
		.and()
		.header("Server", "nginx");
}
	
	//WIth RequestSpecification
		@Test
		public void Oauth2_API_Test_With_RequestSpecification() {

			
		RequestSpecification request=
					RestAssured.
						given()
							.auth()
								.oauth2("0275f2ff7480154d0eb245c405df694fd32acfa7");
		
				Response response=	request.post("http://coop.apps.symfonycasts.com/api/546/eggs-collect");
				System.out.println(response.statusCode());
				System.out.println(response.prettyPrint());
		
		
		}
		
		@Test
		public void GenerateAuth2Token() {
			//Generate Token by filling the form
			//fill the form params and store request object by using RequestSpecification Interface
			
			RequestSpecification request =
					RestAssured.given()
						.formParam("client_id", "Coops APIs")
							.formParam("client_secret", "a1d1eedc3fda549653d8533e066b8e2a")
								.formParam("grant_type", "client_credentials");

			//by using the request object, post the token URL and store the response in Response Object
		
			Response response = request.post("http://coop.apps.symfonycasts.com/token");
			//System.out.println(response.getStatusCode());
			//System.out.println(response.prettyPrint());
	

			//By using response get text of access token
		
			String tokenID = response.jsonPath().getString("access_token");
			System.out.println(tokenID);
			
			//Send the response to provided server URL by using generated token
			
						given().log().all()
							.auth()
								.oauth2(tokenID)
						.when().log().all()
								.post("http://coop.apps.symfonycasts.com/api/546/eggs-collect")
						.then().log().all()
							.assertThat()
						 		.statusCode(200);
		}
}