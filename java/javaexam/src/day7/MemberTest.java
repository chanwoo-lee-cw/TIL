package day7;


public class MemberTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Member friend1 = new Member();
		friend1.name="비버";
		friend1.account ="beaver";
		friend1.passwd="123";
		friend1.birthyear=3;
		Member friend2 = new Member();
		friend2.name="토끼";
		friend2.account ="rabbit";
		friend2.passwd="qwe";
		friend2.birthyear=2;
		Member friend3 = new Member();
		friend3.name="고양이";
		friend3.account ="car";
		friend3.passwd="asd";
		friend3.birthyear=1;
		

		memberPrint(friend1,1);
		memberPrint(friend2,2);
		memberPrint(friend3,3);
		
	}
	public static void memberPrint(Member alpha,int i) {
		System.out.printf("회원%d : %s(%s,%s,%d)\n",i,alpha.name,alpha.account,alpha.passwd,alpha.birthyear);
	}

}
