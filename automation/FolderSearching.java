import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FolderSearching {
	public static void main(String[] args) {
    BufferedReader reader = null;
		Properties prop = new Properties();
		String valuesCheck;
		try {
			ZipFile file = new ZipFile("Z:\\directory.csv.zip");
      //load values from properties file.
			prop.load(new FileInputStream(
					"Y:\\src\\test.properties"));
			valuesCheck = prop.getProperty("values");
			String values[] = valuesCheck.split(",");
				for (Enumeration e = file.entries(); e.hasMoreElements();) {
				ZipEntry e1 = (ZipEntry) e.nextElement();
				for (int i = 0; i < values.length; i++) {
        //check whether the values in properties file are available in Z folder's zip file.
					if (values[i].contains(e1.toString())) {
						System.out.println(values[i]+"(((((((((((((((");
					}
				}
			System.out.println("Nothing Found");
			}
		} catch (Exception e) {	System.out.println(e.getMessage());
		}
	}
}
