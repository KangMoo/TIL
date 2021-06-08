## 함수형 인터페이스

### @FunctionalInterface

- **구현해야할 추상메서드가 하나만 정의된 인터페이스**
- 자바 컴파일러는 이렇게 명시된 함수형 인터페이스에 두 개 이상의 메서드가 선언되면 오류를 발생시킨다
  - 불상사를 막기 위해 `@FunctionalInterface` 어노테이션은 추상 메서드의 개수를 제한한다.

```java
//구현해야 할 메소드가 한개이므로 Functional Interface이다.
@FunctionalInterface
public interface Math {
  public int Calc(int first, int second);
}

//구현해야 할 메소드가 두개이므로 Functional Interface가 아니다. (오류 사항)
@FunctionalInterface
public interface Math {
  public int Calc(int first, int second);
  public int Calc2(int first, int second);
}
```



| 종류      | 특징                                                         | 설명                           |
| --------- | ------------------------------------------------------------ | ------------------------------ |
| Consumer  | 매개값은 있고, 리턴값은 없음                                 | 매개값 -> Consumer             |
| Supplier  | 매개값은 없고, 리턴값은 있음                                 | Supplier -> 리턴값             |
| Function  | 매개값도 있고, 리턴값도 있음 주로 매개값을 리턴값으로 매핑(타입 변환) | 매개값 -> Function -> 리턴값   |
| Operator  | 매개값도 있고, 리턴값도 있음 주로 매개값을 연산하고 결과를 리턴 | 매개값 -> Operator -> 리턴값   |
| Predicate | 매개값은 있고, 리턴 타입은 boolean 매개값을 조사해서 true/false를 리턴 | 매개값 -> Predicate -> boolean |



### Consumer

| 인터페이스명      | 추상 메소드                    | 설명                           |
| ----------------- | ------------------------------ | ------------------------------ |
| Consumer          | void accept(T t)               | 객체 T를 받아 소비             |
| BiConsumer<T, U>  | void accept(T t, U u)          | 객체 T와 U를 받아 소비         |
| DoubleConsumer    | void accept(double value)      | double 값을 받아 소비          |
| IntConsumer       | void accept(int value)         | int값을 받아 소비              |
| LongConsumer      | void accept(long value)        | long 값을 받아 소비            |
| ObjDoubleConsumer | void accept(T t, double value) | 객체 T와 double 값을 받아 소비 |
| ObjIntConsumer    | void accept(T t, int value)    | 객체 T와 int 값을 받아 소비    |
| ObjLongConsumer   | void accept(T t, long value)   | 객체 T와 long 값을 받아 소비   |



### Supplier

| 인터페이스명    | 추상 메소드            | 설명              |
| --------------- | ---------------------- | ----------------- |
| Supplier        | T.get()                | T 객체를 리턴     |
| BooleanSupplier | boolean getAsBoolean() | boolean 값을 리턴 |
| DoubleSupplier  | double getAsDouble()   | double 값을 리턴  |
| IntSupplier     | int getAsInt()         | int 값을 리턴     |
| LongSupplier    | long getAsLong()       | long 값을 리턴    |