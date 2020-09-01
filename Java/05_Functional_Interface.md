## 함수형 인터페이스

### @FunctionalInterface

- 구현해야할 추상메서드가 하나만 정의된 인터페이스
- 자바 컴파일러는 이렇게 명시된 함수형 인터페이스에 두 개 이상의 메서드가 선언되면 오류를 발생시킴

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



