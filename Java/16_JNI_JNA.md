## 개요

- JVM위에서 돌아가는 Java프로그램은 기본적으로는 특성 프로세서의 기계어로 제작된 실행파일이나 라이브러리를 수행할 수 없다. 하지만 자바의 특성상 수행할 수 없는 업무나 다른 네이티브 코드(C, C++)를 실행시켜야 할 때가 있는데, 이럴 때 JNI 또는 JNA를 사용하여 대처할 수 있다.
  - 대표적으로 shared memory의 구현은 자바만으로는 구현할 수 없다

## JNI / JNA

#### JNI

- Java Native Interface
- Java에서 Native영역으로 들어가 호출 또는 native에서 Java로 호출하는 interface
- C, C++ 언어로 만든 라이브러리, 솔루션을 java에서 사용가능 하다.
- 얻을 수 있는 효과
  - Java영역에서 할 수 이쓴 부분을 Native영역으로 이동하여 연동 할 수 있다
  - 속도가 늦은 부분은 속도를 높일 수 있다
    - 안드로이드의 경우 껍데기는 java이고, 내부는 JNI코드로 이루어져 있다고 할 수 있다
- 단점
  - Auto GC를 사용할 수 없어 메모리 관리를 직접 해주어야 한다.
    - 메모리 릭이 발생할 수 있다
  - JVM 메모리를 침범하여  crash가 날 수 있다.
  - 따라서 JNI를 사용할 때는 신중해야 한다

- JNI 개발 과정

  ![JNI 개발과정](./image/16_1.png)



#### JNA

- Java Native Access

- JNI 개발에서 번거로운 부분을 쉽게 해결해주는 API

  - Native언어로 만들어진 함수를 사용하기 위해서 Header파일 생성, Header파일을 구현한 C소스, compile과정이 없다

- JNA는 libffi (Foreign function interface library)라 불리는 native library를 사용하여 dynamic하게 쓸 수 있게 한다.

- 얻을 수 있는 효과

  - 단순히 Java단에서 C코드로 일을 시킬 때 아주 편리하다.
  - 권한만 있다면 리눅스 또는 윈도우 커널 라이브러리에 접근해서 interface에 바인딩하여 명령어를 실행할 수 있다.
  - JNI처럼 C언어에서 함수에 Pointer를 연결하여  Java에서 쓸 수 있도록 할 수 있으며 Callback이 일어나면 Java로 올려치기도 가능하다
  - JNI 개발과정보다 단순하고 JNI 개발의 번거로운 부분을 해소해준다
    - DLL제작 과정이 필요없습니다. 사전에 JNA 사용을 위한 JAR 파일만 다운로드 받아서 라이브러리로 추가만 해주면 간편하게 네이티브 코드를 사용할 수 있다.
  - 기존에 이미 만들어진 shared object를 바로 클래스만 작성해서 호출하는 방식이기 때문에 복잡한 interface가 필요없다.
  - Java의 src 디렉토리에 지저분한 C header 파일과 C 소스 파일이 필요없다

- 단점

  - C++ 코드는 사용할 수 없다
    - (jnaerator라는 서드파티 오픈소스를 사용하면 되긴 한다)
  - api특성상 JNI의 성격을 다 포함하지는 못한다.
    - 예를 들어 native에서 jvm을 start하는 것은 JNA가 지원하지 않는다

- JNA 개발과정

  ![JNA 개발과정](./image/16_2.png)

**JNI 의 불편함대신 간편하게 사용될 수 있는 JNA는 점차 보편화 되고 있어서, 오픈소스 코드를 분석하거나 또는 Native 모듈을 개발하는데 큰 도움이 된다.**



출처: https://knight76.tistory.com/entry/jni-vs-jna





