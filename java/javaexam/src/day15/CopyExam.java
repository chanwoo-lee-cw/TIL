package day15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CopyExam {

	public static void main(String args[]) {
		FileReader fr = null;
		FileWriter fw = null;
		try {
			String path = "C:/iotest";
	        
			File isDir = new File(path);
	        if (!isDir.exists()) {
	        	isDir.mkdirs();
	        }
			
			fr = new FileReader(path+"/sample.txt");
			BufferedReader br = new BufferedReader(fr);
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			fw = new FileWriter(path+"/"+currentDate.format(formatter)+".txt",true);
			String data = "";
			while (true) {
				data = br.readLine();
				if(data == null)
					break;
				fw.write(data+"\r\n");			
			}
		}catch (MalformedURLException e) {
			System.out.println("URL문자열 오류 : "+e.getMessage());
		} catch (IOException e) {
			System.out.println("IO 오류 : "+e.getMessage());
		}  finally {
			try {
				if (fw != null) 
					fw.close();
				if (fr != null) 
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
}
