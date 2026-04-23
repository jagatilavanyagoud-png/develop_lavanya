import static io.restassured.RestAssured.*;


import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import POJO.API;

import POJO.GetCourses;
import POJO.WebAutomation;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Deserialization {
	
	public static void main(String[] main) {
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
	
	GetCourses cs=given().queryParam("access_token", access_token)
	.when().get("oauthapi/getCourseDetails")
	.then().log().all().assertThat().statusCode(401)
	.body("services",equalTo("projectSupport")).extract().response().as(GetCourses.class);
	System.out.println(cs.getInstructor());
	System.out.println(cs.getUrl());
	System.out.println("success");
	
	//prbm1: Get the price for the api which is having title "SoapUI Webservices testing"
	
//	List<API> apis=cs.getCourses()
//	System.out.println(apis);
//	
//	for(int i=0;i<apis.size();i++) {
//		if(apis.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
//			String price=apis.get(i).getPrice();
//			System.out.println("output of prbm 1 is:"+price);
//		}
//	}
//	
	//prbm2: Get all the WebAutomation course Titles
List<POJO.WebAutomation> webautomation=cs.getCourses().getWebAutomation();
	for(int i=0;i<webautomation.size();i++) {
		String course_title=webautomation.get(i).getCourseTitle();
		System.out.println(course_title);
	}
}
}