package standard;

import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class E2E_Userstory {
	public static String value="";
	public static String storyid="";
	
	@Test(priority=1)
	public void logintojira() throws IOException, ParseException {
	FileReader fr=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\practiceOct\\src\\main\\java\\input\\LoginReqBody.json");
	JSONParser jp=new JSONParser();
	String reqbody=jp.parse(fr).toString();
	
	Response response =	RestAssured.given().baseUri("http://localhost:9009").body(reqbody).contentType(ContentType.JSON).
		when().post("/rest/auth/1/session").then().log().body().extract().response();
	
	System.out.println(response.getStatusCode());
	
	JSONObject j=new JSONObject(response.asString());
	value="JSESSIONID="+j.getJSONObject("session").get("value").toString();
		
	}
	@Test(priority=2)
	public void createStory() throws IOException, ParseException {
	FileReader fr=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\practiceOct\\src\\main\\java\\input\\CreateStoryBody.json");
	JSONParser jp=new JSONParser();
	String reqbody=jp.parse(fr).toString();
	
	Response response =	RestAssured.given().baseUri("http://localhost:9009").body(reqbody).contentType(ContentType.JSON).header("Cookie",value).
		when().post("/rest/api/2/issue").then().log().body().extract().response();
	
	System.out.println(response.getStatusCode());
	
	JSONObject j=new JSONObject(response.asString());
	storyid=j.get("key").toString();
	
	}

	
	@Test(priority=3)
	public void getUserstory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").contentType(ContentType.JSON).header("Cookie",value)
		.when().get("/rest/api/2/issue/"+ storyid)
		.then().log().body().extract().response();
		System.out.println(response.getStatusCode());  
	}
	
	@Test(priority=4)
	public void updateStory() throws IOException, ParseException {
	FileReader fr=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\practiceOct\\src\\main\\java\\input\\UpdateStory.json");
	JSONParser jp=new JSONParser();
	String reqbody=jp.parse(fr).toString();
	
	Response response =	RestAssured.given().baseUri("http://localhost:9009").body(reqbody).contentType(ContentType.JSON).header("Cookie",value).
		when().put("/rest/api/2/issue/"+ storyid).then().log().body().extract().response();
	
	System.out.println(response.getStatusCode());

	}
	
	@Test(priority=5)
	public void getUpdatedUserstory() {
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
	
	@Test(priority=7)
	public void getdeletedUserstory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").contentType(ContentType.JSON).header("Cookie",value)
		.when().get("/rest/api/2/issue/"+ storyid)
		.then().log().body().extract().response();
		System.out.println(response.getStatusCode());  
	}
	
	
	
	
}
