## 프로그래밍 기본 개념

Java 프로그래밍을 시작하기 전에 프로그래밍의 기본적인 개념과 환경 설정에 대해 알아보자

### 프로그램과 프로그래밍이란?

- 프로그램은 메뉴얼처럼 컴퓨터에게 특정 작업을 수행하도록 지시하는 명령어들의 집합이다
- 프로그래밍이란 컴퓨터에게 원하는 작업을 정확하고 효율적으로 수행하도록 지시하는 일련의 명령어들을 만드는 과정이다
  - 프로그래밍을 통해 우리는 컴퓨터에게 원하는 작업을 정확하고 효율적으로 수행하도록 할 수 있다
- 프로그래밍 언어란 컴퓨터가 이해할 수 있는 언어로, 프로그램을 작성하는 데에 사용된다

### 프로그래밍 환경 설정

프로그래밍을 시작하기 전에 필요한 소프트웨어를 설치하고 환경을 구성해야 한다.

Java 프로그래밍을 위해서는 JDK(Java Development Kit)를 설치해야 하며, 추가로 코드를 작성하고 관리하기 위한 IDE(Integrated Development Environment)가 필요할 수 있다

IDE의 예로는 `IntelliJ IDEA`, `Eclipse`, `NetBeans` 등이 있다.

1. **JDK 설치**: Java 프로그래밍을 위해서는 Java Development Kit(JDK)가 필요하다. JDK는 Java 프로그램을 개발하고 실행할 수 있게 해주는 소프트웨어 패키지이다.
2. **IDE 설치**: 코드 작성, 디버깅, 빌드 등을 편리하게 할 수 있게 해주는 도구인 IDE를 설치한다. IntelliJ IDEA, Eclipse 등을 사용할 수 있다.

> 여기서는 IntelliJ IDEA를 사용한다

### 프로젝트 생성

IntelliJ IDE를 사용하여 새로운 프로젝트를 만드는 방법을 알아보자

- ![프로젝트-생성-1](https://hackmd.io/_uploads/SJHkQytOp.png)
  - `New Project`를 선택하여 새로운 프로젝트를 생성한다
- ![프로젝트-생성-2](https://hackmd.io/_uploads/BJd1mktOa.png)
  1. Name : 프로젝트의 이름을 입력한다
  2. Location : 프로젝트를 저장할 경로를 지정한다
  3. Language : 프로젝트에서 사용할 언어를 선택한다. 여기서는 Java를 선택한다.
  4. Build System : 프로젝트를 빌드할 시스템을 선택한다. 여기서는 Maven을 선택한다.
     - 추후 Gradle에 대해서 공부하는 것도 좋다
  5. JDK : 프로젝트에서 사용할 JDK를 선택한다.

### 프로젝트 구성

새로 생성된 프로젝트에 이미 여러 디렉토리 및 파일이 생성되어 있는 것을 확인할 수 있다.

각 디렉토리에 대해 간단히 살펴보면 다음과 같다

1. 소스 디렉토리 (`src`)
   - `src/main/java`: 프로젝트의 주요 소스 파일이 저장되는 곳. 여기에는 프로젝트의 자바 클래스들이 위치한다.
   - `src/main/resources`: 자바 코드에서 사용되는 리소스 파일들이 저장되는 곳. 예를 들어, 설정 파일, 프로퍼티 파일 등이 이곳에 위치한다.
   - `src/test/java`: 단위 테스트를 위한 자바 소스 코드가 저장되는 곳. 일반적으로 JUnit 또는 다른 테스팅 프레임워크를 사용하여 테스트 케이스가 작성된다.
   - `src/test/resources`: 테스트 중에 사용되는 리소스 파일들이 저장되는 곳.
2. 빌드 스크립트 (`build.gradle`, `pom.xml` 등)
   - Maven (`pom.xml`) 또는 Gradle (`build.gradle`)과 같은 빌드 도구를 사용하는 프로젝트의 경우, 프로젝트의 빌드 프로세스와 종속성을 관리하는 빌드 스크립트 파일
3. `bin` 또는 `target` 디렉토리
   - 컴파일된 바이트코드 (.class 파일)가 저장되는 곳. Maven이나 Gradle을 사용하는 경우 일반적으로 `target` 디렉토리에 저장된다.
4. `lib` 디렉토리
   - 프로젝트에서 사용되는 외부 라이브러리나 JAR 파일들이 저장되는 곳. Maven이나 Gradle을 사용할 경우, 이들은 `pom.xml` 또는 `build.gradle` 파일을 통해 관리되며, 별도의 `lib` 디렉토리는 필요하지 않을 수 있다.
5. 기타 파일들
   - `.gitignore`, `README.md`, 라이선스 파일, 설정 파일 등 프로젝트 관리 및 문서화를 위한 기타 파일들

이처럼 프로젝트 내에는 다양한 디렉토리와 파일들이 존재한다. 이들은 프로젝트를 관리하는 데에 중요한 역할을 하며, 이들을 적절히 활용하여 소스코드, 라이브러리, 리소스, 테스트 코드 등을 체계적으로 관리할 수 있다.

### 자바 프로그램의 실행

프로젝트 내에 작성된 코드를 실행하기 위해서는 일반적으로 다음과 같은 과정을 거친다

1. 소스 코드 작성 : 프로그램의 동작을 정의하는 코드를 작성한다.
2. 빌드(컴파일) : 코드를 컴퓨터가 이해할 수 있는 형태로 변환하는 과정이다. Java에서는 `.java` 파일이 `.class` 파일로 컴파일된다.
3. 실행 : 빌드된 프로그램을 실행하여 결과를 확인한다.

#### 소스 코드 작성

- 자바 프로그램은 한 개 이상의 클래스(class)로 구성된다
  - 만약 클래스에 대해 잘 모른다면, 일단 넘어가도 좋다
- 자바 프로그램은 `public static void main(String[] args)` 메소드를 포함하는 클래스로 구성된다
  - `main` 메소드는 프로그램의 시작점을 의미한다
  - Java 프로그램은 `main` 메소드를 기본으로 실행된다
  - 마찬가지로, `public`, `static` 등 이해가 안되어도 일단 넘어가도 좋다

```java
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성한다.
        // 코드는 위에서 아래로 차례대로 실행된다.
    }
}
```

#### 주석(Comment)

- 주석이란 프로그램의 실행에는 영향을 주지 않지만, 프로그램의 이해를 돕기 위해 사용되는 문장을 의미한다. 즉, 일종의 메모라고 할 수 있다.
- Java에서는 `//`를 사용하여 한 줄 주석을 작성할 수 있으며, `/* */`를 사용하여 여러 줄 주석을 작성할 수 있다.



#### 빌드 및 실행

- Java 프로그램은 컴파일된 후에 실행된다
  - Java 프로그램을 컴파일하려면, Java 컴파일러(`javac`)를 사용하고, Java 프로그램을 실행하려면, Java 인터프리터(`java`)를 사용한다
- Java 프로그램을 컴파일하고 실행하는 방법은 크게 두 가지로 구분된다
  1. 명령어를 통한 실행
  2. IDE를 통한 실행

- 명령어를 통한 컴파일 및 실행 방법
  1. 터미널을 열고 프로젝트 디렉토리로 이동한다.
  2. `javac Main.java` 명령어를 입력하여 소스 코드를 컴파일한다.
  3. `java Main` 명령어를 입력하여 프로그램을 실행한다.

- IntelliJ IDEA를 통한 컴파일 실행 방법
  - 방법 1 : `Main`문 좌측의 초록색 화살표를 클릭한다.
  - 방법 2 : `Run` 메뉴에서 `Run...`을 선택 후 `Main`을 선택한다.

## 출력(Output)

- 프로그래밍의 기본적인 부분 중 하나는 화면에 글자를 출력하는 것이다. 이는 프로그램이 사용자와 상호작용하는 가장 기본적인 방법이며, 프로그램의 동작을 확인하는 데에도 중요하다.
- Java에서 화면에 글자를 출력하는 기본적인 방법은 `System.out.println()` 메소드를 사용하는 것이다. 이 메소드는 괄호 안에 있는 내용을 화면에 출력하고, 줄바꿈을 한다.
- `"Hello, World!"`와 같은 문자열을 출력해보자. `main` 메소드 안에 다음 코드를 작성한다.

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

이제 프로그램을 실행하여 `"Hello, World!"`가 화면에 출력되는지 확인한다.

## 변수(Variable)

- 변하는 값
- 변수는 데이터를 저장하고, 필요에 따라 그 값을 변경할 수 있는 공간을 의미한다
- Java에서 변수를 사용하기 위해서는 먼저 변수의 타입을 선언해야 한다
- 변수의 타입은 크게 기본형/참조형 두 가지로 구분된다.
  - 기본형
    - `boolean`, `char`, `byte`, `short`, `int`, `long`, `float`, `double`
    - 실제 값을 저장
  - 참조형
    - 기본형을 제외한 나머지 (String, System 등)
    - 객체의 주소를 저장 (4 byte)

### 변수 생성 규칙

- 변수의 이름은 영문자, 숫자, `$`, `_`로 구성된다
- 변수의 이름은 숫자로 시작할 수 없다
- 공백을 포함할 수 없다
- 변수의 이름으로 자바 예약어를 사용할 수 없다
  - ex) `int`, `double`, `public`, `class` 등

### 변수의 종류

- 변수는 타입에 따라 크게 기본형(primitive type) 변수와 참조형(reference type) 변수로 구분된다.
  - 기본형 변수는 실제 값을 저장하고, 참조형 변수는 객체의 주소를 저장한다.

### 기본형 변수의 종류

|자료형|데이터|메모리 크기|표현 가능 범위|
|---|---|---|---|
|`boolean`|참과 거짓|1 바이트|true,false|
|`char`|문자|2 바이트|모든 유니코드 문자|
|`byte`|정수|1 바이트|-128 ~ 127|
|`short`|정수|2 바이트|-32768 ~ 32767|
|`int`|정수|4 바이트|-2147483648 ~ 2147483467|
|`long`|정수|8 바이트|-9223372036854775808 ~ 9223372036854775807|
|`float`|실수|4 바이트|3.4E+/-38(7개의 자리수)|
|`double`|실수|8 바이트|1.7E+/-308(15개의 자리수)|

### 변수의 선언 방법

- 변수를 사용하기 위해서는 먼저 변수를 선언해야 한다
- 변수를 선언하는 방법 : '타입' + '변수 이름' ( = '초기값')
    - ex) `int number = 10;` : 정수형 변수 `number`를 선언하고 10으로 초기화한다

### 변수의 선언 및 출력 예제

```java
public class Main {
    public static void main(String[] args) {
        // boolean 타입: 결혼 여부
        boolean isMarried = true;

        // char 타입: 성별 ('M'은 남성, 'F'는 여성)
        char gender = 'M';

        // byte 타입: 나이
        byte age = 30;

        // short 타입: 사용자의 키 (cm)
        short height = 175;

        // int 타입: 총 소득 (연단위)
        int totalIncome = 50000;

        // long 타입: 사용자의 총 자산
        long totalAssets = 1500000L;

        // float 타입: 몸무게
        float weight = 70.5f;

        // double 타입: 평균 월 소득
        double averageMonthlyIncome = totalIncome / 12.0;

        // 결과 출력
        System.out.println("Profile Information");
        System.out.println("Married: " + isMarried);
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height + " cm");
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Assets: $" + totalAssets);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Average Monthly Income: $" + averageMonthlyIncome);
    }
}
```

## 상수(Constant)

- 변하지 않는 값
- 변수와 마찬가지로 데이터를 저장할 수 있는 메모리 공간을 의미하지만, 변수와 달리 한 번 저장되면 그 값을 변경할 수 없다
- Java에서는 `final` 키워드를 사용하여 상수를 선언한다
- 상수는 선언과 동시에 반드시 초기화해야 한다
- 상수에 이름을 붙이는 것은 프로그램의 명확성을 높여준다.
  - 예를 들어, `3.14159`라는 숫자보다 `PI`라는 이름을 가진 상수가 훨씬 이해하기 쉽고, 프로그램 내에서 일관성 있게 사용할 수 있다.
- 상수는 크게 '리터럴 상수', '명명된 상수' 둘로 구분된다

### 리터럴 상수

- 직접 값을 코드에 입력하는 상수.
  - 예를 들어, `5`, `3.14`, `"Hello"`와 같은 값들이 이에 해당한다.

#### 리터럴 타입 접미사(literal type suffix)

- 자바에서 `3.14`와 같은 실수형 리터럴을 그대로 사용하면, 해당 리터럴은 실수형 타입 중에서도 double형으로 인식한다
- 하지만 실수형 리터럴 맨 뒤에 F나 f를 추가하면, 자바는 해당 실수형 리터럴을 float형으로 인식한다
  - ex) `3.14F`, `3.14f`

|타입 접미사|리터럴 타입|예제|
|---|---|---|
|L 또는 l|long형|123456789L, ...|
|F 또는 f|float형|1.234567F, 8.9f, ...|
|D 또는 d (생략 가능)|double형|1.2345D, 6.789d, ...|

### 명명된 상수

- `final` 키워드를 사용하여 선언한 변수.
- 이 상수는 이름을 가지며, 값을 한번만 할당할 수 있다.

### 명명된 상수의 장점

1. **가독성 향상**: 코드 내에서 상수의 의미를 쉽게 파악할 수 있다.
2. **오류 방지**: 같은 값을 여러 번 사용할 때 실수를 줄일 수 있다.
3. **유지보수 용이**: 상수 값을 변경해야 할 경우 한 곳에서만 변경하면 된다.

상수를 사용함으로써 프로그램의 안정성과 유지보수성이 향상되며, 코드의 가독성도 높아진다.

```java
final int AGES = 100; // AGES : 명명된 상수. 100 : 리터럴 상수
final float PI = 3.14f; // 'PI' : 명명된 상수. 3.14 : 리터럴 상수
```

## 연산자(Operator)

- 특정한 연산을 수행하는 기호를 의미한다
- 연산 순서는 연산자 우선순위에 따라 결정된다

자바에서 제공하는 대표적인 연산자는 다음과 같다

1. 산술 연산자(arithmetic operator)
2. 대입 연산자(assignment operator)
3. 증감 연산자(increment and decrement operators)
4. 비교 연산자(comparison operator)
5. 논리 연산자(logical operator)
6. 비트 연산자(bitwise operator)
7. 삼항 연산자(ternary operator)
8. `instanceof` 연산자

### 1. 산술 연산자(Arithmetic Operators)

산술 연산자는 수학적 연산을 수행할 때 사용된다. 이들은 기본적인 사칙연산(더하기, 빼기, 곱하기, 나누기) 및 나머지 연산을 포함한다.

- 더하기 연산자(`+`): 두 숫자를 더한다.
- 빼기 연산자(`-`): 첫 번째 숫자에서 두 번째 숫자를 뺀다.
- 곱하기 연산자(`*`): 두 숫자를 곱한다.
- 나누기 연산자(`/`): 첫 번째 숫자를 두 번째 숫자로 나눈다.
- 나머지 연산자(`%`): 첫 번째 숫자를 두 번째 숫자로 나눈 후의 나머지를 반환한다.

#### 예제 코드

```java
public class ArithmeticExample {
    public static void main(String[] args) {
        int a = 10, b = 5;

        System.out.println("a + b = " + (a + b)); // 15
        System.out.println("a - b = " + (a - b)); // 5
        System.out.println("a * b = " + (a * b)); // 50
        System.out.println("a / b = " + (a / b)); // 2
        System.out.println("a % b = " + (a % b)); // 0
    }
}
```

#### 문자열 연산

- 문자열은 문자의 집합이며, Java에서는 문자열의 연결을 위해 `+` 연산자를 사용한다.
- 연결 연산자(`+`) 외의 다른 산술 연산자는 문자열에 사용할 수 없다.

```java
String firstName = "홍";
String lastName = "길동";
String fullName = firstName + lastName;
System.out.println("전체 이름: " + fullName);
```

---

### 2. 대입 연산자(Assignment Operators)

대입 연산자는 값 또는 표현식의 결과를 변수에 할당하는 데 사용된다. 가장 기본적인 대입 연산자는 `=`이다. 또한, 대입 연산자는 다른 연산자와 결합하여 축약된 형태로 사용될 수 있다(예: `+=`, `-=`, `*=`, `/=`, `%=`).

- 기본 대입 연산자(`=`): 오른쪽 피연산자의 값을 왼쪽 피연산자(변수)에 할당한다.
- 복합 대입 연산자(`+=`, `-=`, `*=`, `/=`, `%=`): 연산과 대입을 한 번에 수행한다.

#### 예제 코드

```java
public class AssignmentExample {
    public static void main(String[] args) {
        int a = 10;
        a += 5; // a = a + 5
        System.out.println("a = " + a); // 15

        a -= 3; // a = a - 3
        System.out.println("a = " + a); // 12

        a *= 2; // a = a * 2
        System.out.println("a = " + a); // 24

        a /= 4; // a = a / 4
        System.out.println("a = " + a); // 6

        a %= 5; // a = a % 5
        System.out.println("a = " + a); // 1
    }
}
```

---

### 3. 증감 연산자(Increment and Decrement Operators)

증감 연산자는 변수의 값을 1 증가시키거나 감소시킨다. 이 연산자에는 전위(`++i`, `--i`)와 후위(`i++`, `i--`) 두 가지 형태가 있다. 전위는 연산을 먼저 수행하고 값을 반환하며, 후위는 값을 반환한 후 연산을 수행한다.

#### 예제 코드

```java
public class IncrementDecrementExample {
    public static void main(String[] args) {
        int i = 0;

        System.out.println("전위 증가: " + (++i)); // 1
        System.out.println("후위 증가: " + (i++)); // 1 (출력 후 i는 2가 됨)

        System.out.println("현재 i: " + i); // 2

        System.out.println("전위 감소: " + (--i)); // 1
        System.out.println("후위 감소: " + (i--)); // 1 (출력 후 i는 0이 됨)

        System.out.println("최종 i: " + i); // 0
    }
}
```

---

### 4. 비교 연산자(Comparison Operators)

비교 연산자는 두 값이나 변수를 비교하고, 그 결과로 true 또는 false 값을 반환한다. 이 연산자들은 조건문과 반복문에서 주로 사용된다.

- 동등 연산자(`==`): 두 값이 같으면 true, 다르면 false를 반환한다.
- 부등 연산자(`!=`): 두 값이 다르면 true, 같으면 false를 반환한다.
- 크기 비교 연산자(`>`, `<`, `>=`, `<=`): 두 값의 크기를 비교한다.

#### 예제 코드

```java
public class ComparisonExample {
    public static void main(String[] args) {
        int a = 10, b = 5;

        System.out.println("a == b: " + (a == b)); // false
        System.out.println("a != b: " + (a != b)); // true
        System.out.println("a > b: " + (a > b));   // true
        System.out.println("a < b: " + (a < b));   // false
        System.out.println("a >= b: " + (a >= b)); // true
        System.out.println("a <= b: " + (a <= b)); // false
    }
}
```

---

### 5. 논리 연산자(Logical Operators)

논리 연산자는 불리언(boolean) 값 간의 논리적 연산을 수행한다. 이 연산자들은 조건문과 반복문에서 조건을 결합할 때 사용된다.

- 논리 AND(`&&`): 모든 조건이 true일 때 true를 반환한다.
- 논리 OR(`||`): 조건 중 하나라도 true이면 true를 반환한다.
- 논리 NOT(`!`): 조건의 반대 값을 반환한다(true면 false, false면 true).

#### 예제 코드

```java
public class LogicalExample {
    public static void main(String[] args) {
        boolean a = true, b = false;

        System.out.println("a && b: " + (a && b)); // false
        System.out.println("a || b: " + (a || b)); // true
        System.out.println("!a: " + (!a));         // false
        System.out.println("!b: " + (!b));         // true
    }
}
```

---

### 6. 비트 연산자(Bitwise Operators)

비트 연산자는 정수형 변수에 대해 비트 단위의 연산을 수행한다. 이 연산자들은 비트 레벨에서 데이터를 조작할 때 사용된다.

- 비트 AND(`&`): 두 비트 모두 1이면 1을 반환한다.
- 비트 OR(`|`): 두 비트 중 하나라도 1이면 1을 반환한다.
- 비트 XOR(`^`): 두 비트가 서로 다르면 1을 반환한다.
- 비트 NOT(`~`): 모든 비트를 반전한다(0은 1로, 1은 0으로).
- 비트 시프트(`<<`, `>>`, `>>>`): 비트를 좌측 또는 우측으로 이동시킨다.

#### 예제 코드

```java
public class BitwiseExample {
    public static void main(String[] args) {
        int a = 5; // 0101 in binary
        int b = 3; // 0011 in binary

        System.out.println("a & b: " + (a & b)); // 1
        System.out.println("a | b: " + (a | b)); // 7
        System.out.println("a ^ b: " + (a ^ b)); // 6
        System.out.println("~a: " + (~a));       // -6
        System.out.println("a << 1: " + (a << 1)); // 10
        System.out.println("a >> 1: " + (a >> 1)); // 2
    }
}
```

---

### 7. 삼항 연산자(Ternary Operator)

삼항 연산자는 조건에 따라 두 개의 값 중 하나를 선택하는 데 사용된다. 구문은 `조건 ? 값1 : 값2`로, 조건이 true면 값1을, 그렇지 않으면 값2를 반환한다.

#### 예제 코드

```java
public class TernaryExample {
    public static void main(String[] args) {
        int a = 5, b = 10;

        int max = (a > b) ? a : b;
        System.out.println("큰 값: " + max); // 10
    }
}
```

---

### 8. `instanceof` 연산자

`instanceof` 연산자는 객체가 특정 클래스의 인스턴스인지를 확인하는 데 사용된다. 이 연산자는 객체의 형 변환 가능성을 검사할 때 유용하다.

#### 예제 코드

```java
public class InstanceofExample {
    public static void main(String[] args) {
        String str = "Hello";

        boolean result = str instanceof String;
        System.out.println("str은 String의 인스턴스입니까? " + result); // true
    }
}
```

## 입력(Input)

프로그램이 사용자의 입력을 받아 처리할 수 있도록 하는 것은 매우 중요하다. 사용자로부터 정보를 받기 위해 주로 사용되는 방법 중 하나는 키보드 입력이다.

사용자 입력은 프로그램에 다양성과 상호작용성을 부여한다. 예를 들어, 사용자가 원하는 데이터를 입력하면 프로그램이 그에 맞는 결과를 반환할 수 있다. 이는 프로그램을 더 유연하고 동적으로 만든다.

### Java의 키보드 입력

Java에서 키보드 입력을 받기 위해서는 `Scanner` 클래스를 사용한다. `Scanner` 클래스는 `java.util` 패키지에 포함되어 있으며, 다양한 형태의 입력을 처리할 수 있다.

1. **Scanner 객체 생성**: 키보드 입력을 위해 `Scanner` 객체를 생성한다.

   ```java
   import java.util.Scanner;

    // ...

    Scanner scanner = new Scanner(System.in);
   ```

2. **입력 받기**: 사용자로부터 문자열, 정수, 실수 등 다양한 형태의 데이터를 입력받을 수 있다.

   ```java
   Scanner scanner = new Scanner(System.in);
   String name = scanner.nextLine();  // 문자열 입력 받기
   int age = scanner.nextInt();       // 정수 입력 받기
   double height = scanner.nextDouble(); // 실수 입력 받기
   ```

### 문자열 변환

입력받은 데이터는 때때로 다른 형태로 변환되어야 할 필요가 있다. 예를 들어, 숫자 형태의 문자열을 정수나 실수로 변환할 수 있다.

1. **문자열에서 정수로 변환**: `Integer.parseInt()` 메소드를 사용한다.

   ```java
   String numberStr = "123";
   int number = Integer.parseInt(numberStr);
   ```

2. **문자열에서 실수로 변환**: `Double.parseDouble()` 메소드를 사용한다.

   ```java
   String doubleStr = "3.14";
   double pi = Double.parseDouble(doubleStr);
   ```

키보드 입력을 통해 사용자로부터 데이터를 받고, 이를 적절히 처리하는 방법을 배웠다. 이러한 입력 처리는 프로그램이 사용자와 상호작용하는 데에 핵심적인 역할을 한다.


## 조건문

- 프로그램을 작성할 때 특정 조건에 따라 다른 동작을 수행하게 하는 것이 중요할 수 있다. 이를 위해 사용하는 것이 '조건문'이다.
- Java에서 조건문을 사용하면, 주어진 조건에 따라 다른 코드를 실행할 수 있다.
- 자바에서 사용하는 대표적인 조건문의 형태는 다음과 같다.
  1. if 문
  2. if / else 문
  3. if / else if / else 문
  4. switch 문

### if 문

```java
if (조건식) {
    // 조건식이 참일 때 실행되는 코드
}
```

#### 예시

```java
Scanner scanner = new Scanner(System.in);
int number = scanner.nextInt()
if (number % 2 == 0){
    System.out.println("짝수입니다.");
}
```

### if / else 문

```java
if (조건식) {
    // 조건식이 참일 때 실행되는 코드
} else {
    // 조건식이 거짓일 때 실행되는 코드
}
```

#### 예시

```java
Scanner scanner = new Scanner(System.in);
int number = scanner.nextInt()
if (number % 2 == 0){
    System.out.println("짝수입니다.");
} else {
    System.out.println("홀수입니다.");
}
```

### if / else if / else 문

```java
if (조건식1) {
    // 조건식1이 참일 때 실행되는 코드
} else if (조건식2) {
    // 조건식2가 참일 때 실행되는 코드
} else {
    // 조건식1과 조건식2가 모두 거짓일 때 실행되는 코드
}
```

#### 예시

```java
Scanner scanner = new Scanner(System.in);
int score = scanner.nextInt()
if (score >= 90) {
    System.out.println("A");
} else if (score >= 80) {
    System.out.println("B");
} else if (score >= 70) {
    System.out.println("C");
} else if (score >= 60) {
    System.out.println("D");
} else {
    System.out.println("F");
}
```

### 논리연산

조건문에서 두 개 이상의 조건을 조합할 때는 논리연산자를 사용한다. 주로 사용되는 논리연산자는 `&&`(AND), `||`(OR), `!`(NOT) 이다.

#### 예시

```java
Scanner scanner = new Scanner(System.in);
int number = scanner.nextInt()
if (number >= 0 && number <= 100) {
    System.out.println("유효한 점수입니다.");
} else {
    System.out.println("유효하지 않은 점수입니다.");
}
```

## 배열

- 여러 개의 데이터를 각각 다른 변수에 저장하면, 변수가 많아질수록 코드가 복잡해진다. 예를 들어, 100명의 학생 점수를 다루려면 100개의 변수가 필요하다. 이런 경우 배열을 사용하면 훨씬 간단하고 효율적으로 데이터를 관리할 수 있다.
- 배열은 같은 타입의 여러 데이터를 연속적인 공간에 저장하여 관리하는 데이터 구조이다.
- Java에서 배열을 사용하기 위해서는 먼저 배열의 크기와 타입을 정해야 한다.

1. 배열 선언 : 변수 선언과 같이 배열 선언도 타입과 이름으로 선언된다. 변수 선언과 다른 점이라면 배열 선언 시에는 대괄호(`[]`)를 사용한다.
   - ex) `타입[] 배열이름`;
2. 배열 초기화 : 배열을 생성하고 초기값을 할당한다. 배열을 생성할 때는 `new` 연산자를 사용한다. 대괄호 안에는 배열의 크기를 지정한다.
   - ex) `배열이름 = new 타입[크기]`;
3. 배열 사용: 배열의 각 요소는 인덱스를 통해 접근할 수 있다. 인덱스는 0부터 시작한다. 배열의 요소에 접근할 때는 배열 이름 뒤에 대괄호를 사용하고, 인덱스를 지정한다.

### 예시

```java
int[] scores; // 배열 선언
scores = new int[100];  // 배열 생성 및 대입
scores[0] = 90;  // 첫 번째 요소에 90 저장
scores[1] = 85;  // 두 번째 요소에 85 저장
// ...

System.out.println(scores[0]);  // 첫 번째 요소 출력
System.out.println(scores[1]);  // 두 번째 요소 출력
// ...
```

## 반복문

- 반복문이란 특정 코드를 반복적으로 실행할 때 사용된다.
  - 예를 들어, 1부터 10까지의 숫자를 출력하거나, 1부터 100까지의 합을 구하는 등의 작업을 반복문을 통해 간단하게 처리할 수 있다.
- Java의 반복문 종류는 다음과 같다.
  1. while 문
  2. for 문
  3. do-while 문

### while 문

- `while` 문은 주어진 조건이 참인 동안, 코드 블록을 반복 실행한다.
- `while` 문의 구조는 다음과 같다.

```java
while (조건) {
    // 반복 실행할 코드
}
```

#### while 문 예시

```java
int i = 1;
while (i <= 10) {
    System.out.println(i);
    i++;
}
```

### do / while 문

- `do / while` 문은 코드 블록을 먼저 실행한 후, 주어진 조건이 참인 동안 코드 블록을 반복 실행한다.
- `while` 문과 차별화되는 점은 `do / while` 문은 코드 블록을 먼저 실행한 후 조건을 검사한다는 것이다.
  - 즉 `do / while` 문은 코드 블록을 최소한 한 번 이상 실행한다.
- `do while` 문의 구조는 다음과 같다.

```java
do {
    // 반복 실행할 코드
} while (조건);
```

#### do / while 문 예시

```java
int i = 1;
do {
    System.out.println(i);
    i++;
} while (i <= 10);
```

### for 문

- 특정 작업을 정해진 횟수만큼 반복하고자 할 때 가장 적합한 반복문은 `for` 문이다
- Java에서 `for` 문은 초기화, 조건 검사, 반복 후 조정 등 세 부분으로 구성되어 있으며, 이를 통해 코드의 반복 실행을 정교하게 제어할 수 있다
- `for` 문은 주어진 조건이 참인 동안, 코드 블록을 반복 실행한다.
- `for` 문의 구조는 다음과 같다.

```java
for (초기화; 조건; 반복 후 조정) {
    // 반복 실행할 코드
}
```

#### for 문 예시

```java
for (int i = 1; i <= 10; i++) {
    System.out.println(i);
}
```

### continue / break

- `continue` 문과 `break` 문은 반복문을 제어하는 데 사용되며, 반복문의 실행 흐름을 조작할 수 있다.
  - `continue` 문은 반복문 내에서만 사용되며, 반복문의 나머지 부분을 건너뛰고 다음 반복을 진행한다.
  - `break` 문은 반복문 내에서만 사용되며, 반복문을 즉시 종료한다.

#### continue 문 예시

```java
for (int i = 1; i <= 10; i++) {
    if (i % 2 == 0) {
        continue;  // i가 짝수이면 아래 코드를 실행하지 않고 다음 반복으로 넘어감
    }
    System.out.println(i);
}
```

#### break 문 예시

```java
int count = 0;
while (true) {
    if (count == 5) {
        break;  // count가 5가 되면 반복문 종료
    }
    count++;
}
```

### 이중 for문

`for` 문은 중첩하여 사용할 수도 있다. 이를 '이중 for문'이라고 한다. 이중 for문은 주로 2차원 배열을 다루거나, 복잡한 패턴을 출력할 때 사용된다.

예를 들어, 구구단을 출력하는 프로그램은 다음과 같이 이중 for문을 사용하여 작성할 수 있다.

```java
for (int i = 1; i <= 9; i++) {
    for (int j = 1; j <= 9; j++) {
        System.out.println(i + " * " + j + " = " + (i * j));
    }
}
```

> 이 코드는 각 `i`에 대해 1부터 9까지 `j`를 곱한 결과를 출력한다