import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.LocationPOJO;
import POJO.MapsPOJO;
import io.restassured.RestAssured;

public class Seriailization {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		MapsPOJO mpj=new MapsPOJO();
		
		mpj.setAccuracy(50);
		mpj.setAddress("29, side layout, cohen 09");
		mpj.setLanguage("Telugu");
		mpj.setName("Lavanya");
		mpj.setPhone_number("(+91) 983 893 3937");
		mpj.setWebsite("http://google.com");
		
		LocationPOJO l = new LocationPOJO();
		l.setLat(-34.67);
		l.setLng(21.67);
		
		mpj.setLocation(l);
		
		List<String> typesList = new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("shop");
		mpj.setTypes(typesList);
		
		given().queryParam("key", "qaclick123").body(mpj)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);

	}

}
