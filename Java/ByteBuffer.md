## ByteBuffer

- 커널 버퍼에 직접 접근할 수 있는 NIO의 장점을 이용하기 위해서는 ByteBuffer 클래스만 Direct Buffer를 지원한다.
  - 즉, 커널 버퍼에 직접 접근할 수 있는 NIO의 장점을 이용하기 위해서는 ByteBuffer의 allocateDirect()라는 메서드를 이용해서 ByteBuffer를 만들어 내야 한다.



#### ByteBuffer의 네 가지 포인터

- ByteBuffer에는 위치를 나타내는 네가지 포인터가 있다. (position, limit, capacity, mark)
- position : 현재 읽을 위치나 현재 쓸 위치를 가리킨다. ByteBuffer에서 get()함수로 읽기를 시도할 경우 position 위치부터 읽기 시작하여, put()함수로 ByteBuffer에 쓰기를 시도할 경우 position 위치부터 쓰기를 시작한다. 읽거나 쓸 때마다 position의 위치는 자동으로 이동한다
- limit : 현재 ByteBuffer의 유요한 쓰기 위치나 유효한 읽기 위치를 나타낸다. 다시 말해 "이 버퍼는 여기까지 읽을 수 있습니다" 혹은 "여기까지 쓸 수 있습니다"를 나타낸다.
- capacity : ByteBuffer의 용량을 나타낸다. 따라서 항상 ByteBuffer의 맨 마지막을 가리키고 있다. 그 때문에 position과 limit와는 달리 그 위치를 바꿀 필요가 없다
- mark : 편리한 표인터이다. 특별한 의미가 있는 건 아니고 사용자가 마음대로 지정할 수 있다. 특별히 이 위치를 기억하고 있다가 다음데 되돌아가야할 때 사용한다.



#### ByteBuffer의 생성 방법 3가지

- allocate : JVM의 힙 영역에 바이트 버퍼를 생성한다. 인수는 생성할 바이트 버퍼 크기다.
- allocateDirect : JVM 힙 영역이 아닌 운영체제의 커널 영역에 바이트 버퍼를 생성한다.
- wrap : 입력된 바이트 배열을 사용하여 바이트 버퍼를 생성한다. 입력에 사용된 바이트 배열이 변경되면 wrap을 사용하여 생성한 바이트 버퍼도 변경된다