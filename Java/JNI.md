## JNI란?

- Java Native Programming Interface

- 다른언어(C, C++)로 쓰여진 어플리케이션이나 라이브러리가 자바 가상머신과 상호작동하도록 해준다

  

### JNI 사용 방법 예시

1. Java코드를 작성
2. Java코드에 맞는 `.h` 파일 생성
   - `javah -jni JNIExample.java`
3. .h파일에 맞게끔 `.c`파일 생성 후 코드 작성
   - c/c++ 문법에 맞게 알맞게 작성
4. 각 운영체제에 맞는 라이브러리로 변환 (윈도우: `.dll`, Mac:`.jnilib`, Linux:`.so`)
   - ex) `gcc -o libsharedmem.jnilib -shared SharedMem.c`
     - jni라이브러리 위치를 못찾을 경우 jni라이브러리를 환경변수에 추가해주거나 `-I` 옵션으로 알려주면 된다
       - ex) `gcc -I/Library/Java/JavaVirtualMachines/jdk1.8.0_241.jdk/Contents/Home/include -I/Library/Java/JavaVirtualMachines/jdk1.8.0_241.jdk/Contents/Home/include/darwin -o libsharedmem.so -shared SharedMem.c`
5. Java코드 실행
   - 이 때 4번에서 만든 라이브러리 위치를 명시해줘야 하는데 `-Djava.library.path` 명령어를 이용하면 된다. (`java.lang.UnsatisfiedLinkError` 라는 에러가 뜨면 이 문제일 가능성이 높다)
     - ex) `java -Djava.library.path=. JNIExample`
       - 현재 디렉토리를 라이브러리 경로에 추가한다는 뜻