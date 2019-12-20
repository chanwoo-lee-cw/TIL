package day4;

public class ArrayLab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] ary = {'J','a','v','a','c',' ','-','-','v','e','r','s','i','o','n'};
		int temp= 'a'-'A';
		for (int i=0; i<ary.length;i++) {
			if('A'<=ary[i]&&ary[i]<='Z')
				ary[i]=(char) (ary[i]+temp);
			else if('a'<=ary[i]&&ary[i]<='z')
				ary[i]=(char) (ary[i]-temp);
		}
		
		System.out.print("변화된 배열 : ");
		for (int i=0; i<ary.length;i++) {
			System.out.print(ary[i]);
		}
	}

}
