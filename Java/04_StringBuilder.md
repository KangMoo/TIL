### StringBuilder

- StringBuffer와 같은 역할을 함. 한 마디로 StringBuffer를 대체해서 사용가능
- StringBuffer와의 차이점
  - StringBuilder는 동시에 처리하는것 (Synchronization)을 허용하지 않기 때문에 **단일 스레드 환경**에서만 사용 가능
  - StringBuffer보다 빠름

### String Buffer

- StringBuilder 대신 사용가능
- **멀티 스레드 환경에서는 StringBuffer를 사용**



### StringBuilder, StringBuffer공통점

- 문자열의 저장 및 변경을 위한 메모리 공간을 지닌 클래스
- 문자열 데이터의 추가를 위해 append와 insert메서드를 가지고 있다
- String클래스는 문자열상수를 지니는 메모리 공간을 지니고 있으나, StringBuffer, StringBuilder안의 메모리 공간은 값이 변경 가능한 **변수의 성격**을 지닌다고 얘기할 수 있다.



### 사용예시

>```java
>StringBuilder sb = new StringBuilder();
>String temp1 = sb.append("A").append("B").append("CD").toString();
>// temp1 = "ABCD"
>```