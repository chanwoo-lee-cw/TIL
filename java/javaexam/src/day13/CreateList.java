package day13;

import java.util.ArrayList;
import java.util.Collections;

public class CreateList {

	ArrayList<Integer> list;
	
	CreateList() {
		list= new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> convertLsit(int array[]) {
		

		for(int i=0;i<array.length;i++) 
		list.add(array[i]);
		
		Collections.reverse(list);
		return list;
	}
}
