package sample6;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutput implements Outputter{
	private String filePath;  
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		System.out.println("2. File's info set");
	}

	@Override
	public void output(String message) throws IOException {
		FileWriter out=new FileWriter(filePath);
		out.write(message);
		out.close();
		System.out.println("5. Writing successful");
	}
}






