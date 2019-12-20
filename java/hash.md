# Hash

해쉬 코드 검사는 중복을 방지해 주는 효과가 있지만

클래스에는 보통 제대로 통하지 않는다.

예를 들면

```java
Class Name{
    String firstName;
	String lastName;
    
    Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
public static void main(String[] args) {
    Name nam1 = new Name("na","me");
    Name nam2 = new Name("na","me");
	HashSet<Class> set = new HashSet<Class>();
    
    set.add(nam1);
    set.add(nam2);
}
```

라고하면 set은 중복을 방지해 주지만, 이것의 비교는 nam1==nam2 라는 객체의 참조값을 비교하기 때문에 중복 방지가 되지 않고, 둘 다 들어가게 된다.



그렇다면 이제 객체의 멤버값을 비교해 줘야 하는 것인데, 이것은 상속받은 Object클래스의 해쉬코드를 만드는 것과 equals연산을 오버라이딩 해주면 된다.

```java
Class Name{
    String firstName;
	String lastName;
    
    Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
    public int hashCode() {
		return firstName.hashCode() + lastName.hashCode();
	}
    public boolean equals(Object o) {
		boolean result = false;
		if(o!=null && o instanceof Name) {
			Name obj =(Name)o;
			if(firstName.equals(obj.firstName)&&
					lastName.equals(obj.lastName))
				result =true;
		}
		return result;
	}
}
public static void main(String[] args) {
    Name nam1 = new Name("na","me");
    Name nam2 = new Name("na","me");
	HashSet<Class> set = new HashSet<Class>();
    
    set.add(nam1);
    set.add(nam2);
}
```



그럼 이 코드를 디버깅 하면 진행 순서를 알 수가 있는데

set.add()에 의해 해쉬 테이블을 만들기 위해 일단 이 코드를 바탕으로 해쉬코드를 만들어야 하는데 원해 대로라면 참조주소를 객체 비교해줘야 하지만, 이 코드에는

```java
public int hashCode() {
	return firstName.hashCode() + lastName.hashCode();
}
```

의 행태로 해쉬 코드가 오버라이딩 되어 있으므로 반환 되는 것은 주소값으로 만든 해쉬 코드가 반환된다.

그리고 이제 hashset의 해당 위치를 비교하게 되는데 만약 해당 위치가 null이라면 동등 연산을 할 필요가 없으므로 이퀄 연산을 하지 않고 그대로 넣어주게 된다.



이제, 동등한 값이 들어갈 때를 생각해보면

해쉬 값이 같은 경우가 생기면, equal연산을 하게 되고

nam1==nam2

라고 당연히 참조값이 다르게 되고 해쉬값은 조금 변한 다음에 해쉬 테이블에 저장되게 된다.



그럼 이제 equals연산을 오버라이딩 하자.

```java
public boolean equals(Object o) {
	boolean result = false;
	if(o!=null && o instanceof Name) {
		Name obj =(Name)o;
		if(firstName.equals(obj.firstName)&&
				lastName.equals(obj.lastName))
			result =true;
	}
	return result;
}
```

해쉬 값은 무한히 많은 경우의 수를 한정된 수로 바꾸는 것이다 보니 어쩔 수 없이 중복 값이 생길 수 밖에 없다.

그럴 때를 대비해서 경우의 수를 2가지로 나눈다.

1. 아예 다른 클래스의 해쉬값이 우연히 같을 경우
2. 같은 클래스지만, 값이 다른 것이 우연히 해쉬값이 같은 경우
3. 같은 클래스이고, 값도 같은 경우

1번의 경우에는 그냥 그대로 false를 리턴해서 해쉬 값을 set에 넣어주면 된다.

```java
boolean result = false;
if(o!=null && o instanceof Name) {
	....
}
return result;
```

위와 같은 경우로 비교해서 클래스가 다를 경우 내용물을 비교할 필요 없이 동등하지 않다는 표시로 false를 리턴해 준다.



그럼 남은 경우는 2번 3번을 비교해서 맞다면 true를 리턴해 준다.

```java
if(firstName.equals(obj.firstName)&&
   lastName.equals(obj.lastName))
	result =true;
```

이것을 합하면 위의 코드가 된다.



그러면 이제 중복도 되지 않는 해쉬값을 만들어 줄 수 있다.

