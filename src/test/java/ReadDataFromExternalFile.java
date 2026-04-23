import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import io.restassured.RestAssured;

public class ReadDataFromExternalFile {

	public static void main(String[] args) throws IOException {
RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		
		//Add Place
		given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Jagat\\OneDrive\\Desktop\\Udemy Practice\\RestAssuredUdemyPractice\\src\\test\\java\\Files\\JsonFile.json"))))
		
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
	}

}
