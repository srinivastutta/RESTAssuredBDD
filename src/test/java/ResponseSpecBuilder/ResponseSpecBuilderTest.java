package ResponseSpecBuilder;
import static  io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;


public class ResponseSpecBuilderTest {
	
	ResponseSpecBuilder res = new ResponseSpecBuilder();
	ResponseSpecification Coops_Resspec_401_Auth_Fail= res
				.expectContentType(ContentType.JSON)
				.expectStatusCode(401)
				.expectHeader("Server", "cloudflare")
				.build();
	
	ResponseSpecification Coops_Resspec_200_OK= res
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("Server", "cloudflare")
			.build();
							

	@Test
	public void Coops_Response_OK_Test() {
		
		
		given().log().all()
			.auth()
				.oauth2("82b34b67d12f167251dd4010efcde6a8ac11bf4e")
		.when().log().all()
				.get("http://coop.apps.symfonycasts.com/api/me")
		.then().log().all()
			.assertThat()
				.spec(Coops_Resspec_200_OK);
	}

	@Test
	public void Coops_Response_Auth_Fail_Test() {
		
		
		given().log().all()
			.auth()
				.oauth2("82b34b67d12f167251dd4010efcde6a8ac11bf4e111")
		.when().log().all()
				.get("http://coop.apps.symfonycasts.com/api/me")
		.then().log().all()
			.assertThat()
				.spec(Coops_Resspec_401_Auth_Fail);
	}
	

}
