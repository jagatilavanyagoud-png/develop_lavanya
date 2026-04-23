import static io.restassured.RestAssured.*;


import java.io.File;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JIraCreateBugAndAddAttachment {

	public static void main(String[] args) {
		RestAssured.baseURI="https://jagatilavanyagoud.atlassian.net/";
		
		//Create Bug
		String response=given().header("Authorization","Basic amFnYXRpbGF2YW55YWdvdWRAZ21haWwuY29tOkFUQVRUM3hGZkdGMEp5N1NHalBUUkh6X205QTJwcTBPWnlvb1pURVBreFBhdzVKRGN0Q0oxREY3OVJPSFRCN19hWmJIeDZET0lfMFo2QnUwLWlrS3pGOE1pSzNiek53X3VqVHl5WE56Smw1VWxMQ3Y5X3ZQeU5rMXJ4RTNDaVhmQk9hbERBRnFGMUR5RVJYY2ZCdV9OQkRnSHdza25pOFpldXBHWWRCcWhkV0htajc5bzNXZUxPST03MUI4NzgyQg==")
		.header("Content-Type","application/json")
				.body(Payload.CreateBug())
				.when().post("rest/api/3/issue")
				.then().log().all().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String bug_id=js.get("id");
		System.out.println(bug_id);
		
		
		//Add Attachment
		
		given().header("Authorization","Basic amFnYXRpbGF2YW55YWdvdWRAZ21haWwuY29tOkFUQVRUM3hGZkdGMEp5N1NHalBUUkh6X205QTJwcTBPWnlvb1pURVBreFBhdzVKRGN0Q0oxREY3OVJPSFRCN19hWmJIeDZET0lfMFo2QnUwLWlrS3pGOE1pSzNiek53X3VqVHl5WE56Smw1VWxMQ3Y5X3ZQeU5rMXJ4RTNDaVhmQk9hbERBRnFGMUR5RVJYY2ZCdV9OQkRnSHdza25pOFpldXBHWWRCcWhkV0htajc5bzNXZUxPST03MUI4NzgyQg==")
		.header("X-Atlassian-Token","no-check")
		.pathParam("key", bug_id)
		.multiPart("file", new File("C:\\Users\\Jagat\\Downloads\\Sample.png"))
		.when().post("rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		System.out.println("success");
	}

}
