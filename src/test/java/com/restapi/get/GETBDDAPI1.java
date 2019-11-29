package com.restapi.get;

import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GETBDDAPI1 {
	
	@DataProvider(name="GetCountryData")
	public Object [][] GetCircuitData(){
		return new Object [][] {
			{0,"Australia"},
			{1, "Bahrain"},
			{2, "Spain"},
			{3, "Germany"},
			
		};
	}
	
	//Verifying all country values in response payload
	
	
	@Test(dataProvider="GetCountryData")
	public void GetAPICountryTest(int index, String Country) {
		
		given()
			.when()
			.get("http://ergast.com/api/f1/2010/circuits.json")
		.then()
			.body("MRData.CircuitTable.Circuits.Location.country"+ "["+index+"]", equalTo(Country));
			

}
}
