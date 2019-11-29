package com.different.types.auth;

import static  io.restassured.RestAssured.*;
import org.testng.annotations.Test;


public class Basic_auth {

@Test
public void Basic_API_Auth_Test() {
	given().log().all()
		.auth()
			.preemptive()
				.basic("admin", "admin")
	.when().log().all()
		.get("https://the-internet.herokuapp.com/basic_auth")
	.then().log().all()
		.assertThat()
			.statusCode(200)
		.and()
			.header("Connection", "keep-alive");
	
}
}
