import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		
		//Add Place
		String Response=given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.addpayload())
		
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		
		//Extract Place ID:
			
		JsonPath js = new JsonPath(Response);
		String place_id=js.get("place_id");
		System.out.println(place_id);
		
		//Update Place
		String Expected_address="Nagawara, banglore";
		given().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+Expected_address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	//Get place
	
	String Response1=given().queryParam("key", "qaclick123").queryParam("place_id", place_id)
	.when().get("maps/api/place/get/json")
	.then().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js1= new JsonPath(Response1);
	String response_address=js1.get("address");
	Assert.assertEquals(response_address, Expected_address);
	System.out.println("success");

}
}
