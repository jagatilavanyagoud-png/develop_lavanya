import io.restassured.builder.RequestSpecBuilder;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.*;

import Files.Payload;

public class ECommerce {

	public static void main(String[] args) {

		
		

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://wwww.rahulshetty.com")
				.setContentType(ContentType.JSON).build();

		

		RequestSpecification reqlogin = given().log().all().spec(req).body(Payload.ecommerceLogin());
		String res1 = reqlogin.when().post("api/ecom/auth/login").then().extract()
				.response().asString();
		System.out.println(res1);
//		JsonPath js= new JsonPath(res1);
//		String token=js.get("token");
//		String userId=js.get("userId");
		System.out.println("success");
		System.out.println("Git modification is done");
		
	}

}
