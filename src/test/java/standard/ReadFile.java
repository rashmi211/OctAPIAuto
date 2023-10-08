package standard;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadFile {
	public static void main(String[] args) throws IOException, ParseException {	
	FileReader fr=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\practiceOct\\src\\main\\java\\input\\LoginReqBody.json");
	JSONParser jp=new JSONParser();
	String data=jp.parse(fr).toString();
	System.out.println(data);
	

}}
