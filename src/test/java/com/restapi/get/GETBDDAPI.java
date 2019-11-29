package com.restapi.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class GETBDDAPI {

	@Test
	public void GetAPICircuitTest1() {
		given().log().all()
		 .when().log().all()
		  .get("http://ergast.com/api/f1/2017/circuits.json")
		.then().log().all()
			.assertThat()
			.body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
		
	}
	@Test
	public void GetAPICircuitTest2() {
		Response response = 
		given().log().all()
		 .when().log().all()
		  .get("http://ergast.com/api/f1/2017/circuits.json");
		
		int StatusValue=response.getStatusCode();
		System.out.println("API response code is " +StatusValue);
		Assert.assertEquals(StatusValue, 200);
		System.out.println(response.prettyPrint());
		
	}
	
	@Test
	public void GetAPICircuitTest3() {
		
		RestAssured.baseURI = "http://ergast.com";
				
		given().log().all()
			.when().log().all()
			.get("/api/f1/2017/circuits.json")
		.then().log().all()
		.assertThat()
			.statusCode(200)
		.and()
			.contentType(ContentType.JSON)
		.and()
			.header("Content-Length", equalTo("4551"));
		
	}
	@Test
	public void GetAPIMD5Value() {
	String paramValue = 	"test";
	String ExpectedValue = "098f6bcd4621d373cade4e832627b4f6";
	
		given().log().all()
			.param("text", paramValue)
			.when().log().all()
				.get("http://md5.jsontest.com")
		.then().log().all()
			.assertThat()
				.body("md5", equalTo(ExpectedValue));
	}

@DataProvider(name="GetCircuitYearData")
public Object [][] GetCircuitData(){
	return new Object [][] {
		{"2017", 20},
		{"2016", 21},
		{"1966", 9},
		{"2010", 19},
		
	};
}
	@Test(dataProvider="GetCircuitYearData")
	public void CircuitCountVsYearTest(String SeasonYear, int CircuitCount) {
		
		given().log().all()
			.pathParam("raceYear", SeasonYear)
		.when().log().all()
			.get("http://ergast.com/api/f1/{raceYear}/circuits.json")
		.then().log().all()
			.body("MRData.CircuitTable.Circuits.circuitId", hasSize(CircuitCount));
	
	
}

}
