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
public List<String> getList(String name, int age){ // do something code
}

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



### doNothing()

- void로 선언된 메서드에 `when()` 을 걸고 싶을 때 사용한다.

```java
@Test
public void example(){
  Person p = mock(Person.class);
  doNothing().when(p).setAge(anyInt());
  p.setAge(20);
  verify(p).setAge(anyInt());
}
```



### verify()

- `verify()` 는 해당 구문이 호출되었는지를 체크한다. 단순한 호출뿐만 아니라 횟수나 타임아웃 시간까지 지정해서 체크해 볼 수 있다.

```java
@Test
public void example(){
  Person p = mock(Person.class);
  String name = "JDM";
  p.setName(name);
  // n번 호출했는지 체크
  verify(p, times(1)).setName(any(String.class)); // success
  // 호출 안했는지 체크
  verify(p, never()).getName(); // success
  verify(p, never()).setName(eq("ETC")); // success
  verify(p, never()).setName(eq("JDM")); // fail
  // 최소한 1번 이상 호출했는지 체크
  verify(p, atLeastOnce()).setName(any(String.class)); // success
  // 2번 이하 호출 했는지 체크
  verify(p, atMost(2)).setName(any(String.class)); // success
  // 2번 이상 호출 했는지 체크
  verify(p, atLeast(2)).setName(any(String.class)); // fail
  // 지정된 시간(millis)안으로 메소드를 호출 했는지 체크
  verify(p, timeout(100)).setName(any(String.class)); // success
  // 지정된 시간(millis)안으로 1번 이상 메소드를 호출 했는지 체크
  verify(p, timeout(100).atLeast(1)).setName(any(String.class)); // success
}
```



### @InjectMocks

- 클래스 내부에 다른 클래스를 포함한느 경우 `@InjectMocks` 를 사용한다. 이 어노테이션은 `@Mock` 이나 `@Spy` 어노테이션이 붙은 목 객체를 자신의 멤버 클래스와 일치하면 주입시킨다.



- `AuthService` 의 `isLogin()` 메서드를 사용할 경우 `AuthDao.isLogin()` 반환값을 사용한다. 이 경우 다음과 같은 mockito로 처리할 수 있다.

```java
public class AuthService{
  private AuthDao dao;
  // some code...
  public boolean isLogin(String id){
    boolean isLogin = dao.isLogin(id);
    if( isLogin ){
      // some code...
    }
    return isLogin;
  }
}
public class AuthDao {
  public boolean isLogin(String id){ //some code ... }
  }
```

```java
@Mock
AuthDao dao;

@InjectMocks
AuthService service;

@Test
public void example(){
  MockitoAnnotations.initMocks(this);
  when(dao.isLogin(eq("JDM"))).thenReturn(true);
  assertTrue(service.isLogin("JDM") == true);
  assertTrue(service.isLogin("ETC") == false);
}
```



### @Spy

`@Spy` 로 선언된 목 객체는 목 메서드를 별도로 만들지 않는다면 실제 메서드가 호출된다. 또한 `spy()`로도 같은 효과를 낸다.

```java
Person p = spy(Person.class);
or
  Person p = spy(new Person());
or
  @Spy Person p;
```

