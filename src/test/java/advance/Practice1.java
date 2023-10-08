package advance;

import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Practice1 {
	public static void main(String[] args) throws IOException, ParseException {
	FileReader fr=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\practiceOct\\src\\main\\java\\advance\\Readdata.json");
	JSONParser jp=new JSONParser();
	String data=jp.parse(fr).toString();
	System.out.println(data);
	
	
	JSONObject j=new JSONObject(data);
	
	//print id of first student
	String id=j.getJSONArray("students").getJSONObject(0).get("id").toString();
	System.out.println(id);
	
	//print name of student 2
	String name=j.getJSONArray("students").getJSONObject(1).get("name").toString();
	System.out.println(name);
	
	//print no of jsonobject in jsonarray
	int  n=j.getJSONArray("students").length();
	System.out.println(n);
	
	
	//Upadte student 1 name,city and id
	
	
	
	

}
}