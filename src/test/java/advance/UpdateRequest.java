package advance;

import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import groovy.json.JsonParser;

public class UpdateRequest {
	public static void main(String[] args) throws IOException, ParseException {
		FileReader fr=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\practiceOct\\src\\main\\java\\advance\\Readdata.json");
		JSONParser jp=new JSONParser();
		String body=jp.parse(fr).toString();
		
		System.out.println(body);
		
		JSONObject j=new JSONObject(body);
		j.getJSONArray("students").getJSONObject(2).put("name","Rashmi").toString();
		System.out.println(j);
		
	}

}
