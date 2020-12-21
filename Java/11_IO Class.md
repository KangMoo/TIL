

## I/O 입출력 클래스

Java의 I/O는 Java.io 패키지에 클래스가 정의되어 있는 경우가 대부분이다.

Java의 초기에는 입출력 클래스도 단순했 단순히 바이트 단위의 입출력만 지원했지만, 현재는 계속적으로 확장되며 문자 단위의 입출력 뿐만 아니라 ㄷ양한 기능을 지원하는 클래스들이 생겨났다.

### I/O 클래스의 이름과 의미

- Stream : 바이트 단위로 입출력 수행

- Reader / Writer : 캐릭터 단위로 입출력 수행

- File : 하드디스크의 파일을 사용

- Data : 자바의 원시 자료형을 출력하기 위한 클래스

- Buffered : 시스템의 버퍼를 사용

  

- 1차 스트림 : 입/출력 통로를 직접 만드는 클래스

- 2차 스트림 : 기존의 통로를 이용하여 새로운 기능을 더하는 클래스



### InputStream / OutputStream (바이트 입출력)

![바이트 입출력](./image/io_class.jpeg)

- InputSttream

  | 클래스                  | 설명                                              | Stream     |
  | ----------------------- | ------------------------------------------------- | ---------- |
  | InputStream             | 바이트 입력 스트림을 위한 추상 클래스             | 2차 스트림 |
  | FileInputStream         | 파일에서 바이트를 읽어들여 바이트 스트림으로 변환 | 1차 스트림 |
  | PipedInputStream        | PipedOutputStream에서 읽어들임                    | 1차 스트림 |
  | FilterInputStream       | 필터 적용 바이트 입력을 위한 추상 클래스          | 2차 스트림 |
  | LineNumberInputStream   | 바이트 입력시 라인 번호를 유지 (비추천)           | 2차 스트림 |
  | DataInputStream         | 기본 자료형 데이터를 바이트로 출력                | 2차 스트림 |
  | BufferedInputStream     | 바이트 버퍼 입력                                  | 2차 스트림 |
  | PushbackInputStream     | 읽어들인 바이트를 되돌림 (pushback)               | 2차 스트림 |
  | ByteArrayInputStream    | 바이트 배열에서 읽어들임                          | 1차 스트림 |
  | SequenceInputStream     | 서로 다른 InputStream을 입력받은 순서대로 이어줌  | 2차 스트림 |
  | StringBufferInputStream | 문자열에서 읽어들임 (비추천)                      | 1차 스트림 |
  | ObjectInputStream       | 객체로 직렬화된 데이터를 역직렬화하여 읽음        | 2차 스트림 |