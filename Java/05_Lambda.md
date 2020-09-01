## Lambda

- 익명함수
  - 익명함수는 말 그대로 이름이 없는 함수이다.
- 공통으로 **일급 객체**라는 특징을 가지고 있어서 다른 객체들에 적용 가능한 연산을 모두 지원한다.
- 함수를 값으로 사용할 수도 있으며 파라미터로 전달 및 변수에 대입이 가능하다

### 장점

- 코드의 간결성
- 지연연산 수행
- 병렬처리 가능

### 단점

- 호출이 까다로움
- 람다 stream 사용시 단순 for문 혹은  while문 사용시 성능이 떨어짐
- 불필요하게 너무 사용되면 오히려 가독성을 떨어뜨림

### 람다의 표현식

- 매개변수 화살표 `->` 함수몸체로 이용하여 사용할 수 있음
- 매개변수가 하나일 경우 매개변수 생략 가능
- 함수몸체가 단일 실행문이면 괄호 `{}`생략 가능
- 함수몸체가 return문으로만 구성되어있는 경우 괄호 `{}` 생략 가능
- 형식

> `(매개변수) -> {함수몸체}`
>
> `() -> {함수몸체)`
>
> `(매개변수) -> 함수몸체`
>
> `(매개변수) -> {return 0;}`

- 형식 예시

```java
//정상적인 유형
() -> {}
() -> 1
() -> { return 1; }

(int x) -> x+1
(x) -> x+1
x -> x+1
(int x) -> { return x+1; }
x -> { return x+1; }

(int x, int y) -> x+y
(x, y) -> x+y
(x, y) -> { return x+y; }

(String lam) -> lam.length()
lam -> lam.length()
(Thread lamT) -> { lamT.start(); }
lamT -> { lamT.start(); }


//잘못된 유형 선언된 type과 선언되지 않은 type을 같이 사용 할 수 없다.
(x, int y) -> x+y
(x, final y) -> x+y  
```



### 예시

**기존 자바 문법**

```java
new Thread(new Runnable() {
  @Override
  public void run() { 
    System.out.println("Welcome Heejin blog"); 
  }
}).start();
```

**람다식 문법**

```java
new Thread(()->{
  System.out.println("Welcome Heejin blog");
}).start();
```

