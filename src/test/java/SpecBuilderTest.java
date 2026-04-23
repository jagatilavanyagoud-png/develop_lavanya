import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import POJO.LocationPOJO;
import POJO.MapsPOJO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {
	public static void main(String[] args) {
		
		
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
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification reqs = given().spec(req).body(mpj);
		
		
		Response res1=reqs.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response();

System.out.println("success");
		
		
		
		
        
		
		
		
		

	}
}
