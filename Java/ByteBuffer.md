## ByteBuffer

- 커널 버퍼에 직접 접근할 수 있는 NIO의 장점을 이용하기 위해서는 ByteBuffer 클래스만 Direct Buffer를 지원한다.
  - 즉, 커널 버퍼에 직접 접근할 수 있는 NIO의 장점을 이용하기 위해서는 ByteBuffer의 allocateDirect()라는 메서드를 이용해서 ByteBuffer를 만들어 내야 한다.
- 자바가 C에 비해 느린 이유 중 하나는 IO가 JVM 내부에 IO 버퍼를 두었기 때문인데, ByteBuffer를 사용함으로 속도 향상이 가능하다.
- 특히 네트워크 통신 등 byte배열 사용이 잦은 경우 GC가 성능에 영향을 끼질 수 있지만 ByteBuffer는 이러한 상황을 예방해줄 수 있다.

![buffer](./image/buffer.png)



### java.nio의 Buffer 계층도

![buffer계층도](./image/buffer2.gif)

#### ByteBuffer의 생성 방법 3가지

- allocate : **JVM의 힙 영역**에 바이트 버퍼를 생성한다. 인수는 생성할 바이트 버퍼 크기다.
  - 생성시간이 빠르지만, 입출력 성능은 낮다
- allocateDirect : JVM 힙 영역이 아닌 **운영체제의 커널 영역**에 바이트 버퍼를 생성한다.
  - 생성시간이 느리지만, 입출력 성능은 높다
- wrap : 입력된 바이트 배열을 사용하여 바이트 버퍼를 생성한다. 입력에 사용된 바이트 배열이 변경되면 wrap을 사용하여 생성한 바이트 버퍼도 변경된다



#### ByteBuffer의 네 가지 포인터

- ByteBuffer에는 위치를 나타내는 네가지 포인터가 있다.
  -  **position, limit, capacity, mark**
  - **0 <= mark <= position <= limit <= capacity **
- **position** : 현재 읽을 위치나 현재 쓸 위치를 가리킨다. ByteBuffer에서 get()함수로 읽기를 시도할 경우 position 위치부터 읽기 시작하여, put()함수로 ByteBuffer에 쓰기를 시도할 경우 position 위치부터 쓰기를 시작한다. 읽거나 쓸 때마다 position의 위치는 자동으로 이동한다
- **limit** : 현재 ByteBuffer의 유요한 쓰기 위치나 유효한 읽기 위치를 나타낸다. 다시 말해 "이 버퍼는 여기까지 읽을 수 있습니다" 혹은 "여기까지 쓸 수 있습니다"를 나타낸다.
- **capacity** : ByteBuffer의 용량을 나타낸다. 따라서 항상 ByteBuffer의 맨 마지막을 가리키고 있다. 그 때문에 position과 limit와는 달리 그 위치를 바꿀 필요가 없다
- **mark** : 편리한 표인터이다. 특별한 의미가 있는 건 아니고 사용자가 마음대로 지정할 수 있다. 특별히 이 위치를 기억하고 있다가 다음데 되돌아가야할 때 사용한다.



#### java.nio.Buffer의 주요 메서드

- `clear()` -> return Buffer
  - 말 그대로 초기화
  - 포지션을 0으로 설정
- `flip()` -> return Buffer
  - 마지막으로 썼던 버퍼를 읽기 좋게 변경
  - 포지션을 0으로 설정
  - limit을 현재 내용의 마지막 위치로 압축
- `hasRemaining()` -> return Buffer
  - 포지션과 리미트가 같지 않은지 확인
  - 버퍼내 내용이 있으면 true
- `rewind()` -> return Buffer
  - 포지션은 맨 처음으로 초기화 됨
  - 포지션이 0이 아닐 때 0으로 위치시킴
- `mark()` -> return Position
  - 현재 위치(P)를 임시 저장한다.
- `reset()` -> return Position
  - 임시저장된 위치로 다시 P를 옮겨놓는다.
  - mark-reset은 주로 데이터를 미리 살짝 읽어볼 때 (Peek) 주로 쓴다
  - reset과 clear는 헷갈리기 쉬우니 주의

#### java.nio.ByteBuffer의 주요 메서드

- `compact()` -> abstact ByteBuffer
  - 포지션과 리미트를 앞당기고 다음 포지션과 리미트를 설정
- wrap(byte[] array) -> static ByteBuffer
  - byte[]를 byteBuffer로 만듦
  - 만들어진 ByteBuffer는 byte[]에 의존하게 되며, capcaity, limit은 byte[]의 사이즈와 같음



## ByteBuffer의 get()/put()

- `get()`, `put()` 은 각각 버퍼에 데이터를 읽고 쓰는 메서드이다
- `get()`, `put()` 은 다시 상대적/절대적으로 구분된다
  - 상대적 `get()`, `put()` : position값 증가
  - 절대적 `get()`, `put()` : position값 변화없음



### 예시

- P : Position, L : Limit C : Capacity

**allocate**

- 8개의 글자가 들어갈 수 있는 버퍼를 만든다. allocate하면 버퍼는 현재 쓰기모드이다

```java
CharBuffer buf = CharBuffer.allocate(8); // capacity : 8
//	P               L
//  |0|1|2|3|4|5|6|7|
//  | | | | | | | | |
```



**put**

- put을 사용할 경우 Position이 이동하면서 버퍼에 데이터가 들어간다.

```java
//	P               L
//  |0|1|2|3|4|5|6|7|
//  | | | | | | | | |

buf.put('a'); buf.put('b'); buf.put('c'); buf.put('e');

// P------>P       L 
// |0|1|2|3|4|5|6|7|
// |a|b|c|e| | | | |

buf.put(3,'d');

//         P       L 
// |0|1|2|3|4|5|6|7|
// |a|b|c|d| | | | |
```



**flip**

- 입력된 데이터를 읽기 위해 read-mode로 바꾼다. (읽기 좋게 바꾼다)
- 버퍼의 시작이자 데이터의 시작인 0으로 Position을 이동하고, 데이터의 끝 부분에 L이 위치하게 된다.
  - Limit이 Position이 되고, Position이 0이 된다.

```java
//         P       L 
// |0|1|2|3|4|5|6|7|
// |a|b|c|d| | | | |

buf.flip();

//          L<------L 
//  P<------P 
//  |0|1|2|3|4|5|6|7|
//  |a|b|c|d| | | | | 
//  L = P
//  P = 0
```



**get**

- Position을 이동시키며 데이터를 읽는다

```java
//  P               L
//  |0|1|2|3|4|5|6|7|
//  |a|b|c|d| | | | | 

char a, b, c;
a = buf.get(); b = buf.get(); c = buf.get();

// P---->P         L 
// |0|1|2|3|4|5|6|7|
// |a|b|c|d| | | | |
```



**compact**

- 데이터를 쓰기모드로 바꾼다.
- 읽지 않은 데이터 'd'가 복사되고, 'd'의 바로 뒤에 쓸 수 있도록 Position이 변했다.

```java
buf.compact();
//   P<--P
//         L------>L 
// |0|1|2|3|4|5|6|7| 
// |d| | | | | | | | 
// 남아있는 데이터를 처음으로 복사 
// P = 남아있는 데이터 수 
// L = C
```



