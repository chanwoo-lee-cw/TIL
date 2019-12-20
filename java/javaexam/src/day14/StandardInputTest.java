package day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StandardInputTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

//		int input = new InputStreamReader(System.in).read();
//		System.out.print((char)input);
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		for(int i=0;i<input.length;i++) {
			System.out.print(input[i]);
		}
	}

}
