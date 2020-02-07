package sampleanno03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// @@Component를 빈 객체르 정의해서 만들 때 사용될 이름을 정한다.
@Component("myFood")  //default : myFoodMgr
public class MyFoodMgr{
	@Autowired
	// 이 멤버 변수에는 알아서 빈 객체를 대입해달라
	//@Qualifier(value="unFavoriteFood")
	private Food favoriteFood;     // setter - Can be omitted
	// 타입이 우선이다. 일단 스트링이 보관하고 있는 객체 중에 푸드 타입 하나를 찾는다.
	// Food타입이 여러개 있다면 일단 이름을 찾는다.
	// 같은 이름이 없다면 에러가 뜬다.
	@Autowired
	private Food unFavoriteFood;
	//setter,getter가 없어도 된다.
	
	@Override
	public String toString() {
		return "[Food1=" + favoriteFood + ", Food2=" + unFavoriteFood + "]";
	}
}
