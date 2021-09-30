## 변수

- Java - 타입 지정

  ```java
  int num = 10;
  float height = 175.3;
  long area = 2500L;
  String name = "Mike";
  ```

- Dart - 타입 지정 or 타입 추론

  ```dart
  int num = 10;
  double height = 175.3;
  String name = "Mike";
  var world = "Hello world"; // 동적 타입, 타입 추론
  var age = 78;
  ```

  `var`를 사용한 타입 추론의 경우 타입을 중간의 바꾸는건 허용하지 않는다. 



## 배열/리스트

자바에선 배열과 리스트를 구분하지만 다트는 리스트만 사용하기에 좀 더 편하게 데이터를 다룰 수 있다.

-  Java

  ```java
  int[] numbers = [10, 20, 30];
  String[] countries = {"USA", "JAPAN", "KOREA"};
  ArrayList<Integer> lists = new ArrayList<>();
  lists.add(100);
  lists.add(200);
  lists.add(300);
  ```

- Dart

  ```dart
  var numbers = [10, 20, 30];
  var countries = ["USA", "JAPAN", "KOREA", 10];
  print(countries[0]);
  ```



## 함수

Dart에서는 타입을 명시해도 되고, 안 해도 된다.

- Java

  ```java
  int add(int a, int b) {
    return a + b;
  }
  ```

- Dart - 타입 추론

  ```dart
  combine(a,b) {
    return a + b;
  }
  
  var result = combine(10, 20);
  print(result);
  
  var word = combine("hello", "world");
  print(word);
  ```

- Dart - 타입 지정 (권장)

  ```dart
  int add(int a, int b) {
    return a + b;
  }
  
  int sum = add(15, 25);
  print(sum);
  ```

  

## Class / 생성자

- Java

  ```java
  class Point {
    int x;
    int y;
  
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  ```

- Dart

  ```dart
  class Point {
    num x;
    num y;
  
    Point(num x, num y) {
      this.x = x;
      this.y = y;
    }
  
    Point.origin() {
      x = 0;
      y = 0;
    }
  }
  ```

  Dart는 `origin` 키워드를 통해 이름없는 생성자를 초기화하는 역할을 한다.

  ```dart
  Point p = Point(10, 30);
  print(p.x); // 10
  
  var origin = Point.origin();
  print(origin.x); // 0
  ```

  

