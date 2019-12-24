package day15;

import java.net.*;
import java.io.*;
public class URLTest4 {
	public static void main(String[] args) {
		InputStream is = null;
		BufferedReader reader = null;
		BufferedWriter fw = null;
		try {
			URL req = new URL("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168064000");
			is = req.openStream();
			reader = new BufferedReader(
					                       new InputStreamReader(is, "utf-8"));
			fw = new BufferedWriter(new OutputStreamWriter(
					     new FileOutputStream("c:/iotest/weather.xml"), "utf-8"));
			// UTF-8 로 내보내기 위해서. FileWriter은 무조건 컴퓨터의 기본 설정을 사용하기 때문에 UTF로 생성하려면 일케 해야됨.
			String lineStr = "";
			while(true) {
				lineStr = reader.readLine();
				if(lineStr == null)
					break;
				fw.write(lineStr+"\r\n");				
			}	
			System.out.println("weather.xml이 성공적으로 생성되었습니다.");
		} catch (MalformedURLException e) {
			System.out.println("URL문자열 오류 : "+e.getMessage());
		} catch (IOException e) {
			System.out.println("IO 오류 : "+e.getMessage());
		}  finally {
			try {
				if (fw != null) 
					fw.close();
				if (reader != null) 
					reader.close();
				if (is != null) 
					is.close();
				//파일은 클로즈 안하면 생성 안될 가능성이 크니 무조건 해야한다.
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
}



