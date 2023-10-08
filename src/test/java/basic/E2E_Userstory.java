package basic;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class E2E_Userstory {
	public static String value ="";
	public static String storyid ="";
	
	@Test (priority=1)
	public void logintest() {
	Response response = RestAssured.given().baseUri("http://localhost:9009").body("{\r\n"
			+ "     \"username\": \"rashmi\",\r\n"
			+ "     \"password\": \"rashmi\"\r\n"
			+ " }").contentType(ContentType.JSON).
	       when().post("/rest/auth/1/session").
	       then().log().body().extract().response();
	
	    System.out.println(response.getStatusCode());   
	   
	   JSONObject job=new JSONObject(response.asString());
	   value= "JSESSIONID="+job.getJSONObject("session").get("value").toString();
	
	}
	
	@Test(priority=2)
	public void createstory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").body("{\r\n"
				+ "   \"fields\": {\r\n"
				+ "       \"project\": {\r\n"
				+ "           \"key\": \"AM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Create new userstory for practice in Oct Month\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "           \"name\": \"Story\"\r\n"
				+ "       }\r\n"
				+ " }\r\n"
				+ " }").contentType(ContentType.JSON).header("Cookie",value).
		    when().post("/rest/api/2/issue")
		    .then().log().body().extract().response();
		System.out.println(response.getStatusCode());  
		
		 JSONObject job=new JSONObject(response.asString());
		   storyid= job.get("key").toString();
		   System.out.println(storyid);
	
	}
	
	@Test(priority=3)
	public void getUserstory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").contentType(ContentType.JSON).header("Cookie",value)
		.when().get("/rest/api/2/issue/"+ storyid)
		.then().log().body().extract().response();
		System.out.println(response.getStatusCode());  
	}
	
	@Test(priority=4)
	public void updateUserstory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").body("{\r\n"
				+ "   \"fields\": {\r\n"
				+ "       \"project\": {\r\n"
				+ "           \"key\": \"AM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Update new userstory for practice in Oct Month\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "           \"name\": \"Story\"\r\n"
				+ "       }\r\n"
				+ " }\r\n"
				+ " }").contentType(ContentType.JSON).header("Cookie",value)
		.when().put("/rest/api/2/issue/"+ storyid)
		.then().log().body().extract().response();
		System.out.println(response.getStatusCode());  
	}
	
	@Test(priority=5)
	public void getupdatedUserstory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").contentType(ContentType.JSON).header("Cookie",value)
		.when().get("/rest/api/2/issue/"+ storyid)
		.then().log().body().extract().response();
		System.out.println(response.getStatusCode());  
	}
	@Test(priority=6)
	public void deleteUserstory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").contentType(ContentType.JSON).header("Cookie",value)
		.when().delete("/rest/api/2/issue/"+ storyid)
		.then().log().body().extract().response();
		System.out.println(response.getStatusCode());  
	}
	
	
	
	
}
