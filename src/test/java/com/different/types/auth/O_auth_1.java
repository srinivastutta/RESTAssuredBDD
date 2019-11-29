package com.different.types.auth;
import static  io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class O_auth_1 {

	@Test
	public void Oauth1APITest() {
		
		RequestSpecification request =
				given()
			.auth()
				.oauth("QMrfcqcHiRv800mOFlGmc8HgA",
						"dNXQgG4tMiMoKIXxJCP8tLVmSFBWTa0JsMRpDFhxPPlT4yfyWG",
						"826864399096958987-eMrdev2r5AicSNQOZcGnohzyD4sny0t",
						"kv3AjI7bhIMxLIvBRfr9weSuDDFbBG0mCAk5mFOHD2zsa");
		
		Response response = request.post("https://api.twitter.com/1.1/statuses/update.json?status=Hello World");
		
				
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
}
