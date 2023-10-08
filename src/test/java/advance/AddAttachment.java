package advance;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ReadPropertyfile;
import utils.ReadReqbody;

public class AddAttachment {
	public static String cookie="";
	public static String bugid="";
	
	@Test(priority=1)
	public void LogintoJira() throws IOException, ParseException {
		String data=ReadReqbody.readJsonInput("C:\\Users\\HP\\eclipse-workspace1\\practiceOct\\src\\main\\java\\input\\LoginReqBody.json");
		
		String url=ReadPropertyfile.readproperty("URL");
		
		Response response=RestAssured.given().baseUri(url).body(data).contentType(ContentType.JSON).
		when().post("/rest/auth/1/session").then().log().body().extract().response();
		
		System.out.println(response.getStatusCode());	
		
		JSONObject j=new JSONObject(response.asString());
		cookie="JSESSIONID="+j.getJSONObject("session").get("value").toString();	
	}
	
	@Test(priority=2)
	public void createBug() throws IOException, ParseException {
		String data=ReadReqbody.readJsonInput("C:\\Users\\HP\\eclipse-workspace1\\practiceOct\\src\\main\\java\\input\\CreateStoryBody.json");
		
		JSONObject j=new JSONObject(data);
		j.getJSONObject("fields").put("summary", "Create a bug by reading data from json file").getJSONObject("issuetype").put("name","Bug").toString();
		
		System.out.println(j);
		String url=ReadPropertyfile.readproperty("URL");
		
		Response response=RestAssured.given().baseUri(url).body(j.toString()).contentType(ContentType.JSON).header("Cookie",cookie).
				when().post("/rest/api/2/issue").then().log().body().extract().response();
		
		System.out.println(response.getStatusCode());
		
		JSONObject job=new JSONObject(response.asString());
		   bugid= job.get("key").toString();
	}
	
	@Test(priority=3)
	public void addAttachment() throws IOException {
		String url=ReadPropertyfile.readproperty("URL");
		
		File file=new File("C:\\Users\\HP\\eclipse-workspace1\\practiceOct\\src\\main\\java\\docs\\1615553396264.jpg");
		
		Response response=RestAssured.given().baseUri(url).contentType(ContentType.MULTIPART).multiPart(file).header("Cookie",cookie).header("Cookie",cookie).header("X-Atlassian-Token", "no-check")
		.when().post(" /rest/api/2/issue/"+bugid+"/attachments").then().log().body().extract().response();
	
		
		System.out.println(response.getStatusCode());
	
	
	}

}
