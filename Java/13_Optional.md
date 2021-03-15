## Java Optional

- Java 8의 API로 null처리를 도와주는 API

메서드가 반환할 결과값이 ‘없음’을 명백하게 표현할 필요가 있고, `null`을 반환하면 에러를 유발할 가능성이 높은 상황에서 메서드의 반환 타입으로 `Optional`을 사용하자는 것이 `Optional`을 만든 주된 목적이다. `Optional` 타입의 변수의 값은 절대 `null`이어서는 안 되며, 항상 `Optional` 인스턴스를 가리켜야 한다.

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

value가 `null`인 경우 `NPE`예외를 던진다. 반드시 값이 있어야 하는 경우 해당 메서드를 사용하면 된다.

```java
// 메서드 시그니처
public static <T> Optional<T> of(T value);
// 예제
Optional<String> opt = Optional.of("result");
```

### Optional.ofNullable

value가 `null`인 경우 비어있는 `Optional`을 반환한다. 값이 `null`일수도 있는 것은 해당 메서드를 사용하면 된다.

```java
// 메서드 시그니처
public static <T> Optional<T> ofNullable(T value);
// 예제
Optional<String> opt = Optional.ofNullable(null);
```

### Optional.empty

비어있는 옵셔널 객체를 생서한다. 조건에 따라 분기를 태워야 하고 반환할 값이 없는 경우에도 사용된다.

```java
// 메서드 시그니처
public static<T> Optional<T> empty();
// 예제
Optional<String> emptyOpt = Optional.empty();
```

> **비어있는 옵셔널 객체**
>
> 옵셔널이 '비어있다'는 표현은 옵셔널 객체는 있지만 옵셔널 객체가 가진 유효한 객체가 없는 경우이다.
>
> ```
> * 비어있는 옵셔널 객체
> ----------
> |Optional| 이 옵셔널 객체는 생성은 되었으나 값(객체)을 가지지 않았다.
> |        | 이 상태는 Optional.empty() 라고 볼 수 있다.
> |        |
> ----------
> 
> * 값이 있는 옵셔널 객체
> ----------
> |Optional| 이 옵셔널 객체는 생성 되고 값(객체)을 가지고 있다.
> |--------|
> ||String||
> |--------|
> ----------
> ```



## Optional 중간 처리

옵셔널 객체를 생성한 후 사용 가능한 메서드다. 해당 메서드들은 다시 옵셔널을 반환하므로 메서드 체이닝을 통해 원하는 로직을 반복 사용할 수 있다.

### filter

predicate 갓이 참이면 필터를 통과시키고 거짓이면 통과되지 않습니다.

```java
// 메서드 시그니처
public Optional<T> filter(Predicate<? super T> predicate);
// 예제
Optional.of("True").filter((val) -> "True".eqauls(val)).orElse("NO DATA"); // "True"
Optional.of("False").filter((val) -> "True".eqauls(val)).orElse("NO DATA"); // "NO DATA"
```

### map

mapper함수를 통해 입력값을 다른 값으로 변환하는 메서드이다

```java
// 메서드 시그니처
public<U> Optional<U> map(Function<? super T, ? extends U> mapper);
// 예제
Integer test = Optional.of("1").map(Integer::valueOf).orElseThrow(NoSuchElementException::new); // string to integer
```

### filterMap

mapper함수를 통해 입력값을 다른 값으로 변환하는 메서드이다. `map()` 메서드와 다른점은 메서드 시그니처의 매개변수이다. `map()`에서는 제너릭으로 `U`를 정의했지만 `filterMap()`에서는 제너릭으로 `Optional(U)`를 정의했다.

이것을 뜻하는 바는 `flatpMap()`메서드가 반환해야 하는 값은 `Optional`이라는 의미다.

```java
// 메서드 시그니처
public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper);
// 예제
String result = Optional.of("result")
        .flatMap((val) -> Optional.of("good"))
        .get();
System.out.println(result); // print 'good'
```



## Optional 종단처리

종단 처리라는 것은 객체의 체이닝을 끝낸다는 것이다.

### ifPresent

최종적으로 연산을 끝낸 후 값이 비어있지 않다면 입력값으로 주어진다. 이 값을 가지고 원한는 작업을 수행하면 된다. 하지만 중간 옵션을 하자 비어있는 객체를 받게 되면 `.isPresent()`메서드의 내용을 수행하지 않는다.

```java
// 메서드 시그니처
public void ifPresent(Consumer<? super T> consumer);
// 예제1
Optional.of("test").ifPresent((value) -> {
	// something to do
});
// 예제2 (ifPresent 미수행)
Optional.ofNullable(null).ifPresent((value) -> {
	// nothing to do
});
```

### isPresent

최종적으로 연산을 끝낸 후 객체가 존재하는지 여부를 판별한다

```java
// 메서드 시그니처
public boolean isPresent();
// 예제
Optional.ofNullable("test").isPresent(); // true
Optional.ofNullable("test").filter((val) -> "result".eqauls(val)).isPresent(); // false
```

### get

최종적으로 연산을 끝낸 후 객체를 껀낸다. 이 때 비어있는 옵셔널 객체였다면 예외가 발생한다.

```java
// 메서드 시그니처
public T get();
// 예제
Optional.of("test").get(); // 'test'
Optional.ofNullable(null).get(); // NoSuchElementException!
```

### orElse

최종적으로 연산을 끝낸 후에도 옵셔널 객체가 비어있다면 기본값으로 제공할 객체를 지정한다.

```java
// 메서드 시그니처
public T orElse(T other);
// 예제
String result = Optional.ofNullable(null).orElse("default");
System.out.println(result); // print 'default
```

### orElseGet

최종적으로 연산을 끝낸 후에도 옵셔널 객체가 비어있다면 기본값으로 제공할 공급자 함수 `supplier`를 지정한다.

```java
// 메서드 시그니처
public T orElseGet(Supplier<? extends T> other);
// 예제
String result = Optional.ofNullable("input").filter("test"::equals).orElseGet(() -> "default");
System.out.println(result); // print 'default'
```

### orElseThrow

최종적으로 연산을 끝낸 후에도 옵셔널 객체가 비어있다면 예외 공급자 함수를 통해 예외를 발생시킨다.

```java
// 메서드 시그니처
public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;
// 예제
Optional.ofNullable("input").filter("test"::equals).orElseThrow(NoSuchElementException::new);
```

> **orElse, orElseGet 중 무엇을 사용할까?**
>
>orElse 메서드는 옵셔널 객체가 비어있든 비어있지 않든 **반드시 실행**한다. orElseGet 메서드는 옵셔널 객체가 비어있으면 실행한다. 따라서 기본값을 주고자 할때에 기본값을 구하는 과정이 오래 걸린다면 orElseGet을 사용하면 된다.
>
>또 한가지의 측면은 orElse 메서드가 받는 매개변수는 객체다.
>
>```java
>Optional.ofNullable(something).orElse(new Something());
>```
>
>orElseGet 메서드가 받는 매개변수는 공급자 함수Supplier입다. 이는 기본값으로 제공할 때 사용할 비즈니스 로직을 메서드 안으로 포함 할 수 있다는 것이다.
>
>```java
>Optional.ofNullable(something).orElseGet(() -> {
>	// business logic ...
>	return value;
>});
>```

> **Optional<List<T>> vs List<T>**
>
> List는 항상 변수를 할당할때마다 초기화해주는 습관이 필요합니다. List를 Optional로 감싸게 되면 Optional로 체크하고 내부의 List 도 체크해야하는 상태가 생겨 코드 가독성이 좋지 않아요. 따라서 항상 아래의 코드처럼 List를 사용할때에 List를 채워주면 좋겠습니다.
>
> ```java
> List data = Optional.ofNullable(somethingList).orElse(Collections.emptyList());
> if (!data.isEmpty()) {
> 	// do something...
> }
> ```



## Optional의 바른 사용

1. `Optional` 변수에 절대로 `null` 을 할당하지 말 것 (`Optional.empty` 사용 권장)
2. `Optional.get()` 호출 전에 `Optional` 객체가 값을 가지고 있음을 확실히 할 것
3. `isPresent()-get()` 대신 `orElse()/orElseGet()/orElseThrow()`
4. `orElse(new ...)` 대신 `orElseGet(() -> new ...)`
5. 단지 값을 얻을 목적이라면 `Optional` 대신 `null` 비교
6. `Optional` 대신 비어있는 컬렉션 반환
7. `Optional`을 필드로 사용 금지
8. `Optional`을 생성자나 메서드 인자로 사용 금지
9. `Optional`을 컬렉션의 원소로 사용 금지
10. `of()`, `ofNullable()` 혼동 주의
11. `Optional<T>` 대신 `OptionalInt`, `OptionalLong`, `OptionalDouble`