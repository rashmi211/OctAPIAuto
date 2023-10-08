package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyfile {
	public static String readproperty(String key) throws IOException {
		FileReader f=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\practiceOct\\src\\main\\java\\utils\\Global.properties");
		Properties p=new Properties();
		p.load(f);

		String keyvalue=p.getProperty(key);
	    return keyvalue;

		}
		

}
