## Java Bean이란?

- 데이터를 표현하는 것을 목적으로 하는 자바 클래스
- 컴포넌트와 비슷한 의미로도 사용
- JavaBean규격서에 따라 정의된 자바 클래스



## Java Bean의 규격

- 클래스는 패키지화 하여야 한다.
- 멤버변수는 프로퍼티(Property)라 칭한다.
- 클래스는 필요에 따라 직렬화가 가능하다.
- 프로퍼티의 접근자는 private이다.
- 프로퍼티마다 getter/setter 가 존재해야 하며, 그 이름은 각각 get/set으로 시작해야 한다.
- 위의 프로퍼티 getter/setter 메서드의 접근자는 public이어야 한다.
- 외부에서 프로퍼티에 접근은 메서드를 통해서 접근한다.
- 프로퍼티는 반드시 읽기/쓰기가 가능해야 하지만, 읽기 전용인 경우 getter만 정의할 수 있다.
- getter의 경우 파라미터가 존재하지 않아야 하고, setter의 경우 한 개 이상의 파라미터가 존재한다.
- 프로퍼티의 형이 boolean일 경우 get 메서드 대신 is메서드를 사용해도 된다.

## Example

```java
package 패키지_명;

[import 패키지_명;]

public class Bean_ClassName [ implements java.io.Serializable ] {
  private String name;    // 값을 저장하는 속성 정의(필드)
  public Bean_ClassName() { }    // 기본 생성자

  public String getName() {    // 필드 값을 읽어오는 메소드 
    return name; 
  }
  public void setName(String name) {    // 필드 값을 저장하는 메소드
    this.name = name;
  }
}
```
