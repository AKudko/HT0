import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class HTMLGenerator {
	String dir;
	public HTMLGenerator(String dir) {
		super();
		this.dir = dir;
	}

	File outHTML = new File(dir);
	
	String HTM_DOC_STARTING = "<!DOCTYPE html>\r\n" + 
			"<html lang=\"ru\">\r\n" + 
			"<head>\r\n" + 
			"    <title>List of music</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n";
	
	String HTM_DOC_ENDING ="</table>\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
	try {
	FileWriter writer = new FileWriter((outHTML+"\\myMusic.htm"), false);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	public void name(String dir, List<MP3FileDescription> listMP3FilesDescription) {
		
		
	}
	
	
/*	
	<!DOCTYPE html>
	<html>
	<body>

	<h2>A Nested List</h2>
	<p>List can be nested (lists inside lists):</p>

	<ul>
	  <li>Coffee</li>
	  <li>Tea
	    <ul>
	      <li>Black tea</li>
	      <li>Green tea</li>
	    </ul>
	  </li>
	  <li>Milk</li>
	</ul>

	</body>
	</html>
*/

}
