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



`@Mock` 어노테이션을 선언하여 목 객체를 만들 수도 있다. `MockitoAnnotations.initMocks(this)`를 이용하면 Mockito 어노테이션이 선언된 변수들은 객체를 만들어낸다.

```java
@Mock
Person p;

@Test
public void example1(){
  MockitoAnnotations.initMocks(this);
  assertTrue(p != null);
}
```



### when()

- 목 객체를 만들었다면 이 객체로부터  특정 조건을 지정할 수 있다. 이 때 사용하는 것이 `when()`메서드이다.

```java
@Test
public void example(){
  Person p = mock(Person.class);
  when(p.getName()).thenReturn("JDM");
  when(p.getAge()).thenReturn(20);
  assertTrue("JDM".equals(p.getName()));
  assertTrue(20 == p.getAge());
}
```

```java
public List<String> getList(String name, int age){ // do something code }

  // ...
  when(mockIns.getList(anyString(), anyInt()))
    .thenReturn(
    new ArrayList<String>(){
      { this.add("JDM"); this.add("BLOG"); }
    }
  );
```

매개변수가 어떤 값이라도 관계가 없다면 `any...`로 시작하는 메서드를 사용한다. 만약 특정 값을 넣어야 한다면 `eq()` 메서드를 사용하면 된다.

```java
when(mockIns.getList(eq("JDM"), anyInt()))
```



### doThrow()

- 예외를 던지고 싶을 때 사용한다

```java
@Test(expected = IllegalArgumentException.class)
public void example(){
    Person p = mock(Person.class);
    doThrow(new IllegalArgumentException()).when(p).setName(eq("JDM"));
    String name = "JDM";
    p.setName(name);
}
```





### 