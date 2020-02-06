package sample1;

public class MessageBeanImpl implements MessageBean{
	// impl로 인터페이스 상속하고 있다는 것을 알려준다.
	private String fruit;
	private int cost;	
	public MessageBeanImpl() {
		super();
		System.out.println("MessageBeanImpl Default Constructor Call");
	}
	public MessageBeanImpl(String fruit) {
		super();
		this.fruit = fruit;
		System.out.println(fruit + " :  MessageBeanImpl Constructor Call");
	}

	public void setCost(int cost) {
		this.cost = cost;
		System.out.println("setCost() Call");
	}

	@Override
	public void sayHello() {      
		System.out.println(fruit + "   " + cost);
	}

	@Override                     
	public void sayHello(String fruit, int cost) {   
		System.out.println(fruit + "   " + cost);
	}
}






