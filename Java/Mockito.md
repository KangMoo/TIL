## Mockito

- 유닛 테스트를 위한 Java mocking framework이다.
- mockito를 사용하면 대부분의 로직을 검증할 수 있다.



mockito는 Maven과 저장소를 지원한다. Maven은 아래와 같은 의존성을 pom.xml에 선언하면 사용할 수 있다.

```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-all</artifactId>
    <version>1.10.19</version>
</dependency>
```

mockito의 사용법을 위해 Junit도 pom.xml에 추가 해줍니다.

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
```



## 사용법

### mock()

- `mock()`메서드는 목 객체를 만들어서 반환한다.

```java
public class Person {
  private String name;
  private int age;

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public int getAge() { return age; }
  public void setAge(int age) { this.age = age; }
}
```

```java
@Test
public void example(){
  Person p = mock(Person.class);
  assertTrue( p != null );
}
```

