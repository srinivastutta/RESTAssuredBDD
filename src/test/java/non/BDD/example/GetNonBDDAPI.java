package non.BDD.example;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetNonBDDAPI {

	
	@Test
	public void GetNonBDDTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		RequestSpecification request= RestAssured.given();
		request.header("Authorization", "Bearer sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW");
		
		Response response = request.get("/public-api/users");
		
		System.out.println(response.prettyPrint());
		System.out.println(response.statusCode());
		System.out.println(response.getHeader("Server"));
		
	}
	
	@Test
	public void GetNonBDD_withQueryParam_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		RequestSpecification request= RestAssured.given();
		request.header("Authorization", "Bearer sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW");
		request.param("first_name", "John");
		request.param("gender", "male");
		Response response = request.get("/public-api/users");
		
		System.out.println(response.prettyPrint());
		System.out.println(response.statusCode());
		System.out.println(response.getHeader("Server"));
		
	}
	
	@Test
	public void GetNonBDD_withHashMap_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		RequestSpecification request= RestAssured.given();
		request.header("Authorization", "Bearer sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW");
		
		Map<String, String > params = new HashMap<String, String> ();
		
		params.put("first_name", "Rose");
		params.put("status", "active");
		params.put("email", "jermey.hahn@example.net");
		
		
		request.params(params);
		
		Response response = request.get("/public-api/users");
		
		System.out.println(response.prettyPrint());
		System.out.println(response.statusCode());
		System.out.println(response.getHeader("Server"));
		System.out.println(response.getStatusLine());
		System.out.println(response.getContentType());
		
		JsonPath js= response.jsonPath();
		System.out.println(js.getString("_meta.success"));
		
		Assert.assertEquals(js.getString("_meta.success"), "true");
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.getHeader("Server"), "nginx");
}
}