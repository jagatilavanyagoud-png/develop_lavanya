import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class LibraryAPI {

	@Test(dataProvider = "BooksData")
	public void test(String bookname, String bookid) {

		RestAssured.baseURI = "http://216.10.245.166";
		// Add Book
		String Response = given().header("Content-Type", "application/json")
				.body(Payload.addBook(bookname, bookid))
				.when().post("/Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		JsonPath js = new JsonPath(Response);
		String ID = js.get("ID");
		System.out.println(ID);

	}

	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		return new Object[][] { { "Tharun", "1111" }, { "Jayanthi", "2222" }, { "Sindhu", "3333" } };
	}
}
