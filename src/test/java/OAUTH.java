import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

public class OAUTH {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		//Getting access token from authorization server
		String response=given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().post("oauthapi/oauth2/resourceOwner/token")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String access_token=js.get("access_token");
		System.out.println(access_token);
		
		//GetCourseDetails by using access token
		
		String response1=given().queryParam("access_token", access_token)
		.when().get("oauthapi/getCourseDetails")
		.then().log().all().assertThat().statusCode(401).body("services",equalTo("projectSupport")).extract().response().asString();
		System.out.println(response);
	}

}
