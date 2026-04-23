import org.testng.Assert;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(Payload.courses());

		// 1. Print No of courses returned by API
		int no_of_courses = js.getInt("courses.size()");
		System.out.println(no_of_courses);

		// 2.Print Purchase Amount
		int purchasedAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchasedAmount);

		// 3. Print Title of the first course
		String titleFirstCourse = js.get("courses[0].title");
		System.out.println(titleFirstCourse);

		// 4. Print All course titles and their respective Prices
		for (int i = 0; i < no_of_courses; i++) {
			String course_titles = js.get("courses[" + i + "].title");
			int course_price = js.get("courses[" + i + "].price");
			System.out.println(course_titles);
			System.out.println(course_price);
		}

		// 5. Print no of copies sold by RPA Course
		for (int i = 0; i < no_of_courses; i++) {
			String course_title = js.get("courses[" + i + "].title");
			if (course_title.equalsIgnoreCase("RPA")) {
				int RPA_copies = js.get("courses[" + i + "].copies");
				System.out.println(RPA_copies);
				break;
			}
		}
		int total = 0;
		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		for (int i = 0; i < no_of_courses; i++) {
			int price = js.get("courses[" + i + "].price");
			int copies = js.get("courses[" + i + "].copies");
			int amount = price * copies;
			total = total + amount;

		}
		System.out.println(total);
		int purchaseAmount = js.get("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		Assert.assertEquals(total, purchaseAmount);
		System.out.println("success");

	}
}
