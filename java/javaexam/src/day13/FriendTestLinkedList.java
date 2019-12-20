package day13;

import java.util.Iterator;
import java.util.LinkedList;

class Person {

	private String name;

	Person(String name) {
		this.name = name;
	}

	public String getInfo() {
		return name;
	}

}

class Friend extends Person {

	private String phoneNum;
	private String email;

	Friend(String name, String phoneNum, String email) {
		super(name);
		this.phoneNum = phoneNum;
		this.email = email;
	}

	public String getInfo() {
		return (super.getInfo() + "\t" + phoneNum + "\t" + email);
	}
}

public class FriendTestLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList<Friend> list = new LinkedList<Friend>();

		list.add(new Friend("앨리스", "010-1234-5678", "Alice@fairytale.com"));
		list.add(new Friend("모자장수", "010-5678-1234", "Madhatter@fairytale.com"));
		list.add(new Friend("채셔 캣", "010-4321-8765", "Cheshire@fairytale.com"));
		list.add(new Friend("흰 토끼", "010-8765-4321", "WhiteRabbit@fairytale.com"));
		list.add(new Friend("하트여왕", "010-6543-6432", "HeartQueen@fairytale.com"));

		System.out.printf("이름 \t 전화번호 \t\t 메일주소\n");
		System.out.printf("-------------------------------\n");

		Iterator<Friend> iter = list.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next().getInfo());
		}

		LinkedList<Friend> stack = new LinkedList<Friend>();

		iter = list.iterator();
		while (iter.hasNext()) {
			stack.addLast(iter.next());
		}
		System.out.println();

		System.out.printf("이름 \t 전화번호 \t\t 메일주소\n");
		System.out.printf("-------------------------------\n");

		while (!stack.isEmpty()) {
			System.out.println(stack.removeLast().getInfo());
		}

		LinkedList<Friend> queue = new LinkedList<>();
		iter = list.iterator();
		while (iter.hasNext()) {
			queue.offer(iter.next());
		}

		System.out.println();

		System.out.printf("이름 \t 전화번호 \t\t 메일주소\n");
		System.out.printf("-------------------------------\n");

		while (!queue.isEmpty()) {
			System.out.println(queue.poll().getInfo());
		}

	}

}
