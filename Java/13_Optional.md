## Java Optional

- Java 8의 API로 null처리를 도와주는 API



### 예시

다음과 같은 클래스가 있을 때 null 체크를 수행해보자

```java
class Person {
	private String name;

	// constructor, getter, setter method 생략
}

class House {
	private Person onwer;
	private String address;

	// constructor, getter, setter method 생략
}
```

- Optional을 사용하지 않은 코드

```java
void main() {
	House house = houseService.getRandomHouse();
	if (house.getOnwer() != null && house.getOnwer().getName() != null) {
		System.out.println("onwer: " + house.getOnwer().getName());
	}
	if (house.getAddress() != null) {
		System.out.println("address: " + house.getAddress());
	}
}
```

- Optional을 사용한 코드

```java
void main() {
	House house = houseService.getRandomHouse();
	Optional.of(house)
        .map(House::getOnwer)
        .map(Person::getName)
        .ifPresent(name -> System.out.println("onwer:" + name));

	Optional.of(house)
        .map(House::getAdress)
        .ifPresent(address -> System.out.println("address:" + address));
}
```

```java
void main() {
	House house = houseService.getRandomHouse();
	String onwerName = Optional.of(house).map(House::getOnwer).map(Person::getName).orElse("없음");
	String address = Optional.of(house).map(House::getAdress).orElse("발급 되지 않음");

	System.out.println("onwer:" + onwerName);
	System.out.println("address:" + address);
}
```



## Optional

### Optional.of

value가 null인 경우 NPE예외를 던진다. 반드시 값이 있어야 하는 경우 해당 메서드를 사용하면 된다.

```java
// 메서드 시그니처
public static <T> Optional<T> of(T value);
// 예제
Optional<String> opt = Optional.of("result");
```

