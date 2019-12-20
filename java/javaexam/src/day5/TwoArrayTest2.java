package day5;

public class TwoArrayTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] nums = { 
				{ 10, 20, 30,40 },
				{ 11, 21, 31,41 },
				{ 12, 22, 32,42 } 
				};

		int sum=0;
		for (int i = 0; i < nums.length; i++) 
			for (int j = 0; j < nums[i].length; j++) 
				sum+=nums[i][j];
		
		System.out.println("전체 데이터의 합 :" +sum);
		
		int sumRow =0, sumCol=0;
		
		for (int j = 0; j < nums[nums.length-1].length; j++) 
			sumRow+=nums[nums.length-1][j];
		for (int i = 0; i < nums.length; i++) 
			sumCol+=nums[i][2];
		System.out.println("마지막 행 데이터의 합 :" +sumRow);
		System.out.println("두번째 열 데이터의 합 :" +sumCol);
			
	}

}
