package advance;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Readpropertyfile {
	public static void main(String[] args) throws IOException {
	FileReader f=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\practiceOct\\src\\main\\java\\utils\\Global.properties");
	Properties p=new Properties();
	p.load(f);

	String URL=p.getProperty("URL");
	System.out.println(URL);

	}
	
}