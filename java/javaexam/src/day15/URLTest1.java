package day15;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLTest1 {
	public static void main(String[] args) throws Exception {
		URL url = new URL("https://movie.naver.com/");
		InputStream is = url.openStream();	/// 바이트 스트림 객체를 리턴함
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		// 입력 받은 것을 문자 스트림으로 변환, 뒤에 저거는 어떤 캐릭터 셋으로 읽게 할 건지 결정
		String line = null;
		while (true) {
			line = br.readLine();
			if (line == null)
				break;
			System.out.println(line);
		}
	}
}
