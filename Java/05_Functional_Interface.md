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





| 인터페이스명      | 추상 메소드              | 설명              |
| ----------------- | ------------------------ | ----------------- |
| `Supplier<T>`     | `T get()`                | T 객체를 리턴     |
| `BooleanSupplier` | `boolean getAsBoolean()` | boolean 값을 리턴 |
| `DoubleSupplier`  | `double getAsDouble()`   | double 값을 리턴  |
| `IntSupplier`     | `int getAsInt()`         | int 값을 리턴     |
| `LongSupplier`    | `long getAsLong()`       | long 값을 리턴    |